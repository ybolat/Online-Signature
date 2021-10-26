create table users
(
    id           serial primary key,
    username     varchar(255) not null unique,
    name         varchar(255) not null,
    surname      varchar(255) not null,
    base_role    varchar(255) not null,
    is_locked    boolean default false,
    created_date timestamp    not null
);

create table roles
(
    id        serial primary key,
    role_name varchar(255) not null unique
);

create table authorities
(
    id             serial primary key,
    authority_name varchar(255) not null unique
);

create table users_roles
(
    id      serial primary key,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    UNIQUE (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

create table roles_authorities
(
    id           serial primary key,
    role_id      bigint NOT NULL,
    authority_id bigint NOT NULL,
    UNIQUE (role_id, authority_id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (authority_id) REFERENCES authorities (id)
);