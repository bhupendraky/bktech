
create table COUNTER
(
	value INT AUTO_INCREMENT PRIMARY KEY
);

insert into COUNTER values(1);

update COUNTER set value = LAST_INSERT_ID(value+1);
