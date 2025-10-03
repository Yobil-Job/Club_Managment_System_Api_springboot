package com.club.api.club_managment_api.dtos.announcement;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public class RequestAnnouncementUpdateDto {

	
	

	private int clubId;
	
	
	@Length(min = 3,max = 50)
	private String title;
	
	
	@Length(min = 3,max = 1000)
	private String description;
	
	
    @NotNull
	private long createdById;


	public int getClubId() {
		return clubId;
	}


	public void setClubId(int clubId) {
		this.clubId = clubId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public long getCreatedById() {
		return createdById;
	}


	public void setCreatedById(long createdById) {
		this.createdById = createdById;
	}	
    
    
}
