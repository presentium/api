-- Disable foreign key checks to allow truncating tables in any order
SET session_replication_role = replica;

-- Truncate tables with cascading to remove all data, considering foreign key relationships
TRUNCATE TABLE
    presence,
    class_session,
    class_student,
    school_class,
    student,
    teacher,
    person,
    course,
    room,
    presence_box,
    api_user
    CASCADE;

-- Re-enable foreign key checks
SET session_replication_role = origin;
