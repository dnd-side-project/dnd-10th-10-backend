package com.dnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
public class BasterdzApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BasterdzApiApplication.class, args);
	}
}
