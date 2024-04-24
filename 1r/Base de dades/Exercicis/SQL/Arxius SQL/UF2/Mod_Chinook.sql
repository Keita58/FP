-- Insercions 
-- Ex1
INSERT INTO Customer(FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId)
	VALUES('Marc', 'Sánchez', 'DAMvi', 'Carrer de Juvenal, 1', 'Sabadell', 'BCN', 'Catalunya', '08206', '+34 606 060660', '+34 606 060660', 'e.msanchezl@ies-sabadell.cat', 3);

-- Ex2
INSERT INTO Artist(Name)
	VALUES('M.I.A');

INSERT INTO Album(Title, ArtistId)
	VALUES('Kala', LAST_INSERT_ID());

set @IdAlbum = LAST_INSERT_ID();

INSERT INTO Track(Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice)
VALUES
	('Bamboo Banga', @IdAlbum, 1, 9, 'M.I.A., Switch, Jonathan Richman, Ilaiyaraaja', 298.000, 5000000, 0.99),
	('Bird Flu', @IdAlbum, 1, 9, 'M.I.A., Switch, R. P. Patnaik', 204.000, 3000000, 0.99),
	('Boyz', @IdAlbum, 1, 9, 'M.I.A., Switch', 207.000, 3500000, 0.99),
	('Jimmy', @IdAlbum, 1, 9, 'M.I.A., Switch, Bappi Lahiri', 209.000, 4000000, 0.99),
	('Hussel', @IdAlbum, 1, 9, 'M.I.A., Switch, Diplo', 265.000, 3000000, 0.99),
	('Mango Pickle Down River', @IdAlbum, 1, 9, 'M.I.A., Keith Dutton, Lendal King, Colin Roy Johnson, Walter Ebsworth, Buddy Blair, Morgan Lewis, Brendan Adams, Daniel Wright, Will Jarrett', 233.000, 2500000, 0.99),
	('20 Dollar', @IdAlbum, 1, 9, 'M.I.A., Switch, Cahrles Thompson', 274.000, 3000000, 0.99),
	('World Town', @IdAlbum, 1, 9, 'M.I.A., Switch, Blaqstarr', 232.000, 4000000, 0.99),
	('The Turn', @IdAlbum, 1, 9, 'M.I.A., Blaqstarr', 232.000, 4000000, 0.99),
	('XR2', @IdAlbum, 1, 9, 'M.I.A., Diplo, Switch', 260.000, 2000000, 0.99),
	('Paper Planes', @IdAlbum, 1, 9, 'M.I.A., Diplo, Joe Strummer, Mick Jones, Paul Simonon, Topper Headon', 204.000, 4000000, 0.99),
	('Come Around', @IdAlbum, 1, 9, 'Timothy Mosley, Timothy Clayton, M.I.A.', 233.000, 5000000, 0.99);

-- Ex3
set @PrimeraCanço = LAST_INSERT_ID();

INSERT INTO Playlist(Name)
	VALUES('Prova');

set @IdLlista = LAST_INSERT_ID();

INSERT INTO PlaylistTrack
VALUES
	(@IdLlista, @PrimeraCanço),
	(@IdLlista, @PrimeraCanço + 1),
	(@IdLlista, @PrimeraCanço + 2),
	(@IdLlista, @PrimeraCanço + 3),
	(@IdLlista, @PrimeraCanço + 4),
	(@IdLlista, @PrimeraCanço + 5);

-- Ex4
set @Client = (select c.CustomerId
from Customer c 
where c.FirstName like 'Niklas' and c.LastName like 'Schröder');

set @LosingPreu = (select t.UnitPrice
from Track t
where t.Name like 'Losing My Religion');

set @LosingId = (select t.TrackId
from Track t
where t.Name like 'Losing My Religion');

set @HeartlandPreu = (select t.UnitPrice
from Track t
where t.Name like 'Heartland');

set @HeartlandId = (select t.TrackId
from Track t
where t.Name like 'Heartland');

set @BillingAddress = (select c.Address 
from Customer c 
where c.FirstName like 'Niklas' and c.LastName like 'Schröder');

set @BillingCity = (select c.City 
from Customer c 
where c.FirstName like 'Niklas' and c.LastName like 'Schröder');
	
set @BillingState = (select c.State 
from Customer c 
where c.FirstName like 'Niklas' and c.LastName like 'Schröder');

set @BillingCountry = (select c.Country 
from Customer c 
where c.FirstName like 'Niklas' and c.LastName like 'Schröder');

set @BillingPostalCode = (select c.PostalCode 
from Customer c 
where c.FirstName like 'Niklas' and c.LastName like 'Schröder');

INSERT INTO Invoice(CustomerId, InvoiceDate, BillingAddress, BillingCity, BillingState, BillingCountry, BillingPostalCode, Total)
	VALUES(@Client, NOW(), @BillingAddress, @BillingCity, @BillingState, @BillingCountry, @BillingPostalCode, @LosingPreu*2 + @HeartlandPreu);
	
set @Compra = LAST_INSERT_ID();

INSERT INTO InvoiceLine(InvoiceId, TrackId, UnitPrice, Quantity)
VALUES
	(@Compra, @LosingId, @LosingPreu, 2),
	(@Compra, @HeartlandId, @HeartlandPreu, 1);

-- Ex5
set @Cap = (select e.EmployeeId from Employee e where e.FirstName like 'Nancy' and e.LastName like'Edwards');

set @Carrer = (select e.Address from Employee e limit 1);

set @Ciutat = (select e.City from Employee e order by e.FirstName desc limit 1);

set @Estat = (select e.State from Employee e order by e.EmployeeId limit 1);

set @Pais = (select e.Country from Employee e order by e.LastName asc limit 1);

set @CodiPostal = (select e.PostalCode from Employee e order by e.LastName asc limit 1);

set @Numero = (select e.Phone from Employee e order by e.Phone desc limit 1);

INSERT INTO Employee(LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)
	VALUES('Gordon', 'John', 'Sales Manager', @Cap, '2000-08-26 00:00:00.000', now(), @Carrer, @Ciutat, @Estat, @Pais, @CodiPostal, @Numero, @Numero, 'john@chinookcorp.com')

-- Actualitzacions
-- Ex1
set @Id = (select CustomerId from Customer c where FirstName like 'Mark' and LastName like 'Taylor');
	
UPDATE Customer 
	set Address = '68-70 Oxford St',
	City = 'Darlinghurst',
	State = 'NSW', 
	PostalCode = '2010',
	Country = 'Australia'
	where CustomerId = @Id;

-- Ex2
UPDATE Track 
	set UnitPrice = UnitPrice - (UnitPrice * .2)
	where TrackId in (select * from (select t.TrackId  
		from Track t 
		order by t.Milliseconds asc
		limit 30) t);

-- Ex3	
UPDATE Track 
	set UnitPrice = UnitPrice + (UnitPrice * .15)
	where TrackId in (select * from (select il.TrackId 
		from InvoiceLine il 
		group by il.TrackId
		having count(il.TrackId) = (
			select count(il.TrackId)
			from InvoiceLine il 
			group by il.TrackId
			order by 1 desc
			limit 1)
		limit 20) t);

-- Ex4
set @nou = (select e.EmployeeId from Employee e where e.FirstName like 'Margaret' and e.LastName like 'Park');

UPDATE Customer 
	set SupportRepId = @nou
	where Country like 'India'

-- Ex5
set @Id = (SELECT c.CustomerId 
from Customer c 
where c.FirstName like 'Eduardo' and c.LastName like 'Martins');

set @IdCanço = (select t.TrackId 
from Track t 
where t.Name like 'Garota De Ipanema'
limit 1);

set @CostCanço = (select t.UnitPrice 
from Track t 
where t.Name like 'Garota De Ipanema'
limit 1);

set @DataCompra = (select max(i.InvoiceDate)
from Invoice i 
where i.CustomerId = @Id);

UPDATE Invoice 
	set Total = Total + @CostCanço
	where InvoiceDate = @DataCompra
	
set @IdCompra = (select i.InvoiceId from Invoice i where i.InvoiceDate = @DataCompra);

INSERT INTO InvoiceLine(InvoiceId, TrackId, UnitPrice, Quantity)
	VALUES(@IdCompra, @IdCanço, @CostCanço, 1);

-- Eliminacions
-- Ex1
delete 
	from InvoiceLine 
	where InvoiceId in (select * from(select i.InvoiceId 
		from Invoice i 
		where i.InvoiceDate = '2013-01-28') t)
		
delete 
	from Invoice 
	where InvoiceId in (select * from(select i.InvoiceId 
		from Invoice i 
		where i.InvoiceDate = '2013-01-28') t)
		
-- Ex2
delete
	from PlaylistTrack 
	where PlaylistId = (select PlayListId from Playlist where Name like 'Music' limit 1);

DELETE 
	from Playlist
	where PlaylistId = (select PlayListId from Playlist where Name like 'Music' limit 1);

-- Ex3
DELETE 
	from PlaylistTrack 
	where TrackId in (select * from (select t2.TrackId 
		from Track t2 
		where t2.TrackId not in (select distinct t.TrackId
		from InvoiceLine il
		join Track t on il.TrackId = t.TrackId)) t)
		
DELETE 
	from Track 
	where TrackId in (select * from (select t2.TrackId 
		from Track t2 
		where t2.TrackId not in (select distinct t.TrackId
		from InvoiceLine il
		join Track t on il.TrackId = t.TrackId)) t)