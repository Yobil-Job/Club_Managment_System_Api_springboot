package com.club.api.club_managment_api.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.repository.StudentRepository;

public class CustomUserDetailsService implements UserDetailsService{

	private StudentRepository studentRepository;
	
	  
	
	public CustomUserDetailsService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Student student = studentRepository.findByEmail(username)
	        .orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
	    return new CustomUserDetails(student);
	} 


}
