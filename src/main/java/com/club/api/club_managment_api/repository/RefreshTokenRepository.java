package com.club.api.club_managment_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.club.api.club_managment_api.models.RefreshToken;
import com.club.api.club_managment_api.models.Student;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	  Optional<RefreshToken> findByToken(String token);
	    int deleteByStudent(Student student);

}
 