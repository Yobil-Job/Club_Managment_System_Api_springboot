package com.club.api.club_managment_api.dtos.authorities;

import java.time.LocalDate;

public class RequestAuthorityUpdateDto {
	
	
	
	private String name;
	
	
	private  long studentId;
	
	
	private int clubId;
	
	
	private LocalDate startDate;
	
	
	private LocalDate endDate;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getStudentId() {
		return studentId;
	}


	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}


	public int getClubId() {
		return clubId;
	}


	public void setClubId(int clubId) {
		this.clubId = clubId;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}



	

	
}
