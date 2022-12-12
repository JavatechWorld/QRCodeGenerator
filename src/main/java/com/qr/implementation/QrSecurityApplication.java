package com.qr.implementation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class QrSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrSecurityApplication.class, args);
	}

}
