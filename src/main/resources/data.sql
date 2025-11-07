INSERT INTO Students (first_name, last_name, email, password, year_of_stay, gender, role)
VALUES 
('Wolloyew', 'Kindu', 'mlki@xample.com', 'securePass123', 'Second_Year', 'MALE', 'STUDENT'),
('Sara', 'Bekele', 'sara@example.com', 'passSara321', 'First_Year', 'FEMALE', 'STUDENT'),
('Abel', 'Tesfaye', 'abel@example.com', 'passAbel456', 'Third_Year', 'MALE', 'STUDENT'),
('Mimi', 'Dawit', 'mimi@example.com', 'passMimi789', 'Fourth_Year', 'FEMALE', 'STUDENT');

INSERT INTO clubs (title, description, LOGO_URL, club_type, created_at, updated_at)
VALUES 
('Solution Archtect Assocate', 'A student club focused on artificial intelligence, robotics projects, and tech innovations.', 'https://example.com/logos/ai-club.png', 'Acadamic', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Cloud Computing', 'A student club focused on cloud computing, DevOps, and scalable architecture.', 'https://example.com/logos/cloud.png', 'Acadamic', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Cyber Security Club', 'A student club focusing on ethical hacking, network defense, and cybersecurity awareness.', 'https://example.com/logos/cyber.png', 'Sport', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('AI and Robotics Society', 'A club dedicated to AI research, robotics projects, and innovation challenges.', 'https://example.com/logos/robotics.png', 'Creative', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO STUDENT_CLUB (STUDENT_ID,CLUB_ID) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(3, 2),
(4, 3);


ALTER TABLE student_club ADD COLUMN status VARCHAR(20);



INSERT INTO Students (first_name, last_name, email, password, year_of_stay, gender, role)
VALUES 
('System', 'Admin', 'superadmin@example.com', 
 '$2a$10$zA24CX1qgumck8PCojSj6u3w9eFsFKLKtHKe94oXetcmxE7iCoVc6', 
 'First_Year', 'MALE', 'SUPER_ADMIN');

 

INSERT INTO students (
    email,
    first_name,
    last_name,
    gender,
    password,
    role,
    year_of_stay
) VALUES (
    'superadmin2@example.com',
    'System',
    'Admin',
    'MALE', 
    '$2a$10$XKMqiQmnljEtDh12pQbtsuDu0cOseUd3qcTi9LQ4z5TZ1nFwJ79a2',
    'SUPER_ADMIN',
    'First_Year'
);

INSERT INTO students (
    email,
    first_name,
    last_name,
    gender,
    password,
    role,
    year_of_stay
) VALUES (
   'mlolai@example.com',
    'mola',
    'dawit',
    'MALE', 
    '$2a$10$x2NcTWwKXHiWzOvJlbeU4uLph3LH8VurkxQez0f93JU6VvMZpF2tW',
    'ADMIN',
    'First_Year'
);

insert into student_club(club_id,student_id,status)values
(3,7,null);






