create table user_account
(
   id long not null primary key,
   user_id varchar(50) not null,
   account_balance double not null,
   created_by varchar(50),
   created_on date,
   updated_by varchar(50),
   updated_on date
);

create sequence user_account_id_seq start with 100001 increment by 1;