package com.club.api.club_managment_api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.api.club_managment_api.Service.ClubService;
import com.club.api.club_managment_api.dtos.clubs.PendingRequestGetterDto;
import com.club.api.club_managment_api.dtos.clubs.RequestClubDto;
import com.club.api.club_managment_api.dtos.clubs.RequestClubDtoFull;
import com.club.api.club_managment_api.dtos.clubs.ResponseClubDto;
import com.club.api.club_managment_api.dtos.student.StudentResponseDto;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.repository.ClubRepository;
import com.club.api.club_managment_api.repository.StudentClubRepository;
import com.club.api.club_managment_api.repository.StudentRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/clubs")
public class ClubController {

	private final ClubService clubService;
	//private final ClubRepository clubRepository;
	//private final StudentRepository studentRepository;
	private final StudentClubRepository studentClubRepository;
	
	
	
	public ClubController(ClubService clubService, ClubRepository clubRepository, StudentRepository studentRepository,
			StudentClubRepository studentClubRepository) {
		super();
		this.clubService = clubService;
		//this.clubRepository = clubRepository;
		//this.studentRepository = studentRepository;
		this.studentClubRepository = studentClubRepository;
	}

	
	@PostMapping("create")
	public ResponseEntity<EntityModel<ResponseClubDto>>  createClub(@Valid @RequestBody RequestClubDto dto) {
		ResponseClubDto club= clubService.createClub(dto);
		EntityModel<ResponseClubDto> e=EntityModel.of(club,
				//linkTo(methodOn(ClubController.class).updateClubInfo()).withRel("update_club_info"),
				linkTo(methodOn(ClubController.class).retriveClubById(club.getId())).withSelfRel());
		URI location =linkTo(methodOn(ClubController.class).retriveClubById(club.getId())).toUri();
		return ResponseEntity.created(location).body(e);
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<ResponseClubDto>> retriveClubById(@PathVariable int id) {
		ResponseClubDto club = clubService.getClubById(id);
		EntityModel<ResponseClubDto> response=EntityModel.of(club,
				linkTo(methodOn(ClubController.class).retriveAllClubs()).withRel("All-clubs"));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("all-clubs")
	public ResponseEntity<CollectionModel<EntityModel<ResponseClubDto>>> retriveAllClubs() {
		
		List<EntityModel<ResponseClubDto>> e=clubService.getAllClubs().stream()
				.map(c->EntityModel.of(c,
						linkTo(methodOn(ClubController.class).retriveClubById(c.getId())).withSelfRel())).toList();
		
		CollectionModel<EntityModel<ResponseClubDto>> response=CollectionModel.of(e,
				linkTo(methodOn(ClubController.class).retriveAllClubs()).withSelfRel());
		return ResponseEntity.ok(response);
		
	}

	
	@PatchMapping("/{id}/update")
	  public void updateClubInfo(@PathVariable int id, @Valid @RequestBody RequestClubDtoFull dto) {
		clubService.updateClub(id, dto);
		
		  }
	
	@DeleteMapping("/{id}/delete")
	public void deleteClub(@PathVariable int id) {
		
		clubService.deleteClub(id);
	}
	
	
	 @GetMapping("/{clubId}/requests/pending")
	    public ResponseEntity<CollectionModel<EntityModel<PendingRequestGetterDto>>> getPendingRequests(@PathVariable Long clubId) {
		   
		   //used for  hateos collection link 
		    int clubIdInt = (int) (long) clubId;

	        List<Object[]> results = studentClubRepository.findPendingRequests(clubId);

	        List<Map<String, Object>> response = results.stream().map(row -> {
	            Map<String, Object> map = new HashMap<>();
	            map.put("studentId", row[0]);
	            map.put("firstName", row[1]);
	            map.put("lastName", row[2]);
	            map.put("email", row[3]);
	            map.put("status", row[4]);
	            return map;
	        }).toList();
	        
	        
	        List<PendingRequestGetterDto> ldto = new ArrayList<>();;
	        for(Map o:response) {
	        	PendingRequestGetterDto dto=new PendingRequestGetterDto();
	        	dto.setStudentId((long) o.get("studentId"));
	        	dto.setFirstName((String) o.get("firstName"));
	        	dto.setLastName((String) o.get("lastName"));
	        	dto.setEmail((String) o.get("email"));
	        	dto.setStatus((String) o.get("status"));
	        	ldto.add(dto);
	        }
	        
	        List<EntityModel<PendingRequestGetterDto>> e=ldto.stream().map(o->EntityModel.of(o,
	        		linkTo(methodOn(ClubController.class).approveRequest(clubId, o.getStudentId())).withRel("Approve"),
	        		linkTo(methodOn(ClubController.class).rejectRequest(clubId, o.getStudentId())).withRel("Reject"))).toList();
	        
	        CollectionModel<EntityModel<PendingRequestGetterDto>> c=CollectionModel.of(e,
	        		linkTo(methodOn(ClubController.class).getPendingRequests(clubId)).withSelfRel(),
	        		linkTo(methodOn(ClubController.class).retriveClubById(clubIdInt)).withRel("getClubinfo"));
	        

	        return ResponseEntity.ok(c);
	    }
	 
	 @PatchMapping("/{clubId}/requests/{studentId}/approve")
	    public ResponseEntity<String> approveRequest(@PathVariable Long clubId, @PathVariable Long studentId) {
	        studentClubRepository.updateRequestStatus(clubId, studentId, "APPROVED");
	        return ResponseEntity.ok("Request approved successfully");
	    }
	 
	 @PatchMapping("/{clubId}/requests/{studentId}/reject")
	    public ResponseEntity<String> rejectRequest(@PathVariable Long clubId, @PathVariable Long studentId) {
	        studentClubRepository.updateRequestStatus(clubId, studentId, "REJECTED");
	        return ResponseEntity.ok("Request rejected successfully");
	    }
	 
	 @GetMapping("/{clubId}/get-members")
	 public List<StudentResponseDto> getMembers(@PathVariable int clubId) {
		 return clubService.getMembers(clubId);
		 
	 }
	
	 @PatchMapping("/{clubId}/assign-clubAdmin/{memberId}")
	 public ResponseEntity<String> assignClubAdmin(@PathVariable long memberId,@PathVariable int clubId) {
		 if(!studentClubRepository.isApprovedMemberOfClub(memberId, clubId)) {
			 throw new resourceNotFoundException("To assigne as club admin the student must be approved member:"+memberId);
		 }else {
			 clubService.addClubAdmin(memberId, clubId);
			 return ResponseEntity.ok("Club Admin Assigned Succesfuly");
		 }
		 
	 }
	

	
	  
	 
}


/*
 * 3. ClubController

 * POST /api/clubs/{clubId}/members/{studentId} → 
 * 
 * DELETE /api/clubs/{clubId}/members/{studentId} → removeMember
 * 
 * GET /api/clubs/{clubId}/members → 
 * 
 * POST /api/clubs/{clubId}/authorities/{studentId} → assignAuthority
 * 
 * GET /api/clubs/{clubId}/authorities → getAuthorities
 * 
 * GET /api/clubs/{clubId}/events → getEvents
 * 
 * GET /api/clubs/{clubId}/announcements → getAnnouncements
 */