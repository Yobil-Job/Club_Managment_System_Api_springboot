package com.club.api.club_managment_api.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.club.api.club_managment_api.dtos.news.RequestNewsDto;
import com.club.api.club_managment_api.dtos.news.RequestNewsUpdateDto;
import com.club.api.club_managment_api.exceptions.notAuthorizedUserException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.News;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.NewsRepository;
import com.club.api.club_managment_api.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NewsService {
	
	private final NewsRepository newsRepository;
	private final StudentRepository studentRepository;
	private final StudentService studentService;

	public NewsService(NewsRepository newsRepository, StudentRepository studentRepository, StudentService studentService) {
		this.newsRepository = newsRepository;
		this.studentRepository = studentRepository;
		this.studentService = studentService;
	}
	
	public News createNews(RequestNewsDto dto) {
		Long studentId = dto.getCreatedById();
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalArgumentException("Student not found"));
		
		if (!student.getRole().equals(Role_enum.SUPER_ADMIN)) {
			throw new notAuthorizedUserException("Only system admin can create news");
		}
		
		News news = new News();
		news.setTitle(dto.getTitle());
		news.setDescription(dto.getDescription());
		news.setCreatedBy(student);
		news.setCreatedAt(LocalDateTime.now());
		
		if (dto.getImages() != null && !dto.getImages().isEmpty()) {
			news.setImagesList(dto.getImages());
		}
		
		return newsRepository.save(news);
	}
	
	public News getNewsById(int newsId) {
		return newsRepository.findById(newsId)
				.orElseThrow(() -> new resourceNotFoundException("No news with id: " + newsId));
	}
	
	public List<News> getAllNews() {
		return newsRepository.findAllOrderByCreatedAtDesc();
	}
	
	public News updateNews(int newsId, RequestNewsUpdateDto dto, Long requesterId) {
		News news = newsRepository.findById(newsId)
				.orElseThrow(() -> new resourceNotFoundException("No news found with id: " + newsId));
		
		if (news.getCreatedBy().getId() != requesterId) {
			throw new notAuthorizedUserException("Only the creator of the news can edit it");
		}
		
		Student student = studentService.getStudentByIdEntity(requesterId);
		if (!student.getRole().equals(Role_enum.SUPER_ADMIN)) {
			throw new notAuthorizedUserException("Only system admin can update news");
		}
		
		if (dto.getTitle() != null && !dto.getTitle().trim().isEmpty()) {
			news.setTitle(dto.getTitle());
		}
		if (dto.getDescription() != null && !dto.getDescription().trim().isEmpty()) {
			news.setDescription(dto.getDescription());
		}
		if (dto.getImages() != null) {
			news.setImagesList(dto.getImages());
		}
		
		return newsRepository.save(news);
	}
	
	public void deleteNews(int newsId, Long requesterId) {
		News news = newsRepository.findById(newsId)
				.orElseThrow(() -> new resourceNotFoundException("News with id: " + newsId + " not found"));
		
		if (news.getCreatedBy().getId() != requesterId) {
			throw new notAuthorizedUserException("Only the creator of the news can delete it");
		}
		
		Student student = studentService.getStudentByIdEntity(requesterId);
		if (!student.getRole().equals(Role_enum.SUPER_ADMIN)) {
			throw new notAuthorizedUserException("Only system admin can delete news");
		}
		
		newsRepository.deleteById(newsId);
	}
}

