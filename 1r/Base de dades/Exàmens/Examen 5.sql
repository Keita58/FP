-- Exercici 1

-- a)

psql -U admin -h 127.0.0.1 -d chinook

create user chinook createrole password 'super3';

grant chinook to admin;

alter database chinook owner to chinook;

chinook=> \l chinook
                                              List of databases
  Name   |  Owner  | Encoding |   Collate   |    Ctype    | ICU Locale | Locale Provider | Access privileges
---------+---------+----------+-------------+-------------+------------+-----------------+-------------------
 chinook | chinook | UTF8     | en_US.UTF-8 | en_US.UTF-8 |            | libc            |
(1 row)

-- Per passar el propietari de les taules hem de canviar el propietari de l'esquema en sí al que ho volem traspassar, en aquest cas d'admin a chinook

alter schema chinook owner to chinook;

-- I ara ja podem canviar els propietaris de totes les taules de chinook

chinook => alter table album owner to chinook;
ALTER TABLE
chinook=> alter table artist owner to chinook;
ALTER TABLE
chinook=> alter table customer owner to chinook;
ALTER TABLE
chinook=> alter table employee owner to chinook;
ALTER TABLE
chinook=> alter table genre owner to chinook;
ALTER TABLE
chinook=> alter table invoice owner to chinook;
ALTER TABLE
chinook=> alter table invoiceline owner to chinook;
ALTER TABLE
chinook=> alter table mediatype owner to chinook;
ALTER TABLE
chinook=> alter table playlist owner to chinook;
ALTER TABLE
chinook=> alter table playlisttrack owner to chinook;
ALTER TABLE
chinook=> alter table track owner to chinook;
ALTER table

psql -U chinook -h 127.0.0.1 -d chinook

chinook=> \dt
             List of relations
 Schema  |     Name      | Type  |  Owner
---------+---------------+-------+---------
 chinook | album         | table | chinook
 chinook | artist        | table | chinook
 chinook | customer      | table | chinook
 chinook | employee      | table | chinook
 chinook | genre         | table | chinook
 chinook | invoice       | table | chinook
 chinook | invoiceline   | table | chinook
 chinook | mediatype     | table | chinook
 chinook | playlist      | table | chinook
 chinook | playlisttrack | table | chinook
 chinook | track         | table | chinook
(11 rows)

-- b)

chinook=> create role player;
CREATE role
chinook=> grant select on table album, artist, genre, mediatype, playlist, playlisttrack, track to player;
grant

-- c) 

chinook=> create role store;
CREATE ROLE
chinook=> grant player to store;
GRANT ROLE
chinook=> grant select on employee to store;
GRANT
chinook=> grant select, insert, delete, update on customer, invoice, invoiceline to store;
grant

                                              Access privileges
 Schema  |             Name              |   Type   |    Access privileges    | Column privileges | Policies
---------+-------------------------------+----------+-------------------------+-------------------+----------
 chinook | album                         | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | player=r/chinook        |                   |
 chinook | album_albumid_seq             | sequence |                         |                   |
 chinook | artist                        | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | player=r/chinook        |                   |
 chinook | artist_artistid_seq           | sequence |                         |                   |
 chinook | customer                      | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | store=arwd/chinook      |                   |
 chinook | customer_customerid_seq       | sequence |                         |                   |
 chinook | employee                      | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | store=r/chinook         |                   |
 chinook | employee_employeeid_seq       | sequence |                         |                   |
 chinook | genre                         | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | player=r/chinook        |                   |
 chinook | genre_genreid_seq             | sequence |                         |                   |
 chinook | invoice                       | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | store=arwd/chinook      |                   |
 chinook | invoice_invoiceid_seq         | sequence |                         |                   |
 chinook | invoiceline                   | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | store=arwd/chinook      |                   |
 chinook | invoiceline_invoicelineid_seq | sequence |                         |                   |
 chinook | mediatype                     | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | player=r/chinook        |                   |
 chinook | mediatype_mediatypeid_seq     | sequence |                         |                   |
 chinook | playlist                      | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | player=r/chinook        |                   |
 chinook | playlist_playlistid_seq       | sequence |                         |                   |
 chinook | playlisttrack                 | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | player=r/chinook        |                   |
 chinook | track                         | table    | chinook=arwdDxt/chinook+|                   |
         |                               |          | player=r/chinook        |                   |
 chinook | track_trackid_seq             | sequence |                         |                   |
(21 rows)

-- d) 

chinook=> create user manel;
CREATE ROLE
chinook=> grant store to manel;
GRANT ROLE
chinook=> \du
                                   List of roles
 Role name |                         Attributes                         | Member of
-----------+------------------------------------------------------------+-----------
 admin     | Create role, Create DB                                     | {chinook}
 chinook   | Create role                                                | {}
 manel     |                                                            | {store}
 player    | Cannot login                                               | {}
 postgres  | Superuser, Create role, Create DB, Replication, Bypass RLS | {}
 store     | Cannot login                                               | {player}

-- e)
 
 
 
-- Exercici 2

create or replace function update_cost(id int, minsells int, maxsells int) returns void as $$
declare
	copies int;
	preuAct numeric;
	preuCanvi numeric;
begin
	if exists(select name from genre where genreid = id) then
		if(minsells > maxsells) then 
			raise exception 'El número de vendes màxim és menor al número menor de vendes';
		else
			select sum(quantity) into copies
			from invoiceline il
			join track t on t.trackid = il.trackid
			join genre g on t.genreid = g.genreid
			where g.genreid = id;
			if(copies < minsells) then
				select unitprice into preuAct
				from track
				where genreid = id;
				update track 
				set unitprice = unitprice * 0.95
				where genreid = id;
				select unitprice into preuCanvi
				from track
				where genreid = id;
			elsif(copies > maxsells) then
				select unitprice into preuAct
				from track
				where genreid = id;
				update track 
				set unitprice = unitprice * 1.05
				where genreid = id;
				select unitprice into preuCanvi
				from track
				where genreid = id;
			end if;
			raise info 'La quantitat de vendes d''aquest gènere és de %', copies;
			raise info 'El preu abans del canvi és de %', preuAct;
			raise info 'El preu després del canvi és de %', preuCanvi;
		end if;
	else
		raise exception 'El tema que s''ha passat per paràmetre no existeix';
	end if;
end;
$$ language plpgsql;

select update_cost(1, 1000, 100000)

/*
La quantitat de vendes d'aquest gènere és de 835
El preu abans del canvi és de 0.99
El preu després del canvi és de 0.94
*/

select *
from track t 
where t.genreid = 1
limit 1;

trackid|name                                   |albumid|mediatypeid|genreid|composer                                 |milliseconds|bytes   |unitprice|
-------+---------------------------------------+-------+-----------+-------+-----------------------------------------+------------+--------+---------+
      1|For Those About To Rock (We Salute You)|      1|          1|      1|Angus Young, Malcolm Young, Brian Johnson|      343719|11170334|     0.94|

select update_cost(-1, 1, 10)

/*
SQL Error [P0001]: ERROR: El tema que s'ha passat per paràmetre no existeix
Where: PL/pgSQL function update_cost(integer,integer,integer) line 38 at RAISE
 */
 
select update_cost(1, 10000, 1)

/*
SQL Error [P0001]: ERROR: El número de vendes màxim és menor al número menor de vendes
  Where: PL/pgSQL function update_cost(integer,integer,integer) line 9 at RAISE
*/

-- Exercici 3

alter table album add column duration numeric(7, 2) not null default 0;

update album a
	set duration = (select sum(milliseconds)/1000 from track t where t.albumid = a.albumid)
	from track t
	where t.albumid = a.albumid;
	
create or replace function canvia() returns trigger as $$
begin 
	if(TG_OP='INSERT') then
		update album 
		set duration = duration + (new.milliseconds/1000)
		where albumid = new.albumid;
		return new;
	elsif(TG_OP='DELETE') then
		update album 
		set duration = duration - (old.milliseconds/1000)
		where albumid = new.albumid;
		return old;
	else
		if(old.trackid != new.trackid) then
			raise exception 'No es pot canviar l''id de la cançó.';
		elsif(old.albumid != new.albumid) then
			raise exception 'No es pot canviar l''id de l''àlbum a la cançó.';
		else
			if(old.milliseconds < new.milliseconds or old.milliseconds > new.milliseconds) then 
				update album
				set duration = duration + ((new.milliseconds/1000) - (old.milliseconds/1000))
				where albumid = new.albumid;
				return new;
			end if;
		end if;
	end if;
end
$$ language plpgsql;

create trigger actualitza before update or delete or insert on track for each row execute procedure canvia();

select *
from track t 
where t.albumid = 1
limit 1;

trackid|name                                   |albumid|mediatypeid|genreid|composer                                 |milliseconds|bytes   |unitprice|
-------+---------------------------------------+-------+-----------+-------+-----------------------------------------+------------+--------+---------+
      1|For Those About To Rock (We Salute You)|      1|          1|      1|Angus Young, Malcolm Young, Brian Johnson|      343719|11170334|     0.94|

select duration
from album
where albumid = 1;

duration|
--------+
 2400.42|

update track 
set albumid = 2
where trackid = 1;

/*
SQL Error [P0001]: ERROR: No es pot canviar l'id de l'àlbum a la cançó.
Where: PL/pgSQL function canvia() line 15 at RAISE
 */

update track 
set trackid = 2
where trackid = 1;

/*
SQL Error [P0001]: ERROR: No es pot canviar l'id de la cançó.
Where: PL/pgSQL function canvia() line 13 at RAISE
 */

update track 
set milliseconds = milliseconds - 1000
where trackid = 1;

select duration
from album
where albumid = 1;

duration|
--------+
 2401.42|
 
insert into track(name, albumid, mediatypeid, milliseconds, unitprice)
values('Prova', 1, 1, 1000, 0.99)

select *
from track t 
where t."name" = 'Prova';

trackid|name |albumid|mediatypeid|genreid|composer|milliseconds|bytes|unitprice|
-------+-----+-------+-----------+-------+--------+------------+-----+---------+
   3505|Prova|      1|          1|       |        |        1000|     |     0.99|
   
select duration
from album
where albumid = 1;

duration|
--------+
 2402.42|
 
delete 
from track
where trackid = 3505;

select *
from track t 
where t."name" = 'Prova';

trackid|name|albumid|mediatypeid|genreid|composer|milliseconds|bytes|unitprice|
-------+----+-------+-----------+-------+--------+------------+-----+---------+

select duration
from album
where albumid = 1;