create table _history(
    id serial primary key,
    type varchar(4) not null,
    date timestamp without time zone,
    value integer not null
);

create sequence seq_history start with 1 increment by 1;