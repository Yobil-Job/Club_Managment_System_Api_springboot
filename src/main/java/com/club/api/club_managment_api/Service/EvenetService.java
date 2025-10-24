package com.club.api.club_managment_api.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.club.api.club_managment_api.dtos.events.RequestEventDto;
import com.club.api.club_managment_api.dtos.events.RequestEventUpdateDto;
import com.club.api.club_managment_api.exceptions.notAuthorizedUserException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Event;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.AuthorityRepository;
import com.club.api.club_managment_api.repository.EventRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EvenetService {

	private final EventRepository eventRepository;
	private final StudentService studentService;
	private final ClubService clubService;
	private final AuthorityRepository authorityRepository;

	public EvenetService(EventRepository eventRepository, StudentService studentService, ClubService clubService,AuthorityRepository authorityRepository) {
		super();
		this.eventRepository = eventRepository;
		this.studentService = studentService;
		this.clubService = clubService;
		this.authorityRepository=authorityRepository;
	}

	public boolean checkPortalAdminAccess(int studentId, int clubId) {
		boolean haveAccess = false;
		Student s = studentService.getStudentByIdEntity(studentId);

		if (s.getRole().equals(Role_enum.ADMIN) && clubService.getMemberExistanseByStudentId(clubId, studentId)) {
			haveAccess = true;
		} else {
			haveAccess = false;
		}

		return haveAccess;

	}

	public Event createEvent(RequestEventDto dto, long studentId) {
	    
	    
	    boolean hasAuthority = authorityRepository.existsByStudentIdAndClubId(studentId, dto.getClubId());
	    if (!hasAuthority) {
	        throw new notAuthorizedUserException("Only authorities can post events");
	    }
 
	    
	    Event event = new Event();
	    Club club = clubService.getClubByIdEntity(dto.getClubId()); 
	    Student creator = studentService.getStudentByIdEntity(studentId);

	    event.setTitle(dto.getTitle());
	    event.setDescription(dto.getDescription());
	    event.setClub(club);
	    event.setCreatedBy(creator);
	    event.setStartAt(dto.getStartAt());
	    event.setEndAt(dto.getEndAt());
	    event.setLatitude(dto.getLatitude());
	    event.setLongitude(dto.getLongitude());
	   
	    if (dto.getAttendees() != null && !dto.getAttendees().isEmpty()) {
	        List<Student> attendeeList = dto.getAttendees().stream()
	            .map(id -> studentService.getStudentByIdEntity(id))
	            .collect(Collectors.toList());
	        event.setAttendees(attendeeList);
	    }

	    
	    return eventRepository.save(event);
	}
	public Event getEventById(int id) {
		return eventRepository.findById(id).orElseThrow(() -> new resourceNotFoundException("No event found: " + id));
	}

	public List<Event> getEventsByClub(int clubId) {
		Club c = clubService.getClubByIdEntity(clubId);
		return eventRepository.findByclub(c);
	}
	
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	public Event updateEvent(int eventId, RequestEventUpdateDto dto, long createdById) {

		boolean admins=false;
	    Event event = eventRepository.findById(eventId)
	            .orElseThrow(() -> new resourceNotFoundException("Event with id: " + eventId + " not found"));
	      Role_enum role= studentService.getStudentByIdEntity(createdById).getRole();
	      
	      if(role == Role_enum.ADMIN||role == Role_enum.SUPER_ADMIN) {
	    	  admins=true;
	      }

	    
	    if (event.getCreatedBy().getId() != createdById && !admins) {
	        throw new notAuthorizedUserException("Only the authority who created the event can edit it");
	    }

	    
	    if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
	        event.setTitle(dto.getTitle());
	    }

	    
	    if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
	        event.setDescription(dto.getDescription());
	    }

	   
	    if (dto.getAttendees() != null && !dto.getAttendees().isEmpty()) {
	        List<Student> updatedAttendees = dto.getAttendees().stream()
	                .map(id -> studentService.getStudentByIdEntity(id))
	                .collect(Collectors.toList());
	        event.setAttendees(updatedAttendees);
	    }

	   
	    return eventRepository.save(event);
	}


	public void deleteEvent(int eventId, long requesterId) {
		Event e = getEventById(eventId);
		boolean admins=false;
		
        Role_enum role= studentService.getStudentByIdEntity(requesterId).getRole();
	      
	      if(role == Role_enum.ADMIN||role == Role_enum.SUPER_ADMIN) {
	    	  admins=true;
	      }
		
		if(!(e.getCreatedBy().getId()==requesterId) && !admins) {
			
			throw new notAuthorizedUserException("Only the creator of the event can delete it");
		}
		else {
			eventRepository.delete(e);
		}
		
	}

	public Event addAttendee(int eventId, Student student, int requesterId, int clubId) {
		Event e = getEventById(eventId);
		if (!checkPortalAdminAccess(requesterId, clubId)) {
			throw new notAuthorizedUserException("Not Authorized: " + requesterId);
		} else {
			if (e.getAttendees().contains(student)) {
			    throw new IllegalStateException("Student already registered for event.");
			}
			e.getAttendees().add(student);
		}

		return eventRepository.save(e);

	}

	public Event removeAttendee(int eventId, int studentId, int requesterId, int clubId) {

		Event e = getEventById(eventId);
		Student s = studentService.getStudentByIdEntity(studentId);
		if (!checkPortalAdminAccess(requesterId, clubId)) {
			throw new notAuthorizedUserException("Not Authorized: " + requesterId);
		} else {
			e.getAttendees().remove(s);
		}
		return eventRepository.save(e);
	}

	public List<Student> getAttendees(int eventId) {
		Event e = getEventById(eventId);
		return e.getAttendees();
	}

}
