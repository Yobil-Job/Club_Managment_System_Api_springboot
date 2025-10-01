package com.club.api.club_managment_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.club.api.club_managment_api.models.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
	
	public Club findByTitle(String title);

} 
