package com.club.api.club_managment_api.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.club.api.club_managment_api.Service.ClubService;
import com.club.api.club_managment_api.Service.StudentService;
import com.club.api.club_managment_api.Service.utilities.ClubMapper;
import com.club.api.club_managment_api.Service.utilities.StudentMapper;
import com.club.api.club_managment_api.dtos.clubs.RequestClubDto;
import com.club.api.club_managment_api.dtos.clubs.ResponseClubDto;
import com.club.api.club_managment_api.exceptions.DuplicateResourceException;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.repository.ClubRepository;
import com.club.api.club_managment_api.repository.StudentClubRepository;
import com.club.api.club_managment_api.repository.StudentRepository;

class ClubServiceTest {

    @Mock
    private ClubRepository clubRepository;
    @Mock
    private StudentService studentService;
    @Mock
    private ClubMapper clubMapper;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private StudentClubRepository studentClubRepository;

    @InjectMocks
    private ClubService clubService;

    private Club club;
    private Student student;
    private RequestClubDto requestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        club = new Club();
        club.setId(1);
        club.setTitle("Tech Club");
        club.setDescription("Innovation group");
        club.setMembers(new ArrayList<>());
        club.setAuthorities(new ArrayList<>());
        club.setEvents(new ArrayList<>());
        club.setAnnouncement(new ArrayList<>());

        student = new Student();
        student.setId(1L);
        student.setFirstname("Eyob");

        requestDto = new RequestClubDto();
        requestDto.setTitle("Tech Club");
        requestDto.setDescription("Innovation group");
    }
    
    @Test
    void createClub_ShouldSave_WhenTitleIsUnique() {
        when(clubRepository.findByTitle("Tech Club")).thenReturn(null);
        when(clubMapper.toClubEntity(requestDto)).thenReturn(club);
        when(clubRepository.save(club)).thenReturn(club);
        when(clubMapper.toResponseClubDto(club)).thenReturn(new ResponseClubDto());

        ResponseClubDto result = clubService.createClub(requestDto);

        assertNotNull(result);
        verify(clubRepository).save(club);
    }

    @Test
    void createClub_ShouldThrowException_WhenTitleExists() {
        when(clubRepository.findByTitle("Tech Club")).thenReturn(club);

        assertThrows(DuplicateResourceException.class, () -> clubService.createClub(requestDto));
    }

}