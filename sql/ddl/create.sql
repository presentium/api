
    create table api_user (
        id varchar(255) not null,
        display_name varchar(255) not null,
        email varchar(255) not null,
        subject varchar(255) not null,
        primary key (id),
        unique (subject)
    );

    create table class (
        id uuid not null,
        day_of_week int not null,
        start datetime not null,
        room_id int not null,
        course_id int not null,
        teacher_id int not null,
        primary key (id)
    );

    create table course (
        id varchar(255) not null,
        semester varchar(255) not null,
        year int not null,
        primary key (id)
    );

    create table room (
        room_id varchar(255) not null,
        site_id varchar(255) not null,
        primary key (room_id, site_id)
    );

    create table site (
        id varchar(255) not null,
        name varchar(255) not null,
        address varchar(255) not null,
        city varchar(255) not null,
        zip_code varchar(255) not null,
        primary key (id)
    );

    create table person (
        id int not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        email varchar(255) not null,
        primary key (id)
    );

    create table teacher (
        id int not null,
        person_id uuid not null,
        primary key (id, person_id)
    );

    create table student (
        id int not null,
        person_id uuid not null,
        primary key (id, person_id)
    );



    alter table class
        add constraint fk_class_room
            foreign key (room_id) references room(id);

    alter table class
        add constraint fk_class_course
            foreign key (course_id) references course(id);

    alter table class
        add constraint fk_class_teacher
            foreign key (teacher_id) references teacher(id);

    alter table room
        add constraint fk_room_site
            foreign key (site_id) references site(id);

    alter table teacher
        add constraint fk_teacher_person
            foreign key (person_id) references person(id);
