create table USER_ACCOUNT
(
   ID long not null primary key,
   USER_ID varchar(50) not null,
   ACCOUNT_BALANCE double not null,
   CREATED_BY varchar(50),
   CREATED_ON date,
   UPDATED_BY varchar(50),
   UPDATED_ON date
);

create sequence USER_ACCOUNT_ID_SEQ start with 100001 increment by 1;