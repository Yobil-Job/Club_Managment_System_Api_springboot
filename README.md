# Club Management System API

**Enterprise-Grade RESTful API for University Club Administration**

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-6DB33F?style=flat&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6+-C71A36?style=flat&logo=apache-maven)](https://maven.apache.org/)
[![API Documentation](https://img.shields.io/badge/API-Swagger/OpenAPI-85EA2D?style=flat&logo=swagger)](https://swagger.io/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

---

## 📋 Table of Contents

- [Overview](#overview)
- [Executive Summary](#executive-summary)
- [Technical Architecture](#technical-architecture)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Project Structure](#project-structure)
- [Core Features](#core-features)
- [API Specifications](#api-specifications)
- [Security Implementation](#security-implementation)
- [Database Schema](#database-schema)
- [Testing & Quality Assurance](#testing--quality-assurance)
- [Deployment](#deployment)
- [Troubleshooting](#troubleshooting)
- [Future Roadmap](#future-roadmap)
- [Contributing](#contributing)

---

## Overview

**Club Management System API** is a robust, production-ready Spring Boot RESTful API designed for comprehensive university club administration. The system provides secure, scalable, and maintainable solutions for managing clubs, members, authorities, events, announcements, and organizational hierarchies.

### Key Value Propositions

- **Enterprise-Grade Architecture**: Multi-layered design with clear separation of concerns
- **Role-Based Access Control (RBAC)**: Granular permission management with JWT authentication
- **Scalable Design**: Optimized for high-concurrency environments with caching strategies
- **API-First Development**: Comprehensive OpenAPI/Swagger documentation
- **Production-Ready**: Implements industry best practices for security and performance

---

## Executive Summary

### Use Cases

This system serves multiple stakeholder groups:

| Stakeholder | Primary Functions |
|-------------|------------------|
| **System Administrators** | Full system control, user management, audit logs |
| **Club Administrators** | Club creation/update, member recruitment, authority assignment |
| **Club Authority Members** | Event creation, announcements, member coordination |
| **Students/Members** | Event registration, announcement viewing, participation tracking |

### Business Problem Solved

Universities require centralized management of diverse student organizations. This system eliminates fragmented spreadsheets and email communications by providing a unified platform for:

- Organizational structure management (clubs → authorities → members)
- Communication channels (announcements, event notifications)
- Resource coordination (event planning, attendance tracking)
- Administrative oversight (audit trails, compliance reporting)

---

## Technical Architecture

### System Design Pattern

**Layered Architecture** - Industry-standard three-tier design pattern:

```
┌─────────────────────────────────────┐
│  Presentation Layer (Controllers)   │  ← HTTP REST Endpoints
│  @RestController, Request/Response  │
├───��─────────────────────────────────┤
│   Business Logic Layer (Services)   │  ← Domain Logic, Validation
│  Transaction Management, Caching    │
├─────────────────────────────────────┤
│  Data Access Layer (Repositories)   │  ← JPA, Database Queries
│  Spring Data JPA, Hibernate ORM     │
├─────────────────────────────────────┤
│  Persistence Layer (Database)       │  ← H2/MySQL/PostgreSQL
│  Relational Data Storage            │
└─────────────────────────────────────┘
```

### Technology Stack

| Component | Technology | Version | Purpose |
|-----------|-----------|---------|---------|
| **Language** | Java | 21 (LTS) | Modern language features, enhanced performance |
| **Framework** | Spring Boot | 3.5.5 | Auto-configuration, embedded web server |
| **Build Tool** | Maven | 3.6+ | Dependency management, build automation |
| **ORM** | Hibernate | 6.4+ | Object-relational mapping via Spring Data JPA |
| **Security** | Spring Security + JWT | 0.11.5 | Stateless authentication, token-based authorization |
| **API Documentation** | SpringDoc OpenAPI | 2.8.13 | Automatic Swagger/OpenAPI generation |
| **Validation** | Jakarta Bean Validation | Latest | Input validation, constraint checking |
| **Testing** | JUnit 5 + Mockito | Latest | Unit and integration testing |
| **Database** | H2 (dev) / MySQL/PostgreSQL (prod) | Various | RDBMS with full ACID compliance |
| **Build Automation** | Spring Boot Maven Plugin | 3.5.5 | WAR/JAR packaging, cloud deployment |

### Core Dependencies

```xml
<!-- Core Framework -->
spring-boot-starter-web              <!-- REST APIs, MVC -->
spring-boot-starter-data-jpa         <!-- Database access -->
spring-boot-starter-security         <!-- Authentication/Authorization -->
spring-boot-starter-validation       <!-- Input validation -->

<!-- Advanced Features -->
spring-boot-starter-hateoas          <!-- Hypermedia links in responses -->
jjwt-api/impl/jackson (0.11.5)       <!-- JWT token management -->
springdoc-openapi-starter-webmvc-ui  <!-- Swagger/OpenAPI UI -->

<!-- Testing -->
junit-jupiter                        <!-- JUnit 5 framework -->
mockito-core                         <!-- Mocking for unit tests -->
spring-boot-starter-test            <!-- Spring testing utilities -->
```

---

## Prerequisites

### System Requirements

| Component | Requirement | Notes |
|-----------|------------|-------|
| **JDK** | 21+ | Recommended: Java 21 LTS |
| **Maven** | 3.6+ | For build management |
| **Database** | H2/MySQL/PostgreSQL | H2 for local development |
| **RAM** | 2GB minimum | 4GB recommended for production |
| **Disk Space** | 500MB | Includes dependencies and database |

### Development Tools (Optional)

- **IDE**: IntelliJ IDEA, Eclipse, or VS Code
- **REST Client**: Postman, Insomnia, or Thunder Client
- **Version Control**: Git
- **Database GUI**: MySQL Workbench, pgAdmin (optional)

### Installation Verification

```bash
# Verify Java installation
java -version
# Expected: openjdk version "21.x.x"

# Verify Maven installation
mvn -version
# Expected: Apache Maven 3.6.x
```

---

## Installation & Setup

### Step 1: Clone Repository

```bash
git clone https://github.com/Yobil-Job/Club_Managment_System_Api_springboot.git
cd Club_Managment_System_Api_springboot
```

### Step 2: Verify Project Structure

```bash
# Ensure Maven project structure exists
ls -la
# Should show: pom.xml, src/, .mvn/ (optional), mvnw (optional)

# Install Maven dependencies
mvn clean install
# Downloads ~150MB of dependencies
```

### Step 3: Configure Database

#### For Development (H2 - Embedded)

**No configuration needed!** H2 runs in-memory by default.

Access H2 Console:
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave blank)
```

#### For Production (MySQL)

Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/club_management_db
spring.datasource.username=root
spring.datasource.password=your-secure-password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
```

**Create Database:**

```sql
CREATE DATABASE club_management_db;
USE club_management_db;
```

#### For Production (PostgreSQL)

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/club_management_db
spring.datasource.username=postgres
spring.datasource.password=your-secure-password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL10Dialect
```

### Step 4: Run Application

#### Using Maven (Development)

```bash
mvn spring-boot:run
```

#### Using JAR Package (Production)

```bash
# Build executable JAR
mvn clean package

# Run JAR
java -jar target/club_managment_api-0.0.1-SNAPSHOT.jar
```

#### Using IDE

- IntelliJ IDEA: Right-click `ClubManagmentApiApplication.java` → Run
- VS Code: Install "Debugger for Java" extension

### Step 5: Verify Installation

```bash
# Test API health endpoint
curl http://localhost:8080/actuator/health

# Expected response:
# {"status":"UP"}

# Access Swagger UI
open http://localhost:8080/swagger-ui.html
```

---

## Configuration

### Application Properties

**Location**: `src/main/resources/application.properties`

```properties
# ===== Server Configuration =====
server.port=8080
server.servlet.context-path=/api

# ===== Application Metadata =====
spring.application.name=club_managment_api
app.version=0.0.1

# ===== JPA/Hibernate Configuration =====
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.defer-datasource-initialization=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
```

### Security Configuration

**Location**: `src/main/java/com/club/api/club_managment_api/config/SecurityConfig.java`

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()                          // Stateless API
            .cors().and()                              // Cross-origin support
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .sessionManagement()
                .sessionCreationPolicy(STATELESS);     // Stateless authentication
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();            // Secure password hashing
    }
}
```

### JWT Configuration

Key security parameters in `application.properties`:

```properties
# JWT Token Configuration
jwt.secret=your-secret-key-minimum-256-bits-for-hs256-algorithm
jwt.expiration=3600000                      # 1 hour in milliseconds
jwt.refresh-expiration=604800000            # 7 days in milliseconds
```

**Security Note**: Generate strong secrets using:

```bash
# Generate 32-character secure key
openssl rand -base64 32
```

---

## Project Structure

### Directory Hierarchy

```
club_managment_api/
├── src/main/java/com/club/api/club_managment_api/
│   ├── ClubManagmentApiApplication.java         # Spring Boot entry point
│   │
│   ├── controllers/                             # HTTP Request Handlers
│   │   ├── ClubController.java
│   │   ├── AuthorityController.java
│   │   ├── StudentController.java
│   │   ├── AnnouncementController.java
│   │   ├── EventController.java
│   │   ├── NewsController.java
│   │   └── FeeController.java
│   │
│   ├── Service/                                 # Business Logic Layer
│   │   ├── ClubService.java
│   │   ├── AuthorityService.java
│   │   ├── StudentService.java
│   │   ├── AnnouncementService.java
│   │   ├── EvenetService.java
│   │   ├── NewsService.java
│   │   ├── FeeService.java
│   │   ├── RefreshTokenService.java
│   │   └── utilities/                           # Helper utilities
│   │       ├── UploadUtil.java
│   │       ├── ValidationUtil.java
│   │       └── TimeUtil.java
│   │
│   ├── repository/                              # Data Access Layer (JPA)
│   │   ├── ClubRepository.java
│   │   ├── StudentRepository.java
│   │   ├── AuthorityRepository.java
│   │   ├── AnnouncementRepository.java
│   │   ├── EventRepository.java
│   │   ├── NewsRepository.java
│   │   ├── FeeRepository.java
│   │   └── RefreshTokenRepository.java
│   │
│   ├── models/                                  # JPA Entity Classes
│   │   ├── Student.java
│   │   ├── Club.java
│   │   ├── Authority.java
│   │   ├── Announcement.java
│   │   ├── Event.java
│   │   ├── News.java
│   │   ├── Fee.java
│   │   ├── RefreshToken.java
│   │   └── enums/                               # Enum Types
│   │       ├── Role.java
│   │       ├── YearOfStay.java
│   │       ├── ClubType.java
│   │       └── Gender.java
│   │
│   ├── dtos/                                    # Data Transfer Objects
│   │   ├── StudentDTO.java
│   │   ├── ClubDTO.java
│   │   ├── AuthorityDTO.java
│   │   ├── AnnouncementDTO.java
│   │   ├── EventDTO.java
│   │   ├── request/                            # Request DTOs
│   │   │   ├── CreateClubRequest.java
│   │   │   ├── UpdateClubRequest.java
│   │   │   └── ...
│   │   └── response/                           # Response DTOs
│   │       ├── ApiResponse.java
│   │       ├── ErrorResponse.java
│   │       └── ...
│   │
│   ├── exceptions/                              # Custom Exception Classes
│   │   ├── NotAuthorizedUserException.java
│   │   ├── ResourceNotFoundException.java
│   │   ├── ValidationException.java
│   │   ├── AuthenticationException.java
│   │   └── GlobalExceptionHandler.java
│   │
│   ├── config/                                  # Configuration Classes
│   │   ├── SecurityConfig.java
│   │   ├── JwtConfig.java
│   │   ├── CorsConfig.java
│   │   └── OpenApiConfig.java
│   │
│   └── common/                                  # Shared Utilities
│       ├── Constants.java
│       ├── AppUtils.java
│       └── DateUtils.java
│
├── src/main/resources/
│   ├── application.properties                   # Main configuration
│   ├── application-dev.properties               # Development profile
│   ├── application-prod.properties              # Production profile
│   ├── data.sql                                 # Sample data for testing
│   └── schema.sql                               # Database schema (if needed)
│
├── src/test/java/                               # Unit & Integration Tests
│   ├── controllers/
│   ├── services/
│   └── repositories/
│
├── pom.xml                                      # Maven configuration
├── .mvn/                                        # Maven wrapper config
├── mvnw & mvnw.cmd                              # Maven wrapper scripts
├── README.md                                    # This file
├── .gitignore                                   # Git ignore rules
└── LICENSE                                      # MIT License
```

### Design Patterns Used

| Pattern | Location | Purpose |
|---------|----------|---------|
| **MVC (Model-View-Controller)** | Controllers, Services, Models | Separation of concerns |
| **Repository Pattern** | `repository/` package | Data access abstraction |
| **Service Locator** | `Service/` classes | Business logic centralization |
| **DTO Pattern** | `dtos/` package | Data transformation between layers |
| **Singleton** | Spring @Bean components | Shared resource management |
| **Factory Pattern** | Entity builders | Complex object creation |
| **Dependency Injection** | Spring @Autowired, Constructor | Loose coupling |

---

## Core Features

### 1. Club Management

**Endpoints:**

```
POST   /api/clubs                      Create new club
GET    /api/clubs                      List all clubs with pagination
GET    /api/clubs/{id}                 Retrieve specific club
PUT    /api/clubs/{id}                 Update club details
DELETE /api/clubs/{id}                 Delete club (admin only)
```

**Data Model:**

```java
@Entity
public class Club {
    @Id @GeneratedValue
    private Long clubId;
    
    @Column(nullable = false, unique = true)
    private String title;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private ClubType clubType;              // ACADEMIC, CULTURAL, SPORTS
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private Set<Student> members;
    
    @OneToMany(mappedBy = "club")
    private Set<Authority> authorities;
}
```

**Business Logic:**

- Automatic club administrator assignment
- Member capacity management
- Activity logging and audit trails

### 2. Authority Management

**Hierarchical Roles:**

- **President**: Full club management authority
- **Vice President**: Secondary decision-making authority
- **Secretary**: Record keeping and communication
- **Treasurer**: Financial management
- **Event Manager**: Event coordination
- **General Member**: Basic participation

**Authorization Logic:**

```java
@Service
public class AuthorityService {
    
    public boolean hasAuthority(Long studentId, Long clubId, String role) {
        return authorityRepository
            .existsByStudentIdAndClubIdAndName(studentId, clubId, role);
    }
    
    public void validateCreateEvent(Long studentId, Long clubId) {
        boolean hasAuthority = authorityRepository
            .existsByStudentIdAndClubId(studentId, clubId);
        
        if (!hasAuthority) {
            throw new NotAuthorizedUserException(
                "User lacks required authority to create events"
            );
        }
    }
}
```

### 3. Member Management

**Features:**

- Student registration and enrollment
- Membership status tracking (Active, Inactive, Suspended)
- Attendance records
- Role-based member visibility

**Entity Relationship:**

```
Student (1) ──→ (*) Club      # Many-to-Many: Students join multiple clubs
        ↓
    Authority (1) ──→ (*) Club  # One-to-Many: Each student has role per club
```

### 4. Announcement System

**Access Control:**

- Only authority members can create announcements
- All members can view club announcements
- Timeline-based announcement archiving

**Features:**

- Rich text content support
- Pin important announcements
- Expiration date tracking

### 5. Event Management

**Creation & Participation:**

- Authority members create events
- Students register for events
- Automatic confirmation emails
- Attendance tracking

**Data Model:**

```java
@Entity
public class Event {
    @Id @GeneratedValue
    private Long eventId;
    
    private String title;
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime eventDate;
    
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
    
    @ManyToMany
    @JoinTable(
        name = "event_attendees",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> attendees;
}
```

### 6. News Publishing

**Editorial Features:**

- Club-specific news feed
- Category-based organization
- Publication scheduling
- Featured articles

### 7. Fee Management

**Billing System:**

- Configurable membership fees
- Payment tracking
- Receipt generation
- Refund management

---

## API Specifications

### Authentication Flow

#### 1. User Registration

```bash
POST /api/auth/register
Content-Type: application/json

{
  "email": "student@university.edu",
  "password": "SecurePassword123!",
  "firstName": "John",
  "lastName": "Doe",
  "yearOfStay": "SECOND_YEAR"
}

Response (201 Created):
{
  "id": 1,
  "email": "student@university.edu",
  "firstName": "John",
  "message": "User registered successfully"
}
```

#### 2. User Login

```bash
POST /api/auth/login
Content-Type: application/json

{
  "email": "student@university.edu",
  "password": "SecurePassword123!"
}

Response (200 OK):
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 3600000,
  "user": {
    "id": 1,
    "email": "student@university.edu",
    "role": "STUDENT"
  }
}
```

#### 3. Token Refresh

```bash
POST /api/auth/refresh
Content-Type: application/json

{
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

Response (200 OK):
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 3600000
}
```

### Request/Response Format

**Standard Response Wrapper:**

```json
{
  "success": true,
  "code": 200,
  "message": "Operation successful",
  "data": {
    "id": 1,
    "name": "Technology Club",
    "description": "For tech enthusiasts"
  },
  "timestamp": "2025-01-15T10:30:00Z"
}
```

**Error Response:**

```json
{
  "success": false,
  "code": 401,
  "message": "Unauthorized - User lacks required authority",
  "error": "NotAuthorizedUserException",
  "details": {
    "reason": "User is not an authority member of this club",
    "requiredRole": "PRESIDENT",
    "userRole": "MEMBER"
  },
  "timestamp": "2025-01-15T10:30:00Z"
}
```

### HTTP Status Codes

| Code | Status | Scenario |
|------|--------|----------|
| 200 | OK | Successful GET, PUT request |
| 201 | Created | Successful POST request |
| 204 | No Content | Successful DELETE request |
| 400 | Bad Request | Invalid input, validation failure |
| 401 | Unauthorized | Missing/invalid JWT token |
| 403 | Forbidden | User lacks required authority |
| 404 | Not Found | Resource doesn't exist |
| 409 | Conflict | Business logic violation (e.g., duplicate email) |
| 500 | Server Error | Unexpected server error |

### Complete API Endpoints Reference

**Base URL**: `http://localhost:8080/api`

#### Authentication Endpoints

| Method | Endpoint | Auth Required | Purpose |
|--------|----------|---------------|---------|
| POST | `/auth/register` | No | Register new user |
| POST | `/auth/login` | No | Authenticate user, get tokens |
| POST | `/auth/refresh` | No | Refresh access token |
| POST | `/auth/logout` | Yes | Revoke refresh token |
| GET | `/auth/me` | Yes | Get current user profile |

#### Club Management

| Method | Endpoint | Auth Required | Role |
|--------|----------|---------------|------|
| POST | `/clubs` | Yes | ADMIN |
| GET | `/clubs` | Yes | STUDENT+ |
| GET | `/clubs/{id}` | Yes | STUDENT+ |
| PUT | `/clubs/{id}` | Yes | ADMIN/CLUB_ADMIN |
| DELETE | `/clubs/{id}` | Yes | ADMIN |

#### Student Management

| Method | Endpoint | Auth Required | Role |
|--------|----------|---------------|------|
| GET | `/students` | Yes | ADMIN |
| GET | `/students/{id}` | Yes | ADMIN/SELF |
| PUT | `/students/{id}` | Yes | STUDENT/ADMIN |
| DELETE | `/students/{id}` | Yes | ADMIN |

#### Authority Management

| Method | Endpoint | Auth Required | Role |
|--------|----------|---------------|------|
| POST | `/authorities` | Yes | ADMIN/CLUB_ADMIN |
| GET | `/authorities/club/{clubId}` | Yes | STUDENT+ |
| PUT | `/authorities/{id}` | Yes | ADMIN |
| DELETE | `/authorities/{id}` | Yes | ADMIN |

#### Announcement Endpoints

| Method | Endpoint | Auth Required | Role |
|--------|----------|---------------|------|
| POST | `/announcements` | Yes | AUTHORITY |
| GET | `/announcements` | Yes | STUDENT+ |
| GET | `/announcements/club/{clubId}` | Yes | STUDENT+ |
| PUT | `/announcements/{id}` | Yes | AUTHORITY/ADMIN |
| DELETE | `/announcements/{id}` | Yes | AUTHORITY/ADMIN |

#### Event Management

| Method | Endpoint | Auth Required | Role |
|--------|----------|---------------|------|
| POST | `/events` | Yes | AUTHORITY |
| GET | `/events` | Yes | STUDENT+ |
| GET | `/events/club/{clubId}` | Yes | STUDENT+ |
| PUT | `/events/{id}` | Yes | AUTHORITY/ADMIN |
| DELETE | `/events/{id}` | Yes | AUTHORITY/ADMIN |

#### News & Fees

| Method | Endpoint | Auth Required | Role |
|--------|----------|---------------|------|
| POST | `/news` | Yes | AUTHORITY |
| GET | `/news/club/{clubId}` | Yes | STUDENT+ |
| POST | `/fees` | Yes | AUTHORITY |
| GET | `/fees/{studentId}` | Yes | STUDENT/ADMIN |

### Pagination, Filtering & Sorting

**Supported Query Parameters:**

```bash
# Pagination
GET /api/clubs?page=0&size=20

# Filtering
GET /api/clubs?type=ACADEMIC

# Sorting
GET /api/clubs?sort=title,asc&sort=createdAt,desc

# Combined
GET /api/announcements/club/5?page=0&size=10&sort=createdAt,desc
```

---

## Security Implementation

### 1. Authentication Strategy

**JWT (JSON Web Token) Implementation:**

```java
@Component
public class JwtProvider {
    
    @Value("${jwt.secret}")
    private String secretKey;
    
    @Value("${jwt.expiration}")
    private long expirationTime;
    
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .claim("roles", userDetails.getAuthorities())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }
    
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
```

**Token Structure:**

```
Header: {
  "alg": "HS256",
  "typ": "JWT"
}

Payload: {
  "sub": "student@university.edu",
  "roles": ["STUDENT"],
  "iat": 1705315800,
  "exp": 1705319400
}

Signature: HMACSHA256(base64(header) + "." + base64(payload), secret)
```

### 2. Authorization Implementation

**Role-Based Access Control (RBAC):**

```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/clubs/**").hasAnyRole("STUDENT", "ADMIN")
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .addFilterBefore(
                new JwtAuthenticationFilter(jwtProvider),
                UsernamePasswordAuthenticationFilter.class
            );
        
        return http.build();
    }
}
```

**Method-Level Security:**

```java
@Service
public class AnnouncementService {
    
    @PreAuthorize("hasAnyRole('AUTHORITY', 'ADMIN')")
    public Announcement createAnnouncement(AnnouncementDTO dto) {
        // Only users with AUTHORITY or ADMIN role can execute
    }
    
    @PreAuthorize("@authorityService.hasAuthority(#studentId, #clubId, 'PRESIDENT')")
    public void deleteAnnouncement(Long announcementId, Long studentId, Long clubId) {
        // Custom authorization logic
    }
}
```

### 3. Password Security

```java
@Service
public class StudentService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void registerStudent(StudentRegistrationDTO dto) {
        // Hash password using BCrypt with 10 salt rounds
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        
        Student student = new Student();
        student.setPassword(hashedPassword);
        studentRepository.save(student);
    }
    
    public boolean authenticateStudent(String email, String rawPassword) {
        Student student = studentRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        // Compare raw password with stored hash
        return passwordEncoder.matches(rawPassword, student.getPassword());
    }
}
```

**Password Requirements:**

- Minimum 8 characters
- Must contain uppercase and lowercase letters
- Must contain numbers and special characters
- Cannot be previously used password
- Automatic expiration every 90 days (recommended)

### 4. Input Validation

```java
@RestController
@RequestMapping("/api/clubs")
public class ClubController {
    
    @PostMapping
    public ResponseEntity<?> createClub(
        @Valid @RequestBody CreateClubRequest request
    ) {
        // Validation happens automatically via @Valid
        // Input is guaranteed to meet all constraints
    }
}

@Data
public class CreateClubRequest {
    
    @NotBlank(message = "Club name is required")
    @Size(min = 3, max = 100, message = "Club name must be 3-100 characters")
    private String name;
    
    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    @NotNull(message = "Club type is required")
    @Enumerated(EnumType.STRING)
    private ClubType clubType;
    
    @Email(message = "Valid email is required")
    private String contactEmail;
}
```

### 5. CORS Configuration

```java
@Configuration
public class CorsConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:3000", "https://yourdomain.com")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600);
            }
        };
    }
}
```

### 6. SQL Injection Prevention

Uses **parameterized queries** via Spring Data JPA:

```java
@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    
    // Safe: Uses parameterized query
    @Query("SELECT a FROM Announcement a WHERE a.club.id = :clubId AND a.createdAt >= :date")
    List<Announcement> findRecentByClub(
        @Param("clubId") Long clubId,
        @Param("date") LocalDateTime date
    );
    
    // Safe: Native query with named parameters
    @Query(
        value = "SELECT * FROM announcements WHERE club_id = :clubId",
        nativeQuery = true
    )
    List<Announcement> findByClubNative(@Param("clubId") Long clubId);
}
```

---

## Database Schema

### Entity Relationship Diagram

```
┌─────────────┐         ┌──────────────┐         ┌────────────┐
│  Student    │────────→│    Club      │←────────│  Authority │
│             │ (1..*)  │              │ (*..1)  │            │
│ - id        │         │ - id         │         │ - id       │
│ - email     │         │ - title      │         │ - name     │
│ - password  │         │ - description│         │ - startDate│
│ - firstName │         │ - type       │         │ - endDate  │
│ - lastName  │         │ - createdAt  │         │            │
│ - role      │         │              │         │            │
└─────────────┘         └──────────────┘         └────────────┘
       ▲                        │
       │                        │ (1..*)
       │                        ▼
       │                ┌──────────────┐
       │                │  Announcement│
       │                │              │
       └────────────────│ - id         │
     (1..*) participates│ - title      │
                        │ - content    │
                        │ - createdAt  │
                        │ - createdBy  │
                        └──────────────┘

┌─────────────┐         ┌──────────────┐
│  Student    │────────→│    Event     │
│             │ (1..*)  │              │
│             │ attends │ - id         │
└─────────────┘         │ - title      │
                        │ - date       │
                        │ - location   │
                        └──────────────┘

┌─────────────┐         ┌──────────────┐
│   Club      │────────→│     News     │
│             │ (1..*)  │              │
│             │         │ - id         │
└─────────────┘         │ - title      │
                        │ - content    │
                        │ - publishedAt│
                        └──────────────┘

┌─────────────┐         ┌──────────────┐
│   Club      │────────→│     Fee      │
│             │ (1..*)  │              │
│             │         │ - id         │
└─────────────┘         │ - amount     │
                        │ - dueDate    │
                        │ - status     │
                        └──────────────┘
```

### Table Definitions

```sql
-- Users / Students
CREATE TABLE students (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    year_of_stay VARCHAR(50),
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    role ENUM('STUDENT', 'CLUB_ADMIN', 'SUPER_ADMIN') DEFAULT 'STUDENT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email),
    INDEX idx_role (role)
);

-- Clubs
CREATE TABLE clubs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    logo_url VARCHAR(500),
    club_type VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY unique_title (title),
    INDEX idx_type (club_type)
);

-- Student-Club Membership (Many-to-Many)
CREATE TABLE student_club (
    student_id BIGINT NOT NULL,
    club_id BIGINT NOT NULL,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    PRIMARY KEY (student_id, club_id),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (club_id) REFERENCES clubs(id) ON DELETE CASCADE
);

-- Authorities / Roles within clubs
CREATE TABLE authority (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    start_date DATE,
    end_date DATE,
    student_id BIGINT NOT NULL,
    club_id BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (club_id) REFERENCES clubs(id) ON DELETE CASCADE,
    UNIQUE KEY unique_student_club_authority (student_id, club_id, name),
    INDEX idx_club_id (club_id)
);

-- Announcements
CREATE TABLE announcements (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    created_by_id BIGINT NOT NULL,
    club_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by_id) REFERENCES students(id),
    FOREIGN KEY (club_id) REFERENCES clubs(id) ON DELETE CASCADE,
    INDEX idx_club_created (club_id, created_at)
);

-- Events
CREATE TABLE events (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    event_date DATETIME,
    location VARCHAR(255),
    club_id BIGINT NOT NULL,
    created_by_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (club_id) REFERENCES clubs(id) ON DELETE CASCADE,
    FOREIGN KEY (created_by_id) REFERENCES students(id),
    INDEX idx_event_date (event_date),
    INDEX idx_club_id (club_id)
);

-- Event Attendees (Many-to-Many)
CREATE TABLE event_attendees (
    event_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (event_id, student_id),
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

-- Indexes for performance
CREATE INDEX idx_students_email ON students(email);
CREATE INDEX idx_clubs_type ON clubs(club_type);
CREATE INDEX idx_announcements_club_date ON announcements(club_id, created_at DESC);
CREATE INDEX idx_events_club_date ON events(club_id, event_date DESC);
```

---

## Testing & Quality Assurance

### Testing Framework

**Technologies Used:**
- **JUnit 5**: Modern testing framework
- **Mockito**: Object mocking library
- **Spring Boot Test**: Spring testing utilities
- **AssertJ**: Fluent assertion library (optional)

### Unit Testing Example

```java
@ExtendWith(MockitoExtension.class)
public class AnnouncementServiceTest {
    
    @Mock
    private AnnouncementRepository announcementRepository;
    
    @Mock
    private AuthorityService authorityService;
    
    @InjectMocks
    private AnnouncementService announcementService;
    
    @BeforeEach
    public void setUp() {
        // Initialization
    }
    
    @Test
    @DisplayName("Should create announcement when user has authority")
    public void testCreateAnnouncementSuccess() {
        // Arrange
        Long studentId = 1L;
        Long clubId = 1L;
        AnnouncementDTO dto = new AnnouncementDTO("Title", "Content");
        
        when(authorityService.hasAuthority(studentId, clubId))
            .thenReturn(true);
        
        // Act
        Announcement result = announcementService
            .createAnnouncement(studentId, clubId, dto);
        
        // Assert
        assertNotNull(result);
        assertEquals("Title", result.getTitle());
        verify(announcementRepository, times(1)).save(any());
    }
    
    @Test
    @DisplayName("Should throw exception when user lacks authority")
    public void testCreateAnnouncementUnauthorized() {
        // Arrange
        Long studentId = 1L;
        Long clubId = 1L;
        AnnouncementDTO dto = new AnnouncementDTO("Title", "Content");
        
        when(authorityService.hasAuthority(studentId, clubId))
            .thenReturn(false);
        
        // Act & Assert
        assertThrows(NotAuthorizedUserException.class, () -> {
            announcementService.createAnnouncement(studentId, clubId, dto);
        });
    }
}
```

### Integration Testing

```java
@SpringBootTest
@AutoConfigureMockMvc
public class ClubControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ClubRepository clubRepository;
    
    @Test
    public void testGetAllClubs() throws Exception {
        // Arrange
        Club club = new Club();
        club.setTitle("Tech Club");
        club.setDescription("For tech lovers");
        clubRepository.save(club);
        
        // Act & Assert
        mockMvc.perform(get("/api/clubs")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data", hasSize(greaterThan(0))));
    }
}
```

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AnnouncementServiceTest

# Run with coverage report
mvn clean test jacoco:report

# View coverage report
open target/site/jacoco/index.html
```

### Test Coverage Goals

| Component | Target Coverage | Current |
|-----------|-----------------|---------|
| Service Layer | 85%+ | - |
| Repository Layer | 75%+ | - |
| Controller Layer | 70%+ | - |
| Utilities | 90%+ | - |

---

## Deployment

### Development Deployment

**Local Setup:**

```bash
# 1. Clone and setup
git clone <repo-url>
cd Club_Managment_System_Api_springboot

# 2. Build
mvn clean install

# 3. Run
mvn spring-boot:run

# 4. Access
open http://localhost:8080/swagger-ui.html
```

### Production Deployment

#### Prerequisites

- Production database (PostgreSQL recommended)
- Application server (Tomcat, Jetty, or embedded)
- SSL/TLS certificate (HTTPS)
- Environment configuration

#### Docker Deployment

**Create Dockerfile:**

```dockerfile
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/club_managment_api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENTRYPOINT ["java", "${JAVA_OPTS}", "-jar", "app.jar"]
```

**Build & Run:**

```bash
# Build Docker image
docker build -t club-management-api:1.0 .

# Run container
docker run -d \
  --name club-api \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/club_db \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=secure-password \
  -e JWT_SECRET=your-secure-jwt-secret \
  club-management-api:1.0

# Check logs
docker logs -f club-api
```

#### Cloud Deployment (Recommended Platforms)

**AWS Elastic Beanstalk:**

```bash
# 1. Create .ebextensions/01_tomcat.config
option_settings:
  aws:elasticbeanstalk:container:tomcat:jvmoptions:
    Xmx: 512m
    Xms: 256m

# 2. Deploy
eb init
eb create club-api-env
eb deploy
```

**Heroku:**

```bash
# 1. Create Procfile
web: java -Dserver.port=$PORT $JAVA_OPTS -jar target/club_managment_api-0.0.1-SNAPSHOT.jar

# 2. Deploy
heroku create club-management-api
git push heroku main
```

### Production Checklist

- [ ] Database backups configured
- [ ] SSL/TLS certificates installed
- [ ] JWT secret changed from default
- [ ] CORS origins configured for production domain
- [ ] Logging configured (ERROR level minimum)
- [ ] Monitoring/alerts configured
- [ ] Database migrations tested
- [ ] API rate limiting configured
- [ ] DDoS protection enabled
- [ ] Security headers configured
- [ ] Regular security updates scheduled

---

## Troubleshooting

### Common Issues & Solutions

#### Issue 1: Application Won't Start

**Error**: `Failed to auto-configure DataSource`

**Solutions:**
```properties
# 1. Verify application.properties database URL
spring.datasource.url=jdbc:h2:mem:testdb  # For development

# 2. Check Maven build completed successfully
mvn clean install  # Should say BUILD SUCCESS

# 3. Verify Java version
java -version  # Should be 21+

# 4. Check for port conflicts
lsof -i :8080  # Kill process if needed
```

#### Issue 2: JWT Token Validation Fails

**Error**: `Invalid JWT signature`

**Solutions:**
```
1. Verify JWT_SECRET is consistent across service and client
2. Ensure token hasn't expired (check jwt.expiration)
3. Check token format: "Bearer <token>"
4. Regenerate test tokens if secret changed
```

#### Issue 3: Authorization Denied for Valid User

**Error**: `401 UNAUTHORIZED` despite valid token

**Solutions:**
```java
// 1. Check user roles are properly assigned
@Query("SELECT u FROM Student u WHERE u.id = :id")
Student findWithRoles(@Param("id") Long id);

// 2. Verify authority exists for operation
authorityRepository.existsByStudentIdAndClubId(studentId, clubId);

// 3. Check endpoint security configuration
@PreAuthorize("hasRole('ADMIN')")  // Make sure annotation is present
```

#### Issue 4: Database Connection Issues

**Error**: `Connection refused to database`

**Solutions:**
```bash
# 1. Verify database is running
mysql -u root -p  # MySQL check

# 2. Verify credentials in application.properties
spring.datasource.username=root
spring.datasource.password=yourpassword

# 3. Check firewall allowing connections
telnet localhost 3306

# 4. Create database if missing
mysql> CREATE DATABASE club_management_db;
```

#### Issue 5: Test Data Not Loading

**Error**: `EmptyResultDataAccessException`

**Solutions:**
```properties
# Ensure this is configured in application.properties
spring.jpa.defer-datasource-initialization=true

# Verify data.sql exists in src/main/resources/
# Data SQL loads AFTER table creation with above property
```

---

## Future Roadmap

### Phase 2 (Q1 2026)

- [ ] **Real-time Notifications**: WebSocket integration for instant updates
- [ ] **Advanced Analytics**: Dashboard with club growth metrics
- [ ] **Export Reports**: PDF/Excel generation for club statistics
- [ ] **Image Upload**: Profile pictures and event photos (Cloudinary integration)

### Phase 3 (Q2 2026)

- [ ] **Mobile App**: React Native mobile application
- [ ] **Calendar Integration**: Google Calendar/Outlook sync
- [ ] **Email Notifications**: Automated announcement and event emails
- [ ] **Payment Integration**: Online fee payment processing

### Phase 4 (Q3 2026)

- [ ] **AI Recommendations**: Smart event suggestions based on interests
- [ ] **Microservices Migration**: Separate services for scalability
- [ ] **Message Queue**: Kafka for asynchronous processing
- [ ] **Elasticsearch**: Advanced search and filtering

### Technical Debt & Improvements

- [ ] Add caching layer (Redis) for frequently accessed data
- [ ] Implement request logging and audit trails
- [ ] Improve test coverage to 90%+
- [ ] Add API rate limiting and throttling
- [ ] Implement comprehensive error tracking (Sentry)
- [ ] Add performance monitoring (New Relic/DataDog)

---

## Contributing

### Code Standards

#### Naming Conventions

```java
// Classes: PascalCase
public class ClubController { }

// Methods: camelCase, verb-first
public List<ClubDTO> getAllClubs() { }

// Constants: UPPER_SNAKE_CASE
private static final String DEFAULT_PAGE_SIZE = "20";

// Variables: camelCase
private String clubName;
private List<Student> members;
```

#### Code Style Guide

```java
// 1. Keep methods focused and single-responsibility
public Announcement createAnnouncement(AnnouncementDTO dto) {
    // Maximum 20-30 lines per method
}

// 2. Use meaningful variable names
// Bad:
List<Student> s = repository.findAll();

// Good:
List<Student> activeMembers = repository.findActiveMembers();

// 3. Add JavaDoc for public methods
/**
 * Create new announcement for a club.
 * 
 * @param studentId The ID of the student creating announcement
 * @param clubId The ID of the target club
 * @param dto Announcement details
 * @return Created announcement
 * @throws NotAuthorizedUserException if user lacks authority
 * @throws ResourceNotFoundException if club not found
 */
public Announcement createAnnouncement(Long studentId, Long clubId, AnnouncementDTO dto) {
    // Implementation
}
```

#### Pull Request Process

1. **Create feature branch**: `git checkout -b feature/feature-name`
2. **Make commits**: Use conventional commit messages
   ```
   feat: add club search functionality
   fix: resolve null pointer in authority validation
   docs: update README deployment section
   ```
3. **Push and create PR**: Include description of changes
4. **Code review**: Address reviewer comments
5. **Merge**: Squash commits if needed

---

## Support & Contact

### Documentation

- **API Documentation**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI Spec**: `http://localhost:8080/v3/api-docs`
- **GitHub Issues**: [Report bugs here](https://github.com/Yobil-Job/Club_Managment_System_Api_springboot/issues)

### Contact Information

- **Developer**: Eyob
- **Email**: [your-email@domain.com]
- **LinkedIn**: [Your LinkedIn Profile]
- **GitHub**: [Your GitHub Profile]

### Getting Help

1. Check existing documentation and FAQs
2. Search existing GitHub issues
3. Consult API documentation in Swagger UI
4. Review code examples in repository
5. Create GitHub issue with detailed description

---

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

### License Terms

- ✅ Commercial use permitted
- ✅ Modification permitted
- ✅ Distribution permitted
- ✅ Private use permitted
- ⚠️ Liability limited
- ⚠️ Warranty not provided

---

## Version History

| Version | Release Date | Key Changes |
|---------|-------------|-------------|
| 1.0.0 | Jan 2026 | Initial release, core features |
| 0.1.0 | Dec 2025 | Beta version for testing |

---

## Acknowledgments

- Spring Boot team for excellent framework
- University IT department for requirements gathering
- Contributors and beta testers

---

**Built with ❤️ using Spring Boot | Enterprise-Grade Club Management**

Last Updated: January 2026

---

