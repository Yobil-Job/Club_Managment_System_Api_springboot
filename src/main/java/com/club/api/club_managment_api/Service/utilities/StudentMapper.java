package com.club.api.club_managment_api.Service.utilities;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.club.api.club_managment_api.dtos.student.StudentRequestDto;
import com.club.api.club_managment_api.dtos.student.StudentRequestDtoFull;
import com.club.api.club_managment_api.dtos.student.StudentResponseDto;
import com.club.api.club_managment_api.dtos.student.StudentResponseDtoFull;
import com.club.api.club_managment_api.models.Student;

@Component
public class StudentMapper {
	
	private PasswordEncoder passwordEncoder;
	
	

	public StudentMapper(PasswordEncoder passwordEncoder) {

		this.passwordEncoder = passwordEncoder;
	}

	public Student toStudentEntity(StudentRequestDto dto) {
		Student s = new Student();
		s.setFirstname(dto.getFirstname());
		s.setLastname(dto.getLastname());
		s.setEmail(dto.getEmail());
		s.setPassword(passwordEncoder.encode(dto.getPassword()));
		s.setYearOfStay(dto.getYearOfStay());
		s.setGender(dto.getGender());
		return s;
	}

	public StudentResponseDto toStudentResponseDto(Student s) {
		StudentResponseDto d = new StudentResponseDto();
		d.setId(s.getId());
		d.setFirstname(s.getFirstname());
		d.setLastname(s.getLastname());
		d.setEmail(s.getEmail());
		d.setYearOfStay(s.getYearOfStay());
		d.setGender(s.getGender());
		return d;
	}

	public Student toStudentEntityFull(StudentRequestDtoFull dto) {
		Student s = new Student();
		s.setFirstname(dto.getFirstname());
		s.setLastname(dto.getLastname());
		//s.setEmail(dto.getEmail());
		s.setPassword(dto.getPassword());
		s.setGender(dto.getGender());
		s.setDepartment(dto.getDepartment());
		s.setYearOfStay(dto.getYearOfStay());
		if (dto.getPassword() != null) {
			s.setPassword(dto.getPassword());
		}

		return s;

	}
	
	
	public StudentResponseDtoFull toStudentResponseDtoFull(Student s) {
		StudentResponseDtoFull d = new StudentResponseDtoFull();
		d.setId(s.getId());
		d.setFirstname(s.getFirstname());
		d.setLastname(s.getLastname());
		d.setEmail(s.getEmail());
		d.setYearOfStay(s.getYearOfStay());
		d.setAuthorities(s.getAuthorities());
		if(s.getClubs()!=null) {d.setClubs(s.getClubs());}
		d.setCreatedAt(s.getCreatedAt());
		d.setDepartment(s.getDepartment());
		d.setGender(s.getGender());
		d.setUpdatedAt(s.getUpdatedAt()); 
		
		return d;
	}
	
	
	
}