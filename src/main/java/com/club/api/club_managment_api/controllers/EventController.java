package com.club.api.club_managment_api.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.api.club_managment_api.Service.EvenetService;
import com.club.api.club_managment_api.dtos.events.RequestEventDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {
	
	private final EvenetService evenetService;

	public EventController(EvenetService evenetService) {
		this.evenetService = evenetService;
	}
	
	@PostMapping("/create/{studentId}")
	public void createEvent(@Valid @RequestBody RequestEventDto dto,@PathVariable int studentId) {
		evenetService.createEvent(null, 0, 0)
		
	}
	
	
	

}

/*
 * 2. EventController
 * 
 * POST /api/clubs/{clubId}/events → createEvent
 * 
 * GET /api/events/{id} → getEventById
 * 
 * GET /api/clubs/{clubId}/events → getEventsByClub
 * 
 * GET /api/students/{studentId}/events → getEventsByStudent
 * 
 * PUT /api/events/{id} → updateEvent
 * 
 * DELETE /api/events/{id}?requesterId={studentId} → deleteEvent
 * 
 * POST /api/events/{eventId}/attendees/{studentId} → addAttendee
 * 
 * DELETE /api/events/{eventId}/attendees/{studentId} → removeAttendee
 * 
 * GET /api/events/{eventId}/attendees → getAttendees
 */