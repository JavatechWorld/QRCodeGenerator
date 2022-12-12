package com.qr.implementation.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qr.implementation.pojo.QRDetailsPOJO;

@Controller
@RequestMapping("/qr")
public class QRController {

	
	 @ModelAttribute("qr")
	    public QRDetailsPOJO qrDetailsPojo() {
	        return new QRDetailsPOJO();
	    }
	 
	 @GetMapping
		public String QRDetails() {
			return "qrDetails";
		}
	   
	@PostMapping
	public String genrateQRCode(@ModelAttribute("qr") QRDetailsPOJO qrDetailsPOJO,Model model) {
		try {
		BufferedImage bufferedImage = generateQRCodeImage(qrDetailsPOJO);
		File outputfile = new File("G:\\WorkspaceTech\\QRSecurity\\src\\main\\resources\\templates\\image_"+qrDetailsPOJO.getName()+".jpg");
		ImageIO.write(bufferedImage, "jpg", outputfile);
		
		model.addAttribute("qr", qrDetailsPOJO);
		
		}catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/qr?success";
	}
	
	public static BufferedImage generateQRCodeImage(QRDetailsPOJO barcodeText) throws Exception {
		StringBuilder str = new StringBuilder();
		str = str.append("Name:").append(barcodeText.getName()).append("| |").append("Email:").append(barcodeText.getEmail_id()).append("| |").append("MobileNo:")
				.append(barcodeText.getMobileNo());
	    QRCodeWriter barcodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = 
	      barcodeWriter.encode(str.toString(), BarcodeFormat.QR_CODE, 200, 200);

	    return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
	
	

	
}
