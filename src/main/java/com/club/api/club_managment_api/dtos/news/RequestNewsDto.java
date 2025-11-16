package com.club.api.club_managment_api.dtos.news;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestNewsDto {
	
	@NotBlank
	@Length(min = 5, max = 200)
	private String title;
	
	@NotBlank
	@Length(min = 10, max = 5000)
	private String description;
	
	private List<String> images;
	
	private Long createdById;

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

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}
}

