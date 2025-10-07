package com.club.api.club_managment_api.dtos.events;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;

public class RequestEventUpdateDto {
	
	
	
	
	
	@Length(min = 30,max = 200)
	private String title;
	
	
	
	@Length(min = 10 , max = 1000 )
	private String description;
	
	
	
	private LocalDateTime startAt;
	
	
	private LocalDateTime endAt;
	
	
	
	
	private List<Long> attendeesId;
	
	
	private Double latitude;
	
	private Double longitude;

	
	


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


	public LocalDateTime getStartAt() {
		return startAt;
	}


	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}


	public LocalDateTime getEndAt() {
		return endAt;
	}


	public void setEndAt(LocalDateTime endAt) {
		this.endAt = endAt;
	}


	public List<Long> getAttendees() {
		return attendeesId;
	}


	public void setAttendees(List<Long> attendees) {
		this.attendeesId = attendees;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	


}
