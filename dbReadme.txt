создать пользователя для базы и прописать логин,пароль и URL в resources/login.properties
создать базу с таблицами

create database Bank;
create table bank_account
(
    number_bank_account int         not null,
    name_owner          varchar(30) not null,
    is_active           tinyint(1)  not null,
    balance             int         not null
);

create table client
(
    id       int(30) auto_increment
        primary key,
    name     varchar(30) not null,
    age      int         not null,
    password int(8)      not null,
    gender   varchar(1)  not null
);

create table transaction_history
(
    id        int(30) auto_increment
        primary key,
    sender    int(30)     not null,
    recipient int(30)     not null,
    amount    int(255)    not null,
    dat       varchar(30) not null
);
