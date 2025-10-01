package com.club.api.club_managment_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {

}


/*
 * 6. AuthorityController
 * 
 * POST /api/clubs/{clubId}/authorities/{studentId} → assignAuthority
 * 
 * DELETE /api/authorities/{id} → removeAuthority
 * 
 * GET /api/authorities/{id} → getAuthorityById
 * 
 * GET /api/clubs/{clubId}/authorities → getAuthoritiesByClub
 * 
 * GET /api/students/{studentId}/authorities → getAuthoritiesByStudent
 * 
 * PUT /api/authorities/{id} → updateAuthority
 */