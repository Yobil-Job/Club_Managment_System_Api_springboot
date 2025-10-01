package com.club.api.club_managment_api.Service.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.club.api.club_managment_api.dtos.clubs.RequestClubDto;
import com.club.api.club_managment_api.dtos.clubs.ResponseClubDto;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Authority;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.repository.AuthorityRepository;
import com.club.api.club_managment_api.repository.StudentClubRepository;
import com.club.api.club_managment_api.repository.StudentRepository;

@Component
public class ClubMapper {
	
	private final StudentRepository studentRepository;
	private final AuthorityRepository authorityRepository;
	private final StudentClubRepository studentClubRepository;
	
	
	
	
	public ClubMapper(StudentRepository studentRepository, AuthorityRepository authorityRepository,
			StudentClubRepository studentClubRepository) {
		super();
		this.studentRepository = studentRepository;
		this.authorityRepository = authorityRepository;
		this.studentClubRepository = studentClubRepository;
	}


	public ResponseClubDto toResponseClubDto(Club c) {
		ResponseClubDto d=new ResponseClubDto();
		d.setId(c.getId());
		d.setTitle(c.getTitle());
		d.setClub_Type(c.getClub_Type());
		d.setDescription(c.getDescription());
		d.setLogo(c.getLogo());
		//d.setMembers(c.getMembers());
		d.setNumberOfMmbers(studentClubRepository.countApprovedMembersByClubId(c.getId()));
		
		
		return d;
		
	}
	
	public Club toClubEntity(RequestClubDto dto) {
		Club c = new Club();
		c.setTitle(dto.getTitle());
		c.setClub_Type(dto.getClub_Type());
		c.setDescription(dto.getDescription());
		c.setLogo(dto.getLogo());

		List<Student> students = new ArrayList<>();

		/*
		 * if (dto.getMemberIds() != null) { students = dto.getMemberIds().stream()
		 * .map(id -> studentRepository.findById(id) .orElseThrow(() -> new
		 * resourceNotFoundException("Student not found with id: " + id))) .toList(); }
		 * 
		 * students.forEach(c::addMembers);
		 */


		List<Authority> a = new ArrayList<>();

		if (dto.getAuthorityIds() != null) {

			a = dto.getAuthorityIds().stream()
					.map(id -> authorityRepository.findById(id)
							.orElseThrow(() -> new resourceNotFoundException("Authority not found with id: " + id)))
					.toList();

		}

		c.setAuthorities(a);

		return c;
	}
}
