package com.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.scm.mapper, com.scm.controllers, com.scm.services, com.scm.helper, com.scm.config")
public class SmartContectManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartContectManagerApplication.class, args);
	}

}
