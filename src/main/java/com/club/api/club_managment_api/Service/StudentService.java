package com.club.api.club_managment_api.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.club.api.club_managment_api.Service.utilities.ClubMapper;
import com.club.api.club_managment_api.Service.utilities.StudentMapper;
import com.club.api.club_managment_api.dtos.clubs.ResponseClubDto;
import com.club.api.club_managment_api.dtos.student.StudentRequestDto;
import com.club.api.club_managment_api.dtos.student.StudentRequestDtoFull;
import com.club.api.club_managment_api.dtos.student.StudentResponseDto;
import com.club.api.club_managment_api.dtos.student.StudentResponseDtoFull;
import com.club.api.club_managment_api.exceptions.DuplicateResourceException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Authority;
import com.club.api.club_managment_api.models.Event;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.EventRepository;
import com.club.api.club_managment_api.repository.StudentClubRepository;
import com.club.api.club_managment_api.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	private final StudentMapper studentMapper;
	private final ClubService clubService;
	private final ClubMapper clubMapper;
	private final EventRepository eventRepository;
	private final StudentClubRepository studentClubRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	
	

	

	public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, ClubService clubService,
			ClubMapper clubMapper, EventRepository eventRepository,StudentClubRepository studentClubRepository
			,PasswordEncoder passwordEncoder) {
		super();
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
		this.clubService = clubService;
		this.clubMapper = clubMapper;
		this.eventRepository = eventRepository;
		this.studentClubRepository=studentClubRepository;
		this.passwordEncoder=passwordEncoder; 
		
	}


	public StudentResponseDto registerStudent(StudentRequestDto dto) {
		
		Optional<Student> existing = studentRepository.findByEmail(dto.getEmail());
		if (existing.isPresent()) {
	        throw new DuplicateResourceException("user with this email already found " + dto.getEmail());
	    }
		Student s = studentMapper.toStudentEntity(dto);
		s.setRole(Role_enum.STUDENT);
		Student saved=studentRepository.save(s);

		return studentMapper.toStudentResponseDto(saved);
	}
	
	public StudentResponseDtoFull updateStudent(StudentRequestDtoFull updated,long studentId) {
		Student s=getStudentByIdEntity(studentId); 
		
		//Student update=studentMapper.toStudentEntityFull(updated);
		
		
		  if(updated.getFirstname()!=null)s.setFirstname(updated.getFirstname());
		  if(updated.getLastname()!=null)s.setLastname(updated.getLastname());
		  //if(updated.getEmail()!=null)s.setEmail(updated.getEmail()); 
		  if(updated.getPassword()!=null)s.setPassword(passwordEncoder.encode(updated.getPassword()));
		  if(updated.getYearOfStay()!=null)s.setYearOfStay(updated.getYearOfStay()); 
		  if(updated.getDepartment()!=null)s.setDepartment(updated.getDepartment()); 
		  if(updated.getGender()!=null)s.setGender(updated.getGender());
		

		return studentMapper.toStudentResponseDtoFull(  studentRepository.save(s));
		 
		
	}

	public StudentResponseDtoFull getStudentById(long id) {

		//System.out.println(studentRepository.findById(id));
		return studentMapper.toStudentResponseDtoFull( studentRepository.findById(id)
				.orElseThrow(() -> new resourceNotFoundException("Student with id :" + id + " not found")));

	}
	

	public Student getStudentByIdEntity(long id) {

		return  studentRepository.findById(id)
				.orElseThrow(() -> new resourceNotFoundException("Student with id :" + id + " not found"));

	}

	public Student getStudentByEmail(String email) {

		Student s = studentRepository.findByEmail(email)
				.orElseThrow(()->new resourceNotFoundException("user not found "+email));
		if (s == null) {
			throw new resourceNotFoundException("Student with id :" + email + " not found");
		}
		return s;

	}
	
	public List<StudentResponseDto> getAllStudents() {
		return studentRepository.findAll().stream().map(s->studentMapper.toStudentResponseDto(s)).toList();
	}
	
	
	public void deleteStudent(long studentId) {
		Student s=getStudentByIdEntity(studentId);
		studentRepository.delete(s);
	}
	
	public List<ResponseClubDto> getClubs(long studentId) {
		Student s=getStudentByIdEntity(studentId);
	   return s.getClubs().stream().map(c->clubMapper.toResponseClubDto(c)).toList();
	}
	
	public StudentResponseDtoFull registerForEvent(long studentId,int eventId) {
		Student s=getStudentByIdEntity(studentId);
		
		
		Event e=eventRepository.findById(eventId).
				orElseThrow(()->new resourceNotFoundException("Event with id:"+eventId+" not foud"));
		s.getEventsAttended().add(e);
	    Student saved= studentRepository.save(s);
		return studentMapper.toStudentResponseDtoFull(saved);
		
	}
	
	public List<Event> getEventsAttended(long studentId) { 
		Student s=getStudentByIdEntity(studentId);
		return s.getEventsAttended();
		
	}
	
	public List<Authority> getAuthoritiesHeld(int studentId) {
		Student s=getStudentByIdEntity(studentId);
		return s.getAuthorities();
		
	}
	
	public boolean isMemberOfClub(long studentId,int clubId) {
		Student s=getStudentByIdEntity(studentId);
		return studentClubRepository.isMemberOfClub(studentId, clubId);
		
		//return s.getClubs().stream().anyMatch(club->club.getId()==clubId);
	}
	
	/*
	 * public StudentResponseDtoFull joinClub(long studentId,int clubId) { Student
	 * s=getStudentByIdEntity(studentId); if(!isMemberOfClub(studentId, clubId)) {
	 * throw new
	 * ResourceAlreadyFoundException("Student is already member of the club"
	 * +studentId); } else {
	 * 
	 * s.addClub(clubService.getClubByIdEntity(clubId)); return
	 * studentMapper.toStudentResponseDtoFull(studentRepository.save(s));} }
	 */
  
}




