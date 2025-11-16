package com.club.api.club_managment_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.club.api.club_managment_api.models.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
	
	@Query("SELECT n FROM News n ORDER BY n.createdAt DESC")
	List<News> findAllOrderByCreatedAtDesc();
}

