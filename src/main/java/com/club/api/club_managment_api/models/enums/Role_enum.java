package com.club.api.club_managment_api.models.enums;

public enum Role_enum {
	STUDENT, // regular user who registers
	SUPER_USER,// club admin (promoted by SUPER_ADMIN)
	SUPER_ADMIN,// club-level authority (vice-president, secretary, etc.)
	ADMIN// master admin for the whole system

}
