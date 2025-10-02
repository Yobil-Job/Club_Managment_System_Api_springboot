package com.club.api.club_managment_api.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.club.api.club_managment_api.exceptions.DuplicateResourceException;
import com.club.api.club_managment_api.exceptions.notAuthorizedUserException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Authority;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.AuthorityRepository;
import com.club.api.club_managment_api.repository.StudentClubRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthorityService {

	private final AuthorityRepository authorityRepository;
	private final ClubService clubService;
	private final StudentService studentService;
	private final StudentClubRepository studentClubRepository;

	public AuthorityService(AuthorityRepository authorityRepository, ClubService clubService,
			StudentService studentService,StudentClubRepository studentClubRepository) {
		super();
		this.authorityRepository = authorityRepository;
		this.clubService = clubService;
		this.studentService = studentService;
		this.studentClubRepository=studentClubRepository;
	}

	public Authority createAuthority(int clubId, long studentId, String roleName,LocalDate startDate,LocalDate endDate) {
		Authority a = new Authority();
		Club c = clubService.getClubByIdEntity(clubId);
		Student s = studentService.getStudentByIdEntity(studentId);
		
		if(c.getAuthorities().stream().anyMatch(auth->auth.getName().equalsIgnoreCase(roleName))
				&& !clubService.getMemberExistanseByStudentId(clubId, studentId)) { 
			throw new DuplicateResourceException("This authority already exist: "+
				                                  roleName+" or student is not member of the club:"+studentId);
		}
		
		a.setClub(c);
        a.setName(roleName);
		a.setStudent(s);
		a.setStartDate(startDate);
		a.setEndDate(endDate);
		s.setRole(Role_enum.SUPER_USER);
		//clubService.assignAuthority(clubId, studentId, a);
		Authority saved =authorityRepository.save(a);
		
		return 	saved;
		
		
           
	}
	
	public Authority creatClubPortalAdmin(int clubId, int studentId, String roleName,LocalDate startDate,LocalDate endDate) {
		
		Authority a = new Authority();
		Club c = clubService.getClubByIdEntity(clubId);
		Student s = studentService.getStudentByIdEntity(studentId);
		
		if(c.getAuthorities().stream().anyMatch(auth->auth.getName().equalsIgnoreCase(roleName))
				&& !clubService.getMemberExistanseByStudentId(clubId, studentId)) { 
			throw new DuplicateResourceException("This authority already exist: "+
				                                  roleName+" or student is not member of the club:"+studentId);
		}
		
		a.setClub(c);
        a.setName(roleName);
		a.setStudent(s);
		a.setStartDate(startDate);
		a.setEndDate(endDate);
		s.setRole(Role_enum.ADMIN);
		clubService.assignAuthority(clubId, studentId, a);
		
		return 	a;
	}

	public Authority getAuthorityById(int id) {
		return authorityRepository.findById(id)
				.orElseThrow(() -> new resourceNotFoundException("authority with id: " + id + "not found"));
	}

	public List<Authority> getAuthoritiesByClub(int clubId) {
		Club c = clubService.getClubByIdEntity(clubId);
		return authorityRepository.findByClub(c);
	}

	public List<Authority> getAuthoritiesByStudent(long studentId) {
		Student s = studentService.getStudentByIdEntity(studentId);
		return authorityRepository.findByStudent(s);
	}

	public List<Authority> getAllAuthorities() {
		return authorityRepository.findAll();
	}

	public void removeAuthority(int authorityId, int clubId,int clubAdminId) {
		Club c=clubService.getClubByIdEntity(clubId);
		if(c.getClubAdminId()!=clubAdminId) {
			throw new notAuthorizedUserException("You are not authorized to delete authority clubAdminId:"+clubAdminId);
		}
		else {
			Authority a = getAuthorityById(authorityId);
			clubService.removeAuthority(clubId, a) ;
		
			if(authorityRepository.findById(authorityId)!=null) {
				authorityRepository.delete(a);
			}
		}
		
		
		
			

	}

	public Authority updateAuthority(int authorityId, String newName, long StudentId, int clubId,LocalDate startDate,LocalDate endDate) {

		
		Authority a = getAuthorityById(authorityId);
		if(StudentId!=0) {
			Student s = studentService.getStudentByIdEntity(StudentId);
			if(!studentClubRepository.isApprovedMemberOfClub(StudentId, clubId)) {
				throw new resourceNotFoundException("The student should be approved member of the club studentId:"+StudentId);
			}
			else {
				a.setStudent(s);	
			}
			
			
			
		}
		
		if(newName!=null) {a.setName(newName);}
		if(startDate!=null) {a.setStartDate(startDate);}
		if(endDate!=null) {a.setEndDate(endDate);}
		
		 Authority saved=authorityRepository.save(a);
		return saved;

	}

}
