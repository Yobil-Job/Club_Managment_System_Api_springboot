package com.club.api.club_managment_api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.api.club_managment_api.Service.FeeService;
import com.club.api.club_managment_api.dtos.Fees.RequestFeesDto;
import com.club.api.club_managment_api.dtos.Fees.RequestStatusUpdateDto;
import com.club.api.club_managment_api.models.Fee;

@RestController
@RequestMapping("/fees")
public class FeeController {
	
	private FeeService feeService;
	
	
	
	public FeeController(FeeService feeService) {
		this.feeService = feeService;
	}


	@PostMapping("/clubs/{clubId}/fees/students/{studentId}")
	public ResponseEntity<EntityModel<Fee>> recordFee(@PathVariable int clubId, @PathVariable long studentId,
			@RequestBody RequestFeesDto dto) {

		Fee f = feeService.recordFee(dto, studentId, clubId);
		EntityModel<Fee> e = EntityModel.of(f, linkTo(methodOn(FeeController.class).retriveFeeById(f.getId())).withSelfRel());
		URI location = linkTo(methodOn(FeeController.class).retriveFeeById(f.getId())).toUri();
		return ResponseEntity.created(location).body(e);
	}

	@GetMapping("/{id}")
	public Fee retriveFeeById(@PathVariable int id) {

		return feeService.getFeeById(id);
	}
	
	@GetMapping("/students/{studentId}")
	public List<Fee> retriveFeesByStudent(@PathVariable long studentId) {
		return feeService.getFeesByStudent(studentId);
		
	}
	
	@GetMapping("/clubs/{clubId}")
	public List<Fee> retriveFeesByClub(@PathVariable int clubId) {
		return feeService.getFeesByClub(clubId);
		
	}
	
	@PatchMapping("{feeId}/status")
	public Fee updateFeeStatus(@PathVariable int feeId,@RequestBody RequestStatusUpdateDto dto) {
		return feeService.updateFeeStatus(feeId, dto);
		
	}
	
	@GetMapping("/clubs/{clubId}/total")
	public double getTotalCollectedByClub(@PathVariable int clubId) {
	return	feeService.getTotalCollectedByClub(clubId);
		
	}
	
	
	
	
	
	
	

	
	

}
 


/*
 * 4. FeeController
 * 
 * 

 * 
 * PUT /api/fees/ → 
 * 
 * GET /api/students/{studentId}/fees/outstanding → 
 * 
 * GET /api/clubs/{clubId}/fees/total-collected → 
 */