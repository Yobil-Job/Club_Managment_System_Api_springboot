package com.club.api.club_managment_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	public List<Event> findByclub(Club c);

}
 