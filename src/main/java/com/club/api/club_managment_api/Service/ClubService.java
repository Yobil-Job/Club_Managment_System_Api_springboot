package com.club.api.club_managment_api.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.club.api.club_managment_api.Service.utilities.ClubMapper;
import com.club.api.club_managment_api.Service.utilities.StudentMapper;
import com.club.api.club_managment_api.dtos.clubs.RequestClubDto;
import com.club.api.club_managment_api.dtos.clubs.RequestClubDtoFull;
import com.club.api.club_managment_api.dtos.clubs.ResponseClubDto;
import com.club.api.club_managment_api.dtos.student.StudentResponseDto;
import com.club.api.club_managment_api.exceptions.DuplicateResourceException;
import com.club.api.club_managment_api.exceptions.ResourceAlreadyFoundException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Announcement;
import com.club.api.club_managment_api.models.Authority;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Event;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.ClubRepository;
import com.club.api.club_managment_api.repository.StudentClubRepository;
import com.club.api.club_managment_api.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClubService {

	private final ClubRepository clubRepository;
	private final StudentService studentService;
	private final ClubMapper clubMapper;
	private final StudentRepository studentRepository;
	private final StudentMapper studentMapper;
	private final StudentClubRepository studentClubRpository;
	
	
	
	
	

	public ClubService(ClubRepository clubRepository, @Lazy StudentService studentService, ClubMapper clubMapper,
			StudentClubRepository studentClubRpository,StudentRepository studentRepository
			,StudentMapper studentMapper) {
		super();
		this.clubRepository = clubRepository;
		this.studentService = studentService;
		this.clubMapper = clubMapper;
		this.studentRepository=studentRepository;
		this.studentClubRpository=studentClubRpository;
		this.studentMapper=studentMapper;
		
		
	}


	public ResponseClubDto createClub(RequestClubDto dto) {
		
		Club existingClub = clubRepository.findByTitle(dto.getTitle());
		if (existingClub != null) {
			throw new DuplicateResourceException("Club with this ttitle already exists " + dto.getTitle());
		}
		Club club=clubMapper.toClubEntity(dto); 
		
		return  clubMapper.toResponseClubDto(clubRepository.save(club));
		
	
	}

	public ResponseClubDto getClubById(int id) {
		Club c= clubRepository.findById(id)
				.orElseThrow(() -> new resourceNotFoundException("club wit id :" + id + " not found"));
		return clubMapper.toResponseClubDto(c);
	}
	
	public Club getClubByIdEntity(int id) {
		return clubRepository.findById(id)
				.orElseThrow(() -> new resourceNotFoundException("club wit id :" + id + " not found"));
		
	}

	public Club getClubByTitle(String title) {
		Club c = clubRepository.findByTitle(title);
		if (c == null) {
			throw new resourceNotFoundException("club with title :" + title + " not found");
		}

		return c;
	}

	public List<ResponseClubDto> getAllClubs() {
		List<Club> l= clubRepository.findAll();
		List<ResponseClubDto> r=new ArrayList<>();
		for(Club c: l) {
			r.add(clubMapper.toResponseClubDto(c));
		}
		return r;
	}

	public void updateClub(int clubId, RequestClubDtoFull updated) {
		Club c = getClubByIdEntity(clubId);
		
		if(updated.getTitle()!=null)c.setTitle(updated.getTitle());
		if(updated.getDescription()!=null)c.setDescription(updated.getDescription());
		if (updated.getLogo() != null)c.setLogo(updated.getLogo());
        if(updated.getClub_Type()!=null)c.setClub_Type(updated.getClub_Type());
		
         clubRepository.save(c);

	}

	public void deleteClub(int clubId) {

		Club c = getClubByIdEntity(clubId);
		clubRepository.delete(c);

	}

	public Club addMember(int clubId, int studentId) {
		Student s = studentService.getStudentByIdEntity(studentId);
		Club c = getClubByIdEntity(clubId);
		if (c.getMembers().stream().anyMatch(student -> student.getId() == studentId)) {
			throw new ResourceAlreadyFoundException("Student is already member of the club:" + studentId);
		}
		c.addMembers(s);

		return clubRepository.save(c);
	}

	public Club removeMember(int clubId, int studentId) {
		Student s = studentService.getStudentByIdEntity(studentId);
		Club c = getClubByIdEntity(clubId);
		if (c.getMembers().stream().noneMatch(student -> student.getId() == studentId)) {
			throw new resourceNotFoundException("Student with id " + studentId + " not found");
		}
		c.getMembers().remove(s);
		return clubRepository.save(c);

	}

	public boolean getMemberExistanseByStudentId(int clubId, long studentId) {
		studentService.getStudentById(studentId);
		Club c = getClubByIdEntity(clubId);
		return c.getMembers().stream().anyMatch(student -> student.getId() == studentId);

	}

	public List<StudentResponseDto> getMembers(int clubId) {
	    List<Long> memberIds = studentClubRpository.getMembersId(clubId);

	    List<Student>members= memberIds.stream()
	        .map(id -> studentRepository.findById(id)
	                .orElseThrow(() -> new resourceNotFoundException("Student with ID " + id + " not found")))
	        .toList();
	    
	  return  members.stream().map(stud->studentMapper.toStudentResponseDto(stud)).toList();
	}


	public Club assignAuthority(int clubId, long studentId, Authority authority) {
		if (!getMemberExistanseByStudentId(clubId, studentId)) {
			throw new resourceNotFoundException(
					"student must be member of a club to get Authority " + studentId + " " + clubId);
		}
		Club c = getClubByIdEntity(clubId);
		if (!c.getAuthorities().stream().noneMatch(autho -> autho.getName().equals(authority.getName())))
			throw new DuplicateResourceException("Authorty with the name:" + authority.getName() + " already exist");
		c.addAuthorities(authority);
 
		return clubRepository.save(c);
	}

	public boolean removeAuthority(int clubId, Authority authority) {
		Club c = getClubByIdEntity(clubId);
		if (c.getAuthorities().stream().noneMatch(auth -> auth.getId() == authority.getId()))
			throw new resourceNotFoundException("no authority matches in the club :" + authority.getId());

		return c.getAuthorities().remove(authority);

	}

	public List<Authority> getAuthorities(int clubId) {
		Club c = getClubByIdEntity(clubId);
		return c.getAuthorities();
	}
	
	public Club addEvents(Event event ,int clubId) {
		Club c=getClubByIdEntity(clubId);
		c.addEvents(event);
	  return 	clubRepository.save(c);
		
		
	}

	public List<Event> getEvents(int clubId) {
		Club c = getClubByIdEntity(clubId);
		return c.getEvents();

	}
	
	public Club addAnnouncement(Announcement announcement,int clubId) {
		Club c=getClubByIdEntity(clubId);
		c.addAnnouncement(announcement);
		return clubRepository.save(c);
		
	}

	public List<Announcement> getAnnouncements(int clubId) {
		Club c = getClubByIdEntity(clubId);
		return c.getAnnouncement();

	}
	
	public void addClubAdmin(long studentId,int clubId) {
		
		Club c=getClubByIdEntity(clubId);
		Student s=studentRepository.findById(studentId).orElseThrow(()->new resourceNotFoundException("no user found"));
		c.setClubAdminId(studentId);
		s.setRole(Role_enum.ADMIN);
		clubRepository.save(c);
		
		
	}

}
