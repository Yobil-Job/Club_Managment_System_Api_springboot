package com.club.api.club_managment_api.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "news")
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private int id;
	
	@NotBlank
	@Column(name = "title", nullable = false)
	@Length(min = 5, max = 200)
	private String title;
	
	@NotBlank
	@Column(name = "description", nullable = false, columnDefinition = "TEXT")
	@Length(min = 10, max = 5000)
	private String description;
	
	@Column(name = "images", columnDefinition = "TEXT")
	private String images;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id", nullable = false)
	@JsonIgnore
	private Student createdBy;
	
	public News() {}

	public News(int id, @NotBlank @Length(min = 5, max = 200) String title,
			@NotBlank @Length(min = 10, max = 5000) String description, String images,
			LocalDateTime createdAt, Student createdBy) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.images = images;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
	
	@PrePersist
	public void onCreate() {
		createdAt = LocalDateTime.now();
	}
	
	public List<String> getImagesList() {
		if (images == null || images.trim().isEmpty()) {
			return new ArrayList<>();
		}
		List<String> imageList = new ArrayList<>();
		String[] imageArray = images.split(",");
		for (String img : imageArray) {
			String trimmed = img.trim();
			if (!trimmed.isEmpty()) {
				imageList.add(trimmed);
			}
		}
		return imageList;
	}
	
	public void setImagesList(List<String> imageList) {
		if (imageList == null || imageList.isEmpty()) {
			this.images = null;
		} else {
			this.images = String.join(",", imageList);
		}
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Student getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Student createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", description=" + description
				+ ", createdAt=" + createdAt + ", createdBy=" + (createdBy != null ? createdBy.getId() : null) + "]";
	}
}

