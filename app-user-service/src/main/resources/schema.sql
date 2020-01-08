create table user
(
   id long not null primary key,
   name varchar(50) not null,
   createdby varchar(50),
   createdon date,
   updatedby varchar(50),
   updatedon date
);

create sequence useridseq start with 100001 increment by 1;