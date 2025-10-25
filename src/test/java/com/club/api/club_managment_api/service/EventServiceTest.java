package com.club.api.club_managment_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.club.api.club_managment_api.Service.ClubService;
import com.club.api.club_managment_api.Service.EvenetService;
import com.club.api.club_managment_api.Service.StudentService;
import com.club.api.club_managment_api.dtos.events.RequestEventDto;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Event;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.AuthorityRepository;
import com.club.api.club_managment_api.repository.EventRepository;

public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private AuthorityRepository authorityRepository;

    @Mock
    private ClubService clubService;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private EvenetService eventService;

    private Student student;
    private Club club;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setId(1L);
        student.setRole(Role_enum.STUDENT);

        club = new Club();
        club.setId(1);
        club.setTitle("Tech Club");
    }

    @Test
    void testCreateEvent_Success() {
        RequestEventDto dto = new RequestEventDto();
        dto.setClubId(1);
        dto.setTitle("Tech Talk on AI");
        dto.setDescription("An interactive session about Artificial Intelligence");
        dto.setStartAt(LocalDateTime.now().plusDays(1));
        dto.setEndAt(LocalDateTime.now().plusDays(1).plusHours(2));
        dto.setLatitude(9.03);
        dto.setLongitude(38.74);

        when(authorityRepository.existsByStudentIdAndClubId(1L, 1)).thenReturn(true);
        when(clubService.getClubByIdEntity(1)).thenReturn(club);
        when(studentService.getStudentByIdEntity(1L)).thenReturn(student);
        when(eventRepository.save(any(Event.class))).thenAnswer(i -> i.getArgument(0));

        Event event = eventService.createEvent(dto, 1L);

        assertNotNull(event);
        assertEquals("Tech Talk on AI", event.getTitle());
        assertEquals("Tech Club", event.getClub().getTitle());
        assertEquals(1L, event.getCreatedBy().getId());
    }

    @Test
    void testCreateEvent_FailsWhenNotAuthority() {
        RequestEventDto dto = new RequestEventDto();
        dto.setClubId(1);
        dto.setTitle("Unauthorized Event");
        dto.setDescription("Should fail because student is not an authority");

        when(authorityRepository.existsByStudentIdAndClubId(1L, 1)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventService.createEvent(dto, 1L);
        });

        assertTrue(exception.getMessage().contains("Only authorities can post events"));
    }
}
