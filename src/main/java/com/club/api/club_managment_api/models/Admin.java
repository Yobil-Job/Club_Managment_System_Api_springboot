package com.club.api.club_managment_api.models;

import com.club.api.club_managment_api.models.enums.Role_enum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Admin {
	
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotNull
	    private String username;

	    @NotNull
	    private String password;

	    @Enumerated(EnumType.STRING)
	    private Role_enum role; 

}
