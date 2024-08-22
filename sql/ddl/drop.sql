
    alter table if exists class 
       drop constraint if exists fk_class_course;

    alter table if exists class 
       drop constraint if exists fk_class_room;

    alter table if exists class 
       drop constraint if exists fk_class_teacher;

    alter table if exists class_session 
       drop constraint if exists fk_session_course;

    alter table if exists class_student 
       drop constraint if exists fk_student_class;

    alter table if exists class_student 
       drop constraint if exists fk_class_student;

    alter table if exists person 
       drop constraint if exists fk_person_api_user;

    alter table if exists presence 
       drop constraint if exists fk_presence_session;

    alter table if exists presence 
       drop constraint if exists fk_student;

    alter table if exists presence_box 
       drop constraint if exists fk_box_teacher;

    alter table if exists student 
       drop constraint if exists fk_student_person;

    alter table if exists teacher 
       drop constraint if exists fk_teacher_person;

    drop table if exists api_user cascade;

    drop table if exists class cascade;

    drop table if exists class_session cascade;

    drop table if exists class_student cascade;

    drop table if exists course cascade;

    drop table if exists person cascade;

    drop table if exists presence cascade;

    drop table if exists presence_box cascade;

    drop table if exists room cascade;

    drop table if exists student cascade;

    drop table if exists teacher cascade;
