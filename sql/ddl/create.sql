
    create table api_user (
        id varchar(255) not null,
        display_name varchar(255) not null,
        email varchar(255) not null,
        subject varchar(255) not null,
        primary key (id),
        unique (subject)
    );
