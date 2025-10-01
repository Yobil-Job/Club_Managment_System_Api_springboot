package com.club.api.club_managment_api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.club.api.club_managment_api.exceptions.notAuthorizedUserException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Event;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.EventRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EvenetService {

	private final EventRepository eventRepository;
	private final StudentService studentService;
	private final ClubService clubService;

	public EvenetService(EventRepository eventRepository, StudentService studentService, ClubService clubService) {
		super();
		this.eventRepository = eventRepository;
		this.studentService = studentService;
		this.clubService = clubService;
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

	public Event createEvent(Event event, int clubId, int createdById) {
		Event e = new Event();
		Club c = clubService.getClubByIdEntity(clubId);
		Student s = studentService.getStudentByIdEntity(createdById);
		if (!checkPortalAdminAccess(createdById, clubId)&&
				event.getClub().getId()!=clubId) {
			throw new notAuthorizedUserException("Not Authorized: " + createdById);
		} else { 
			e.setTitle(event.getTitle());
			e.setDescription(event.getDescription());
			e.setClub(c);
			e.setCreatedBy(s);
			e.setAttendees(event.getAttendees());
			e.setLatitude(event.getLatitude());
			e.setLongitude(event.getLongitude());
			e.setStartAt(event.getStartAt());
			e.setEndAt(event.getEndAt());
		}

		return eventRepository.save(e);

	}

	public Event getEventById(int id) {
		return eventRepository.findById(id).orElseThrow(() -> new resourceNotFoundException("No event found: " + id));
	}

	public List<Event> getEventsByClub(int clubId) {
		Club c = clubService.getClubByIdEntity(clubId);
		return eventRepository.findByclub(c);
	}

	public Event updateEvent(int eventId, Event updated, int createdById, int clubId) {
		Event e = getEventById(eventId);
		if (!checkPortalAdminAccess(createdById, clubId)) {
			throw new notAuthorizedUserException("Not Authorized: " + createdById);
		} else {
			e.setTitle(updated.getTitle());
			e.setDescription(updated.getDescription()); 
			e.setAttendees(updated.getAttendees());
			e.setLatitude(updated.getLatitude()); 
			e.setLongitude(updated.getLongitude());
			e.setStartAt(updated.getStartAt());
			e.setEndAt(updated.getEndAt());

		}
		
		return eventRepository.save(e);

	}

	public void deleteEvent(int eventId, int requesterId, int clubId) {
		Event e = getEventById(eventId);
		if (!checkPortalAdminAccess(requesterId, clubId)) {
			throw new notAuthorizedUserException("Not Authorized: " + requesterId);
		} else {
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
