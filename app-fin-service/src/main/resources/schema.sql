create table useraccount
(
   id long not null primary key,
   userid varchar(50) not null,
   createdby varchar(50),
   createdon date,
   updatedby varchar(50),
   updatedon date
);

create sequence useraccountseq start with 100001 increment by 1;