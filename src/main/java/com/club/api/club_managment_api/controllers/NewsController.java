package com.club.api.club_managment_api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.api.club_managment_api.Service.NewsService;
import com.club.api.club_managment_api.config.CustomUserDetails;
import com.club.api.club_managment_api.dtos.news.RequestNewsDto;
import com.club.api.club_managment_api.dtos.news.RequestNewsUpdateDto;
import com.club.api.club_managment_api.models.News;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {
	
	private final NewsService newsService;
	
	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<EntityModel<News>> createNews(@Valid @RequestBody RequestNewsDto dto, Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		dto.setCreatedById(userDetails.getId());
		
		News news = newsService.createNews(dto);
		EntityModel<News> entityModel = EntityModel.of(news,
				linkTo(methodOn(NewsController.class).getNewsById(news.getId())).withSelfRel(),
				linkTo(methodOn(NewsController.class).getAllNews()).withRel("allNews"),
				linkTo(methodOn(NewsController.class).deleteNews(news.getId(), authentication)).withRel("delete"));
		
		URI location = linkTo(methodOn(NewsController.class).getNewsById(news.getId())).toUri();
		return ResponseEntity.created(location).body(entityModel);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<EntityModel<News>> getNewsById(@PathVariable int id) {
		News news = newsService.getNewsById(id);
		EntityModel<News> entityModel = EntityModel.of(news,
				linkTo(methodOn(NewsController.class).getAllNews()).withRel("allNews"));
		return ResponseEntity.ok(entityModel);
	}
	
	@GetMapping("/all")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<CollectionModel<EntityModel<News>>> getAllNews() {
		List<News> newsList = newsService.getAllNews();
		List<EntityModel<News>> entityModels = newsList.stream()
				.map(news -> EntityModel.of(news,
						linkTo(methodOn(NewsController.class).getNewsById(news.getId())).withSelfRel()))
				.toList();
		CollectionModel<EntityModel<News>> collectionModel = CollectionModel.of(entityModels,
				linkTo(methodOn(NewsController.class).getAllNews()).withSelfRel());
		return ResponseEntity.ok(collectionModel);
	}
	
	@PatchMapping("/{id}/update")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<EntityModel<News>> updateNews(@PathVariable int id,
			@Valid @RequestBody RequestNewsUpdateDto dto, Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		News news = newsService.updateNews(id, dto, userDetails.getId());
		EntityModel<News> entityModel = EntityModel.of(news,
				linkTo(methodOn(NewsController.class).getNewsById(news.getId())).withSelfRel(),
				linkTo(methodOn(NewsController.class).getAllNews()).withRel("allNews"));
		return ResponseEntity.ok(entityModel);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<Void> deleteNews(@PathVariable int id, Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		newsService.deleteNews(id, userDetails.getId());
		return ResponseEntity.noContent().build();
	}
}

