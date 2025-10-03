package com.club.api.club_managment_api.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.club.api.club_managment_api.dtos.announcement.RequestAnnouncementDto;
import com.club.api.club_managment_api.exceptions.notAuthorizedUserException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Announcement;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.AnnouncementRepository;
import com.club.api.club_managment_api.repository.AuthorityRepository;
import com.club.api.club_managment_api.repository.ClubRepository;
import com.club.api.club_managment_api.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class AnnouncementService {
	
	
	private final AnnouncementRepository announcementRepository;
	private final StudentService studentService;
	private final ClubService clubService;
	private final AuthorityRepository authorityRepository;
	private final StudentRepository studentRepository;
	private final ClubRepository clubRepository;


	public AnnouncementService(AnnouncementRepository announcementRepository, StudentService studentService,
			ClubService clubService,AuthorityRepository authorityRepository,StudentRepository studentRepository,ClubRepository clubRepository) {
		super();
		this.announcementRepository = announcementRepository;
		this.studentService = studentService;
		this.clubService = clubService;
		this.authorityRepository=authorityRepository;
		this.studentRepository=studentRepository;
		this.clubRepository=clubRepository;
		
	}
	
	public boolean checkPortalAdminAccess(long studentId,int clubId) {
		boolean haveAccess=false;
		Student s=studentService.getStudentByIdEntity(studentId);
		;
		if(s.getRole().equals(Role_enum.ADMIN)&&clubService.getMemberExistanseByStudentId(clubId, studentId)) {
			haveAccess=true;
		}
		else {
			haveAccess=false;
		}
		
		
		return haveAccess;
		
	}
 
	public Announcement createAnnouncement(RequestAnnouncementDto dto) {
		
		
		Long studentId = dto.getCreatedById();
        Integer clubId = dto.getClubId();
        boolean hasAuthority=authorityRepository.existsByStudentIdAndClubId(studentId, clubId);
        

        if (!hasAuthority) {
            throw new IllegalArgumentException("You must have authority in this club to create announcements.");
        }
        else {
        	 Student student = studentRepository.findById(studentId)
                     .orElseThrow(() -> new IllegalArgumentException("Student not found"));
             Club club = clubRepository.findById(clubId)
                     .orElseThrow(() -> new IllegalArgumentException("Club not found"));

             
             Announcement announcement = new Announcement();
             announcement.setTitle(dto.getTitle());
             announcement.setDescription(dto.getDescription());
             announcement.setClub(club);
             announcement.setCreatedBy(student);
             announcement.setCreatedAt(LocalDateTime.now());

             return announcementRepository.save(announcement);
        }
        
        
        
        
		/*
		 * Student s=studentService.getStudentByIdEntity(dto.getCreatedById()); Club
		 * c=clubService.getClubByIdEntity(dto.getClubId()); List<Authority>
		 * savedAuthority=authorityRepository.findByClub(c);
		 * savedAuthority.stream().map(author->author.getStudent().getId()) if( ) {
		 * throw new
		 * notAuthorizedUserException("you dont have authority to creat announcement: "
		 * +dto.getCreatedById()); } Announcement a=new Announcement();
		 * a.setTitle(dto.getTitle()); a.setDescription(dto.getDescription());
		 * a.setClub(c); a.setCreatedBy(s); Announcement
		 * saved=announcementRepository.save(a); clubService.addAnnouncement(a,
		 * dto.getClubId()); return saved;
		 */
	 
	}
	
	public Announcement getAnnouncementById(int announcementId) {
		
		return announcementRepository.findById(announcementId)
				.orElseThrow(()->new resourceNotFoundException("no announcement with id: "+announcementId));
	}
	
	public List<Announcement> getAnnouncementsByClub(int clubId) {
		
		return announcementRepository.findByclub(clubService.getClubByIdEntity(clubId));
				
	}
	
	public List<Announcement> getAllAnnouncemnt() {
	  return announcementRepository.findAll();
	}
	
	public Announcement updateAnnouncement(int announcementId, Announcement updated,int studentId,int clubId) {
		if(!checkPortalAdminAccess(studentId, clubId)) {
			throw new notAuthorizedUserException("Not authorized s:"+studentId);
		}
		
		Announcement a=getAnnouncementById(announcementId);
		a.setTitle(updated.getTitle());
		a.setDescription(updated.getDescription());
		return announcementRepository.save(a);
		
	} 
	
	public void deleteAnnouncement(int announcementId, int requesterId,int clubId) {
		if(!checkPortalAdminAccess(requesterId, clubId)) 
			{
				throw new notAuthorizedUserException("Not authorized s:"+requesterId);
			}
		
		Announcement a=getAnnouncementById(announcementId);
		announcementRepository.delete(a);
		
	}
	
	
	

}


