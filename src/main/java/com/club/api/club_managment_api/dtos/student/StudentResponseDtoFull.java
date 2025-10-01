package com.club.api.club_managment_api.dtos.student;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.club.api.club_managment_api.models.Authority;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.enums.Gender_enum;
import com.club.api.club_managment_api.models.enums.Year_Of_Stay_enum;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentResponseDtoFull {
	
	@Id
	private long id;
	
	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Length(min = 2, max = 30) 
	private String firstname;

	@NotBlank
	@Length(min = 2, max = 30)
	private String lastname;

	
	@NotNull
	private Gender_enum gender;

	
	@Length(min = 3, max = 50)
	private String department;

	
	private List<Authority> authorities;

	
	@NotNull
	private Year_Of_Stay_enum yearOfStay;

	
	private List<Club> clubs;


	private LocalDateTime createdAt;

	
	private LocalDateTime updatedAt;


	
	public long getId() {
		return id;
	}

    

	public String getEmail() {
		return email;
	}


	public void setId(long id) {
		this.id = id;
	}



	public void setEmail(String email) {
		this.email = email;
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

	

}
