-- Insert data into the api_user table
INSERT INTO api_user (id, subject, email, display_name)
VALUES ('user1', 'subject1', 'alice.smith@example.com', 'Alice Smith'),
       ('user2', 'subject2', 'bob.johnson@example.com', 'Bob Johnson'),
       ('user3', 'subject3', 'charlie.brown@example.com', 'Charlie Brown'),
       ('user4', 'subject4', 'david.williams@example.com', 'David Williams');

-- Insert users into the person table
INSERT INTO person (id, api_user_fk, first_name, last_name, email)
VALUES ('00000000-0000-0000-0000-000000000001', 'user1', 'Alice', 'Smith', 'alice.smith@example.com'),
       ('00000000-0000-0000-0000-000000000002', 'user2', 'Bob', 'Johnson', 'bob.johnson@example.com'),
       ('00000000-0000-0000-0000-000000000003', 'user3', 'Charlie', 'Brown', 'charlie.brown@example.com'),
       ('00000000-0000-0000-0000-000000000004', 'user4', 'David', 'Williams', 'david.williams@example.com');
-- Insert students and teacher into their respective tables
INSERT INTO student (person_fk)
VALUES ('00000000-0000-0000-0000-000000000001'),
       ('00000000-0000-0000-0000-000000000002'),
       ('00000000-0000-0000-0000-000000000003');

INSERT INTO teacher (person_fk)
VALUES ('00000000-0000-0000-0000-000000000004');

-- Insert courses into the course table
INSERT INTO course (id, name, semester, year)
VALUES (1, 'PDG', 'T1', 2024),
       (2, 'PRG1', 'T2', 2024),
       (3, 'ASD', 'T3', 2024);

-- Insert rooms into the room table
INSERT INTO room (id, name)
VALUES (1, 'Room 101'),
       (2, 'Room 102'),
       (3, 'Room 103');

-- Insert classes into the school_class table
INSERT INTO school_class (id, name, course_fk, day_of_week, start, "end", room_fk, teacher_fk)
VALUES (1, 'PDG(2024)-A-C1', 1, 1, '08:00:00', '10:00:00', 1, '00000000-0000-0000-0000-000000000004'),
       (2, 'PDG(2024)-A-L1', 1, 2, '10:00:00', '12:00:00', 1, '00000000-0000-0000-0000-000000000004'),
       (3, 'PRG1(2024)-A-C1', 2, 3, '08:00:00', '10:00:00', 2, '00000000-0000-0000-0000-000000000004'),
       (4, 'PRG1(2024)-A-L1', 2, 4, '10:00:00', '12:00:00', 2, '00000000-0000-0000-0000-000000000004'),
       (5, 'ASD(2024)-A-C1', 3, 5, '08:00:00', '10:00:00', 3, '00000000-0000-0000-0000-000000000004'),
       (6, 'ASD(2024)-A-L1', 3, 6, '10:00:00', '12:00:00', 3, '00000000-0000-0000-0000-000000000004');

-- Enroll students into classes
INSERT INTO class_student (class_fk, student_fk)
VALUES (1, '00000000-0000-0000-0000-000000000001'),
       (1, '00000000-0000-0000-0000-000000000002'),
       (1, '00000000-0000-0000-0000-000000000003'),
       (2, '00000000-0000-0000-0000-000000000001'),
       (2, '00000000-0000-0000-0000-000000000002'),
       (2, '00000000-0000-0000-0000-000000000003'),
       (3, '00000000-0000-0000-0000-000000000001'),
       (3, '00000000-0000-0000-0000-000000000002'),
       (3, '00000000-0000-0000-0000-000000000003'),
       (4, '00000000-0000-0000-0000-000000000001'),
       (4, '00000000-0000-0000-0000-000000000002'),
       (4, '00000000-0000-0000-0000-000000000003'),
       (5, '00000000-0000-0000-0000-000000000001'),
       (5, '00000000-0000-0000-0000-000000000002'),
       (5, '00000000-0000-0000-0000-000000000003'),
       (6, '00000000-0000-0000-0000-000000000001'),
       (6, '00000000-0000-0000-0000-000000000002'),
       (6, '00000000-0000-0000-0000-000000000003');

-- Generate class sessions for the past 14 days
-- Define the current date using a PostgreSQL-compatible method
DO $$
    DECLARE
        current_date DATE := CURRENT_DATE;
    BEGIN
        INSERT INTO class_session (id, school_class_fk, dt_session)
        VALUES
            ('11111111-1111-1111-1111-111111111111', 1, current_date - INTERVAL '14 days'),
            ('11111111-1111-1111-1111-111111111112', 1, current_date - INTERVAL '7 days'),
            ('11111111-1111-1111-1111-111111111113', 2, current_date - INTERVAL '13 days'),
            ('11111111-1111-1111-1111-111111111114', 2, current_date - INTERVAL '6 days'),
            ('11111111-1111-1111-1111-111111111115', 3, current_date - INTERVAL '12 days'),
            ('11111111-1111-1111-1111-111111111116', 3, current_date - INTERVAL '5 days'),
            ('11111111-1111-1111-1111-111111111117', 4, current_date - INTERVAL '11 days'),
            ('11111111-1111-1111-1111-111111111118', 4, current_date - INTERVAL '4 days'),
            ('11111111-1111-1111-1111-111111111119', 5, current_date - INTERVAL '10 days'),
            ('11111111-1111-1111-1111-111111111120', 5, current_date - INTERVAL '3 days'),
            ('11111111-1111-1111-1111-111111111121', 6, current_date - INTERVAL '9 days'),
            ('11111111-1111-1111-1111-111111111122', 6, current_date - INTERVAL '2 days');
    END $$;

-- Generate presence records for each student in each session
INSERT INTO presence (id, student_fk, class_session_fk)
VALUES ('22222222-2222-2222-2222-222222222221', '00000000-0000-0000-0000-000000000001',
        '11111111-1111-1111-1111-111111111111'),
       ('22222222-2222-2222-2222-222222222222', '00000000-0000-0000-0000-000000000002',
        '11111111-1111-1111-1111-111111111111'),
       ('22222222-2222-2222-2222-222222222223', '00000000-0000-0000-0000-000000000003',
        '11111111-1111-1111-1111-111111111111'),
       -- Repeat the above for each student and each session
       ('22222222-2222-2222-2222-222222222224', '00000000-0000-0000-0000-000000000001',
        '11111111-1111-1111-1111-111111111112'),
       ('22222222-2222-2222-2222-222222222225', '00000000-0000-0000-0000-000000000002',
        '11111111-1111-1111-1111-111111111112'),
       ('22222222-2222-2222-2222-222222222226', '00000000-0000-0000-0000-000000000003',
        '11111111-1111-1111-1111-111111111112'),
       -- Continue for all class sessions and students
       ('22222222-2222-2222-2222-222222222227', '00000000-0000-0000-0000-000000000001',
        '11111111-1111-1111-1111-111111111113'),
       ('22222222-2222-2222-2222-222222222228', '00000000-0000-0000-0000-000000000002',
        '11111111-1111-1111-1111-111111111113'),
       ('22222222-2222-2222-2222-222222222229', '00000000-0000-0000-0000-000000000003',
        '11111111-1111-1111-1111-111111111113');

-- Continue inserting presence records for all students and all sessions
