package com.club.api.club_managment_api.dtos.student;

import org.hibernate.validator.constraints.Length;

import com.club.api.club_managment_api.models.enums.Gender_enum;
import com.club.api.club_managment_api.models.enums.Year_Of_Stay_enum;

public class StudentRequestDtoFull {
	
	//@Email
	//@NotBlank
	//private String email;

	
	@Length(min = 8)
	private String password;

	//@NotBlank
	@Length(min = 2, max = 30)  
	private String firstname;

	//@NotBlank
	@Length(min = 2, max = 30)
	private String lastname;
 
	
	//@NotNull 
	private Gender_enum gender;

	
	@Length(min = 3, max = 50)
	private String department;

   // @NotNull
	private Year_Of_Stay_enum yearOfStay;

	/*
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 */

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Year_Of_Stay_enum getYearOfStay() {
		return yearOfStay;
	}

	public void setYearOfStay(Year_Of_Stay_enum yearOfStay) {
		this.yearOfStay = yearOfStay;
	}
	

}
