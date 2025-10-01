package com.club.api.club_managment_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.club.api.club_managment_api.models.Announcement;
import com.club.api.club_managment_api.models.Club;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer>{
	public List<Announcement> findByclub(Club c);

}
