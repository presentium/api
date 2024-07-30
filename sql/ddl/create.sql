
    create table api_user (
        id uuid not null,
        email varchar(255) not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        subject varchar(255) not null,
        primary key (id),
        unique (subject)
    );
