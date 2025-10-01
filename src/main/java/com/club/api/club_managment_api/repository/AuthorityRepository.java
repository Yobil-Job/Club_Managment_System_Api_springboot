package com.club.api.club_managment_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.club.api.club_managment_api.models.Authority;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Student;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer>{ 

	public List<Authority> findByClub(Club c);
	public List<Authority> findByStudent(Student s);
}
 