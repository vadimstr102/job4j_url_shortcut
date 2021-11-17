create table persons
(
    id       serial primary key,
    login    varchar(50) unique not null,
    password varchar(100)       not null
);

create table urls
(
    id           serial primary key,
    url          varchar(3000) unique not null,
    code         varchar(50)          not null,
    call_counter int default 0,
    person_id    int references persons (id)
);
