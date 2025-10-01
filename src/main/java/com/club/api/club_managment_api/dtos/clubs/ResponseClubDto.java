package com.club.api.club_managment_api.dtos.clubs;

import org.hibernate.validator.constraints.Length;

import com.club.api.club_managment_api.models.enums.Club_Type_enum;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResponseClubDto {
	
	@Id
	private int id;
	
	@NotBlank
	@Length(min = 2, max = 50)
	private String title;
	
	  @NotNull
	    private Club_Type_enum club_Type;

	@NotBlank
	@Length(min = 10)
	private String description;

	
	private String logo;
	
	private int numberOfMmbers;
	
	// private List<Student> members = new ArrayList<>();;

	

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


	public int getNumberOfMmbers() {
		return numberOfMmbers;
	}


	public void setNumberOfMmbers(int numberOfMmbers) {
		this.numberOfMmbers = numberOfMmbers;
	}


	/*
	 * public List<Student> getMembers() { return members; }
	 * 
	 * 
	 * public void setMembers(List<Student> members) { this.members = members; }
	 */
	 
	
	

	

}
