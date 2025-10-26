package com.club.api.club_managment_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

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
import com.club.api.club_managment_api.dtos.clubs.RequestClubDtoFull;
import com.club.api.club_managment_api.dtos.clubs.ResponseClubDto;
import com.club.api.club_managment_api.exceptions.DuplicateResourceException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
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
    
    @Test
    void getClubById_ShouldReturnDto_WhenClubExists() {
        when(clubRepository.findById(1)).thenReturn(Optional.of(club));
        when(clubMapper.toResponseClubDto(club)).thenReturn(new ResponseClubDto());

        ResponseClubDto result = clubService.getClubById(1);

        assertNotNull(result);
        verify(clubRepository).findById(1);
    }

    @Test
    void getClubById_ShouldThrow_WhenNotFound() {
        when(clubRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(resourceNotFoundException.class, () -> clubService.getClubById(1));
    }

    @Test
    void getClubByTitle_ShouldReturnClub_WhenFound() {
        when(clubRepository.findByTitle("Tech Club")).thenReturn(club);
        Club result = clubService.getClubByTitle("Tech Club");
        assertEquals(club, result);
    }

    @Test
    void getClubByTitle_ShouldThrow_WhenNotFound() {
        when(clubRepository.findByTitle("Tech Club")).thenReturn(null);
        assertThrows(resourceNotFoundException.class, () -> clubService.getClubByTitle("Tech Club"));
    }

    @Test
    void updateClub_ShouldModifyFields() {
        RequestClubDtoFull updateDto = new RequestClubDtoFull();
        updateDto.setTitle("New Club");
        updateDto.setDescription("Updated desc");

        when(clubRepository.findById(1)).thenReturn(Optional.of(club));

        clubService.updateClub(1, updateDto);

        assertEquals("New Club", club.getTitle());
        assertEquals("Updated desc", club.getDescription());
        verify(clubRepository).save(club);
    }

    @Test
    void deleteClub_ShouldRemove_WhenExists() {
        when(clubRepository.findById(1)).thenReturn(Optional.of(club));
        clubService.deleteClub(1);
        verify(clubRepository).delete(club);
    }

    @Test
    void deleteClub_ShouldThrow_WhenNotFound() {
        when(clubRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(resourceNotFoundException.class, () -> clubService.deleteClub(1));
    }


}