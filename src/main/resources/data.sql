-- ============================================================
-- ARBA MINCH UNIVERSITY (AMU) - CLUB MANAGEMENT SEED DATA
-- University located in Arba Minch, Ethiopia (6.0333 N, 37.5500 E)
-- ============================================================

-- ============================================================
-- SUPER ADMINISTRATORS
-- ============================================================
INSERT INTO Students (first_name, last_name, email, password, year_of_stay, gender, role, department)
VALUES 
('Eyob', 'Weldetensay', 'eyob@amu.edu.et', '$2b$10$6E.0sxdroZ0bE5QisYYsYemgYLUF//BuM/tCmp.pRz8CRIqDh.z9q', 'Fourth_Year', 'MALE', 'SUPER_ADMIN', 'Software Engineering'),
('System', 'Admin', 'admin@amu.edu.et', '$2b$10$6E.0sxdroZ0bE5QisYYsYemgYLUF//BuM/tCmp.pRz8CRIqDh.z9q', 'First_Year', 'MALE', 'SUPER_ADMIN', 'Information Technology');

-- ============================================================
-- CLUB ADMINISTRATORS (ADMIN role)
-- ============================================================
INSERT INTO Students (first_name, last_name, email, password, year_of_stay, gender, role, department)
VALUES 
('Abebe', 'Kebede', 'abebe.k@amu.edu.et', '$2b$10$23XEpnNsAXeMsAQnoE4sOOMg4kaPQ1.NQeXitw8388eeND2KgRtcy', 'Third_Year', 'MALE', 'ADMIN', 'Computer Science'),
('Birtukan', 'Molla', 'birtukan.m@amu.edu.et', '$2b$10$23XEpnNsAXeMsAQnoE4sOOMg4kaPQ1.NQeXitw8388eeND2KgRtcy', 'Fourth_Year', 'FEMALE', 'ADMIN', 'Electrical Engineering'),
('Chala', 'Defaru', 'chala.d@amu.edu.et', '$2b$10$23XEpnNsAXeMsAQnoE4sOOMg4kaPQ1.NQeXitw8388eeND2KgRtcy', 'Third_Year', 'MALE', 'ADMIN', 'Business Management');

-- ============================================================
-- REGULAR STUDENTS
-- ============================================================
INSERT INTO Students (first_name, last_name, email, password, year_of_stay, gender, role, department)
VALUES 
('Almaz', 'Wondimu', 'almaz.w@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'Second_Year', 'FEMALE', 'STUDENT', 'Computer Science'),
('Biruk', 'Tessema', 'biruk.t@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'First_Year', 'MALE', 'STUDENT', 'Software Engineering'),
('Chaltu', 'Ayana', 'chaltu.a@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'Third_Year', 'FEMALE', 'STUDENT', 'Civil Engineering'),
('Dawit', 'Eshetu', 'dawit.e@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'Fourth_Year', 'MALE', 'STUDENT', 'Mechanical Engineering'),
('Eden', 'Hailu', 'eden.h@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'Second_Year', 'FEMALE', 'STUDENT', 'Accounting'),
('Fasika', 'Abebe', 'fasika.a@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'First_Year', 'FEMALE', 'STUDENT', 'Biology'),
('Girma', 'Tadesse', 'girma.t@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'Third_Year', 'MALE', 'STUDENT', 'Economics'),
('Hiwot', 'Alemu', 'hiwot.a@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'Fourth_Year', 'FEMALE', 'STUDENT', 'Law'),
('Isaac', 'Mamo', 'isaac.m@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'Second_Year', 'MALE', 'STUDENT', 'Computer Science'),
('Kidist', 'Yohannes', 'kidist.y@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'Third_Year', 'FEMALE', 'STUDENT', 'Architecture'),
('Lemma', 'Guta', 'lemma.g@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'First_Year', 'MALE', 'STUDENT', 'Mathematics'),
('Meron', 'Tefera', 'meron.t@amu.edu.et', '$2b$10$50BRQSt.1x0e0fkYgq.MYuSS7dXf/NeM2CnZgcNUDdOdpJRjqNnGa', 'Second_Year', 'FEMALE', 'STUDENT', 'Chemistry');

-- ============================================================
-- CLUBS (Arba Minch University themed)
-- ============================================================
INSERT INTO clubs (title, description, logo_url, club_type, club_admin_id, created_at, updated_at)
VALUES 
('Arba Minch Coding Club', 'A vibrant community of student developers at AMU passionate about coding, hackathons, and building software solutions for local challenges. We explore web dev, mobile apps, and open source.', 'https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=200&h=200&fit=crop', 'Acadamic', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Lake Abaya Debate Society', 'Home to AMU debate champions! We sharpen critical thinking and public speaking through weekly debates, parliamentary style competitions, and inter-university tournaments across Ethiopia.', 'https://images.unsplash.com/photo-1577962917302-c3e5cbf3537b?w=200&h=200&fit=crop', 'Creative', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Forty Springs Music & Arts', 'Celebrating Arba Minch vibrant culture through music, drama, poetry and visual arts. Named after the 40 springs that give our city its name. Open to all creative souls at AMU.', 'https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?w=200&h=200&fit=crop', 'Creative', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('AMU Tech Innovators', 'We build the future! From AI and IoT to renewable energy solutions, our club works on real-world tech projects tailored for Ethiopia development. Makers, coders, and dreamers welcome.', 'https://images.unsplash.com/photo-1504384308090-c894fdcc538d?w=200&h=200&fit=crop', 'Acadamic', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Nech Sar Green Guardians', 'Environmental club dedicated to preserving Nech Sar National Park and Arba Minch natural heritage. We organize tree planting, lake clean-ups, and environmental awareness campaigns.', 'https://images.unsplash.com/photo-1542601906990-b4d3fb778b09?w=200&h=200&fit=crop', 'Sport', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('AMU Entrepreneurship Hub', 'Fostering the next generation of Ethiopian entrepreneurs! We run startup bootcamps, business plan competitions, and connect students with mentors from Arba Minch business community.', 'https://images.unsplash.com/photo-1559136555-9303baea8ebd?w=200&h=200&fit=crop', 'Acadamic', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Arba Minch Sports United', 'Uniting AMU athletes across football, basketball, athletics, and traditional Ethiopian sports. We organize inter-department tournaments and represent AMU in regional competitions.', 'https://images.unsplash.com/photo-1574629810360-7efbbe195018?w=200&h=200&fit=crop', 'Sport', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('AMU Cultural & Heritage Club', 'Preserving and promoting the rich cultural heritage of Ethiopia southern nations. We showcase traditional music, dance, food, and clothing from diverse communities around Arba Minch.', 'https://images.unsplash.com/photo-1514525253161-7a46d19cd819?w=200&h=200&fit=crop', 'Creative', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================================
-- STUDENT-CLUB MEMBERSHIPS (with status)
-- ============================================================

-- First, ensure status column exists
ALTER TABLE student_club ADD COLUMN IF NOT EXISTS status VARCHAR(20);

-- Club 1: Arba Minch Coding Club - members
INSERT INTO student_club (student_id, club_id, status) VALUES (1, 1, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (6, 1, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (7, 1, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (10, 1, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (13, 1, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (15, 1, 'APPROVED');

-- Club 2: Lake Abaya Debate Society
INSERT INTO student_club (student_id, club_id, status) VALUES (1, 2, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (4, 2, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (8, 2, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (11, 2, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (14, 2, 'APPROVED');

-- Club 3: Forty Springs Music & Arts
INSERT INTO student_club (student_id, club_id, status) VALUES (1, 3, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (5, 3, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (8, 3, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (9, 3, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (12, 3, 'APPROVED');

-- Club 4: AMU Tech Innovators
INSERT INTO student_club (student_id, club_id, status) VALUES (2, 4, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (6, 4, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (7, 4, 'PENDING');
INSERT INTO student_club (student_id, club_id, status) VALUES (10, 4, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (13, 4, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (15, 4, 'APPROVED');

-- Club 5: Nech Sar Green Guardians
INSERT INTO student_club (student_id, club_id, status) VALUES (2, 5, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (5, 5, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (8, 5, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (9, 5, 'PENDING');
INSERT INTO student_club (student_id, club_id, status) VALUES (11, 5, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (14, 5, 'APPROVED');

-- Club 6: AMU Entrepreneurship Hub
INSERT INTO student_club (student_id, club_id, status) VALUES (3, 6, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (4, 6, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (7, 6, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (10, 6, 'PENDING');
INSERT INTO student_club (student_id, club_id, status) VALUES (12, 6, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (13, 6, 'APPROVED');

-- Club 7: Arba Minch Sports United
INSERT INTO student_club (student_id, club_id, status) VALUES (3, 7, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (6, 7, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (9, 7, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (11, 7, 'PENDING');
INSERT INTO student_club (student_id, club_id, status) VALUES (14, 7, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (15, 7, 'APPROVED');

-- Club 8: AMU Cultural & Heritage Club
INSERT INTO student_club (student_id, club_id, status) VALUES (1, 8, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (4, 8, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (5, 8, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (8, 8, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (12, 8, 'APPROVED');
INSERT INTO student_club (student_id, club_id, status) VALUES (14, 8, 'PENDING');

-- ============================================================
-- AUTHORITIES (Club Leadership Positions)
-- ============================================================
INSERT INTO AUTHORITY (name, start_date, end_date, club_id, student_id)
VALUES 
('President', '2025-09-01', NULL, 1, 3),
('Vice President', '2025-09-01', '2026-06-30', 1, 10),
('Technical Lead', '2025-09-01', NULL, 1, 6),
('Secretary', '2025-09-01', NULL, 1, 13),

('President', '2025-09-01', NULL, 2, 4),
('Vice President', '2025-09-01', NULL, 2, 11),
('Debate Coach', '2025-09-01', NULL, 2, 14),

('President', '2025-09-01', NULL, 3, 5),
('Music Director', '2025-09-01', NULL, 3, 9),
('Art Coordinator', '2025-09-01', NULL, 3, 12),

('President', '2025-10-01', NULL, 4, 3),
('Vice President', '2025-10-01', NULL, 4, 15),
('Project Manager', '2025-10-01', NULL, 4, 7);

-- ============================================================
-- NEWS ARTICLES (Arba Minch University related)
-- ============================================================
INSERT INTO news (title, description, images, created_by_id)
VALUES 
('AMU Coding Club Wins National Hackathon', 'The Arba Minch Coding Club team "CodeCrafters" won first place at the 2026 Ethiopian National Hackathon held in Addis Ababa. Our students developed an innovative platform connecting farmers in the Gamo Zone directly with urban buyers, reducing food waste and increasing profits for smallholder farmers. The team of five developers spent 48 hours building the prototype and impressed judges with their practical approach to solving real agricultural challenges. This victory puts AMU on the map as a rising tech hub in Ethiopia.', 'https://images.unsplash.com/photo-1504384308090-c894fdcc538d?w=800,https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=800', 1),

('Lake Abaya Debate Team Reaches Continental Finals', 'AMU debate team has qualified for the African Universities Debating Championship finals after an impressive performance at the East African regional qualifiers in Nairobi. The team, led by club president Birtukan Molla, argued persuasively on topics ranging from regional integration to climate justice. Arba Minch will host the next regional tournament, bringing together 30 universities from across East Africa for a week of intellectual exchange and friendly competition.', 'https://images.unsplash.com/photo-1577962917302-c3e5cbf3537b?w=800,https://images.unsplash.com/photo-1517048676732-d65bc937f952?w=800', 1),

('Forty Springs Festival Showcases AMU Talent', 'The annual Forty Springs Music & Arts Festival was a spectacular celebration of creativity featuring performances from over 200 AMU students. The three-day event included traditional Ethiopian music from the Gamo, Gofa, and Kore ethnic groups, contemporary dance performances, a poetry slam, and an art exhibition showcasing student works inspired by the stunning landscapes of the Rift Valley. The festival attracted over 5,000 visitors including community members from across the Arba Minch region.', 'https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?w=800,https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=800', 1),

('AMU Tech Innovators Launch Solar-Powered IoT Project', 'The AMU Tech Innovators club has launched an ambitious project to deploy solar-powered IoT sensors across Lake Abaya and Lake Chamo to monitor water quality, fish populations, and weather patterns. Working with the Department of Water Resources, students are building low-cost sensor nodes using Arduino and ESP32 microcontrollers. The data will help local communities manage fishing practices and respond to environmental changes. The project has received funding from the Ethiopian Innovation and Technology Ministry.', 'https://images.unsplash.com/photo-1559136555-9303baea8ebd?w=800,https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800', 1),

('Nech Sar Conservation Drive Plants 5,000 Trees', 'The Nech Sar Green Guardians, together with the Arba Minch Department of Forestry, successfully planted 5,000 indigenous tree seedlings around the Nech Sar National Park buffer zone. Over 300 AMU students participated in the two-day event, which aims to restore degraded areas and protect the habitat of endemic species like the Swayne hartebeest. The club has committed to monthly maintenance visits to ensure survival rates exceed 80%. This initiative supports Ethiopia Green Legacy campaign goals.', 'https://images.unsplash.com/photo-1542601906990-b4d3fb778b09?w=800,https://images.unsplash.com/photo-1470071459604-3b5ec3a7fe05?w=800', 2),

('AMU Entrepreneurship Hub Graduates First Cohort', 'The AMU Entrepreneurship Hub celebrated the graduation of its first cohort of 25 student entrepreneurs who completed a 12-week intensive startup bootcamp. Participants developed business plans for ventures ranging from eco-tourism in the Rift Valley lakes region to mobile health clinics serving rural communities around Arba Minch. Three startups have already secured seed funding from local investors. The hub is now accepting applications for the next cohort, which will focus on agri-tech solutions.', 'https://images.unsplash.com/photo-1559136555-9303baea8ebd?w=800,https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?w=800', 2);

-- ============================================================
-- EVENTS (Arba Minch locations)
-- ============================================================
INSERT INTO events (club_id, title, description, start_at, end_at, created_by, latitude, longitude)
VALUES 
(1, 'CodeFest 2026: 24-Hour Hackathon at AMU', 'Join the Arba Minch Coding Club for an intense 24-hour hackathon! Build innovative solutions for local community challenges. Teams of 3-5 will compete for prizes including laptops, internships, and startup seed funding. All skill levels welcome - mentors will be available throughout the event.', '2026-07-15T09:00:00', '2026-07-16T09:00:00', 3, 6.0333, 37.5500),

(1, 'Introduction to Python Programming Workshop', 'A hands-on beginner-friendly Python workshop open to all AMU students. Learn programming fundamentals, data structures, and build your first web scraper. No prior experience needed. Bring your laptop!', '2026-06-20T14:00:00', '2026-06-20T17:00:00', 3, 6.0333, 37.5500),

(2, 'Inter-University Debate Championship - Regional Qualifiers', 'AMU hosts the Southern Ethiopia regional debate qualifiers. Universities from Hawassa, Wolaita Sodo, and Dilla will compete for a spot in the national finals. Topics will focus on African continental development and climate policy.', '2026-07-05T08:00:00', '2026-07-07T18:00:00', 4, 6.0333, 37.5500),

(3, 'Forty Springs Music Concert: Night of Ethiopian Jazz', 'An evening of live Ethiopian jazz and contemporary music featuring AMU student bands and special guest artists from Addis Ababa. All proceeds support the club music education program for local youth.', '2026-06-25T18:00:00', '2026-06-25T22:00:00', 5, 6.0333, 37.5500),

(5, 'Lake Abaya & Chamo Clean-Up Day', 'Join the Nech Sar Green Guardians for a massive clean-up of the lake shores. We will collect plastic waste, plant native vegetation, and conduct water quality testing. Transportation and refreshments provided. Earn community service hours!', '2026-06-28T07:00:00', '2026-06-28T15:00:00', 5, 6.0333, 37.5500),

(7, 'AMU Inter-Department Football Tournament', 'The annual football tournament featuring teams from all AMU colleges. Eight departments compete over two weeks for the championship trophy. Opening ceremony includes a parade and traditional music performances.', '2026-07-01T10:00:00', '2026-07-15T18:00:00', 5, 6.0333, 37.5500),

(8, 'Gamo Gofa Cultural Day Celebration', 'Experience the rich cultural heritage of the Gamo and Gofa peoples! Traditional costume displays, coffee ceremony, folk dances, and storytelling sessions. Students from all backgrounds welcome to learn and participate.', '2026-06-30T09:00:00', '2026-06-30T17:00:00', 4, 6.0333, 37.5500),

(6, 'Startup Pitch Competition: AMU Edition', 'Student entrepreneurs pitch their business ideas to a panel of investors and industry experts. Cash prizes for top 3 startups plus incubation support. Open to all AMU students and recent graduates.', '2026-07-20T13:00:00', '2026-07-20T18:00:00', 3, 6.0333, 37.5500),

(4, 'AI for Social Good: Workshop Series', 'A three-part workshop exploring how artificial intelligence can address challenges in Ethiopian agriculture, healthcare, and education. Hands-on sessions with TensorFlow and computer vision. Certificate upon completion.', '2026-07-08T10:00:00', '2026-07-22T13:00:00', 3, 6.0333, 37.5500);

-- ============================================================
-- EVENT ATTENDEES
-- ============================================================
INSERT INTO event_attendees (event_id, student_id) VALUES (1, 6);
INSERT INTO event_attendees (event_id, student_id) VALUES (1, 10);
INSERT INTO event_attendees (event_id, student_id) VALUES (1, 13);
INSERT INTO event_attendees (event_id, student_id) VALUES (1, 15);
INSERT INTO event_attendees (event_id, student_id) VALUES (2, 7);
INSERT INTO event_attendees (event_id, student_id) VALUES (2, 10);
INSERT INTO event_attendees (event_id, student_id) VALUES (3, 8);
INSERT INTO event_attendees (event_id, student_id) VALUES (3, 11);
INSERT INTO event_attendees (event_id, student_id) VALUES (3, 14);
INSERT INTO event_attendees (event_id, student_id) VALUES (4, 9);
INSERT INTO event_attendees (event_id, student_id) VALUES (4, 12);
INSERT INTO event_attendees (event_id, student_id) VALUES (5, 5);
INSERT INTO event_attendees (event_id, student_id) VALUES (5, 8);
INSERT INTO event_attendees (event_id, student_id) VALUES (5, 11);
INSERT INTO event_attendees (event_id, student_id) VALUES (5, 14);
INSERT INTO event_attendees (event_id, student_id) VALUES (6, 6);
INSERT INTO event_attendees (event_id, student_id) VALUES (6, 9);
INSERT INTO event_attendees (event_id, student_id) VALUES (6, 15);
INSERT INTO event_attendees (event_id, student_id) VALUES (7, 4);
INSERT INTO event_attendees (event_id, student_id) VALUES (7, 8);
INSERT INTO event_attendees (event_id, student_id) VALUES (7, 12);

-- ============================================================
-- ANNOUNCEMENTS
-- ============================================================
INSERT INTO announcements (club_id, title, description, created_by)
VALUES 
(1, 'Hackathon Registration Now Open!', 'Registration for CodeFest 2026 is now open! Form your teams of 3-5 and sign up at the AMU Coding Club office. Early bird registration closes June 30th.', 3),

(1, 'Weekly Coding Sessions Start Tomorrow', 'Our weekly coding sessions begin tomorrow at 4 PM in the CS Lab Building. This semester we will focus on web development with React and Node.js. All members are encouraged to attend.', 3),

(2, 'Debate Tryouts This Saturday', 'Tryouts for the AMU debate team are happening Saturday at 10 AM in the Auditorium. Prepare a 5-minute persuasive speech on any topic of your choice. New members welcome!', 4),

(3, 'Instrument Donation Drive', 'The Forty Springs Music Club is collecting gently used musical instruments for our community music education program. Donations can be dropped at the Student Center Room 12.', 5),

(4, 'IoT Workshop Materials Ready', 'All registered participants for the IoT workshop can pick up their Arduino starter kits from the Engineering Block Room 204. The workshop starts July 8th.', 3),

(5, 'Urgent: Tree Planting This Weekend', 'Due to favorable weather forecasts, we have rescheduled the tree planting activity to this Saturday. Meet at the main gate at 6:30 AM. Bring water and wear closed shoes!', 5),

(6, 'Mentorship Program - Apply Now', 'The Entrepreneurship Hub mentorship program is now accepting applications. Selected students will be paired with successful entrepreneurs from the Arba Minch business community. Deadline: July 15th.', 5),

(1, 'Welcome New Members!', 'A warm welcome to all new members who joined the Arba Minch Coding Club this semester! We are excited to have you. Check your email for onboarding information and your mentor assignment.', 3),

(7, 'Sports Tournament Postponed', 'Due to the upcoming exams, the inter-department football tournament has been postponed to July 1st. All registered teams please confirm your participation with the sports office.', 5),

(8, 'Cultural Day Volunteers Needed', 'We need volunteers for the upcoming Gamo Gofa Cultural Day! Roles include setup, ushering, food service, and cleanup. Sign up at the Cultural Club office by June 25th.', 4);

-- ============================================================
-- FEES
-- ============================================================
INSERT INTO fees (student_id, club_id, amount, purpose, status, date)
VALUES 
(6, 1, 150.00, 'Annual membership fee 2025/26', 'PAID', CURRENT_TIMESTAMP),
(10, 1, 150.00, 'Annual membership fee 2025/26', 'PAID', CURRENT_TIMESTAMP),
(13, 1, 150.00, 'Annual membership fee 2025/26', 'PENDING', CURRENT_TIMESTAMP),
(15, 1, 150.00, 'Annual membership fee 2025/26', 'PAID', CURRENT_TIMESTAMP),
(7, 1, 150.00, 'Annual membership fee 2025/26', 'PAID', CURRENT_TIMESTAMP),
(8, 2, 100.00, 'Debate competition registration fee', 'PAID', CURRENT_TIMESTAMP),
(11, 2, 100.00, 'Debate competition registration fee', 'PENDING', CURRENT_TIMESTAMP),
(14, 2, 100.00, 'Debate competition registration fee', 'PAID', CURRENT_TIMESTAMP),
(9, 3, 200.00, 'Music instrument maintenance fund', 'PAID', CURRENT_TIMESTAMP),
(12, 3, 200.00, 'Music instrument maintenance fund', 'PAID', CURRENT_TIMESTAMP),
(6, 4, 180.00, 'Tech project material fee', 'PAID', CURRENT_TIMESTAMP),
(10, 4, 180.00, 'Tech project material fee', 'PAID', CURRENT_TIMESTAMP),
(15, 4, 180.00, 'Tech project material fee', 'FAILED', CURRENT_TIMESTAMP),
(13, 4, 180.00, 'Tech project material fee', 'PAID', CURRENT_TIMESTAMP),
(5, 5, 50.00, 'Tree planting campaign contribution', 'PAID', CURRENT_TIMESTAMP),
(8, 5, 50.00, 'Tree planting campaign contribution', 'PAID', CURRENT_TIMESTAMP),
(11, 5, 50.00, 'Tree planting campaign contribution', 'PENDING', CURRENT_TIMESTAMP),
(14, 5, 50.00, 'Tree planting campaign contribution', 'PAID', CURRENT_TIMESTAMP),
(7, 6, 250.00, 'Startup bootcamp registration', 'PAID', CURRENT_TIMESTAMP),
(12, 6, 250.00, 'Startup bootcamp registration', 'PAID', CURRENT_TIMESTAMP),
(13, 6, 250.00, 'Startup bootcamp registration', 'PENDING', CURRENT_TIMESTAMP),
(6, 7, 120.00, 'Sports tournament participation fee', 'PAID', CURRENT_TIMESTAMP),
(9, 7, 120.00, 'Sports tournament participation fee', 'PAID', CURRENT_TIMESTAMP),
(15, 7, 120.00, 'Sports tournament participation fee', 'PAID', CURRENT_TIMESTAMP);
