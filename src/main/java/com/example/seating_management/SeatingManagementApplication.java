package com.example.seating_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableFeignClients
public class SeatingManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeatingManagementApplication.class, args);
		
		
		
		
	}
	
	

}
