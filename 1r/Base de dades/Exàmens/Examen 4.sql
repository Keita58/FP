-- Exercici 1

select *
from Hosts h 
where h.DocNumber = 942604018;

Id  |DocType |DocNumber|FirstName|LastName|Nationality|
----+--------+---------+---------+--------+-----------+
3181|Passport|942604018|JANA     |MARSH   |English    |

select *
from Hosts h 
where h.DocNumber = 02351823;

Id|DocType|DocNumber|FirstName|LastName|Nationality|
--+-------+---------+---------+--------+-----------+

select *
from Hosts h 
where h.DocNumber = 479487873;

Id|DocType|DocNumber|FirstName|LastName|Nationality|
--+-------+---------+---------+--------+-----------+

-- Dos dels tres hostes de la reserva 7166 no han sigut hostes amb anterioritat, per tant els inserirem a la base de dades.

INSERT INTO Hosts(DocType, DocNumber, FirstName, LastName, Nationality)
values('Passport', '02351823', 'Adrian', 'Gill', 'English');

INSERT INTO Hosts(DocType, DocNumber, FirstName, LastName, Nationality)
values('Passport', '479487873', 'Susan', 'Collins', 'English');

-- 

set @Hab = (select distinct bc.AssignedRoom from BookingCalendar bc where bc.BookingId = 7166);

set @Preu = (select b.Price from Bookings b where b.Id = 7166);

INSERT INTO Stays(CheckIn, RoomNumber, TotalPrice, BookingId)
values('2023-11-13', @Hab, @Preu, 7166);

set @StayId = LAST_INSERT_ID();

set @Susan = (select h.Id from Hosts h where h.DocNumber = 479487873);

set @Adrian = (select h.Id from Hosts h where h.DocNumber = 02351823);

set @Jana = (select h.Id from Hosts h where h.DocNumber = 942604018);

INSERT INTO StayHosts 
values(@StayId, @Susan),
(@StayId, @Adrian),
(@StayId, @Jana);

-- 

UPDATE Rooms 
	set Empty = 0
	where RoomNumber = @Hab;
	
-- Exercici 2

set @Moqueta = (select f.Id from Facilities f where f.Name like 'Carpeted');

delete
	from RoomTypeFacilities 
	where FacilityId = @Moqueta and RoomTypeId in (select Id from RoomTypes where Name like 'Single room%');

set @TV = (select f.Id from Facilities f where f.Name like '%TV%');

set @Tipus = (select rt.Id from RoomTypes rt where rt.Name like 'Double room%shared bathroom%');

INSERT INTO RoomTypeFacilities 
values(@Tipus, @TV);

-- Exercici 3

set @Tipus = (select rt.Id from RoomTypes rt where rt.Name like 'Double room%private bathroom%');

set @Prim = (select s.Id from Seasons s where s.Name like 'Spring 2024');

set @Est = (select s.Id from Seasons s where s.Name like 'Summer 2024');

set @PreuPrim = (select ps.Price from PriceSeasons ps where ps.RoomTypeId = @Tipus and ps.SeasonId = @Prim);

set @aux = @PreuPrim*1.02;
select @PreuPrim;

@PreuPrim|
---------+
    74.76|
    
select @aux;

@aux   |
-------+
76.2552|

set @PreuEst = (select ps.Price from PriceSeasons ps where ps.RoomTypeId = @Tipus and ps.SeasonId = @Est);

set @aux2 = @PreuEst*1.02;

DELETE 
	from PriceSeasons 
	where RoomTypeId = @Tipus and (SeasonId = @Prim or SeasonID = @Est);
	
INSERT INTO PriceSeasons 
values(@Tipus, @Prim, @aux);

INSERT INTO PriceSeasons 
values(@Tipus, @Est, @aux2);

-- Exercici 4

Drop Table Payments 

CREATE TABLE Payments (
	Id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	Amount DECIMAL(10,2) NOT NULL,
	PaymentDateTime DATE NOT NULL,
	BookingId INT UNSIGNED NOT NULL,
	PaymentType ENUM('Card', 'Cash') NOT NULL,
	HostId INT UNSIGNED,
	CONSTRAINT FOREIGN KEY (BookingId) REFERENCES Bookings(Id)
		ON DELETE NO ACTION ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (HostId) REFERENCES Hosts(Id)
		ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- 

INSERT INTO Payments (Amount, PaymentDateTime, BookingId, PaymentType, HostId)
select s.TotalPrice, s.PaymentDateTime, s.BookingId, s.PaymentType, NULL 
from Stays s 
where s.PaymentDateTime is not NULL; 

-- 

ALTER TABLE Stays DROP COLUMN TotalPrice;

ALTER TABLE Stays DROP COLUMN PaymentDateTime;

ALTER TABLE Stays DROP COLUMN PaymentType;

-- Exercici 5

CREATE VIEW BookedRooms AS
select b.CheckIn, b.RoomTypeId, rt.Name, count(r.Empty) as Num, GROUP_CONCAT(distinct r.RoomNumber) as Habitacions
from Bookings b 
join RoomTypes rt on b.RoomTypeId = rt.Id 
join Rooms r on r.RoomTypeId = rt.Id 
group by b.CheckIn, b.RoomTypeId, rt.Name;

--

select br.Num 
from BookedRooms br 
where br.CheckIn = '2023-11-30' and br.Name like 'Double room with private bathroom';

Num|
---+
 33|