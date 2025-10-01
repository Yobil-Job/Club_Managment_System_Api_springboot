package com.club.api.club_managment_api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.club.api.club_managment_api.exceptions.resourceNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class StudentClubRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertRequest(Long clubId, Long studentId, String status) {
        entityManager.createNativeQuery(
            "INSERT INTO student_club (club_id, student_id, status) VALUES (?, ?, ?)"
        )
        .setParameter(1, clubId)
        .setParameter(2, studentId)
        .setParameter(3, status)
        .executeUpdate();
    }

    @Transactional
    public void updateRequestStatus(Long clubId, Long studentId, String status) {
        entityManager.createNativeQuery(
            "UPDATE student_club SET status = ? WHERE club_id = ? AND student_id = ?"
        )
        .setParameter(1, status)
        .setParameter(2, clubId)
        .setParameter(3, studentId)
        .executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> findPendingRequests(Long clubId) {
        return entityManager.createNativeQuery(
            "SELECT s.student_id, s.first_name, s.last_name, s.email, sc.status " +
            "FROM student_club sc " +
            "JOIN students s ON s.student_id = sc.student_id " +
            "WHERE sc.club_id = ? AND sc.status = 'PENDING'"
        )
        .setParameter(1, clubId)
        .getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public boolean isMemberOfClub(long studentId, int clubId) {
        try {
            Object result = entityManager.createNativeQuery(
                    "SELECT sc.status " +
                    "FROM student_club sc " +
                    "WHERE sc.club_id = ? AND sc.student_id = ?"
                )
                .setParameter(1, clubId)
                .setParameter(2, studentId)
                .getSingleResult();

            if(result==null) {
            	return false;
            }
            String status = result.toString().trim().toUpperCase();

            return status.equals("APPROVED") || status.equals("PENDING");
        } catch (jakarta.persistence.NoResultException e) {
            // No matching record found
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    public boolean isApprovedMemberOfClub(long studentId, int clubId) {
        try {
            Object result = entityManager.createNativeQuery(
                    "SELECT sc.status " +
                    "FROM student_club sc " +
                    "WHERE sc.club_id = ? AND sc.student_id = ?"
                )
                .setParameter(1, clubId)
                .setParameter(2, studentId)
                .getSingleResult();

            if(result==null) {
            	return false;
            }
            String status = result.toString().trim().toUpperCase();

            return status.equals("APPROVED");
        } catch (jakarta.persistence.NoResultException e) {
            // No matching record found
            return false;
        }
    }
    
    public int countApprovedMembersByClubId(int clubId) {
        Object result = entityManager.createNativeQuery(
            "SELECT COUNT(*) FROM student_club sc WHERE sc.club_id = ? AND sc.status = 'APPROVED'"
        )
        .setParameter(1, clubId)
        .getSingleResult();

        
        return ((Number) result).intValue();
    }
    
    @SuppressWarnings("unchecked")
    public List<Long> getMembersId(int clubId) {
        List<Long> members = entityManager.createNativeQuery(
                "SELECT sc.student_id FROM student_club sc WHERE sc.club_id = ? AND sc.status = 'APPROVED'"
            )
            .setParameter(1, clubId)
            .getResultList();

        if (members.isEmpty()) {
            throw new resourceNotFoundException("This club has no approved members yet");
        }

        return members;
    }

 
}
