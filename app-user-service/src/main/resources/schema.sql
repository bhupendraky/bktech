/*
create table user
(
   id long not null primary key,
   name varchar(50) not null,
   created_by varchar(50),
   created_on date,
   updated_by varchar(50),
   updated_on date
);

create sequence user_id_seq start with 100001 increment by 1;


create table users
(
   username varchar(50) not null primary key,
   password varchar(50) not null,
   enabled boolean not null
);
create table authorities
(
   username varchar(50) not null primary key,
   authority varchar(50) not null,
   constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username, authority);
*/