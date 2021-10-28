drop sequence if exists hibernate_sequence
create sequence hibernate_sequence start with 1 increment by 1

drop table url if exists
create table url ( id int, originURL varchar(255), count int, primary key(id))
