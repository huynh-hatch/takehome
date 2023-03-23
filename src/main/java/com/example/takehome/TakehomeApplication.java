package com.example.takehome;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TakehomeApplication {

	private static final Logger log = LoggerFactory.getLogger(TakehomeApplication.class);

	public static void main(String[] args) {
		log.debug("This is a debug message");
		SpringApplication.run(TakehomeApplication.class, args);
	}

}
