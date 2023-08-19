package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RSocketPatientServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RSocketPatientServerApplication.class, args);
		System.out.println("RSocket Patient Server running on Port No 7000...");
		System.out.println("Default Server: Netty...");
		
	}
		/*
		 * 		Dependencie : Rsocket, Devtools 
		 */

}
