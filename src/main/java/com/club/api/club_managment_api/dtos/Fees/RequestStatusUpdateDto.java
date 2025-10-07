package com.club.api.club_managment_api.dtos.Fees;

import com.club.api.club_managment_api.models.enums.Payment_Status_enum;

import jakarta.validation.constraints.NotNull;

public class RequestStatusUpdateDto {
	
  @NotNull
  private long adminStudentId;
  
  @NotNull
  private Payment_Status_enum status;
  
  @NotNull
  private int clubId;
  
  @NotNull
  private long payersId;

  public long getAdminStudentId() {
	return adminStudentId;
  }

  public void setAdminStudentId(long adminStudentId) {
	this.adminStudentId = adminStudentId;
  }

  public Payment_Status_enum getStatus() {
	return status;
  }

  public void setStatus(Payment_Status_enum status) {
	this.status = status;
  }

  public int getClubId() {
	return clubId;
  }

  public void setClubId(int clubId) {
	this.clubId = clubId;
  }

  public long getPayersId() {
	return payersId;
  }

  public void setPayersId(long payersId) {
	this.payersId = payersId;
  }
  
  
  
  

}
