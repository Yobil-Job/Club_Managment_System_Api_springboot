package com.club.api.club_managment_api.dtos.events;

import java.awt.geom.Point2D.Double;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;

@Component
public class ResponseEventsAttended {
	
	@NotBlank
	@Length(min = 30,max = 200)
	private String title;
	
	
	@NotBlank
	@Length(min = 10 , max = 1000 )
	private String description;
	

	private LocalDateTime createdAt;
	
	
	private LocalDateTime startAt;
	
	
	private LocalDateTime endAt;
	
	
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


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
