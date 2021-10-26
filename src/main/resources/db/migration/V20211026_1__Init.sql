create table users
(
    id           serial primary key,
    username     varchar(255) not null unique,
    password     varchar(255) not null,
    is_locked    boolean default false,
    created_date timestamp    not null
);

create table document(
    id serial primary key,
    pdf_base64 text not null,
    created_date timestamp not null
);

create table document_for_signing(
    id serial primary key,
    user_sender_id bigint not null,
    user_receiver_id bigint not null,
    document_id bigint not null,
    status varchar(255) not null,
    created_date timestamp not null,
    constraint fk_user_sender_id foreign key (user_sender_id) references users (id),
    constraint fk_user_receiver_id foreign key (user_receiver_id) references users (id),
    constraint fk_document_id foreign key (document_id) references document (id)
);

create table signed_document(
    id serial primary key,
    user_sender_id bigint not null,
    user_receiver_id bigint not null,
    document_id bigint not null,
    created_date timestamp not null,
    constraint fk_user_sender_id foreign key (user_sender_id) references users (id),
    constraint fk_user_receiver_id foreign key (user_receiver_id) references users (id),
    constraint fk_document_id foreign key (document_id) references document (id)
);