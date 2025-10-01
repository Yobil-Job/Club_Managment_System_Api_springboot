package com.club.api.club_managment_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Fee;
import com.club.api.club_managment_api.models.Student;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Integer> {
	public List<Fee> findBystudent(Student s);
	public List<Fee> findByclub(Club c);

}
