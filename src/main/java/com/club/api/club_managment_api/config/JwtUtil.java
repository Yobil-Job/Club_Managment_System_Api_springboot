package com.club.api.club_managment_api.config;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.club.api.club_managment_api.models.enums.Role_enum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	
	@Value("${jwt.refresh-expiration}")
	private long refreshExpiration;
	
	public Key getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateAccessTocken(long id,String email,String role){
		return Jwts.builder()
				.setSubject(email)
				.claim("id",id)
				.claim("role",role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
				.signWith(getSigningKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
	            .compact();
		
	}
	
	public String generateRefreshToken(long id,String email,String role) {
		return Jwts.builder()
				.setSubject(email) 
				.claim("id", id) 
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+refreshExpiration))
				.signWith(getSigningKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public String extractRole(String token) {
		return extractAllClaims(token).get("role",String.class);
	}

	public boolean validateToken(String token, String username) {
	    final String extractedUsername = extractUsername(token);
	    return (extractedUsername.equals(username) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
	    return extractAllClaims(token).getExpiration().before(new Date());
	}


	
	private Claims extractAllClaims(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(getSigningKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}

	
	 

	
	
}
