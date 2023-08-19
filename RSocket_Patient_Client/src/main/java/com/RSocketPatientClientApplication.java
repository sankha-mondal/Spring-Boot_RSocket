package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RSocketPatientClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RSocketPatientClientApplication.class, args);
		System.out.println("RSocket Patient Client running on Port No 8484...");
	}
	
		/*
		 *   Dependency: RSocket, Spring Reactive web, Devtools
		 */

}
