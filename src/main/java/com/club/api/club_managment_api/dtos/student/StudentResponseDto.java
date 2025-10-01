package com.club.api.club_managment_api.dtos.student;

import org.hibernate.validator.constraints.Length;

import com.club.api.club_managment_api.models.enums.Gender_enum;
import com.club.api.club_managment_api.models.enums.Year_Of_Stay_enum;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentResponseDto {

	@Id 
	private long id;

	@Email(message = "Email must be valid")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "First name is required")
	@Length(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
	private String firstname;

	@NotBlank(message = "Last name is required")
	@Length(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
	private String lastname;
 
	@NotNull(message = "Gender is required")
	private Gender_enum gender;

	@NotNull(message = "Year of stay is required")
	private Year_Of_Stay_enum yearOfStay;
	
	

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

	public Year_Of_Stay_enum getYearOfStay() {
		return yearOfStay;
	}

	public void setYearOfStay(Year_Of_Stay_enum yearOfStay) {
		this.yearOfStay = yearOfStay;
	}

}
