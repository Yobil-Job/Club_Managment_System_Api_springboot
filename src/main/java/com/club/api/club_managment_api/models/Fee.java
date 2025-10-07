package com.club.api.club_managment_api.models;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import com.club.api.club_managment_api.models.enums.Payment_Status_enum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "fees")
public class Fee {
	
	@Id
	@Column(name = "fee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JsonIgnore
	private Student student;
	
	@ManyToOne
	@JsonIgnore
	private Club club;
	
	@NotNull
	@Min(1)
	@Column(nullable = false)
	private double amount;
	
	@Length(max = 100)
	private String purpose;
	
	@NotNull
	@Column(nullable = false,updatable = false)
	private LocalDateTime date;
	
    @Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "status",nullable = false)
	private Payment_Status_enum status;

	public Fee() {}
	public Fee(int id, Student student, Club club, @Min(1) double amount, @Length(max = 100) String purpose,
			LocalDateTime date, @NotNull Payment_Status_enum status) {
		super();
		this.id = id;
		this.student = student;
		this.club = club;
		this.amount = amount;
		this.purpose = purpose;
		this.date = date;
		this.status = status;
	}
	
	@PrePersist
	public void onDate() {
		date=LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Payment_Status_enum getStatus() {
		return status;
	}

	public void setStatus(Payment_Status_enum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Fees [id=" + id + ", student=" + student + ", club=" + club + ", amount=" + amount + ", purpose="
				+ purpose + ", date=" + date + ", status=" + status + "]";
	}
	
	
	


}
