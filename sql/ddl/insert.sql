-- Insert data into the api_user table
INSERT INTO api_user (id, subject, email, display_name)
VALUES ('user1', 'subject1', 'alice.smith@example.com', 'Alice Smith'),
       ('user2', 'subject2', 'bob.johnson@example.com', 'Bob Johnson'),
       ('user3', 'subject3', 'charlie.brown@example.com', 'Charlie Brown'),
       ('user4', 'subject4', 'david.williams@example.com', 'David Williams'),
       ('user5', 'subject5', 'emily.white@example.com', 'Emily White'),
       ('user6', 'subject6', 'john.doe@example.com', 'John Doe'),
       ('user7', 'subject7', 'jane.doe@example.com', 'Jane Doe'),
       ('user8', 'subject8', 'michael.brown@example.com', 'Michael Brown'),
       ('user9', 'subject9', 'sarah.davis@example.com', 'Sarah Davis'),
       ('user10', 'subject10', 'david.miller@example.com', 'David Miller'),
       ('user11', 'subject11', 'linda.wilson@example.com', 'Linda Wilson'),
       ('user12', 'subject12', 'chris.moore@example.com', 'Chris Moore'),
       ('user13', 'subject13', 'lisa.taylor@example.com', 'Lisa Taylor'),
       ('user14', 'subject14', 'kevin.anderson@example.com', 'Kevin Anderson'),
       ('user15', 'subject15', 'laura.thomas@example.com', 'Laura Thomas'),
       ('user16', 'subject16', 'james.jackson@example.com', 'James Jackson'),
       ('user17', 'subject17', 'megan.lee@example.com', 'Megan Lee'),
       ('user18', 'subject18', 'robert.walker@example.com', 'Robert Walker'),
       ('user19', 'subject19', 'michelle.hall@example.com', 'Michelle Hall'),
       ('user20', 'subject20', 'brian.allen@example.com', 'Brian Allen'),
       ('user21', 'subject21', 'samantha.young@example.com', 'Samantha Young'),
       ('user22', 'subject22', 'jessica.hernandez@example.com', 'Jessica Hernandez'),
       ('user23', 'subject23', 'jason.king@example.com', 'Jason King'),
       ('user24', 'subject24', 'angela.wright@example.com', 'Angela Wright');

-- Insert users into the person table
INSERT INTO person (id, api_user_fk, first_name, last_name, email)
VALUES ('00000000-0000-0000-0000-000000000001', 'user1', 'Alice', 'Smith', 'alice.smith@example.com'),
       ('00000000-0000-0000-0000-000000000002', 'user2', 'Bob', 'Johnson', 'bob.johnson@example.com'),
       ('00000000-0000-0000-0000-000000000003', 'user3', 'Charlie', 'Brown', 'charlie.brown@example.com'),
       ('00000000-0000-0000-0000-000000000004', 'user4', 'David', 'Williams', 'david.williams@example.com'),
       ('00000000-0000-0000-0000-000000000005', 'user5', 'Emily', 'White', 'emily.white@example.com'),
       ('00000000-0000-0000-0000-000000000006', 'user6', 'John', 'Doe', 'john.doe@example.com'),
       ('00000000-0000-0000-0000-000000000007', 'user7', 'Jane', 'Doe', 'jane.doe@example.com'),
       ('00000000-0000-0000-0000-000000000008', 'user8', 'Michael', 'Brown', 'michael.brown@example.com'),
       ('00000000-0000-0000-0000-000000000009', 'user9', 'Sarah', 'Davis', 'sarah.davis@example.com'),
       ('00000000-0000-0000-0000-000000000010', 'user10', 'David', 'Miller', 'david.miller@example.com'),
       ('00000000-0000-0000-0000-000000000011', 'user11', 'Linda', 'Wilson', 'linda.wilson@example.com'),
       ('00000000-0000-0000-0000-000000000012', 'user12', 'Chris', 'Moore', 'chris.moore@example.com'),
       ('00000000-0000-0000-0000-000000000013', 'user13', 'Lisa', 'Taylor', 'lisa.taylor@example.com'),
       ('00000000-0000-0000-0000-000000000014', 'user14', 'Kevin', 'Anderson', 'kevin.anderson@example.com'),
       ('00000000-0000-0000-0000-000000000015', 'user15', 'Laura', 'Thomas', 'laura.thomas@example.com'),
       ('00000000-0000-0000-0000-000000000016', 'user16', 'James', 'Jackson', 'james.jackson@example.com'),
       ('00000000-0000-0000-0000-000000000017', 'user17', 'Megan', 'Lee', 'megan.lee@example.com'),
       ('00000000-0000-0000-0000-000000000018', 'user18', 'Robert', 'Walker', 'robert.walker@example.com'),
       ('00000000-0000-0000-0000-000000000019', 'user19', 'Michelle', 'Hall', 'michelle.hall@example.com'),
       ('00000000-0000-0000-0000-000000000020', 'user20', 'Brian', 'Allen', 'brian.allen@example.com'),
       ('00000000-0000-0000-0000-000000000021', 'user21', 'Samantha', 'Young', 'samantha.young@example.com'),
       ('00000000-0000-0000-0000-000000000022', 'user22', 'Jessica', 'Hernandez', 'jessica.hernandez@example.com'),
       ('00000000-0000-0000-0000-000000000023', 'user23', 'Jason', 'King', 'jason.king@example.com'),
       ('00000000-0000-0000-0000-000000000024', 'user24', 'Angela', 'Wright', 'angela.wright@example.com');

-- Insert students and teacher into their respective tables
INSERT INTO student (person_fk)
VALUES ('00000000-0000-0000-0000-000000000001'),
       ('00000000-0000-0000-0000-000000000002'),
       ('00000000-0000-0000-0000-000000000003'),
       ('00000000-0000-0000-0000-000000000005'),
       ('00000000-0000-0000-0000-000000000006'),
       ('00000000-0000-0000-0000-000000000007'),
       ('00000000-0000-0000-0000-000000000008'),
       ('00000000-0000-0000-0000-000000000009'),
       ('00000000-0000-0000-0000-000000000010'),
       ('00000000-0000-0000-0000-000000000011'),
       ('00000000-0000-0000-0000-000000000012'),
       ('00000000-0000-0000-0000-000000000013'),
       ('00000000-0000-0000-0000-000000000014'),
       ('00000000-0000-0000-0000-000000000015'),
       ('00000000-0000-0000-0000-000000000016'),
       ('00000000-0000-0000-0000-000000000017'),
       ('00000000-0000-0000-0000-000000000018'),
       ('00000000-0000-0000-0000-000000000019'),
       ('00000000-0000-0000-0000-000000000020'),
       ('00000000-0000-0000-0000-000000000021'),
       ('00000000-0000-0000-0000-000000000022'),
       ('00000000-0000-0000-0000-000000000023'),
       ('00000000-0000-0000-0000-000000000024');

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
       (1, '00000000-0000-0000-0000-000000000005'),
       (1, '00000000-0000-0000-0000-000000000006'),
       (1, '00000000-0000-0000-0000-000000000017'),
       (1, '00000000-0000-0000-0000-000000000023'),
       (2, '00000000-0000-0000-0000-000000000024'),
       (2, '00000000-0000-0000-0000-000000000007'),
       (2, '00000000-0000-0000-0000-000000000008'),
       (2, '00000000-0000-0000-0000-000000000001'),
       (2, '00000000-0000-0000-0000-000000000002'),
       (2, '00000000-0000-0000-0000-000000000003'),
       (2, '00000000-0000-0000-0000-000000000018'),
       (3, '00000000-0000-0000-0000-000000000019'),
       (3, '00000000-0000-0000-0000-000000000009'),
       (3, '00000000-0000-0000-0000-000000000010'),
       (3, '00000000-0000-0000-0000-000000000001'),
       (3, '00000000-0000-0000-0000-000000000002'),
       (3, '00000000-0000-0000-0000-000000000003'),
       (4, '00000000-0000-0000-0000-000000000001'),
       (4, '00000000-0000-0000-0000-000000000002'),
       (4, '00000000-0000-0000-0000-000000000003'),
       (4, '00000000-0000-0000-0000-000000000011'),
       (4, '00000000-0000-0000-0000-000000000012'),
       (4, '00000000-0000-0000-0000-000000000020'),
       (5, '00000000-0000-0000-0000-000000000021'),
       (5, '00000000-0000-0000-0000-000000000001'),
       (5, '00000000-0000-0000-0000-000000000002'),
       (5, '00000000-0000-0000-0000-000000000003'),
       (5, '00000000-0000-0000-0000-000000000013'),
       (5, '00000000-0000-0000-0000-000000000014'),
       (6, '00000000-0000-0000-0000-000000000001'),
       (6, '00000000-0000-0000-0000-000000000002'),
       (6, '00000000-0000-0000-0000-000000000003'),
       (6, '00000000-0000-0000-0000-000000000015'),
       (6, '00000000-0000-0000-0000-000000000016'),
       (6, '00000000-0000-0000-0000-000000000022');

DO
$$
    DECLARE
        current_date DATE := CURRENT_DATE;
    BEGIN
        INSERT INTO class_session (id, school_class_fk, dt_session)
        VALUES ('11111111-1111-1111-1111-111111111111', 1, current_date - INTERVAL '14 days'),
               ('11111111-1111-1111-1111-111111111112', 1, current_date - INTERVAL '7 days'),
               ('11111111-1111-1111-1111-211111111123', 1, current_date - INTERVAL '8 days'),
               ('11111111-1111-1111-1111-311111111123', 1, current_date - INTERVAL '4 days'),
               ('11111111-1111-1111-1111-411111111123', 1, current_date - INTERVAL '9 days'),
               ('11111111-1111-1111-1111-511111111123', 1, current_date - INTERVAL '10 days'),
               ('11111111-1111-1111-1111-111111111113', 2, current_date - INTERVAL '13 days'),
               ('11111111-1111-1111-1111-111111111114', 2, current_date - INTERVAL '6 days'),
               ('11111111-1111-1111-1111-111111111124', 2, current_date - INTERVAL '4 days'),
               ('11111111-1111-1111-1111-111111111125', 3, current_date - INTERVAL '1 days'),
               ('11111111-1111-1111-1111-111111111115', 3, current_date - INTERVAL '12 days'),
               ('11111111-1111-1111-1111-111111111116', 3, current_date - INTERVAL '5 days'),
               ('11111111-1111-1111-1111-111111111117', 4, current_date - INTERVAL '11 days'),
               ('11111111-1111-1111-1111-111111111118', 4, current_date - INTERVAL '4 days'),
               ('11111111-1111-1111-1111-111111111126', 4, current_date - INTERVAL '3 days'),
               ('11111111-1111-1111-1111-111111111127', 5, current_date - INTERVAL '5 days'),
               ('11111111-1111-1111-1111-111111111119', 5, current_date - INTERVAL '10 days'),
               ('11111111-1111-1111-1111-111111111120', 5, current_date - INTERVAL '3 days'),
               ('11111111-1111-1111-1111-111111111121', 6, current_date - INTERVAL '9 days'),
               ('11111111-1111-1111-1111-111111111122', 6, current_date - INTERVAL '2 days'),
               ('11111111-1111-1111-1111-111111111128', 6, current_date - INTERVAL '6 days');
    END
$$;

INSERT INTO presence (id, student_fk, class_session_fk)
VALUES ('22222222-2222-2222-2222-222222222221', '00000000-0000-0000-0000-000000000001', '11111111-1111-1111-1111-111111111111'),
       ('22222222-2222-2222-2222-226622222223', '00000000-0000-0000-0000-000000000002', '11111111-1111-1111-1111-111111111111'),
       ('22222222-2222-2222-2222-226222222223', '00000000-0000-0000-0000-000000000003', '11111111-1111-1111-1111-111111111111'),
       ('33333333-3333-3333-3333-333333333308', '00000000-0000-0000-0000-000000000005', '11111111-1111-1111-1111-111111111111'),
       ('22222222-2222-2222-2222-222442222239', '00000000-0000-0000-0000-000000000006', '11111111-1111-1111-1111-111111111111'),
       ('22222222-2222-2222-2222-211222222239', '00000000-0000-0000-0000-000000000017', '11111111-1111-1111-1111-111111111111'),
       ('22222222-2222-2222-2222-222222002239', '00000000-0000-0000-0000-000000000023', '11111111-1111-1111-1111-111111111111'),

       ('22222222-2222-2222-2222-222222222224', '00000000-0000-0000-0000-000000000001', '11111111-1111-1111-1111-111111111112'),
       ('22222222-2222-2222-2222-222222222225', '00000000-0000-0000-0000-000000000002', '11111111-1111-1111-1111-111111111112'),
       ('33333333-3333-3333-3333-333333333309', '00000000-0000-0000-0000-000000000006', '11111111-1111-1111-1111-111111111112'),

        ('22222222-2222-2222-2222-222222222226', '00000000-0000-0000-0000-000000000001', '11111111-1111-1111-1111-211111111123'),
        ('22222222-2222-2222-2222-222222222223', '00000000-0000-0000-0000-000000000002', '11111111-1111-1111-1111-211111111123'),


        ('22222222-2222-2222-2222-222222222231', '00000000-0000-0000-0000-000000000001', '11111111-1111-1111-1111-411111111123'),
        ('22222222-2222-2222-2222-224222222232', '00000000-0000-0000-0000-000000000002', '11111111-1111-1111-1111-411111111123'),
        ('22222222-2222-2222-2222-222222222233', '00000000-0000-0000-0000-000000000003', '11111111-1111-1111-1111-411111111123'),

        ('22222222-2222-2222-2222-224422222234', '00000000-0000-0000-0000-000000000005', '11111111-1111-1111-1111-511111111123'),


       ('22222222-2222-2222-2222-222222222227', '00000000-0000-0000-0000-000000000001', '11111111-1111-1111-1111-111111111113'),
       ('22222222-2222-2222-2222-222222222228', '00000000-0000-0000-0000-000000000002', '11111111-1111-1111-1111-111111111113'),
       ('22222222-2222-2222-2222-222222222299', '00000000-0000-0000-0000-000000000003', '11111111-1111-1111-1111-111111111113'),
       ('22222222-2222-2222-2222-222222222232', '00000000-0000-0000-0000-000000000007', '11111111-1111-1111-1111-111111111113'),
       ('22222222-2222-2222-2222-222222272233', '00000000-0000-0000-0000-000000000008', '11111111-1111-1111-1111-111111111113'),
       ('22222222-2222-2222-2222-222222222234', '00000000-0000-0000-0000-000000000009', '11111111-1111-1111-1111-111111111114'),
       ('22222222-2222-2222-2222-222222222235', '00000000-0000-0000-0000-000000000010', '11111111-1111-1111-1111-111111111115'),
       ('22222222-2222-2222-2222-222222222236', '00000000-0000-0000-0000-000000000011', '11111111-1111-1111-1111-111111111116'),
       ('22222222-2222-2222-2222-222222222237', '00000000-0000-0000-0000-000000000012', '11111111-1111-1111-1111-111111111117'),
       ('22222222-2222-2222-2222-222222222238', '00000000-0000-0000-0000-000000000013', '11111111-1111-1111-1111-111111111118'),
       ('22222222-2222-2222-2222-222299222239', '00000000-0000-0000-0000-000000000014', '11111111-1111-1111-1111-111111111119'),
       ('22222222-2222-2222-2222-222222222240', '00000000-0000-0000-0000-000000000015', '11111111-1111-1111-1111-111111111120'),
       ('22222222-2222-2222-2222-222222222241', '00000000-0000-0000-0000-000000000016', '11111111-1111-1111-1111-111111111121'),
       ('22222222-2222-2222-2222-222222222242', '00000000-0000-0000-0000-000000000017', '11111111-1111-1111-1111-111111111122'),
       ('22222222-2222-2222-2222-222222222244', '00000000-0000-0000-0000-000000000019', '11111111-1111-1111-1111-111111111124'),
       ('22222222-2222-2222-2222-222222222245', '00000000-0000-0000-0000-000000000020', '11111111-1111-1111-1111-111111111125'),
       ('22222222-2222-2222-2222-222222222246', '00000000-0000-0000-0000-000000000021', '11111111-1111-1111-1111-111111111126'),
       ('22222222-2222-2222-2222-222222222247', '00000000-0000-0000-0000-000000000022', '11111111-1111-1111-1111-111111111127'),
       ('22222222-2222-2222-2222-222222222248', '00000000-0000-0000-0000-000000000023', '11111111-1111-1111-1111-111111111128'),
       ('22222222-2222-2222-2222-222222222249', '00000000-0000-0000-0000-000000000024', '11111111-1111-1111-1111-111111111128'),
       ('33333333-3333-3333-3333-333333333305', '00000000-0000-0000-0000-000000000002', '11111111-1111-1111-1111-111111111113'),
       ('33333333-3333-3333-3333-333333333306', '00000000-0000-0000-0000-000000000002', '11111111-1111-1111-1111-111111111117'),
       ('33333333-3333-3333-3333-333333333307', '00000000-0000-0000-0000-000000000003', '11111111-1111-1111-1111-111111111119'),
       ('33333333-3333-3333-3333-333333333310', '00000000-0000-0000-0000-000000000007', '11111111-1111-1111-1111-111111111113'),
       ('33333333-3333-3333-3333-333333333311', '00000000-0000-0000-0000-000000000008', '11111111-1111-1111-1111-111111111114'),
       ('33333333-3333-3333-3333-333333333312', '00000000-0000-0000-0000-000000000009', '11111111-1111-1111-1111-111111111115'),
       ('33333333-3333-3333-3333-333333333313', '00000000-0000-0000-0000-000000000010', '11111111-1111-1111-1111-111111111116'),
       ('33333333-3333-3333-3333-333333333314', '00000000-0000-0000-0000-000000000011', '11111111-1111-1111-1111-111111111117'),
       ('33333333-3333-3333-3333-333333333315', '00000000-0000-0000-0000-000000000012', '11111111-1111-1111-1111-111111111118'),
       ('33333333-3333-3333-3333-333333333316', '00000000-0000-0000-0000-000000000013', '11111111-1111-1111-1111-111111111119'),
       ('33333333-3333-3333-3333-333333333317', '00000000-0000-0000-0000-000000000014', '11111111-1111-1111-1111-111111111120'),
       ('33333333-3333-3333-3333-333333333318', '00000000-0000-0000-0000-000000000015', '11111111-1111-1111-1111-111111111121'),
       ('33333333-3333-3333-3333-333333333319', '00000000-0000-0000-0000-000000000016', '11111111-1111-1111-1111-111111111122'),
       ('33333333-3333-3333-3333-333333333321', '00000000-0000-0000-0000-000000000018', '11111111-1111-1111-1111-111111111124'),
       ('33333333-3333-3333-3333-333333333322', '00000000-0000-0000-0000-000000000019', '11111111-1111-1111-1111-111111111125'),
       ('33333333-3333-3333-3333-333333333323', '00000000-0000-0000-0000-000000000020', '11111111-1111-1111-1111-111111111126'),
       ('33333333-3333-3333-3333-333333333324', '00000000-0000-0000-0000-000000000021', '11111111-1111-1111-1111-111111111127'),
       ('33333333-3333-3333-3333-333333333325', '00000000-0000-0000-0000-000000000022', '11111111-1111-1111-1111-111111111128'),
       ('33333333-3333-3333-3333-333333333326', '00000000-0000-0000-0000-000000000023', '11111111-1111-1111-1111-111111111128'),
       ('33333333-3333-3333-3333-333333333327', '00000000-0000-0000-0000-000000000024', '11111111-1111-1111-1111-111111111128');
