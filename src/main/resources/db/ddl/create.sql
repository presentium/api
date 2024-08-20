------------ API Schemas ------------

create table api_user
(
    id         uuid         not null,
    email      varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    subject    varchar(255) not null,
    primary key (id),
    unique (subject)
);

------------ Person Schemas ------------

create table person
(
    id         uuid         not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    email      varchar(255) not null,
    primary key (id)
);

create table teacher
(
    id uuid not null,
    primary key (id)
);

create table student
(
    id uuid not null,
    primary key (id)
);

------------ Academic Schemas ------------

create table discipline
(
    id          varchar(255) not null,
    duration    int          not null,
    description varchar(255) not null,
    primary key (id)
);

create table course
(
    id            varchar(255) not null,
    semester      varchar(255) not null,
    year          int          not null,
    discipline_id varchar(255) not null,
    primary key (id)
);

create table class
(
    id          varchar(255) not null,
    day_of_week smallint     not null,
    start       time         not null,
    room_id     varchar(255) not null,
    site_id     varchar(255) not null,
    course_id   varchar(255) not null,
    teacher_id  uuid         not null,
    primary key (id, course_id)
);

create table class_session
(
    id        uuid         not null,
    class_id  varchar(255) not null,
    course_id varchar(255) not null,
    date      timestamp    not null,
    primary key (id)
);

create table site
(
    id       varchar(255) not null,
    name     varchar(255) not null,
    address  varchar(255) not null,
    city     varchar(255) not null,
    zip_code varchar(255) not null,
    primary key (id)
);

create table room
(
    room_id  varchar(255) not null,
    site_id  varchar(255) not null,
    capacity int          not null,
    primary key (room_id, site_id)
);

------------ Device Schemas ------------

create table loan
(
    id              uuid      not null,
    teacher_id      uuid      not null,
    presence_box_id uuid      not null,
    start           timestamp not null,
    primary key (id)
);

create table returned_loan
(
    id              uuid      not null,
    teacher_id      uuid      not null,
    presence_box_id uuid      not null,
    start_date      timestamp not null,
    end_date        timestamp not null,
    primary key (id)
);

create table presence_box
(
    id uuid not null,
    primary key (id)
);

create table presence
(
    id               uuid not null,
    student_id       uuid not null,
    class_session_id uuid not null,
    primary key (id)
);

------------ Associative Schemas ------------

create table class_student
(
    class_id   varchar(255) not null,
    student_id uuid         not null,
    primary key (class_id, student_id)
);

------------ Foreign Keys ------------

------------ Person Relation ------------

alter table teacher
    add constraint fk_teacher_person
        foreign key (id) references person (id);

alter table student
    add constraint fk_student_person
        foreign key (id) references person (id);

------------ Course Relation ------------

alter table course
    add constraint fk_course_discipline
        foreign key (discipline_id) references discipline (id);

------------ Class Relation ------------

alter table class
    add constraint fk_class_course
        foreign key (course_id) references course (id);

alter table class
    add constraint fk_class_room
        foreign key (room_id, site_id) references room (room_id, site_id);

alter table class
    add constraint fk_class_teacher
        foreign key (teacher_id) references teacher (id);

------------ Class Session Relation ------------

alter table class_session
    add constraint fk_class_course
        foreign key (class_id, course_id) references class (id, course_id);

------------ Room Relation ------------

alter table room
    add constraint fk_room_site
        foreign key (site_id) references site (id);

------------ Loan Relation ------------

alter table loan
    add constraint fk_loan_teacher
        foreign key (teacher_id) references teacher (id);
