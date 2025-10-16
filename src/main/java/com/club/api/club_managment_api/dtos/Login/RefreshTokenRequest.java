package com.club.api.club_managment_api.dtos.Login;

public class RefreshTokenRequest {

	private String refreshToken;

	public RefreshTokenRequest(String refreshToken) {
		super();
		this.refreshToken = refreshToken;
	}

	public RefreshTokenRequest() {
		super();
		
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
