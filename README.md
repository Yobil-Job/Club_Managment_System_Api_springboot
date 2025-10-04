# 🎓 Club Management API

A powerful **Spring Boot RESTful API** designed to manage **university clubs**, their **members**, **authorities**, **events**, **announcements**, and more — enabling seamless coordination and automation of student activities.  

---

## 🚀 Features

✅ **Clubs Management**  
- Create, update, and retrieve clubs  
- Assign a **club admin** (managed by system admin)  
- Categorize clubs (Academic, Cultural, Sports, etc.)

✅ **Authorities Management**  
- Assign multiple authorities per club (President, Secretary, Minister, etc.)  
- Manage their **start** and **end dates**  
- Validate authority permissions before allowing actions (e.g., posting events)

✅ **Members Management**  
- Register students and associate them with clubs  
- Validate membership before performing club-specific actions

✅ **Announcements**  
- Allow only **authorized club members** to create announcements  
- Retrieve announcements by club

✅ **Events**  
- Create events only by **authorized members**  
- Add multiple attendees  
- Retrieve club-specific or all events

✅ **Role-based Permissions**  
- Only users with proper authority can perform specific actions (secure endpoints)

---

## 🧱 Project Structure

src/
┣ 📂main/java/com/club/api/club_managment_api
┃ ┣ 📂controllers → REST controllers (ClubController, AuthorityController, etc.)
┃ ┣ 📂services → Business logic (ClubService, EventService, etc.)
┃ ┣ 📂repositories → Spring Data JPA repositories
┃ ┣ 📂entities → JPA Entities (Club, Student, Authority, Event, Announcement)
┃ ┣ 📂dtos → Data Transfer Objects (Request/Response DTOs)
┃ ┗ 📂exceptions → Custom exceptions (NotAuthorizedUserException, etc.)
┗ 📂resources
┣ application.yml → Database configs
┗ data.sql → Initial test data


---

## 🧠 Tech Stack

| Technology | Purpose |
|------------|----------|
| ☕ Java 17+ | Programming Language |
| 🌱 Spring Boot 3.x | Backend Framework |
| 🧠 Spring Data JPA | ORM and Database Access |
| 🧰 Hibernate | JPA Implementation |
| 🧱 MySQL / PostgreSQL | Relational Database |
| 🧪 JUnit & Mockito | Unit Testing |
| 📡 RESTful API | Communication |
| 🔐 Validation API | Request Validation |

---

## ⚙️ Setup Instructions

### 🔹 1. Clone the repository
```bash
git clone https://github.com/your-username/club-management-api.git
cd club-management-api

🔹 2. Configure the database

In src/main/resources/application.yml:

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/club_db
    username: root
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

🔹 3. Run the project
mvn spring-boot:run


Server runs at 👉 http://localhost:8080

🧭 API Endpoints
🏛 Clubs
Method	Endpoint	Description
POST	/clubs	Create new club
GET	/clubs/{id}	Retrieve club by ID
GET	/clubs	List all clubs
🧑‍💼 Authorities
Method	Endpoint	Description
POST	/authorities	Assign authority to a member
GET	/authorities/{clubId}	List authorities of a club
📣 Announcements
Method	Endpoint	Description
POST	/announcements	Create announcement (only authorized)
GET	/announcements/club/{clubId}	Retrieve club announcements
🎉 Events
Method	Endpoint	Description
POST	/events	Create event (only authorized)
GET	/events/club/{clubId}	Retrieve club events
🔐 Authorization Logic

A student must have at least one authority in a club to:

Create announcements

Create events

Manage other members

🧠 Logic implemented using authorityRepository.existsByStudentIdAndClubId(studentId, clubId)

🧪 Example Requests
📝 Create Announcement
POST /announcements
{
  "clubId": 2,
  "title": "Weekly Cloud Meetup",
  "description": "We will discuss AWS, Azure, and GCP basics.",
  "createdById": 1
}

🎯 Create Event
POST /events
{
  "clubId": 2,
  "title": "DevOps Bootcamp",
  "description": "Hands-on workshop on CI/CD",
  "attendees": [2, 3, 4]
}

🧰 Error Handling
Error	Cause	Example
400 BAD REQUEST	Validation failed	Missing required fields
401 UNAUTHORIZED	No authority in club	Student not an authority
404 NOT FOUND	Resource not found	Invalid club or student ID
🧑‍💻 Author

Eyob
💻 Software Engineer | ☁️ Cloud Enthusiast | 🛡 Cybersecurity Learner
🔗 LinkedIn
 | GitHub

🌟 Future Improvements

🔐 JWT Authentication

🧾 Role-based Access Control (RBAC)

📅 Event calendar integration

📨 Email notifications

📱 Frontend (React / Next.js)

📝 License

This project is licensed under the MIT License — feel free to use and modify.

MIT License © 2025 Eyob


⚡ "Empowering student communities through technology and collaboration."


---

