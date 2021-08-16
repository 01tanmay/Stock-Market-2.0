package com.estock.market.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanyServiceApplication {

	private static final Logger LOGGER = LogManager.getLogger(CompanyServiceApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Starting Company Service...");
		SpringApplication.run(CompanyServiceApplication.class, args);
	}

}
