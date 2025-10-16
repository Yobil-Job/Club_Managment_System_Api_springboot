package com.club.api.club_managment_api.dtos.Fees;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RequestFeesDto {@ManyToOne
	
	
	@NotNull
	@Min(1)
	private double amount;
	
	@Length(max = 100)
	private String purpose;



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
	


}
