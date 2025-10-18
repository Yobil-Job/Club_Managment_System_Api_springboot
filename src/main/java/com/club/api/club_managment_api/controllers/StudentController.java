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

import com.club.api.club_managment_api.Service.StudentService;
import com.club.api.club_managment_api.dtos.clubs.ResponseClubDto;
import com.club.api.club_managment_api.dtos.student.StudentRequestDto;
import com.club.api.club_managment_api.dtos.student.StudentRequestDtoFull;
import com.club.api.club_managment_api.dtos.student.StudentResponseDto;
import com.club.api.club_managment_api.dtos.student.StudentResponseDtoFull;
import com.club.api.club_managment_api.exceptions.ResourceAlreadyFoundException;
import com.club.api.club_managment_api.models.Event;
import com.club.api.club_managment_api.repository.StudentClubRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")   
public class StudentController {

	private final StudentService studentService;
	private final StudentClubRepository studentClubRepository;

	public StudentController(StudentService studentService, StudentClubRepository studentClubRepository) {
		super();
		this.studentService = studentService;
		this.studentClubRepository = studentClubRepository;
	}

	@PostMapping("/register")
	public ResponseEntity<EntityModel<StudentResponseDto>> registerStudentGlobal(
			@Valid @RequestBody StudentRequestDto dto) {

		StudentResponseDto savedStudent = studentService.registerStudent(dto);
		EntityModel<StudentResponseDto> responses = EntityModel.of(savedStudent,
				linkTo(methodOn(StudentController.class).retriveStudentById(savedStudent.getId())).withSelfRel(),
				linkTo(methodOn(StudentController.class).retriveAllStudents()).withRel("all-students"));
		URI location = linkTo(methodOn(StudentController.class).retriveStudentById(savedStudent.getId())).toUri();
		return ResponseEntity.created(location).body(responses);
	} 

	@GetMapping("/{id}")
	@PreAuthorize("#id == authentication.principal.id or hasRole('SUPER_ADMIN','ADMIN')")
	public ResponseEntity<EntityModel<StudentResponseDtoFull>> retriveStudentById(@PathVariable long id) {

		StudentRequestDtoFull dto = null;
		StudentResponseDtoFull student = studentService.getStudentById(id);
		EntityModel<StudentResponseDtoFull> response = EntityModel.of(student,
				linkTo(methodOn(StudentController.class).getClubs(student.getId())).withRel("clubs_joined"),
				linkTo(methodOn(StudentController.class).updateStudentInfo(student.getId(), dto))
						.withRel("update_info"));
		return ResponseEntity.ok(response);

	}

	@GetMapping("/allstudents")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<CollectionModel<EntityModel<StudentResponseDto>>> retriveAllStudents() {
 
		List<EntityModel<StudentResponseDto>> e = studentService.getAllStudents().stream()
				.map(student -> EntityModel.of(student,
						linkTo(methodOn(StudentController.class).retriveStudentById(student.getId())).withSelfRel(),
						linkTo(methodOn(StudentController.class).getEventsAttended(student.getId()))
								.withRel("events_Atended"),
						linkTo(methodOn(StudentController.class).deleteStudentById(student.getId())).withRel("delete")))
				.toList();

		CollectionModel<EntityModel<StudentResponseDto>> response = CollectionModel.of(e,
				linkTo(methodOn(StudentController.class).retriveAllStudents()).withSelfRel());
		return ResponseEntity.ok(response);

	}

	@PatchMapping("/{id}/update")
	@PreAuthorize("#id == authentication.principal.id")
	public StudentResponseDtoFull updateStudentInfo(@PathVariable long id,
			@Valid @RequestBody StudentRequestDtoFull dto) {
		return studentService.updateStudent(dto, id);

	}

	@DeleteMapping("/{id}/delete")
	@PreAuthorize("#id == authentication.principal.id or hasRole('SUPER_ADMIN')")
	public ResponseEntity<Void> deleteStudentById(@PathVariable long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.accepted().build();
	}

	@GetMapping("/{id}/getclubsJoined")
	@PreAuthorize("#id ==authentication.principal.id")
	public ResponseEntity<List<ResponseClubDto>> getClubs(@PathVariable long id) {

		List<ResponseClubDto> clubs = studentService.getClubs(id);

		return ResponseEntity.ok(clubs);
	}

	@GetMapping("/{id}/events")
	@PreAuthorize("#id == authentication.principal.id or hasAnyRole('SUPER_ADMIN','ADMIN')")
	public List<Event> getEventsAttended(@PathVariable long id) {

		return studentService.getEventsAttended(id);

	}

	@PostMapping("/{studentId}/clubs/{clubId}/request")
	@PreAuthorize("#studentId == authentication.principal.id")
	public ResponseEntity<String> requestToJoin(@PathVariable Long clubId, @PathVariable Long studentId) {
		int clubInt = (int) (long) clubId;

		if (studentService.isMemberOfClub(studentId, clubInt)) {
			throw new ResourceAlreadyFoundException("You have already requested to join the club" + clubId);
		} else {
			studentClubRepository.insertRequest(clubId, studentId, "PENDING");
			return ResponseEntity.ok("Request sent successfully!");
		}

	} 

}
