create table if not exists users(
    id uuid primary key,
    created_at timestamp,
    updated_at timestamp,
    username varchar(255) unique,
    name varchar(255),
    last_name varchar(255),
    email varchar(255) unique,
    password varchar (255),
    rating double precision
);


create table if not exists  roles(
  id uuid primary key,
  name varchar(255)

);

CREATE TABLE if not exists  user_roles (
                            user_id UUID NOT NULL
                                CONSTRAINT fk_user_id
                                    REFERENCES users,
                            role_id UUID NOT NULL
                                CONSTRAINT fr_role_id
                                    REFERENCES roles
);

-- SELECT * FROM users;
--
--
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--
-- DELETE FROM users;
