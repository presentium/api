INSERT INTO api_user (id, subject, email, display_name) VALUES ('e40ffef226ecef7538b45785a7f5f5f444fa89c381550d4d30d163e54a171cfe', 'TEST.romuald.mosqueron', 'info+romuald.mosqueron@presentium.ch', 'Romuald Mosqueron');

INSERT INTO person (id, api_user_fk, name) VALUES ('8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', null, 'Vicky Butty');
INSERT INTO person (id, api_user_fk, name) VALUES ('888e4b08-b4d5-4cd7-bfb0-921918170710', null, 'Massimo Stefani');
INSERT INTO person (id, api_user_fk, name) VALUES ('0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', null, 'Léa Cherpillod');
INSERT INTO person (id, api_user_fk, name) VALUES ('5a7e665c-c994-464d-a16a-6e1e150f7f30', null, 'Christophe Roulin');
INSERT INTO person (id, api_user_fk, name) VALUES ('5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', null, 'Sacha Butty');
INSERT INTO person (id, api_user_fk, name) VALUES ('66daeef4-7487-4601-87ce-f744f8d66b2b', 'e40ffef226ecef7538b45785a7f5f5f444fa89c381550d4d30d163e54a171cfe', 'Romuald Mosqueron');

INSERT INTO student (person_fk, card_id) VALUES ('8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', null);
INSERT INTO student (person_fk, card_id) VALUES ('888e4b08-b4d5-4cd7-bfb0-921918170710', null);
INSERT INTO student (person_fk, card_id) VALUES ('0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', null);
INSERT INTO student (person_fk, card_id) VALUES ('5a7e665c-c994-464d-a16a-6e1e150f7f30', null);
INSERT INTO student (person_fk, card_id) VALUES ('5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', null);
INSERT INTO teacher (person_fk) VALUES ('66daeef4-7487-4601-87ce-f744f8d66b2b');

INSERT INTO course (id, name, semester, year) VALUES (18, 'AST', 'S1', 2024);
INSERT INTO course (id, name, semester, year) VALUES (19, 'WEB', 'S1', 2024);
INSERT INTO course (id, name, semester, year) VALUES (20, 'SOS', 'S1', 2024);
INSERT INTO course (id, name, semester, year) VALUES (21, 'SLB', 'S1', 2024);
INSERT INTO course (id, name, semester, year) VALUES (22, 'DAA-TIC', 'S1', 2024);
INSERT INTO course (id, name, semester, year) VALUES (23, 'SLH', 'S1', 2024);
INSERT INTO course (id, name, semester, year) VALUES (27, 'SDR', 'S1', 2024);
INSERT INTO course (id, name, semester, year) VALUES (28, 'SIO', 'S1', 2024);
INSERT INTO course (id, name, semester, year) VALUES (29, 'AMT', 'S1', 2024);
INSERT INTO course (id, name, semester, year) VALUES (30, 'MAC', 'S1', 2024);

INSERT INTO room (id, name) VALUES (14, 'C41');
INSERT INTO room (id, name) VALUES (15, 'G02');
INSERT INTO room (id, name) VALUES (16, 'J03');
INSERT INTO room (id, name) VALUES (17, 'C23');
INSERT INTO room (id, name) VALUES (18, 'F01');
INSERT INTO room (id, name) VALUES (23, 'G04');
INSERT INTO room (id, name) VALUES (24, 'G03');
INSERT INTO room (id, name) VALUES (25, 'H03');
INSERT INTO room (id, name) VALUES (26, 'C37');

INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (16, 'P1', 18, 1, '09:15:00', '12:45:00', 14, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (17, 'C1', 19, 3, '18:30:00', '20:50:00', 15, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (18, 'L1', 19, 4, '18:30:00', '20:50:00', 16, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (19, 'L1', 20, 4, '14:55:00', '16:30:00', 15, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (20, 'C1', 20, 4, '13:15:00', '14:45:00', 15, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (21, 'L1', 21, 1, '14:00:00', '16:30:00', 14, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (22, 'C1', 21, 1, '14:00:00', '16:30:00', 14, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (23, 'L1', 22, 3, '16:35:00', '18:05:00', 17, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'B');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (24, 'C1', 22, 1, '16:35:00', '18:05:00', 18, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'AB');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (25, 'C1', 23, 3, '13:15:00', '14:45:00', 17, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'AB');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (26, 'L1', 23, 3, '14:55:00', '16:30:00', 17, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'B');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (35, 'C1', 27, 1, '13:15:00', '14:45:00', 23, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (36, 'L1', 27, 1, '14:55:00', '16:30:00', 23, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (37, 'C1', 28, 0, '14:55:00', '16:30:00', 24, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (38, 'L1', 28, 4, '10:25:00', '12:00:00', 25, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (39, 'L1', 29, 4, '15:45:00', '18:05:00', 16, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (40, 'C1', 29, 4, '13:15:00', '15:40:00', 16, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (41, 'L1', 22, 3, '14:55:00', '16:30:00', 26, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (42, 'L1', 30, 3, '16:35:00', '18:05:00', 26, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (43, 'C1', 30, 0, '13:15:00', '14:45:00', 17, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');
INSERT INTO school_class (id, name, course_fk, day_of_week, dt_start, dt_end, room_fk, teacher_fk, class_group) VALUES (44, 'L1', 23, 4, '08:30:00', '10:00:00', 25, '66daeef4-7487-4601-87ce-f744f8d66b2b', 'A');

INSERT INTO class_student (class_fk, student_fk) VALUES (16, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (16, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (16, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (17, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (17, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (17, '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076');
INSERT INTO class_student (class_fk, student_fk) VALUES (17, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (18, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (18, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (18, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (18, '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076');
INSERT INTO class_student (class_fk, student_fk) VALUES (19, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (19, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (19, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (20, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (20, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (20, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (21, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (21, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (21, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (22, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (22, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (22, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (23, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (23, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (23, '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076');
INSERT INTO class_student (class_fk, student_fk) VALUES (23, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (41, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (24, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (24, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (24, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (24, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (24, '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076');
INSERT INTO class_student (class_fk, student_fk) VALUES (25, '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076');
INSERT INTO class_student (class_fk, student_fk) VALUES (25, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (25, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (25, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (25, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (26, '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076');
INSERT INTO class_student (class_fk, student_fk) VALUES (26, '5a7e665c-c994-464d-a16a-6e1e150f7f30');
INSERT INTO class_student (class_fk, student_fk) VALUES (26, '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0');
INSERT INTO class_student (class_fk, student_fk) VALUES (26, '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7');
INSERT INTO class_student (class_fk, student_fk) VALUES (44, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (35, '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076');
INSERT INTO class_student (class_fk, student_fk) VALUES (35, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (36, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (36, '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076');
INSERT INTO class_student (class_fk, student_fk) VALUES (37, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (38, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (39, '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076');
INSERT INTO class_student (class_fk, student_fk) VALUES (39, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (40, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (40, '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076');
INSERT INTO class_student (class_fk, student_fk) VALUES (42, '888e4b08-b4d5-4cd7-bfb0-921918170710');
INSERT INTO class_student (class_fk, student_fk) VALUES (43, '888e4b08-b4d5-4cd7-bfb0-921918170710');

INSERT INTO class_session (id, school_class_fk, dt_session)
VALUES  ('6270e4d9-47d1-4a37-af5b-111111111111', 16, '2024-08-20 09:15:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-111111111112', 17, '2024-08-22 18:30:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-211111111123', 18, '2024-08-23 18:30:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-311111111124', 19, '2024-08-23 14:55:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-411111111125', 20, '2024-08-23 13:15:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-511111111126', 21, '2024-08-20 14:00:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-611111111127', 22, '2024-08-20 14:00:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-711111111128', 23, '2024-08-22 16:35:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-811111111129', 24, '2024-08-20 16:35:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-911111111130', 25, '2024-08-22 13:15:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-101111111131', 26, '2024-08-22 14:55:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-111111111132', 35, '2024-08-20 13:15:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-121111111133', 36, '2024-08-20 14:55:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-131111111134', 37, '2024-08-19 14:55:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-141111111135', 38, '2024-08-23 10:25:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-151111111136', 39, '2024-08-23 15:45:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-161111111137', 40, '2024-08-23 13:15:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-171111111138', 41, '2024-08-22 14:55:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-181111111139', 42, '2024-08-22 16:35:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-191111111140', 43, '2024-08-19 13:15:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-201111111141', 44, '2024-08-23 08:30:00'::timestamp),
        ('6270e4d9-47d1-4a37-af5b-211111111142', 16, ('2024-08-20 09:15:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-221111111143', 17, ('2024-08-22 18:30:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-231111111144', 18, ('2024-08-23 18:30:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-241111111145', 19, ('2024-08-23 14:55:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-251111111146', 20, ('2024-08-23 13:15:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-261111111147', 21, ('2024-08-20 14:00:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-271111111148', 22, ('2024-08-20 14:00:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-281111111149', 23, ('2024-08-22 16:35:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-291111111150', 24, ('2024-08-20 16:35:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-301111111151', 25, ('2024-08-22 13:15:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-311111111152', 26, ('2024-08-22 14:55:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-321111111153', 35, ('2024-08-20 13:15:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-331111111154', 36, ('2024-08-20 14:55:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-341111111155', 37, ('2024-08-19 14:55:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-351111111156', 38, ('2024-08-23 10:25:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-361111111157', 39, ('2024-08-23 15:45:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-371111111158', 40, ('2024-08-23 13:15:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-381111111159', 41, ('2024-08-22 14:55:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-391111111160', 42, ('2024-08-22 16:35:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-401111111161', 43, ('2024-08-19 13:15:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-411111111162', 44, ('2024-08-23 08:30:00'::timestamp + interval '1 week')),
        ('6270e4d9-47d1-4a37-af5b-421111111163', 16, ('2024-08-20 09:15:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-431111111164', 17, ('2024-08-22 18:30:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-441111111165', 18, ('2024-08-23 18:30:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-451111111166', 19, ('2024-08-23 14:55:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-461111111167', 20, ('2024-08-23 13:15:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-471111111168', 21, ('2024-08-20 14:00:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-481111111169', 22, ('2024-08-20 14:00:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-491111111170', 23, ('2024-08-22 16:35:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-501111111171', 24, ('2024-08-20 16:35:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-511111111172', 25, ('2024-08-22 13:15:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-521111111173', 26, ('2024-08-22 14:55:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-531111111174', 35, ('2024-08-20 13:15:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-541111111175', 36, ('2024-08-20 14:55:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-551111111176', 37, ('2024-08-19 14:55:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-561111111177', 38, ('2024-08-23 10:25:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-571111111178', 39, ('2024-08-23 15:45:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-581111111179', 40, ('2024-08-23 13:15:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-591111111180', 41, ('2024-08-22 14:55:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-601111111181', 42, ('2024-08-22 16:35:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-611111111182', 43, ('2024-08-19 13:15:00'::timestamp + interval '2 week')),
        ('6270e4d9-47d1-4a37-af5b-621111111183', 44, ('2024-08-23 08:30:00'::timestamp + interval '2 week'));



INSERT INTO presence (id, student_fk, class_session_fk)
--- AST Mardi ---
VALUES  ('6270e4d9-47d1-4a37-af5b-111111111111', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-111111111111'),
        ('6270e4d9-47d1-4a37-af5b-121111111112', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-111111111111'),

        --- WEB Jeudi ---
        ('6270e4d9-47d1-4a37-af5b-111111111113', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-111111111112'),
        ('6270e4d9-47d1-4a37-af5b-111111111114', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-111111111112'),
        ('6270e4d9-47d1-4a37-af5b-111111111115', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-111111111112'),

        --- WEB Vendredi ---
        ('6270e4d9-47d1-4a37-af5b-111111111116', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-211111111123'),
        ('6270e4d9-47d1-4a37-af5b-111111111118', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-211111111123'),

        -- SOS Labo vendredi --
        ('6270e4d9-47d1-4a37-af5b-111111111119', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-311111111124'),
        ('6270e4d9-47d1-4a37-af5b-111111111120', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-311111111124'),

        -- SOS Cours vendredi --
        ('6270e4d9-47d1-4a37-af5b-111111111123', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-411111111125'),
        ('6270e4d9-47d1-4a37-af5b-111111111124', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-411111111125'),

        -- SLB Labo mardi --
        ('6270e4d9-47d1-4a37-af5b-111111111125', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-511111111126'),
        ('6270e4d9-47d1-4a37-af5b-111111111127', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-511111111126'),

        -- SLB Cours mardi --
        ('6270e4d9-47d1-4a37-af5b-111111111128', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-611111111127'),
        ('6270e4d9-47d1-4a37-af5b-111111111129', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-611111111127'),

        -- DAA-TIC Labo jeudi --
        ('6270e4d9-47d1-4a37-af5b-111111111130', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-711111111128'),
        ('6270e4d9-47d1-4a37-af5b-111111111131', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-711111111128'),
        ('6270e4d9-47d1-4a37-af5b-111111111133', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-711111111128'),

        -- DAA-TIC Cours mardi --
        ('6270e4d9-47d1-4a37-af5b-111111111134', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-811111111129'),
        ('6270e4d9-47d1-4a37-af5b-111111111137', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-811111111129'),

        -- SLH Cours jeudi --
        ('6270e4d9-47d1-4a37-af5b-111111111139', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-911111111130'),
        ('6270e4d9-47d1-4a37-af5b-111111111141', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-911111111130'),
        ('6270e4d9-47d1-4a37-af5b-111111111142', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-911111111130'),
        ('6270e4d9-47d1-4a37-af5b-111111111143', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-911111111130'),

        -- SLH Labo jeudi --
        ('6270e4d9-47d1-4a37-af5b-111111111144', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-101111111131'),
        ('6270e4d9-47d1-4a37-af5b-111111111145', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-101111111131'),
        ('6270e4d9-47d1-4a37-af5b-111111111147', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-101111111131'),

        -- SDR Cours mardi --
        ('6270e4d9-47d1-4a37-af5b-111111111152', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-111111111132'),

        -- SDR Labo mardi --

        -- SIO Cours lundi --

        -- SIO Labo vendredi --

        -- AMT Labo vendredi --
        ('6270e4d9-47d1-4a37-af5b-111111111159', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-151111111136'),
        ('6270e4d9-47d1-4a37-af5b-111111111160', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-151111111136'),

        -- AMT Cours vendredi --
        ('6270e4d9-47d1-4a37-af5b-111111111161', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-161111111137'),

        -- DAA-TIC Labo jeudi --
        ('6270e4d9-47d1-4a37-af5b-111111111164', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-171111111138'),

        -- MAC Labo jeudi --
        ('6270e4d9-47d1-4a37-af5b-111111111167', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-181111111139'),

        -- MAC Cours lundi --
        ('6270e4d9-47d1-4a37-af5b-111111111169', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-191111111140'),

        -- SLH Labo vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111172', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-201111111141'),

        -- AST Mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111173', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-211111111142'),
        ('6270e4d9-47d1-4a37-af5b-111112111174', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-211111111142'),
        ('6270e4d9-47d1-4a37-af5b-111112111175', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-211111111142'),

        -- WEB Jeudi --
        ('6270e4d9-47d1-4a37-af5b-111112111176', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-221111111143'),
        ('6270e4d9-47d1-4a37-af5b-111112111177', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-221111111143'),

        -- WEB Vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111178', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-231111111144'),
        ('6270e4d9-47d1-4a37-af5b-111112111179', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-231111111144'),
        ('6270e4d9-47d1-4a37-af5b-111112111180', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-231111111144'),

        -- SOS Labo vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111183', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-241111111145'),

        -- SOS Cours vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111184', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-251111111146'),
        ('6270e4d9-47d1-4a37-af5b-111112111185', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-251111111146'),
        ('6270e4d9-47d1-4a37-af5b-111112111186', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-251111111146'),

        -- SLB Labo mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111187', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-261111111147'),
        ('6270e4d9-47d1-4a37-af5b-111112111189', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-261111111147'),

        -- SLB Cours mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111190', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-271111111148'),
        ('6270e4d9-47d1-4a37-af5b-111112111191', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-271111111148'),
        ('6270e4d9-47d1-4a37-af5b-111112111192', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-271111111148'),

        -- DAA-TIC Labo jeudi --
        ('6270e4d9-47d1-4a37-af5b-111112111193', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-281111111149'),
        ('6270e4d9-47d1-4a37-af5b-111112111194', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-281111111149'),
        ('6270e4d9-47d1-4a37-af5b-111112111196', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-281111111149'),

        -- DAA-TIC Cours mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111197', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-291111111150'),
        ('6270e4d9-47d1-4a37-af5b-111112111199', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-291111111150'),
        ('6270e4d9-47d1-4a37-af5b-111112111200', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-291111111150'),

        -- SLH Cours jeudi --
        ('6270e4d9-47d1-4a37-af5b-111112111201', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-301111111151'),
        ('6270e4d9-47d1-4a37-af5b-111112111203', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-301111111151'),
        ('6270e4d9-47d1-4a37-af5b-111112111204', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-301111111151'),

        -- SLH Labo jeudi --
        ('6270e4d9-47d1-4a37-af5b-111112111206', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-311111111152'),
        ('6270e4d9-47d1-4a37-af5b-111112111209', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-311111111152'),

        -- SDR Cours mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111210', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-321111111153'),
        ('6270e4d9-47d1-4a37-af5b-111112111211', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-321111111153'),

        -- SDR Labo mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111213', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-331111111154'),

        -- SIO Cours lundi --

        -- SIO Labo vendredi --

        -- AMT Labo vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111217', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-361111111157'),

        -- AMT Cours vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111218', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-371111111158'),
        ('6270e4d9-47d1-4a37-af5b-111112111219', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-371111111158'),

        -- DAA-TIC Labo jeudi --
        ('6270e4d9-47d1-4a37-af5b-111112111220', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-381111111159'),

        -- MAC Labo jeudi --

        -- MAC Cours lundi --

        -- SLH Labo vendredi --

        -- AST Mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111225', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-421111111163'),
        ('6270e4d9-47d1-4a37-af5b-111112111227', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-421111111163'),

        -- WEB Jeudi --
        ('6270e4d9-47d1-4a37-af5b-111112111228', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-431111111164'),
        ('6270e4d9-47d1-4a37-af5b-111112111229', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-431111111164'),

        -- WEB Vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111230', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-441111111165'),
        ('6270e4d9-47d1-4a37-af5b-111112111232', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-441111111165'),

        -- SOS Labo vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111233', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-451111111166'),
        ('6270e4d9-47d1-4a37-af5b-111112111235', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-451111111166'),

        -- SOS Cours vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111236', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-461111111167'),
        ('6270e4d9-47d1-4a37-af5b-111112111237', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-461111111167'),
        ('6270e4d9-47d1-4a37-af5b-111112111238', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-461111111167'),

        -- SLB Labo mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111239', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-471111111168'),
        ('6270e4d9-47d1-4a37-af5b-111112111240', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-471111111168'),
        ('6270e4d9-47d1-4a37-af5b-111112111241', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-471111111168'),

        -- SLB Cours mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111242', '5a7e665c-c994-464d-a16a-6e1e150f7f30', '6270e4d9-47d1-4a37-af5b-481111111169'),
        ('6270e4d9-47d1-4a37-af5b-111112111243', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-481111111169'),
        ('6270e4d9-47d1-4a37-af5b-111112111244', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-481111111169'),

        -- DAA-TIC Labo jeudi --
        ('6270e4d9-47d1-4a37-af5b-111112111246', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-491111111170'),
        ('6270e4d9-47d1-4a37-af5b-111112111247', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-491111111170'),
        ('6270e4d9-47d1-4a37-af5b-111112111248', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-491111111170'),

        -- DAA-TIC Cours mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111250', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-501111111171'),
        ('6270e4d9-47d1-4a37-af5b-111112111251', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-501111111171'),
        ('6270e4d9-47d1-4a37-af5b-111112111252', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-501111111171'),

        -- SLH Cours jeudi --
        ('6270e4d9-47d1-4a37-af5b-111112111254', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-511111111172'),
        ('6270e4d9-47d1-4a37-af5b-111112111255', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-511111111172'),
        ('6270e4d9-47d1-4a37-af5b-111112111257', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-511111111172'),

        -- SLH Labo jeudi --
        ('6270e4d9-47d1-4a37-af5b-111112111259', '0e0996bb-123b-4b9b-8ad4-142bc2c5d9e7', '6270e4d9-47d1-4a37-af5b-521111111173'),
        ('6270e4d9-47d1-4a37-af5b-111112111260', '5d01dcc6-6aa3-4d61-9c79-b4a26359f1e0', '6270e4d9-47d1-4a37-af5b-521111111173'),
        ('6270e4d9-47d1-4a37-af5b-111112111261', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-521111111173'),


        -- SDR Cours mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111262', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-531111111174'),
        ('6270e4d9-47d1-4a37-af5b-111112111263', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-531111111174'),

        -- SDR Labo mardi --
        ('6270e4d9-47d1-4a37-af5b-111112111264', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-541111111175'),

        -- SIO Cours lundi --
        ('6270e4d9-47d1-4a37-af5b-111112111266', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-551111111176'),

        -- SIO Labo vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111267', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-561111111177'),

        -- AMT Labo vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111270', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-571111111178'),

        -- AMT Cours vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111271', '8e4112a3-e7d0-4a37-9ffe-f7e2ad265076', '6270e4d9-47d1-4a37-af5b-581111111179'),

        -- DAA-TIC Labo jeudi --

        -- MAC Labo jeudi --

        -- MAC Cours lundi --
        ('6270e4d9-47d1-4a37-af5b-111112111279', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-611111111182'),

        -- SLH Labo vendredi --
        ('6270e4d9-47d1-4a37-af5b-111112111281', '888e4b08-b4d5-4cd7-bfb0-921918170710', '6270e4d9-47d1-4a37-af5b-621111111183');

ALTER SEQUENCE course_id_seq RESTART WITH 500;
ALTER SEQUENCE school_class_id_seq RESTART WITH 1000;
ALTER SEQUENCE room_id_seq RESTART WITH 1000;
