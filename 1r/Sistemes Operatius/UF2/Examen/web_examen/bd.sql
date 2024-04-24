drop database if exists db_examen;
create database db_examen;
use db_examen;
create table messages (
  id int unsigned auto_increment primary key,
  title varchar(255) not null default '',
  body text not null default ''
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
insert into messages(title, body)
  values ('SELECT','Si veus això vol dir que s\'ha pogut llegir la BD.');
