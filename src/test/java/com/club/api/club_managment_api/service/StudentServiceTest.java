package com.club.api.club_managment_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.club.api.club_managment_api.Service.ClubService;
import com.club.api.club_managment_api.Service.StudentService;
import com.club.api.club_managment_api.Service.utilities.ClubMapper;
import com.club.api.club_managment_api.Service.utilities.StudentMapper;
import com.club.api.club_managment_api.dtos.student.StudentRequestDto;
import com.club.api.club_managment_api.dtos.student.StudentRequestDtoFull;
import com.club.api.club_managment_api.dtos.student.StudentResponseDto;
import com.club.api.club_managment_api.dtos.student.StudentResponseDtoFull;
import com.club.api.club_managment_api.exceptions.DuplicateResourceException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.EventRepository;
import com.club.api.club_managment_api.repository.StudentClubRepository;
import com.club.api.club_managment_api.repository.StudentRepository;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private ClubService clubService;
    @Mock
    private ClubMapper clubMapper;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private StudentClubRepository studentClubRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private StudentService studentService;

    private Student student;
    private StudentRequestDto requestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setId(1L);
        student.setFirstname("Eyob");
        student.setEmail("eyob@example.com");
        student.setPassword("pass");
        student.setRole(Role_enum.STUDENT);

        requestDto = new StudentRequestDto();
        requestDto.setEmail("eyob@example.com");
        requestDto.setFirstname("Eyob");
    }
    
    @Test
    void registerStudent_ShouldSaveAndReturnStudentResponse_WhenEmailIsUnique() {
        when(studentRepository.findByEmail(requestDto.getEmail())).thenReturn(Optional.empty());
        when(studentMapper.toStudentEntity(requestDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toStudentResponseDto(student)).thenReturn(new StudentResponseDto());

        StudentResponseDto response = studentService.registerStudent(requestDto);

        assertNotNull(response);
        verify(studentRepository).save(student);
    }

    @Test
    void registerStudent_ShouldThrowDuplicateResourceException_WhenEmailExists() {
        when(studentRepository.findByEmail(requestDto.getEmail())).thenReturn(Optional.of(student));

        assertThrows(DuplicateResourceException.class, () -> studentService.registerStudent(requestDto));
        verify(studentRepository, never()).save(any());
    }
    
    @Test
    void getStudentById_ShouldReturnDto_WhenStudentExists() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDtoFull(student)).thenReturn(new StudentResponseDtoFull());

        StudentResponseDtoFull result = studentService.getStudentById(1L);

        assertNotNull(result);
        verify(studentRepository).findById(1L);
    }

    @Test
    void getStudentById_ShouldThrowException_WhenNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(resourceNotFoundException.class, () -> studentService.getStudentById(1L));
    }

    @Test
    void updateStudent_ShouldUpdateFieldsAndSave() {
        StudentRequestDtoFull updateDto = new StudentRequestDtoFull();
        updateDto.setFirstname("Updated");
        updateDto.setPassword("newPass");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(passwordEncoder.encode("newPass")).thenReturn("encodedPass");
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toStudentResponseDtoFull(any(Student.class)))
            .thenReturn(new StudentResponseDtoFull());

        StudentResponseDtoFull result = studentService.updateStudent(updateDto, 1L);

        assertNotNull(result);
        assertEquals("encodedPass", student.getPassword());
        assertEquals("Updated", student.getFirstname());
    }
    @Test
    void deleteStudent_ShouldDelete_WhenStudentExists() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.deleteStudent(1L);

        verify(studentRepository).delete(student);
    }

    @Test
    void deleteStudent_ShouldThrowException_WhenNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(resourceNotFoundException.class, () -> studentService.deleteStudent(1L));
    }
    @Test
    void getStudentByEmail_ShouldReturnStudent_WhenFound() {
        when(studentRepository.findByEmail("eyob@example.com")).thenReturn(Optional.of(student));

        Student result = studentService.getStudentByEmail("eyob@example.com");

        assertNotNull(result);
        assertEquals("Eyob", result.getFirstname());
    }

    @Test
    void getStudentByEmail_ShouldThrowException_WhenNotFound() {
        when(studentRepository.findByEmail("no@exist.com")).thenReturn(Optional.empty());

        assertThrows(resourceNotFoundException.class, () -> studentService.getStudentByEmail("no@exist.com"));
    }


}
