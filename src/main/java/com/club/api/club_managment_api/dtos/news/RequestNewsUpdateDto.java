package com.club.api.club_managment_api.dtos.news;

import java.util.List;

import org.hibernate.validator.constraints.Length;

public class RequestNewsUpdateDto {
	
	@Length(min = 5, max = 200)
	private String title;
	
	@Length(min = 10, max = 5000)
	private String description;
	
	private List<String> images;

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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
}

