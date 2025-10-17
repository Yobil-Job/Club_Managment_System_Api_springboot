package com.club.api.club_managment_api.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.club.api.club_managment_api.models.RefreshToken;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.repository.RefreshTokenRepository;
import com.club.api.club_managment_api.repository.StudentRepository;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final StudentRepository studentRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                               StudentRepository studentRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.studentRepository = studentRepository;
    }

    public RefreshToken createRefreshToken(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setStudent(student);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusSeconds(7 * 24 * 60 * 60)); // valid for 7 days

        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired. Please log in again.");
        }
        return token;
    }

    @Transactional
    public int deleteByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return refreshTokenRepository.deleteByStudent(student);
    }
}
