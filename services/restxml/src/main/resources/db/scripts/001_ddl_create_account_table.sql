create table account (
    id serial primary key,
    name varchar(100) UNIQUE,
    password varchar(100),
    roles varchar(100)
);