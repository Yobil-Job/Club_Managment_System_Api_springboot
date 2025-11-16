package com.club.api.club_managment_api.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.club.api.club_managment_api.dtos.announcement.RequestAnnouncementDto;
import com.club.api.club_managment_api.dtos.announcement.RequestAnnouncementUpdateDto;
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
        boolean hasAdminAccess = checkPortalAdminAccess(studentId, clubId);
        

        if (!hasAuthority && !hasAdminAccess) {
            throw new IllegalArgumentException("You must have authority in this club or be a club admin to create announcements.");
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
	
	public Announcement updateAnnouncement(int announcementId, RequestAnnouncementUpdateDto dto) {
		 
		Announcement a=announcementRepository.findById(announcementId).orElseThrow(
			()->	new resourceNotFoundException("No announcement found with id :"+announcementId));
		
		if(a.getCreatedBy().getId()!=dto.getCreatedById()) {
			throw new notAuthorizedUserException("Only authorty who created the announcement can edit it");
		}else {
			if(dto.getTitle()!=null) {a.setTitle(dto.getTitle());}
			if(dto.getDescription()!=null) {a.setDescription(dto.getDescription());}
			if(dto.getClubId()!=0) {
				if(!authorityRepository.existsByStudentIdAndClubId(dto.getCreatedById(), dto.getClubId())) {
					throw new notAuthorizedUserException("Wring club id :"+dto.getClubId());
				}else {
					a.setClub(clubRepository.findById(dto.getClubId()).orElseThrow(()->new resourceNotFoundException("no club witth id :"+dto.getClubId()+" found")));
				}
			}
			
		}
		Announcement saved=announcementRepository.save(a);
		return saved;
	} 
	
	public String deleteAnnouncement(int announcementId, long requesterId) {
		
		Announcement a=announcementRepository.findById(announcementId).orElseThrow(
				()->new resourceNotFoundException("Announcement with id :"+announcementId+" not found"));
		if(a.getCreatedBy().getId()!=requesterId) {
			throw new notAuthorizedUserException("Only the creater of the announcement can delete the announcement");
		}
		else {
			announcementRepository.deleteById(announcementId);
			return "deleted succesfully";
		}
		
		
	}
	
	
	

}


