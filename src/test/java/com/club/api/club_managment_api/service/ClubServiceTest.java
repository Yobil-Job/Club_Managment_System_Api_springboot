package com.club.api.club_managment_api.service;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.club.api.club_managment_api.Service.ClubService;
import com.club.api.club_managment_api.Service.StudentService;
import com.club.api.club_managment_api.Service.utilities.ClubMapper;
import com.club.api.club_managment_api.Service.utilities.StudentMapper;
import com.club.api.club_managment_api.dtos.clubs.RequestClubDto;
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
}