package com.sathish;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookappJpaApplication {
	private static final Logger LOGGER = Logger.getLogger(BookappJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookappJpaApplication.class, args);
		LOGGER.info("Application Started");
	}
}
