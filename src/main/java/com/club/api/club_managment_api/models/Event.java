package com.club.api.club_managment_api.models;

import java.awt.geom.Point2D.Double;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id",nullable = false)
	private Club club;
	
	@NotBlank
	@Column(name = "title",nullable = false)
	@Length(min = 30,max = 200)
	private String title;
	
	
	@NotBlank
	@Length(min = 10 , max = 1000 )
	@Column(name = "description")
	private String description;
	
	@Column
	private LocalDateTime createdAt;
	
	@Column
	private LocalDateTime startAt;
	
	@Column
	private LocalDateTime endAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private Student createdBy;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "event_attendees",
	    joinColumns = @JoinColumn(name = "event_id"),
	    inverseJoinColumns = @JoinColumn(name = "student_id")
	)
	private List<Student> attendees;
	
	@Column
	private Double latitude;

	@Column
	private Double longitude;

	
	public Event() {}



	public Event(int id, com.club.api.club_managment_api.models.Club club,
			@NotBlank @Length(min = 30, max = 200) String title,
			@NotBlank @Length(min = 10, max = 1000) String description, LocalDateTime createdAt, LocalDateTime startAt,
			LocalDateTime endAt, Student createdBy, List<Student> attendees, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.club = club;
		this.title = title;
		this.description = description;
		this.createdAt = createdAt;
		this.startAt = startAt;
		this.endAt = endAt;
		this.createdBy = createdBy;
		this.attendees = attendees;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public Student getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Student createdBy) {
		this.createdBy = createdBy;
	}

	public List<Student> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Student> attendees) {
		this.attendees = attendees;
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



	@Override
	public String toString() {
		return "Event [id=" + id + ", Club=" + club + ", title=" + title + ", description=" + description
				+ ", createdAt=" + createdAt + ", startAt=" + startAt + ", endAt=" + endAt + ", createdBy=" + createdBy
				+ ", attendees=" + attendees + ", latitude=" + latitude + ", longitude=" + longitude + ", getId()="
				+ getId() + ", getClub()=" + getClub() + ", getTitle()=" + getTitle() + ", getDescription()="
				+ getDescription() + ", getCreatedAt()=" + getCreatedAt() + ", getStartAt()=" + getStartAt()
				+ ", getEndAt()=" + getEndAt() + ", getCreatedBy()=" + getCreatedBy() + ", getAttendees()="
				+ getAttendees() + ", getLatitude()=" + getLatitude() + ", getLongitude()=" + getLongitude()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
}
