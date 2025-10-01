package com.club.api.club_managment_api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.club.api.club_managment_api.exceptions.notAuthorizedUserException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Announcement;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.AnnouncementRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class AnnouncementService {
	
	private final AnnouncementRepository announcementRepository;
	private final StudentService studentService;
	private final ClubService clubService;


	public AnnouncementService(AnnouncementRepository announcementRepository, StudentService studentService,
			ClubService clubService) {
		super();
		this.announcementRepository = announcementRepository;
		this.studentService = studentService;
		this.clubService = clubService;
	}
	
	public boolean checkPortalAdminAccess(int studentId,int clubId) {
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
 
	public Announcement createAnnouncement(Announcement announcement, int clubId, int createdByStudentId) {
		
		
		Student s=studentService.getStudentByIdEntity(createdByStudentId);
		clubService.getClubById(clubId); 
		if( !checkPortalAdminAccess(createdByStudentId, clubId)&&
				                   announcement.getClub().getId()!=clubId) {
			throw new notAuthorizedUserException("you dont have authority to creat announcement: "+createdByStudentId);
		}
		Announcement a=new Announcement();
		a.setTitle(announcement.getTitle());
		a.setDescription(announcement.getDescription());
		a.setClub(announcement.getClub());
		a.setCreatedBy(s);
		clubService.addAnnouncement(a, clubId);
		return a;
	 
	}
	
	public Announcement getAnnouncementById(int announcementId) {
		
		return announcementRepository.findById(announcementId)
				.orElseThrow(()->new resourceNotFoundException("no announcement with id: "+announcementId));
	}
	
	public List<Announcement> getAnnouncementsByClub(int clubId) {
		
		return announcementRepository.findByclub(clubService.getClubByIdEntity(clubId));
				
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


