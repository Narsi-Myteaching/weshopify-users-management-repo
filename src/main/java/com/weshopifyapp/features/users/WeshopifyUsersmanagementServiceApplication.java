package com.weshopifyapp.features.users;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeshopifyUsersmanagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyUsersmanagementServiceApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
