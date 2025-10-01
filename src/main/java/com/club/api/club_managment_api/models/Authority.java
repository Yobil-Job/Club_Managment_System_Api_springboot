package com.club.api.club_managment_api.models;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "authority")
public class Authority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authority_id")
	private int id;
	
	@NotBlank
	@Length(min = 3,max = 50)
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id",nullable = false)
	private Club club;
	
	@Column
	private LocalDate startDate;
	
	@Column
	private LocalDate endDate;
	
	public Authority() {}

	public Authority(int id, @NotBlank @Length(min = 3, max = 50) String name, Student student, Club club,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.name = name;
		this.student = student;
		this.club = club;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
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

	@Override
	public String toString() {
		return "Authority [id=" + id + ", name=" + name + ", student=" + student + ", club=" + club + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	
	 

}
