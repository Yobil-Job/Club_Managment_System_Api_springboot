package com.club.api.club_managment_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClubManagmentApiApplication {

	public static void main(String[] args) {
		//MakeHash();
		SpringApplication.run(ClubManagmentApiApplication.class, args);
	}
	
	

	
	/*
	 * public static void MakeHash() { BCryptPasswordEncoder enc = new
	 * BCryptPasswordEncoder(); System.out.println("here the bycript ==");
	 * System.out.println(enc.encode("SuperSecret123!"));
	 * 
	 * }
	 */


}
