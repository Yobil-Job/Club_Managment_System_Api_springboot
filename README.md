# 🎓 Club Management System API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-6DB33F?style=flat-square&logo=spring-boot)
![Spring Security](https://img.shields.io/badge/Security-JWT%2FRBAC-black?style=flat-square)
![Database](https://img.shields.io/badge/Database-H2%2FMySQL%2FPostgreSQL-336791?style=flat-square&logo=postgresql)
![OpenAPI](https://img.shields.io/badge/API%20Docs-OpenAPI%203.0-85EA2D?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)

> **Enterprise-grade REST API** powering comprehensive university club operations with production-ready security, role-based access control, and comprehensive event & member management.

---

## 🎯 Executive Summary

A **full-stack REST API** built with Spring Boot 3.5.5 and Java 21 for managing university clubs at scale. Features stateless JWT authentication, granular role-based access controls, comprehensive member & event workflows, and clean architectural patterns suitable for enterprise deployments.

**Perfect for demonstrating:**
- ✅ Production-grade backend architecture
- ✅ Security best practices (JWT, BCrypt, SQL injection prevention)
- ✅ RESTful API design with HATEOAS
- ✅ Database modeling & JPA/Hibernate expertise
- ✅ Spring Security & authorization patterns
- ✅ Testing strategies (unit & integration tests)
- ✅ CI/CD & containerization

---

## 🏗️ Architecture at a Glance

```
┌─────────────────┐
│   Controllers   │ (Endpoint routing & validation)
├─────────────────┤
│    Services     │ (Business logic & authorization)
├─────────────────┤
│  Repositories   │ (JPA data access)
├─────────────────┤
│    Database     │ (MySQL/PostgreSQL/H2)
└─────────────────┘

🔐 Security Layer: JWT + Spring Security + Role-Based Access Control (@PreAuthorize)
📊 HATEOAS: Hypermedia links for RESTful navigation
```

---

## ⚡ Core Features

### 🏢 **Club Management**
- Create, read, update, delete clubs with configurable club types
- Member request approval workflow (pending → approved)
- Club-level authority assignments (President, Secretary, Treasurer, etc.)

### 👥 **Member & Authority System**
- Student registration with email validation & strong password requirements
- Four-tier role hierarchy:
  - `STUDENT` – Regular members
  - `SUPER_USER` – Club-level admins (President, Secretary, etc.)
  - `SUPER_ADMIN` – Club portal administrators
  - `ADMIN` – System-wide administrators
- Authority validity date ranges (start/end dates for terms)

### 📅 **Event Management**
- Create & publish events with RSVP tracking
- Attendance registration & marking system
- Event discovery by club & date
- Event update & deletion with ownership checks

### 📢 **Announcements & News**
- Role-restricted announcement & news publishing
- Club-specific content distribution
- Full audit trail (creator, creation date)

### 💰 **Fee Management**
- Configurable fee entries per club
- Payment status tracking (PAID, PENDING, FAILED)
- Billing records linked to student-club relationships
- Total fee collection analytics per club

### 🔐 **Authentication & Authorization**
- **JWT-based stateless authentication** (access + refresh tokens)
- **Method-level authorization** using `@PreAuthorize`
- **BCrypt password hashing** with configurable strength
- **Token refresh mechanism** for extended sessions
- **Login/Logout workflows** with token invalidation

---

## 🛠️ Tech Stack

| Layer | Technology | Purpose |
|-------|-----------|---------|
| **Runtime** | Java 21, Spring Boot 3.5.5 | Latest LTS Java with Spring modernization |
| **Security** | Spring Security, JWT (JJWT 0.11.5) | Authentication & authorization |
| **Data Access** | Spring Data JPA, Hibernate | ORM & database abstraction |
| **Database** | H2 (dev), MySQL/PostgreSQL (prod) | Multi-environment support |
| **API Docs** | OpenAPI 3.0, Springdoc | Auto-generated Swagger UI |
| **Testing** | JUnit 5, Mockito | Unit & integration testing |
| **DevTools** | Spring Boot DevTools | Hot reload during development |
| **HTTP** | Spring HATEOAS | RESTful hypermedia support |

---

## 📚 API Endpoints (Complete Reference)

### 🔓 **Authentication** (`/auth`)
```
POST   /auth/login              → Login with email/password, get JWT tokens
POST   /auth/refresh            → Refresh access token using refresh token
POST   /auth/logout             → Invalidate refresh token & logout
```

### 👤 **Student Management** (`/student`)
```
POST   /student/register        → Create new student account
GET    /student/me              → Fetch authenticated student profile
GET    /student/{id}            → Get student by ID
PATCH  /student/{id}/update     → Update student info (email, password, year, etc.)
DELETE /student/{id}/delete     → Delete student account
GET    /student/{id}/getclubsJoined   → List clubs joined by student
GET    /student/{id}/events     → Get events attended by student
POST   /student/{studentId}/clubs/{clubId}/request → Join club (creates pending request)
```

### 🏢 **Club Management** (`/clubs`)
```
POST   /clubs/create                                → Create new club
GET    /clubs/{clubId}                              → Get club details
GET    /clubs                                       → List all clubs
PATCH  /clubs/{id}/update                           → Update club info
DELETE /clubs/{id}/delete                           → Delete club
GET    /clubs/{clubId}/requests/pending             → Get pending join requests
PATCH  /clubs/{clubId}/requests/{studentId}/approve → Approve join request
PATCH  /clubs/{clubId}/requests/{studentId}/reject  → Reject join request
```

### 🎖️ **Authority Management** (`/authorities`)
```
POST   /authorities/create                  → Assign role to student in club
GET    /authorities/{authorityId}           → Get authority details
GET    /authorities/club/{clubId}           → List all authorities in club
PATCH  /authorities/{id}/update             → Update authority (end date, role)
DELETE /authorities/{id}/delete             → Remove authority
```

### 📅 **Events** (`/events`)
```
POST   /events/create                       → Create event (SUPER_ADMIN, SUPER_USER)
GET    /events/{eventId}                    → Get event details
GET    /events/club/{clubId}                → List events by club
GET    /events/allEvents                    → List all events
PATCH  /events/{eventId}/update             → Update event details
DELETE /events/{eventId}/delete             → Delete event
POST   /events/{eventId}/attend             → Register attendance
```

### 📢 **Announcements** (`/announcements`)
```
POST   /announcements/create                → Create announcement (role-restricted)
GET    /announcements/{id}                  → Get announcement by ID
GET    /announcements/retriveAnnouncementByClub/{clubId} → List by club
GET    /announcements/retriveAllAnnouncement → List all announcements
PATCH  /announcements/{id}/update           → Update announcement
DELETE /announcements/{id}/delete           → Delete announcement
```

### 📰 **News** (`/news`)
```
POST   /news/create                         → Create news post
GET    /news/{id}                           → Get news post
GET    /news/club/{clubId}                  → List news by club
PATCH  /news/{id}/update                    → Update news post
DELETE /news/{id}/delete                    → Delete news post
```

### 💰 **Fees** (`/fees`)
```
POST   /fees/create                         → Create fee entry
GET    /fees/{feeId}                        → Get fee details
GET    /fees/club/{clubId}                  → List fees by club
GET    /fees/clubs/{clubId}/total           → Get total fees collected by club
PATCH  /fees/{feeId}/update                 → Update fee status (PAID, PENDING, FAILED)
DELETE /fees/{feeId}/delete                 → Delete fee entry
```

---

## 🚀 Quick Start (60 seconds)

### Prerequisites
- Java 21+ installed
- Maven 3.6+
- MySQL or PostgreSQL (optional for production)

### 1️⃣ Clone & Setup
```bash
git clone https://github.com/Yobil-Job/Club_Managment_System_Api_springboot.git
cd Club_Managment_System_Api_springboot
mvn clean install
```

### 2️⃣ Run the Application
```bash
# Development (H2 in-memory, auto hot-reload)
mvn spring-boot:run

# Or build and run JAR
mvn clean package
java -jar target/club_managment_api-0.0.1-SNAPSHOT.jar
```

### 3️⃣ Access the API
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **Health Check**: http://localhost:8080/actuator/health

---

## 📋 Example Workflows

### 🔑 Login & Get JWT Token
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "student@university.edu",
    "password": "securePassword123"
  }'

# Response:
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "studentId": 1,
  "email": "student@university.edu",
  "role": "STUDENT"
}
```

### 📢 Create Announcement (as Club Admin)
```bash
curl -X POST http://localhost:8080/announcements/create \
  -H "Authorization: Bearer <ACCESS_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "clubId": 2,
    "title": "Weekly Meetup - Cloud Architecture",
    "description": "Join us for a deep dive into AWS microservices patterns",
    "createdById": 5
  }'
```

### 📅 Create Event (as Club President)
```bash
curl -X POST http://localhost:8080/events/create \
  -H "Authorization: Bearer <ACCESS_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "clubId": 2,
    "title": "Tech Conference 2025",
    "description": "Annual technology conference",
    "eventDate": "2025-06-15T10:00:00",
    "location": "Main Hall",
    "createdById": 5
  }'
```

### 💰 Track Fee Payments
```bash
curl -X PATCH http://localhost:8080/fees/{feeId}/update \
  -H "Authorization: Bearer <ACCESS_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "status": "PAID"
  }'

# Get total collected
curl http://localhost:8080/fees/clubs/{clubId}/total \
  -H "Authorization: Bearer <ACCESS_TOKEN>"
```

---

## 🔐 Security & Best Practices

### ✅ Implemented Security Measures

| Feature | Implementation |
|---------|-----------------|
| **Authentication** | JWT (JJWT 0.11.5) with HS256 algorithm |
| **Password Hashing** | BCrypt with configurable strength |
| **Authorization** | Method-level @PreAuthorize with role checks |
| **SQL Injection Prevention** | Parameterized JPA queries (no string concatenation) |
| **Input Validation** | Jakarta Bean Validation (@Valid, @NotNull, @Email, etc.) |
| **Token Expiration** | Configurable access (15min) & refresh tokens (7 days) |
| **CORS** | Configurable for different frontend environments |
| **Stateless Design** | No session storage; JWT carries all auth info |

### 📝 Security Configuration
```java
// src/main/java/com/club/api/club_managment_api/config/SecurityConfig.java
- HttpSecurity configuration with JWT filters
- CORS settings for API access
- Endpoint-level security rules

// src/main/java/com/club/api/club_managment_api/config/JwtUtil.java
- Token generation with configurable expiration
- Token validation & claim extraction
- Signature verification with HMAC-SHA256
```

---

## 🧪 Testing Strategy

### Unit Tests (Service Layer)
```bash
# Run all tests
mvn test

# Example: AuthorityService tests
# - Create authority with duplicate role → DuplicateResourceException
# - Retrieve non-existent authority → resourceNotFoundException
# - Authority validity date validation
```

### Integration Tests
- MockMvc for controller testing
- `@SpringBootTest` for full application context
- H2 in-memory database for test isolation
- Custom annotations for auth testing

### Test Coverage Areas
- Authentication workflows (login, token refresh, logout)
- Authorization checks (role-based access)
- Business logic (fee calculations, event attendance)
- Exception handling & error responses

---

## 📦 Project Structure

```
src/main/java/com/club/api/club_managment_api/
├── ClubManagmentApiApplication.java      # Application entry point
├── controllers/                           # REST endpoints
│   ├── StudentController.java
│   ├── ClubController.java
│   ├── EventController.java
│   ├── AuthController.java
│   ├── AuthorityController.java
│   ├── AnnouncementController.java
│   ├── NewsController.java
│   └── FeeController.java
├── Service/                               # Business logic & transactions
│   ├── StudentService.java
│   ├── ClubService.java
│   ├── EventService.java
│   ├── AuthorityService.java
│   ├── FeeService.java
│   └── utilities/
│       ├── StudentMapper.java
│       └── ClubMapper.java
├── repository/                            # JPA data access
│   ├── StudentRepository.java
│   ├── ClubRepository.java
│   ├── EventRepository.java
│   ├── AuthorityRepository.java
│   ├── FeeRepository.java
│   └── RefreshTokenRepository.java
├── models/                                # JPA entities
│   ├── Student.java
│   ├── Club.java
│   ├── Event.java
│   ├── Authority.java
│   ├── Fee.java
│   ├── Announcement.java
│   ├── News.java
│   └── enums/
│       ├── Role_enum.java
│       ├── Gender_enum.java
│       └── Payment_Status_enum.java
├── dtos/                                  # Data Transfer Objects
│   ├── student/
│   ├── clubs/
│   ├── events/
│   ├── authorities/
│   ├── Fees/
│   ├── news/
│   └── Login/
├── config/                                # Spring configurations
│   ├── SecurityConfig.java
│   ├── JwtUtil.java
│   ├── JwtAuthenticationFilter.java
│   ├── CustomUserDetails.java
│   ├── CustomUserDetailsService.java
│   └── OpenApiConfig.java
└── exceptions/                            # Custom exceptions
    ├── DuplicateResourceException.java
    ├── resourceNotFoundException.java
    └── notAuthorizedUserException.java

src/main/resources/
├── application.properties                 # Config (DB, JWT, server)
├── application-dev.properties             # Dev environment
└── data.sql                               # Initial SQL schema & seed data
```

---

## 🐳 Docker & Deployment

### Build Docker Image
```bash
docker build -t club-api:v1.0 .
```

### Run with Production Database
```bash
docker run \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/club_db \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=your_secure_password \
  -e JWT_SECRET=your_very_long_secure_jwt_secret_key \
  -e JWT_EXPIRATION=900000 \
  -e JWT_REFRESH_EXPIRATION=604800000 \
  -p 8080:8080 \
  club-api:v1.0
```

### Docker Compose (with PostgreSQL)
```yaml
version: '3.8'
services:
  api:
    image: club-api:v1.0
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/club_db
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres
    depends_on:
      - postgres

  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: club_db
      POSTGRES_PASSWORD: postgres
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

---

## 🔧 Configuration

### Application Properties (`application.properties`)
```properties
# Server
server.port=8080
server.servlet.context-path=/api

# Database (H2 default, override for prod)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.h2.console.enabled=true

# JPA
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false

# JWT
jwt.secret=${JWT_SECRET:your_super_secret_key_change_in_production}
jwt.expiration=${JWT_EXPIRATION:900000}
jwt.refresh-expiration=${JWT_REFRESH_EXPIRATION:604800000}

# OpenAPI
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

---

## 📊 Database Schema Highlights

### Key Relationships
```
Student (1) ──→ (M) Authority (N:1 joins with Club)
Student (M) ──→ (M) Club (via StudentClub join table)
Club (1) ──→ (M) Event
Club (1) ──→ (M) Announcement
Club (1) ──→ (M) News
Club (1) ──→ (M) Fee
```

### Important Columns
- **Student**: id, email, password_hash, firstname, lastname, gender, yearOfStay, role, createdAt, updatedAt
- **Club**: id, title, description, clubType, createdAt, updatedAt
- **Authority**: id, studentId, clubId, name, startDate, endDate
- **Fee**: id, studentId, clubId, amount, purpose, date, status (PAID/PENDING/FAILED)
- **Event**: id, clubId, title, description, eventDate, location, createdById, createdAt

---

## 🎓 Learning Outcomes & Skills Demonstrated

This project showcases expertise in:

✅ **Backend Architecture**
- Layered architecture (Controller → Service → Repository)
- Clean separation of concerns
- Dependency injection & IoC container management

✅ **API Design**
- RESTful principles with HATEOAS links
- Proper HTTP status codes & error handling
- Data Transfer Objects (DTOs) for response shaping

✅ **Security**
- JWT token-based authentication
- Role-based access control (RBAC)
- Input validation & sanitization
- Password security best practices

✅ **Database Design**
- Normalized relational schema
- Effective use of JPA/Hibernate
- Transaction management with `@Transactional`

✅ **Testing**
- Unit tests with Mockito
- Integration tests with MockMvc
- Test fixtures & assertions

✅ **DevOps & Deployment**
- Docker containerization
- Environment-based configurations
- CI/CD pipeline readiness

---

## 🐛 Known Issues & Fixes

### Lazy Loading in `/student/me` Endpoint
A fix document is provided: [FIX_STUDENT_ME_ENDPOINT.md](./FIX_STUDENT_ME_ENDPOINT.md)

**Issue**: Lazy-loaded collections cause serialization errors.  
**Solution**: Cast to `CustomUserDetails` and manually build response DTO.

---

## 📈 Future Enhancements

- [ ] Caching layer (Redis) for frequently accessed data
- [ ] Message queue (RabbitMQ) for async notifications
- [ ] Event-driven architecture with Spring Cloud Stream
- [ ] GraphQL API alongside REST
- [ ] Comprehensive audit logging
- [ ] Advanced search & filtering (Elasticsearch)
- [ ] Multi-tenancy support for multiple universities
- [ ] Payment gateway integration (Stripe, PayPal)

---

## 📞 Contact & Links

**Developer**: Eyob Weldetensay – Software Engineer  
- 🔗 **GitHub**: https://github.com/Yobil-Job
- 💼 **LinkedIn**: https://www.linkedin.com/in/eyob-weldetensay-a68160254/

---

## 📄 License

MIT License – See [LICENSE](./LICENSE) file for details.

---

## 📖 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Reference](https://spring.io/projects/spring-security)
- [JWT Best Practices](https://tools.ietf.org/html/rfc7519)
- [RESTful API Design](https://restfulapi.net/)
- [OpenAPI 3.0 Specification](https://spec.openapis.org/oas/v3.0.3)

---

**⭐ If this project helps you, please consider giving it a star on GitHub!**
