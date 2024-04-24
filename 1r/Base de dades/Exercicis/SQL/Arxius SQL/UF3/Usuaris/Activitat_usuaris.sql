-- Ex 1

psql -U admin -h 127.0.0.1 -d hotel;

create database airports;

-- Ex 2

create user airadmin createrole password 'llamps_i_trons';

airports=> \du
                                   List of roles
 Role name |                         Attributes                         | Member of
-----------+------------------------------------------------------------+-----------
 admin     | Create role, Create DB                                     | {}
 airadmin  | Create role                                                | {}

airports=> alter database airports owner to airadmin;
ERROR:  must be member of role "airadmin"

-- No podem canviar el propietari si tots dos no estan en el mateix rol/usuari, 
-- en aquest cas hem de posar l'usuari airadmin com a rol a admin per poder fer el canvi de propietari
 
airports=> grant airadmin to admin;
GRANT ROLE
 
airports=> alter database airports owner to airadmin;
ALTER database

airports=> \l airports
                                           List of databases
   Name   |  Owner   | Encoding | Collate |  Ctype  | ICU Locale | Locale Provider | Access privileges
----------+----------+----------+---------+---------+------------+-----------------+-------------------
 airports | airadmin | UTF8     | C.UTF-8 | C.UTF-8 |            | libc            |
(1 row)

airports=> revoke airadmin from admin;
REVOKE role

airports=> \du admin
                 List of roles
 Role name |       Attributes       | Member of
-----------+------------------------+-----------
 admin     | Create role, Create DB | {}

-- Ex 3
 
airports=> create schema flights
airports-> ;
CREATE schema

airports=> create schema aircrafts;
CREATE schema

airports=> create schema passengers;
CREATE schema

-- Ex 4

airports=> select current_user, session_user;
 current_user | session_user
--------------+--------------
 airadmin     | airadmin
(1 row)

airports=> create role GroundControl;
CREATE role

airports=> grant usage on schema flights, aircrafts, passengers to groundcontrol;
GRANT

airports=> create role AirTrafficControl;
CREATE role

airports=> alter role airtrafficcontrol noinherit;
ALTER role

airports=> grant create on schema flights to airtrafficcontrol;
grant

airports=> grant usage on schema flights to airtrafficcontrol;
GRANT

airports=> grant usage on schema aircrafts to airtrafficcontrol;
GRANT

airports=> create role TicketSeller;
CREATE role

airports=> alter role ticketseller noinherit;
ALTER role

airports=> grant usage on schema flights, aircrafts to ticketseller;
grant

airports=> grant create on schema passengers to ticketseller;
grant

airports=> grant usage on schema passengers to ticketseller;
GRANT

airports=> create role Manager;
CREATE role

airports=> grant usage on schema flights to manager;
grant

airports=> grant create on schema aircrafts to manager;
grant

airports=> grant usage on schema aircrafts to manager;
GRANT

-- Ex 5

airports=> create user maria noinherit password 'maria';
CREATE role

airports=> grant manager, airtrafficcontrol to maria;
GRANT ROLE

airports=> create user pere password 'pere';
CREATE role

airports=> grant groundcontrol, ticketseller to pere;
GRANT ROLE

airports=> create user pau password 'pau';
CREATE role

airports=> grant airtrafficcontrol to pau;
GRANT ROLE

airports=> create user anna noinherit password 'anna';
CREATE role

airports=> grant groundcontrol, manager to anna;
GRANT role

airports=> \du
                                                 List of roles
     Role name     |                         Attributes                         |          Member of
-------------------+------------------------------------------------------------+------------------------------
 admin             | Create role, Create DB                                     | {}
 airadmin          | Create role                                                | {}
 airtrafficcontrol | No inheritance, Cannot login                               | {}
 anna              | No inheritance                                             | {groundcontrol,manager}
 groundcontrol     | Cannot login                                               | {}
 manager           | Cannot login                                               | {}
 maria             | No inheritance                                             | {manager,airtrafficcontrol}
 pau               |                                                            | {airtrafficcontrol}
 pere              |                                                            | {groundcontrol,ticketseller}
 postgres          | Superuser, Create role, Create DB, Replication, Bypass RLS | {}
 ticketseller      | No inheritance, Cannot login                               | {}
 
-- Ex 6
 
-- Al estar utilitzant ara l'usuari de Maria per crear les taules, hem d'utilitzar el rol de manager per a poder-les crear, 
-- ja que el rol de Maria es noinheritance i no hereta cap tipus de privilegis dels rols que té. 

airports=> set role manager;
SET
airports=> select current_user, session_user;
 current_user | session_user
--------------+--------------
 manager      | maria
(1 row)

create table aircrafts.Airlines (
  Id serial primary key,
  ICAOCode char(3) unique not null,
  Name varchar(50) not null,
  Country varchar(40) not null,
  OtherDetails text not null default ''
);

create table aircrafts.Manufacturers (
  Id serial primary key,
  ICAOCode varchar(50) unique not null,
  Name varchar(100) not null,
  OtherDetails text not null default ''
);

create table aircrafts.AircraftModels (
  Id serial primary key,
  Code varchar(10) unique not null,
  ManufacturerId int not null,
  Name varchar(100) not null,
  Capacity smallint not null,
  Weight int not null,
  OtherDetails text not null default '',
  CONSTRAINT fkAircraftModelsManufacturers FOREIGN KEY (ManufacturerId) REFERENCES Manufacturers (Id)
		ON DELETE NO ACTION ON UPDATE CASCADE
);

create table aircrafts.Aircrafts (
  Id serial primary key,
  AirlineId int not null,
  RegistrationId varchar(10) unique not null,
  ModelId int not null,
  Name varchar(100) not null,
  OtherDetails text not null default '',
  CONSTRAINT fkAircraftsAirlines FOREIGN KEY (AirlineId) REFERENCES Airlines (Id)
		ON DELETE NO ACTION ON UPDATE cascade,
  CONSTRAINT fkAircraftsAircraftModels FOREIGN KEY (ModelId) REFERENCES AircraftModels (Id)
		ON DELETE cascade ON UPDATE CASCADE
);

-- Ex 7

set role AirTrafficControl;

airports=> select current_user, session_user;
 current_user | session_user
--------------+--------------
 manager      | maria
(1 row)

create table flights.Airports (
  Id serial primary key,
  AirportCode char(6) not null,
  Terminal varchar(2) not null default '',
  City varchar(100) not null,
  CityCode char(3) not null,
  Country varchar(100) not null,
  CountryCode char(3) not null
);

-- En aquesta taula hem d'assignar la clau AircraftId com a forana, però és d'una altra taula on ara mateix no hi podem accedir, 
-- per tant ens hem de donar permissos per poder-hi accedir.
-- Per a donar-li aquests privilegis hem de canviar de session_user al creador de les taules, en aquest cas manager

airports=> set role manager;
set

airports=> grant references on aircrafts.aircrafts to airtrafficcontrol;
GRANT

-- I després tornar a canviar de session_user al que estàvem 

airports => set role airtrafficcontrol;
set

create table flights.ActualFlights (
  Id serial primary key,
  AircraftId int,
  DepartureTime time,
  ArrivalTime time,
  FlightDuration interval hour to minute,
  CONSTRAINT fkActualFlightsAircrafts FOREIGN KEY (AircraftId) REFERENCES aircrafts.Aircrafts (Id)
		ON DELETE cascade ON UPDATE cascade
);

-- Per aquesta taula el mateix, li hem de donar permissos de references per a la taula Airlines

airports=> set role manager;
set

airports=> grant references on aircrafts.airlines to airtrafficcontrol;
grant

airports => set role airtrafficcontrol;
set

create table flights.FlightSchedules (
  Id serial primary key,
  FlightCode char(6) not null,
  ActualFlightId int,
  AirlineId int not null,
  Date date not null,
  DepartureTime time not null,
  ArrivalTime time not null,
  Origin int not null,
  Destination int not null,
  FlightDuration interval hour to minute not null,
  CONSTRAINT fkFlightSchedulesActualFlights FOREIGN KEY (ActualFlightId) REFERENCES flights.ActualFlights (Id)
		ON DELETE cascade ON UPDATE cascade,
  CONSTRAINT fkFlightSchedulesAirlines FOREIGN KEY (AirlineId) REFERENCES aircrafts.Airlines (Id)
		ON DELETE cascade ON UPDATE cascade,
  CONSTRAINT fkFlightSchedulesAirportsOrigin FOREIGN KEY (Origin) REFERENCES flights.Airports (Id)
		ON DELETE cascade ON UPDATE cascade,
  CONSTRAINT fkFlightSchedulesAirportsDestination FOREIGN KEY (Destination) REFERENCES flights.Airports (Id)
		ON DELETE cascade ON UPDATE cascade
);

-- Ex 8

create table passengers.Passengers (
  Id serial primary key,
  FirstName varchar(50) not null,
  LastName varchar(100) not null,
  CountryCode char(3) not null,
  DocumentNumber varchar(15) not null,
  DocumentType varchar(40) not null,
  Email varchar(100) not null default '',
  PhoneNumber varchar(15) not null default '',
  OtherDetails text not null default ''
);

-- Per poder fer la relació de la clau forània hem de fer com en l'exercici anterior, 
-- però aquesta vegada amb el session_user airtrafficcontrol (amb l'usuari maria)

airports=> set role airtrafficcontrol;
set

airports=> grant references on flights.flightschedules to groundcontrol;
GRANT

-- I per què al groundcontrol? Doncs perquè, encara que tant aquest rol com ticketseller tenen connexió amb passengers, 
-- ens estem aprofitant de que pere hereta els privilegis dels seus rols i que groundcontrol te herència dels seus
-- i per tant només afegint el privilegi de references a groundcontrol podem crear la taula amb la clau forània

create table passengers.Reservations (
  Id serial primary key,
  Code char(6) unique not null,
  FlightScheduleId int,
  CONSTRAINT fkReservationsFlightSchedules FOREIGN KEY (FlightScheduleId) REFERENCES flights.FlightSchedules (Id)
		ON DELETE cascade ON UPDATE cascade
);

create table passengers.PassengerReservations (
  Id serial primary key,
  PassengerId int not null,
  ReservationId int not null,
  Seat varchar(5),
  CONSTRAINT fkPassengerReservationsPassenger FOREIGN KEY (PassengerId) REFERENCES passengers.Passengers (Id)
		ON DELETE cascade ON UPDATE cascade,
  CONSTRAINT fkPassengerReservationsreservations FOREIGN KEY (ReservationId) REFERENCES passengers.Reservations (Id)
		ON DELETE cascade ON UPDATE cascade
);

-- Ex 9

-- Per poder fer aquesta vista, hem de donar més permisos especials a l'usuari Pere, 
-- en específic permisos de select per a la taula flights.flightschedules, a la que li hem donat abans permisos de reference 
-- (això ho farem amb l'usuari Maria). 

psql -U maria -h 127.0.0.1 -d airports

airports=> set role airtrafficcontrol;
set

airports=> grant select on flights.flightschedules to groundcontrol;
grant

-- I després ens tornem a connectar amb l'usuari Pere

psql -U pere -h 127.0.0.1 -d airports

CREATE VIEW passengers.ReservationsInfo AS
select r.Code, count(pr.passengerId), f.date, f.flightcode, f.origin, f.destination, f.arrivaltime, f.departuretime
from passengers.reservations r 
join passengers.passengerreservations pr on pr.reservationId = r.Id
join flights.flightschedules f on f.id = r.flightscheduleId
group by r.Code, f.date, f.flightcode, f.origin, f.destination, f.arrivaltime, f.departuretime;

-- Ex 10

-- Per poder fer aquesta vista, hem de donar més permisos especials a l'usuari Pere, 
-- en específic permisos de select per a les taules de l'esquema aircrafts (això ho farem amb l'usuari Maria). 

psql -U maria -h 127.0.0.1 -d airports

airports=> set role manager;
SET

airports=> grant select on aircrafts.aircrafts to groundcontrol;
GRANT

airports=> grant select on aircrafts.aircraftmodels to groundcontrol;
grant

airports=> grant select on aircrafts.airlines to groundcontrol;
grant

airports=> grant select on aircrafts.manufacturers to groundcontrol;
grant

-- Per a poder crear la taula a l'esquema aircrafts li hem de donar permisos de creació, per tant ara hem de canviar
-- d'usuari a la connexió

psql -U maria -h 127.0.0.1 -d airports

airports=> grant create on schema aircrafts to groundcontrol;
grant

-- I una vegada li hem donat els permisos necessaris tornem a canviar a l'usuari pere per a crear la vista

create view aircrafts.AircraftsView as
select a.id, al.name as airlinename, a.registrationid, am.name as modelname, a.name as aircraftname, m.name as manufacturername, a.otherdetails
from aircrafts.aircrafts a 
join aircrafts.aircraftmodels am on am.Id = a.ModelId
join aircrafts.airlines al on a.airlineid = al.id
join aircrafts.manufacturers m on m.id = am.manufacturerid;



