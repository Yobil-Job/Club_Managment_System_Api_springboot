package com.club.api.club_managment_api.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;

public class CustomUserDetails implements UserDetails {
	
	private Student student;
	
	

	public CustomUserDetails(Student student) {
		this.student = student;
	}
	
	public Long getId() {
		return student.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Role_enum role=student.getRole();
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+role.name()));
		
	}

	@Override
	public String getPassword() {
	 return	student.getPassword();
	
	}

	@Override
	public String getUsername() {
		return student.getEmail();

	}
	
	public Student getStudent() {
		return student;
	}

}
