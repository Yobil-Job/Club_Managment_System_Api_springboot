package com.club.api.club_managment_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.api.club_managment_api.Service.RefreshTokenService;
import com.club.api.club_managment_api.config.JwtUtil;
import com.club.api.club_managment_api.dtos.Login.LoginRequest;
import com.club.api.club_managment_api.dtos.Login.LoginResponse;
import com.club.api.club_managment_api.dtos.Login.RefreshTokenRequest;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.RefreshToken;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.repository.StudentRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private  final AuthenticationManager authenticationManager;
	private final StudentRepository studentRepository;
	private final JwtUtil jwtUtil;
	private final RefreshTokenService refreshTokenService;
	
	public AuthController(AuthenticationManager authenticationManager, StudentRepository studentRepository,
			JwtUtil jwtUtil,RefreshTokenService refreshTokenService) {
		super();
		this.authenticationManager = authenticationManager;
		this.studentRepository = studentRepository;
		this.jwtUtil = jwtUtil;
		this.refreshTokenService=refreshTokenService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

		Authentication authentication= authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	     Student student=studentRepository.findByEmail(request.getEmail()).orElseThrow(
	    		 ()->new resourceNotFoundException("invalid Credientional "+ request.getEmail()));
	     
	     String accessToken=jwtUtil.generateAccessTocken(student.getId(),student.getEmail(),student.getRole().name());
	     String refreshToken=jwtUtil.generateRefreshToken(student.getId(),student.getEmail(),student.getRole().name());
	     
	     return ResponseEntity.ok(new LoginResponse(accessToken,refreshToken,student.getRole().name()));
	}
	
	 @PostMapping("/refresh")
	    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshTokenRequest request) {
	        String refreshTokenStr = request.getRefreshToken();
	        
	        RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenStr)
	                .orElseThrow(() -> new resourceNotFoundException("Refresh token not found"));

	        refreshTokenService.verifyExpiration(refreshToken);

	        Student student = refreshToken.getStudent();

	       
	        String newAccessToken = jwtUtil.generateAccessTocken(student.getId(), student.getEmail(), student.getRole().name());

	       
	        refreshTokenService.deleteByStudentId(student.getId());
	        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(student.getId());

	        return ResponseEntity.ok(
	                new LoginResponse(newAccessToken, newRefreshToken.getToken(), student.getRole().name())
	        );
	    }

	
	@PostMapping("/logout")
	public ResponseEntity<Object> logout() {
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok().build();
	}

}
