package com.club.api.club_managment_api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.LinkBuilderSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.api.club_managment_api.Service.AnnouncementService;
import com.club.api.club_managment_api.dtos.announcement.RequestAnnouncementDto;
import com.club.api.club_managment_api.dtos.announcement.RequestAnnouncementUpdateDto;
import com.club.api.club_managment_api.models.Announcement;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
	
	private final AnnouncementService announcementService;
	
	
	
	public AnnouncementController(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}



	@PostMapping("/create")
	public ResponseEntity<EntityModel<Announcement>> createAnnouncement(@Valid @RequestBody RequestAnnouncementDto dto) {
		
		  Announcement a=announcementService.createAnnouncement(dto);
		  EntityModel<Announcement> e= EntityModel.of(a,
				  linkTo(methodOn(AnnouncementController.class).retriveAnnouncementById(a.getId())).withSelfRel(),
				  linkTo(methodOn(AnnouncementController.class).updateAnnouncement()).withRel("update"),
				  linkTo(methodOn(AnnouncementController.class).deleteAnnouncement()).withRel("delete"));
		  
		  URI location =linkTo(methodOn(AnnouncementController.class).retriveAnnouncementById(a.getId())).toUri();
		  return ResponseEntity.created(location).body(e);
		  
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<EntityModel<Announcement>> retriveAnnouncementById(int id) {
		Announcement a=announcementService.getAnnouncementById(id);
		EntityModel<Announcement> e=EntityModel.of(a,
				linkTo(methodOn(AnnouncementController.class).retriveAllAnnouncement()).withRel("retriveAllAnnouncement"));
		return 	ResponseEntity.ok(e);
	}


    @GetMapping("")
	private ResponseEntity<CollectionModel<EntityModel<Announcement>>> retriveAllAnnouncement() {
		
    	List<Announcement> result=announcementService.getAllAnnouncemnt();
    	List<EntityModel<Announcement>> e=result.stream().map(a->EntityModel.of(a,
    			linkTo(methodOn(AnnouncementController.class).retriveAnnouncementById(a.getId())).withSelfRel())).toList();
		CollectionModel<EntityModel<Announcement>> response=CollectionModel.of(e,
				linkTo(methodOn(AnnouncementController.class).retriveAllAnnouncement()).withSelfRel());
    	return ResponseEntity.ok(response);
	}


    @PatchMapping("/{announcementId}/update")
    private Announcement updateAnnouncement(@Valid @RequestBody RequestAnnouncementUpdateDto dto ,@PathVariable int announcementId) {
    	
    	       return announcementService.updateAnnouncement(announcementId, dto);
			
	}

    @DeleteMapping("/{announcementId}/{creaatedById}")
	private void deleteAnnouncement(@PathVariable int announcementId,@PathVariable long creaatedById  ) {
    	
		
		return null;
	}



	



	

}



/*
 * 1. AnnouncementController
 * 
 * POST /api/clubs/{clubId}/announcements → createAnnouncement
 * 
 * GET /api/announcements/{id} → getAnnouncementById
 * 
 * GET /api/clubs/{clubId}/announcements → getAnnouncementsByClub
 * 
 * GET /api/students/{studentId}/announcements → getAnnouncementsByStudent
 * 
 * PUT /api/announcements/{id} → updateAnnouncement
 * 
 * DELETE /api/announcements/{id}?requesterId={studentId} → deleteAnnouncemen
 */