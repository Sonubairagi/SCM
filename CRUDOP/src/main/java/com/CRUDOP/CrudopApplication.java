package com.CRUDOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.CRUDOP.controllers", "com.CRUDOP.services", "com.CRUDOP.mapper"})
public class CrudopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudopApplication.class, args);
	}

}
