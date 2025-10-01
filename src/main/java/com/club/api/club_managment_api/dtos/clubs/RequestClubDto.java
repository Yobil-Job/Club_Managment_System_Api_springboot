package com.club.api.club_managment_api.dtos.clubs;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import com.club.api.club_managment_api.models.enums.Club_Type_enum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestClubDto {

    @NotBlank
    @Length(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    private String title;
    
    @NotNull
    private Club_Type_enum club_Type;

    @NotBlank
    @Length(min = 10, message = "Description should be at least 10 characters long")
    private String description;

    @URL(message = "Logo must be a valid URL")
    private String logo;

    
    //private List<Long> memberIds;

    
    private List<Integer> authorityIds;

 
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    

    public Club_Type_enum getClub_Type() {
		return club_Type;
	}
	public void setClub_Type(Club_Type_enum club_Type) {
		this.club_Type = club_Type;
	}
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

   // public List<Long> getMemberIds() { return memberIds; }
   // public void setMemberIds(List<Long> memberIds) { this.memberIds = memberIds; }

    public List<Integer> getAuthorityIds() { return authorityIds; }
    public void setAuthorityIds(List<Integer> authorityIds) { this.authorityIds = authorityIds; }
}
