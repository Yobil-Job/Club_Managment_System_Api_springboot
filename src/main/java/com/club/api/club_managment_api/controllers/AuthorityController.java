package com.club.api.club_managment_api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.api.club_managment_api.Service.AuthorityService;
import com.club.api.club_managment_api.dtos.authorities.RequestAuthorityDto;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Authority;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.repository.ClubRepository;
import com.club.api.club_managment_api.repository.StudentClubRepository;
import com.club.api.club_managment_api.repository.StudentRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {
	
	private final AuthorityService authorityService;
	private final ClubRepository clubRepository;
	private final StudentClubRepository studentClubRepository;
	private final StudentRepository studentRepository;
	

	public AuthorityController(AuthorityService authorityService,ClubRepository clubRepository, StudentClubRepository studentClubRepository,StudentRepository studentRepository) {
		super();
		this.authorityService=authorityService;
		this.clubRepository = clubRepository;
		this.studentClubRepository = studentClubRepository;
		this.studentRepository=studentRepository;
	}
	

	@PostMapping("/{clubAdminId}/create")
	public ResponseEntity<EntityModel<Authority>> createAuthority(@Valid @RequestBody RequestAuthorityDto dto,@PathVariable int clubAdminId) {
		
		int clubId=dto.getClubId();
		Club c=clubRepository.findById(clubId).orElseThrow(()-> new resourceNotFoundException("club not foun clubId:"));
		
		if(c.getClubAdminId()!=clubAdminId) {
			throw new resourceNotFoundException("To assign authority to the club you must be club Admin");
		}
		else {
			long studentId=dto.getStudentId();
			if(!studentClubRepository.isApprovedMemberOfClub(studentId, clubId)) {
				throw new resourceNotFoundException("Student must be member of the group studentId:"+studentId);
			}
			else {
				 
				Authority result=authorityService.createAuthority(clubId, studentId,dto.getName(),dto.getStartDate(),dto.getEndDate());
				EntityModel<Authority> response=EntityModel.of(result,
						linkTo(methodOn(AuthorityController.class).retriveAuthorityById(result.getId())).withSelfRel());
				
				URI location=linkTo(methodOn(AuthorityController.class).retriveAuthorityById(result.getId())).toUri();
				
				return ResponseEntity.created(location).body(response);
				
			}
			
			
		}
		
		
		
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<Authority>> retriveAuthorityById(@PathVariable int id) {
	    Authority authority = authorityService.getAuthorityById(id) ;
	            

	    EntityModel<Authority> resource = EntityModel.of(
	            authority,
	            linkTo(methodOn(AuthorityController.class).retriveAuthorityById(id)).withSelfRel()
	            
	    );

	    return ResponseEntity.ok(resource);
	}
	
	@GetMapping("/clubs/{clubId}")
	public ResponseEntity<CollectionModel<EntityModel<Authority>>>  getAuthoritiesByClub(@PathVariable int clubId) {
		List<Authority> authorities=authorityService.getAuthoritiesByClub(clubId);
		List<EntityModel<Authority>> e=authorities.stream().map(a->EntityModel.of(a,
				linkTo(methodOn(AuthorityController.class).retriveAuthorityById(a.getId())).withSelfRel())).toList();
		
		CollectionModel<EntityModel<Authority>> response=CollectionModel.of(e,
				linkTo(methodOn(AuthorityController.class).getAuthoritiesByClub(clubId)).withSelfRel());
		return ResponseEntity.ok(response);
		
		
	}
	
	@GetMapping("/students/{studentId}")
	public ResponseEntity<CollectionModel<EntityModel<Authority>>> getAuthoritiesByStudent(@PathVariable long studentId) {
		List<Authority> authorities= authorityService.getAuthoritiesByStudent(studentId);
		List<EntityModel<Authority>> e=authorities.stream().map(a->EntityModel.of(a,
				linkTo(methodOn(AuthorityController.class).retriveAuthorityById(a.getId())).withSelfRel())).toList();
		
		CollectionModel<EntityModel<Authority>> response=CollectionModel.of(e,
				linkTo(methodOn(AuthorityController.class).getAuthoritiesByStudent(studentId)).withSelfRel());
		return ResponseEntity.ok(response);
		
		
	}
}


/*
 * 6. AuthorityController
 * 

 * 
 * DELETE /api/authorities/{id} → removeAuthority
 * 

 * 
 * 
 * GET /api/students/{studentId}/authorities → getAuthoritiesByStudent
 * 
 * PUT /api/authorities/{id} → updateAuthority
 */