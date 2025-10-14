package com.club.api.club_managment_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.club.api.club_managment_api.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public Optional<Student> findByEmail(String email);
	

}
 
