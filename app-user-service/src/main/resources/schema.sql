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