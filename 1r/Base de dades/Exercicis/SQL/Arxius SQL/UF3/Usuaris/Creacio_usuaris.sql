-- Ex1

CREATE USER terrassa;

-- No ens podem connectar a la base de dades hotel perquè el DBeaver requereix d'una contrasenya per a fer una connexió, i com que a l'usuari no li hem proporcionat cap no ens hi podem connectar.

-- Ex2

create user sabadell password 'super3';

psql -h 127.0.0.1 -U sabadell -d hotel;

-- Sí que podem fer la connexió a la base de dades hotel amb l'usuari sabadell perquè li he proporcionat una contrasenya i per tant ens hem pogut logejar amb el mateix.

-- Ex3

set schema 'hotel';

select *
from customers c;

SQL Error [42P01]: ERROR: relation "customers" does not exist
-- Si bé em puc connectar a la base de dades hotel no puc veure les seves dades perquè no tinc els permissos necessaris per veure les dades que té.

-- Ex 4

-- Quan creem els usuaris com els hem creat només els hi donem el privilegi de LOGIN per a poder fer una connexió, però cap altre tipus de privilegi per veure la informació de les dades de les taules de les bases de dades.

-- Ex 5

postgres=# \du
                                   List of roles
 Role name |                         Attributes                         | Member of
-----------+------------------------------------------------------------+-----------
 admin     | Create role, Create DB                                     | {}
 postgres  | Superuser, Create role, Create DB, Replication, Bypass RLS | {}
 sabadell  |                                                            | {}
 
-- Ex 6

vagrant@postgresqlserver:~$ psql -h 127.0.0.1 -U terrassa -d hotel
Password for user terrassa:
psql: error: connection to server at "127.0.0.1", port 5432 failed: FATAL:  password authentication failed for user "terrassa"
connection to server at "127.0.0.1", port 5432 failed: FATAL:  password authentication failed for user "terrassa"

psql -> Per connectar-nos directament a la base de dades per línia de comandes
-h -> L'usuari terrassa, al no tenir un usuari al sistema operatiu, li hem de posar -h i la ip local per forçar a que ens demani la contrasenya i poder entrar dins del psql
-U -> L'usuari amb el que volem accedir a les bases de dades de psql
-d -> A quina base de dades en específic volem accedir, si no en posessim cap intentaria accedir a la base de dades terrassa que no existeix

-- Ex 7

vagrant@postgresqlserver:~$ psql -h 127.0.0.1 -U admin -d hotel
Password for user admin: "super3"
psql (15.5 (Debian 15.5-0+deb12u1))
SSL connection (protocol: TLSv1.3, cipher: TLS_AES_256_GCM_SHA384, compression: off)
Type "help" for help.

hotel=>

-- Ex 8

-- Abans de posar-li donar cap privilegi, li haurem de donar el privilegi de que pugui utilitzar la base de dades hotel

hotel=> grant usage on schema hotel to sabadell
hotel-> ;
GRANT

hotel=> grant select on customers to sabadell;
GRANT

hotel=> \dp customers
                                Access privileges
 Schema |   Name    | Type  |  Access privileges  | Column privileges | Policies
--------+-----------+-------+---------------------+-------------------+----------
 hotel  | customers | table | admin=arwdDxt/admin+|                   |
        |           |       | sabadell=r/admin    |                   |
(1 row)

select c.id
from customers c
limit 10;

id|
--+
 1|
 2|
 3|
 4|
 5|
 6|
 7|
 8|
 9|
10|

-- Ex 9

vagrant@postgresqlserver:~$ psql -h 127.0.0.1 -U admin -d hotel
hotel=> \dp
                                       Access privileges
 Schema |         Name         |   Type   |  Access privileges  | Column privileges | Policies
--------+----------------------+----------+---------------------+-------------------+----------
 hotel  | bookingcalendar      | table    |                     |                   |
 hotel  | bookings             | table    |                     |                   |
 hotel  | bookings_id_seq      | sequence |                     |                   |
 hotel  | customers            | table    | admin=arwdDxt/admin+|                   |
        |                      |          | sabadell=r/admin    |                   |
 hotel  | customers_id_seq     | sequence |                     |                   |
 hotel  | facilities           | table    |                     |                   |
 hotel  | facilities_id_seq    | sequence |                     |                   |
 hotel  | hosts                | table    |                     |                   |
 hotel  | hosts_id_seq         | sequence |                     |                   |
 hotel  | priceseasons         | table    |                     |                   |
 hotel  | rooms                | table    |                     |                   |
 hotel  | rooms_roomnumber_seq | sequence |                     |                   |
 hotel  | roomtypefacilities   | table    |                     |                   |
 hotel  | roomtypes            | table    |                     |                   |
 hotel  | roomtypes_id_seq     | sequence |                     |                   |
 hotel  | seasons              | table    |                     |                   |
 hotel  | seasons_id_seq       | sequence |                     |                   |
 hotel  | stayhosts            | table    |                     |                   |
 hotel  | stays                | table    |                     |                   |
 hotel  | stays_id_seq         | sequence |                     |                   |
(20 rows)

-- Ex 10

-- La taula customers té privilegis diferents per al seu creador i per a l'usuari sabadell: Per al seu creador té tots els permisos disponibles, mentre que per a l'usuari sabadell només té els permisos de lecura que li hem donat anteriorment amb l'usuari admin (propietari de la base de dades i lúnic que pot donar aquests privilegis)

-- Ex 11

hotel=> GRANT UPDATE, INSERT ON bookings TO sabadell;
grant

-- Només donant-li permisos per a la taula bookings no funciona, li hem de donar permisos també per a la taula bookings_ids_seq

hotel=> grant update, insert on bookings_id_seq to sabadell;
WARNING:  sequence "bookings_id_seq" only supports USAGE, SELECT, and UPDATE privileges
grant;

insert into bookings(reservationdatetime, customerid, roomtypeid, price, state, nhosts) 
values(now(), 1, 1, 100, 'Reserved', 1);

hotel=> select * from bookings where reservationdatetime = (select max(reservationdatetime) from bookings);
  id  |     reservationdatetime     | customerid | roomtypeid | checkin | checkout | price  |  state   | nhosts
------+-----------------------------+------------+------------+---------+----------+--------+----------+--------
 6727 | 2024-01-31 10:22:04.4178+00 |          1 |          1 |         |          | 100.00 | Reserved |      1
(1 row)

-- Ex 12

hotel=> \dp
                                       Access privileges
 Schema |         Name         |   Type   |  Access privileges  | Column privileges | Policies
--------+----------------------+----------+---------------------+-------------------+----------
 hotel  | bookingcalendar      | table    |                     |                   |
 hotel  | bookings             | table    | admin=arwdDxt/admin+|                   |
        |                      |          | sabadell=aw/admin   |                   |
 hotel  | bookings_id_seq      | sequence |                     |                   |
 hotel  | customers            | table    | admin=arwdDxt/admin+|                   |
        |                      |          | sabadell=r/admin    |                   |
 hotel  | customers_id_seq     | sequence |                     |                   |
 hotel  | facilities           | table    |                     |                   |
 hotel  | facilities_id_seq    | sequence |                     |                   |
 hotel  | hosts                | table    |                     |                   |
 hotel  | hosts_id_seq         | sequence |                     |                   |
 hotel  | priceseasons         | table    |                     |                   |
 hotel  | rooms                | table    |                     |                   |
 hotel  | rooms_roomnumber_seq | sequence |                     |                   |
 hotel  | roomtypefacilities   | table    |                     |                   |
 hotel  | roomtypes            | table    |                     |                   |
 hotel  | roomtypes_id_seq     | sequence |                     |                   |
 hotel  | seasons              | table    |                     |                   |
 hotel  | seasons_id_seq       | sequence |                     |                   |
 hotel  | stayhosts            | table    |                     |                   |
 hotel  | stays                | table    |                     |                   |
 hotel  | stays_id_seq         | sequence |                     |                   |
(20 rows)

-- Ex 13

hotel=> grant select on all tables in schema hotel to sabadell;
grant

select * 
from seasons s;

id|startingday|name       |
--+-----------+-----------+
 1| 2020-12-21|Winter 2020|
 2| 2020-03-21|Spring 2020|
 3| 2020-06-21|Summer 2020|
 4| 2020-09-21|Autumn 2020|
 5| 2021-12-21|Winter 2021|
 6| 2021-03-21|Spring 2021|
 7| 2021-06-21|Summer 2021|
 8| 2021-09-21|Autumn 2021|
 9| 2022-12-21|Winter 2022|
10| 2022-03-21|Spring 2022|
11| 2022-06-21|Summer 2022|
12| 2022-09-21|Autumn 2022|
13| 2023-12-21|Winter 2023|
14| 2023-03-21|Spring 2023|
15| 2023-06-21|Summer 2023|
16| 2023-09-21|Autumn 2023|

-- Ex 14

miniwind=> grant usage on schema miniwind to sabadell;
GRANT
miniwind=> grant select on products to sabadell;
grant

miniwind=> \dp
                                        Access privileges
  Schema  |         Name         |   Type   |  Access privileges  | Column privileges | Policies
----------+----------------------+----------+---------------------+-------------------+----------
 miniwind | customers            | table    |                     |                   |
 miniwind | customers_id_seq     | sequence |                     |                   |
 miniwind | employees            | table    |                     |                   |
 miniwind | employees_id_seq     | sequence |                     |                   |
 miniwind | invoices             | table    |                     |                   |
 miniwind | invoices_id_seq      | sequence |                     |                   |
 miniwind | order_details        | table    |                     |                   |
 miniwind | order_details_id_seq | sequence |                     |                   |
 miniwind | order_details_status | table    |                     |                   |
 miniwind | orders               | table    |                     |                   |
 miniwind | orders_id_seq        | sequence |                     |                   |
 miniwind | orders_status        | table    |                     |                   |
 miniwind | products             | table    | admin=arwdDxt/admin+|                   |
          |                      |          | sabadell=r/admin    |                   |
 miniwind | products_id_seq      | sequence |                     |                   |
 miniwind | suppliers            | table    |                     |                   |
 miniwind | suppliers_id_seq     | sequence |                     |                   |
(16 rows)

-- Ex 15

REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA hotel FROM sabadell;
REVOKE ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA hotel FROM sabadell;
REVOKE ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA hotel FROM sabadell;
revoke usage on schema hotel from sabadell;
-- Aquí canviar a la BD de miniwind
REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA miniwind FROM sabadell;
REVOKE ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA miniwind FROM sabadell;
REVOKE ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA miniwind FROM sabadell;
revoke usage on schema miniwind from sabadell;
DROP USER sabadell;

DROP USER terrassa;