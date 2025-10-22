package com.club.api.club_managment_api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.api.club_managment_api.Service.EvenetService;
import com.club.api.club_managment_api.dtos.events.RequestEventDto;
import com.club.api.club_managment_api.dtos.events.RequestEventUpdateDto;
import com.club.api.club_managment_api.models.Event;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {
	
	private final EvenetService evenetService;

	public EventController(EvenetService evenetService) {
		this.evenetService = evenetService;
	}
	
	@PostMapping("/create/{studentId}")
	@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','SUPER_USER')")
	public ResponseEntity<EntityModel<Event>> createEvent(@Valid @RequestBody RequestEventDto dto,@PathVariable int studentId) {
		
		Event event=evenetService.createEvent(dto, studentId);
		EntityModel<Event> e=EntityModel.of(event,
				linkTo(methodOn(EventController.class).retriveEventById(event.getId())).withSelfRel());
		URI location=linkTo(methodOn(EventController.class).retriveEventById(event.getId())).toUri();
		return ResponseEntity.created(location).body(e);
		
	}

	
	@GetMapping("/{eventId}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<EntityModel<Event>> retriveEventById(@PathVariable int eventId) {
		Event event=evenetService.getEventById(eventId);
		EntityModel<Event> e=EntityModel.of(event,
				linkTo(methodOn(EventController.class).deleteEvent(event.getId(),event.getCreatedBy().getId())).withRel("delete"));
		return ResponseEntity.ok(e);
	}
	
	@GetMapping("/club/{clubId}")
	@PreAuthorize("isAuthenticated()")
	public List<Event> retriveEventByClubId(@PathVariable int clubId) {
		return evenetService.getEventsByClub(clubId);
		
		
	}
	
	@GetMapping("/allEvents")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<CollectionModel<EntityModel<Event>>> retriveAllEvents() {
		List<Event> events= evenetService.getAllEvents();
		List<EntityModel<Event>> e=events.stream().map(event->
		EntityModel.of(event,
				linkTo(methodOn(EventController.class).retriveEventById(event.getId())).withSelfRel())).toList();
		CollectionModel<EntityModel<Event>> response=CollectionModel.of(e,
				linkTo(methodOn(EventController.class).retriveAllEvents()).withSelfRel());
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{eventId}/update/{studentId}")
	@PreAuthorize("hasAnyRole('SUPER_ADMI','ADMIN','SUPER_USER')")
	public  Event updateEvent(@PathVariable int eventId, @PathVariable long studentId,@RequestBody 	RequestEventUpdateDto dto) {
		
		
		return evenetService.updateEvent(eventId, dto,studentId);
		
	}
	
	@DeleteMapping("/{eventId}/delete/{studentId}")
	@PreAuthorize("hasAnyRole('SUPER_ADMI','ADMIN','SUPER_USER')")
	public ResponseEntity<Void> deleteEvent(@PathVariable int eventId ,@PathVariable long studentId) {
		
		evenetService.deleteEvent(eventId, studentId);
		return ResponseEntity.noContent().build();
	}

	
	
	
	

}

