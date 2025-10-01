package com.club.api.club_managment_api.dtos.clubs;

import org.hibernate.validator.constraints.Length;

import com.club.api.club_managment_api.models.enums.Club_Type_enum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RequestClubDtoFull {
	 
	
	private int id;

	
	@Length(min = 2, max = 50,message = "Title must be between 2-50 char long")
	private String title;
	
	@Enumerated(EnumType.STRING)
	private Club_Type_enum club_Type;

	@Length(min = 10,message = "description shoud be at least 10 character long")
	private String description;

	private String logo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Club_Type_enum getClub_Type() {
		return club_Type;
	}

	public void setClub_Type(Club_Type_enum club_Type) {
		this.club_Type = club_Type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	

	

	


}
