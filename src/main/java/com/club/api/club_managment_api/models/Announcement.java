package com.club.api.club_managment_api.models;

import java.time.LocalDateTime;

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
@Table(name = "announcements")
public class Announcement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "announcement_id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id",nullable = false)
	@JsonIgnore
	private Club club;
	
	@NotBlank
	@Column(name = "title",nullable = false)
	@Length(min = 3,max = 50)
	private String title;
	
	@NotBlank
	@Column(name = "description",nullable = false)
	@Length(min = 3,max = 1000)
	private String description;
	
	@Column
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Student createdBy;
	
	public Announcement() {}

	public Announcement(int id, Club club, @NotBlank @Length(min = 3, max = 50) String title,
			@NotBlank @Length(min = 3, max = 1000) String description, LocalDateTime createdAt, Student createdBy) {
		super();
		this.id = id;
		this.club = club;
		this.title = title;
		this.description = description;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
	
	
	@PrePersist
	public void onCreate() {
		createdAt=LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
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
	    return "Announcement [id=" + id +
	           ", club=" + (club != null ? club.getId() : null) +
	           ", title=" + title +
	           ", description=" + description +
	           ", createdAt=" + createdAt +
	           ", createdBy=" + (createdBy != null ? createdBy.getId() : null) + "]";
	}

	
	

}
