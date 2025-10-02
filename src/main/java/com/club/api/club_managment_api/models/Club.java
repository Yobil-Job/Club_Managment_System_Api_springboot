package com.club.api.club_managment_api.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.club.api.club_managment_api.models.enums.Club_Type_enum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Clubs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Club {		


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "club_id")
	private int id;

	@NotBlank
	@Length(min = 2, max = 50,message = "Title must be between 2-50 char long")
	@Column(name = "title", nullable = false)
	private String title;
	
	@Enumerated(EnumType.STRING)
	private Club_Type_enum club_Type;

	@NotBlank
	@Length(min = 10,message = "description shoud be at least 10 character long")
	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "logo_url")
	private String logo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_club", joinColumns = @JoinColumn(name = "club_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	@JsonIgnore
	private List<Student> members = new ArrayList<>();

	@OneToMany(mappedBy = "club", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Authority> authorities;

	@OneToMany(mappedBy = "club", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Event> events;

	@NotNull
	@Column(nullable = false)
	private LocalDateTime createdAt;

	@NotNull
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Announcement> announcement;
	
	@Column(name = "club_admin_id", nullable = true)
	private Long clubAdminId;

	public Club() {
	}

	public Club(int id, @NotBlank @Length(min = 2, max = 50) String title,
			@NotBlank @Length(min = 10) String description, @NotNull LocalDateTime createdAt,
			@NotNull LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Club(int id, @NotBlank @Length(min = 2, max = 50) String title,
			@NotBlank @Length(min = 10) String description, List<Student> members, List<Authority> authorities,
			List<Event> events, @NotNull LocalDateTime createdAt, @NotNull LocalDateTime updatedAt,Club_Type_enum club_Type,Long clubAdminId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.members = members;
		this.authorities = authorities;
		this.events = events;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.club_Type = club_Type;
		this.clubAdminId=clubAdminId;
	}
	

	public void addMembers(Student student) {
		if (!this.members.contains(student)) {
		members.add(student);
		student.addClub(this);
		}

	}

	public void addAuthorities(Authority authority) {
		authorities.add(authority);
		authority.setClub(this);

	}

	public void addEvents(Event event) {
		events.add(event);
		event.setClub(this);

	}

	public void addAnnouncement(Announcement announcement) {
		this.announcement.add(announcement);
		announcement.setClub(this);
	}

	@PrePersist
	public void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();

	}

	@PreUpdate
	public void onUpdated() {
		updatedAt = LocalDateTime.now();
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

	public List<Student> getMembers() {
		return members;
	}

	public void setMembers(List<Student> members) {
		this.members = members;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Announcement> getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(List<Announcement> announcement) {
		this.announcement = announcement;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	
	public long getClubAdminId() {
		return clubAdminId;
	}

	public void setClubAdminId(long clubAdminId) {
		this.clubAdminId = clubAdminId;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", title=" + title + ", description=" + description + ", logo=" + logo + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getDescription()=" + getDescription() + ", getLogo()=" + getLogo() + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	

}
