package com.club.api.club_managment_api.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.club.api.club_managment_api.models.enums.Gender_enum;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.models.enums.Year_Of_Stay_enum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Students")
public class Student {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private long id;

	@Email
	@NotBlank
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@NotBlank
	@Length(min = 8)
	@Column(name = "password", nullable = false)
	@JsonIgnore
	private String password;

	@NotBlank
	@Column(name = "first_name", nullable = false)
	@Length(min = 2, max = 30)
	private String firstname;

	@NotBlank
	@Column(name = "last_name", nullable = false)
	@Length(min = 2, max = 30)
	private String lastname;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "gender", nullable = false)
	private Gender_enum gender;

	@Column(name = "department")
	@Length(min = 3, max = 50)
	private String department;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Authority> authorities;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "year_of_stay", nullable = false)
	private Year_Of_Stay_enum yearOfStay;

	@ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
	private List<Club> clubs;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "update_at", insertable = false)
	private LocalDateTime updatedAt; 

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(nullable = false)
	private Role_enum role;

	@ManyToMany(mappedBy = "attendees", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Event> eventsAttended;

	public Student() {
	}

	public Student(long id, @Email @NotBlank String email, @NotBlank @Length(min = 8) String password,
			@NotBlank @Length(min = 2, max = 30) String firstname, @NotBlank @Length(min = 2, max = 30) String lastname,
			@NotNull Gender_enum gender, @Length(min = 3, max = 50) String department,
			@NotNull Year_Of_Stay_enum yearOfStay, List<Club> clubs, LocalDateTime createdAt, LocalDateTime updatedAt,
			@NotNull Role_enum role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.department = department;
		this.yearOfStay = yearOfStay;
		this.clubs = clubs;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
	}

	public void addClub(Club clubs) {
		this.clubs.add(clubs);
		clubs.addMembers(this);
	}

	@PrePersist
	public void onCreat() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Gender_enum getGender() {
		return gender;
	}

	public void setGender(Gender_enum gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	
	public List<Event> getEventsAttended() {
		return eventsAttended;
	}

	public void setEventsAttended(List<Event> eventsAttended) {
		this.eventsAttended = eventsAttended;
	}

	public Year_Of_Stay_enum getYearOfStay() {
		return yearOfStay;
	}

	public void setYearOfStay(Year_Of_Stay_enum yearOfStay) {
		this.yearOfStay = yearOfStay;
	}

	public List<Club> getClubs() {
		return clubs;
	}

	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
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

	public Role_enum getRole() {
		return role;
	}

	public void setRole(Role_enum role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", gender=" + gender + ", department=" + department + ", yearOfStay="
				+ yearOfStay + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", role="
				+ role  + "]";
	}

}
