-- Consulta quants àlbums hi ha a la base de dades.
select count(Albumid)
from Album;

-- Cerca quants grups (Artist) hi ha el nom dels quals comenci per l'article The.
select count(ArtistId)
from Artist
where Name like 'The_%';

-- Amb dues consultes, cerca quants àlbums hi ha de Deep Purple. Primera consulta. Obté ArtistId del grup 'Deep Purple'
select ArtistId 
from Artist
where Name = 'Deep Purple';

-- Amb dues consultes, cerca quants àlbums hi ha de Deep Purple. Segona consulta. Compta quants àlbums ha fet 'Deep Purple' fent servir ArtistId obtingut a la consulta anterior
select count(AlbumId)
from Album
where Artistid = '58';

-- Amb dues consultes, cerca els tres grups dels quals hi ha més àlbums. Primera consulta Obté ArtistId dels 3 grups que tenen més àlbums
select ArtistId
from Album 
group by ArtistId
order by count(AlbumId) desc 
limit 3;

-- Amb dues consultes, cerca els tres grups dels quals hi ha més àlbums. Segona consulta. Obté el Name dels 3 grups que has calculat a la consulta anterior
select Name 
from Artist
where ArtistId in ('22', '58', '90');

-- Cerca la durada mitjana de totes les pistes. Volem el resultat en segons.
select (sum(Milliseconds)/count(TrackId))/1000 as segons
from Track;

-- Amb tres consultes, cerca els 5 àlbums que tenen una durada mitjana de les seves pistes més gran. De cada àlbum en volem el seu nom i el nom del grup. Primera consulta. Obté AlbumId dels cinc àlbums que tinguin la durada mitjana de les seves pistes més gran.
select AlbumId 
from Track
group by AlbumId 
order by (sum(Milliseconds)/count(AlbumId)) desc
limit 5;

-- Amb tres consultes, cerca els 5 àlbums que tenen una durada mitjana de les seves pistes més gran. De cada àlbum en volem el seu nom i el nom del grup. Segona consulta. Obté el Title i ArtistId dels àlbums obtinguts a la consulta anterior.
select Title, ArtistId 
from Album
where AlbumId in ('253', '227', '229', '231', '226');

-- Amb tres consultes, cerca els 5 àlbums que tenen una durada mitjana de les seves pistes més gran. De cada àlbum en volem el seu nom i el nom del grup. Tercera consulta. Obté el Name dels artistes obtinguts a la consulta anterior.
select Name 
from Artist
where ArtistId in ('147', '149', '158');

-- Amb dues consultes, cerca el nom dels tres gèneres dels quals en tenim més pistes. Primera consulta. Obté GenreId dels tres gèneres del quals tinguem més pistes.
select GenreId 
from Track 
group by GenreId
order by count(GenreId) desc 
limit 3;

-- Amb dues consultes, cerca el nom dels tres gèneres dels quals en tenim més pistes. Segona consulta. Obté el Name dels gèneres obtinguts a la consulta anterior.
select Name 
from Genre
where GenreId in ('1', '3', '7');

-- Cerca quantes pistes hi ha en què hi consti algun compositor.
select count(TrackId)
from Track 
where Composer is not null;

-- Cerca quants minuts de música disposem del compositor Johann Sebastian Bach.
select (sum(Milliseconds)/1000)/60
from Track
where Composer like 'Johann Sebastian Bach';

-- Cerca el preu mitjà per pista.
select sum(UnitPrice)/count(TrackId)
from Track;

-- Cerca els diversos preus que tenen les pistes, i quantes n'hi ha de cadascun d'ells.
select UnitPrice, count(TrackId)
from Track
group by UnitPrice;

#----------------------------------------------------------------------
-- Mostra el codi i títol de cada àlbum amb la llista dels codis i títols de les seves cançons (trackss) ordenats pel codi de l'àlbum i dins de cada àlbum pel codi de la cançó..
select a.AlbumId, a.Title, t.TrackId, t.Name 
from Album a
join Track t on t.AlbumId = a.AlbumId 
order by a.AlbumId, t.TrackId;

-- Mostra la llista dels títols de les cançons de l'àlbum "Body Count" ordenada alfabèticament.Mostra la llista dels títols de les cançons de l'àlbum "Body Count" ordenada alfabèticament.
select t.Name 
from Album a 
join Track t on t.AlbumId = a.AlbumId
where a.Title = 'Body Count'
order by t.Name;

-- Mostra el codi i el títol de les cançons que hem facturat en les factures amb codi 3 i 4.
select t.TrackId, t.Name 
from Track t 
join InvoiceLine il on t.TrackId = il.TrackId 
where il.InvoiceId in ('3', '4');

-- Mostra totes les dades dels empleats que tenen assignat un supervisor (camp ReportsTo).
select *
from Employee e 
where ReportsTo is not null;

-- Mostra l'id, nom i cognom dels empleats amb l'id, nom i cognom del seu supervisor.
select e.EmployeeId, e.FirstName, e.LastName, e2.EmployeeId, e2.FirstName, e2.LastName 
from Employee e 
join Employee e2 on e.ReportsTo = e2.EmployeeId;

-- Mostra l'id, import total, l'id del client i data de les factures que hem fet durant el mes de novembre de 2012 ordenades per la data de facturació.
select i.InvoiceId, i.Total, c.CustomerId, i.InvoiceDate 
from Invoice i  
join Customer c ON c.CustomerId = i.CustomerId 
where i.InvoiceDate between '2012-11-01' and '2012-11-30'
order by i.InvoiceDate;

-- Mostra l'id, import total, l'id del client, nom del client, cognom del client i data de les factures que hem fet durant el mes de novembre de 2012 ordenades per la data de facturació.
select i.InvoiceId, i.Total, c.CustomerId, c.FirstName, c.LastName, i.InvoiceDate 
from Invoice i
join Customer c on i.CustomerId = c.CustomerId 
where i.InvoiceDate between '2012-11-01' and '2012-11-30'
order by i.InvoiceDate;

-- Mostra l'id, el país de la factures (BillingCountry), acompanyades de l'id , nom i cognom del client de les factures enviades a Portugal, amb un total superior a 10 o a Canadà amb un total superior a 12 ordenades pel país de facturació i l'id de factura
select i.InvoiceId, i.BillingCountry, c.CustomerId, c.FirstName, c.LastName
from Invoice i 
join Customer c on c.CustomerId = i.CustomerId 
where ((i.BillingCountry like 'Portugal' and i.Total > 10) or (i.BillingCountry like 'Canada' and i.total > 12))
order by i.BillingCountry, i.InvoiceId;

-- Mostra nom de la llista de reproducció 3 amb els id de les seves cançons.
select Name, TrackId 
from PlaylistTrack pt
join Playlist p on pt.PlaylistId = p.PlaylistId 
where p.PlaylistId = '3'

-- Mostra l'id i nom de les llistes de reproducció 3 i 5 amb els id de les seves cançons compresos entre 3350 i 3370 ordenats per l'id de la llista i l'id de la cançò.
select p.PlaylistId, p.Name, pt.TrackId 
from Playlist p 
join PlaylistTrack pt on p.PlaylistId = pt.PlaylistId 
where p.PlaylistId in ('3', '5') and pt.TrackId between '3350' and '3370'
order by pt.PlaylistId, pt.TrackId;

-- Mostra l'id i nom de les llistes de reproducció 3 i 5 amb l'id i nom de les seves cançons. Només han de sortir les cançons amb id comprès entre 3350 i 3370. Ordena els resultats per l'id de la llista i l'id de la cançò.
select p.PlaylistId, p.Name, pt.TrackId, t.Name 
from Playlist p 
join PlaylistTrack pt on pt.PlaylistId = p.PlaylistId 
join Track t on pt.TrackId = t.TrackId 
where p.PlaylistId in ('3', '5') and pt.TrackId between '3350' and '3370'
order by pt.PlaylistId, pt.TrackId;

-- Mostra l'id i nom de les llistes de reproducció 3 i 5 amb l'id i nom de les seves cançons i l'id i nom de l'album en què es van publicar. Només han de sortir les cançons amb id comprès entre 3350 i 3370. Ordena els resultats per l'id de la llista i l'id de la cançò.
select p.PlaylistId, p.Name, pt.TrackId, t.Name, a.AlbumId, a.Title 
from Playlist p 
join PlaylistTrack pt on pt.PlaylistId = p.PlaylistId 
join Track t on pt.TrackId = t.TrackId 
join Album a on a.AlbumId = t.AlbumId 
where p.PlaylistId in ('3', '5') and pt.TrackId between '3350' and '3370'
order by pt.PlaylistId, pt.TrackId;

#---------------------------------------------------------------------
-- Mostra el nom i cognoms dels empleats que estan assignats com a suport d'algun client (SupportRepId).
select distinct e.FirstName, e.LastName 
from Employee e 
join Customer c on c.SupportRepId = e.EmployeeId;

-- Troba el nom de totes les cançons de l'àlbum Back to Black i el nom de l'artista que l'ha fet (sortirà repetit per cada tema). A la sortida, el nom de les columnes ha de ser: TrackName , ArtistName
select t.Name as TrackName, a.Name as ArtistName
from Artist a 
join Album a2 on a2.ArtistId = a.ArtistId 
join Track t on a2.AlbumId = t.AlbumId 
where a2.Title like 'Back to Black';

-- Troba el nom de tots els artistes que es diuen igual que alguna de les cançons.
select distinct a.Name 
from Artist a 
join Album a2 on a2.ArtistId = a.ArtistId 
join Track t on t.AlbumId = a2.AlbumId 
where a.Name like t.Name 

-- Troba el nom de totes les cançons, el nom de l'àlbum, i el nom de l'artista, que s'han comprat a la factura 225.
select t.Name, a.Title, a2.Name as Artista
from Invoice i 
join InvoiceLine il on i.InvoiceId = il.InvoiceId 
join Track t on t.TrackId = il.TrackId 
join Album a on a.AlbumId = t.AlbumId 
join Artist a2 on a2.ArtistId = a.ArtistId 
where i.InvoiceId = 225;

-- Mostra la llista de reproducció anomenada Grunge. Volem veure una llista amb tots els títols de les cançons, la seva durada en segons i el nom de l'artista.
select t.Name, (t.Milliseconds)/1000, a2.Name as Artista 
from Playlist p 
join PlaylistTrack pt on pt.PlaylistId = p.PlaylistId 
join Track t on pt.TrackId = t.TrackId 
join Album a on t.AlbumId = a.AlbumId 
join Artist a2 on a.ArtistId = a2.ArtistId 
where p.Name like 'Grunge';

-- Troba el nom de les cançons que ha comprat el client Enrique Muñoz, i la data en què s'ha comprat cadascuna de les cançons.
select t.Name, i.InvoiceDate 
from Customer c 
join Invoice i on i.CustomerId = c.CustomerId 
join InvoiceLine il on i.InvoiceId = il.InvoiceId 
join Track t on t.TrackId = il.TrackId 
where c.FirstName like 'Enrique' and c.LastName like 'Muñoz';

-- Obté una llista del nom i cognom dels empleats que tenen com a responsable (ReportsTo) a Nancy Edwards, ordenats per cognom
select e.FirstName, e.LastName 
from Employee e 
join Employee e2 on e2.EmployeeId = e.ReportsTo 
where e2.FirstName like 'Nancy' and e2.LastName like 'Edwards'
order by e.LastName;

-- I ara volem saber el nom i cognom del cap directe de l'empleada Nancy Edwards
select e2.FirstName, e2.LastName 
from Employee e 
join Employee e2 on e2.EmployeeId = e.ReportsTo
where e.FirstName like 'Nancy' and e.LastName like 'Edwards';

-- Troba el nom dels artistes que coincideix amb el nom d'alguna cançó d'un altre artista. Volem veure el nom dels dos artistes relacionats.
select a.Name, a3.Name as Creador 
from Artist a, Artist a3  
join Album a2 on a2.ArtistId = a3.ArtistId 
join Track t on t.AlbumId = a2.AlbumId 
where a.Name like t.Name and a.Name not like a3.Name;  

-- Mostra el títol de les cançons i a l'àlbum al qual pertany, d'aquelles que siguin del gènere de rock. Ordena el resultat pel títol de la cançó.
select t.Name, a.Title 
from Genre g 
join Track t on t.GenreId = g.GenreId 
join Album a on a.AlbumId = t.AlbumId 
where g.Name like 'Rock'
order by t.Name;

-- Mostra el nom i cognom de tots els clients juntament amb el nom i cognom de l'empleat que l'ha atès.
select distinct c.FirstName, c.LastName, e.FirstName as EmpleatNom, e.LastName as EmpleatCognom 
from Employee e 
join Customer c on c.SupportRepId = e.EmployeeId;

-- Mostra el nom dels àlbums del grup 'Green Day'
select a2.Title 
from Artist a 
join Album a2 on a2.ArtistId = a.ArtistId 
where a.Name like 'Green Day';

-- Mostra el nom dels àlbums i el nom de totes les cançons de cada àlbum del grup 'Green Day'. Ordena el resultat pe nom de l'àlbum i pel nom de la cançó dins de cada àlbum.
select a2.Title, t.Name 
from Artist a 
join Album a2 on a2.ArtistId = a.ArtistId
join Track t on a2.AlbumId = t.AlbumId 
where a.Name like 'Green Day'
order by a2.Title, t.Name;

-- Mostra l'identificador de la factura, el total i el nom i cognom del client a qui pertany, només d'aquelles factures generades al gener del 2013.
select i.InvoiceId, i.Total, c.FirstName, c.LastName 
from Invoice i 
join Customer c on c.CustomerId = i.CustomerId 
where i.InvoiceDate between '2013-01-01' and '2013-01-31';

-- Mostra l'identificador de la factura i el nom de la cançó, de les factures emeses el gener del 2013. Ordena el resultat per id i després pel nom de la cançó.
select i.InvoiceId, t.Name 
from Invoice i 
join InvoiceLine il on i.InvoiceId = il.InvoiceId 
join Track t on t.TrackId = il.TrackId 
where i.InvoiceDate between '2013-01-01' and '2013-01-31'
order by i.InvoiceId, t.Name;

#-----------------------------------------------------------------------------------------------
-- Cerca quants àlbums hi ha de 'Deep Purple'.
select count(*)
from Artist a 
join Album a2 on a2.ArtistId = a.ArtistId
where a.Name like 'Deep Purple';

-- Mostra el nom i cognoms de tots els empleats i la quantitat de clients a qui estan assignats per donar suport.
select e.FirstName, e.LastName, count(c.CustomerId)
from Employee e 
left join Customer c on e.EmployeeId = c.SupportRepId
group by e.FirstName, e.LastName;

-- Troba els beneficis que hem obtingut amb el gènere 'Blues'. Mostra l'identificador i el nom del gènere, i els beneficis (una única línia)
select g.GenreId, g.Name, sum(il.UnitPrice)
from Genre g 
join Track t on t.GenreId = g.GenreId 
join InvoiceLine il on il.TrackId = t.TrackId 
where g.Name = 'Blues'
group by g.GenreId, g.Name;

-- Troba els diners que hem fet per cadascun dels gèneres musicals. Mostra'n l'identificador, el nom, i els beneficis obtinguts dels gèneres pels quals n'hem obtingut algun benefici. Ordena els resultats de més a menys beneficis, i en cas d'empat pel nom del gènere.
select g.GenreId, g.Name, sum(il.UnitPrice)
from Genre g 
join Track t on g.GenreId = t.GenreId 
join InvoiceLine il on il.TrackId = t.TrackId 
group by g.GenreId, g.Name
order by 3 desc, g.Name;

-- Troba els beneficis que hem obtingut per cadascun dels gèneres musicals. Mostra'n l'identificador, el nom, i els beneficis. Volem una llista amb tots els gèneres, encara que no s'hagi venut cap cançó d'un gènere determinat. Ordena els resultats de més a menys beneficis, i en cas d'empat pel nom del gènere.
select g.GenreId, g.Name, sum(il.UnitPrice)
from Genre g 
left join Track t on g.GenreId = t.GenreId 
left join InvoiceLine il on il.TrackId = t.TrackId 
group by g.GenreId, g.Name
order by 3 desc, g.Name;

-- Troba els beneficis que hem obtingut amb els gèneres pels quals n'hem obtingut més de 100 dòlars. Mostra'n l'identificador, el nom, i els beneficis. Ordena els resultats pels beneficis obtinguts, de més a menys.
select g.GenreId, g.Name, sum(il.UnitPrice)
from Genre g 
left join Track t on g.GenreId = t.GenreId 
left join InvoiceLine il on il.TrackId = t.TrackId 
group by g.GenreId, g.Name
having sum(il.UnitPrice) > 100
order by 3 desc, g.Name;

-- Volem una llista del nom i cognoms de tots els clients i de quantes comandes ha fet cadascun.
select c.FirstName, c.LastName, count(i.InvoiceId)
from Customer c 
left join Invoice i on c.CustomerId = i.CustomerId 
group by c.FirstName, c.LastName; 

-- Obté una llista de tots els gèneres de la base de dades (identificador i nom) i de quantes cançons hi ha de cada gènere.
select g.GenreId, g.Name, count(t.TrackId)
from Genre g 
left join Track t on t.GenreId = g.GenreId 
group by g.GenreId, g.Name;

-- Cerca el nom dels tres grups dels quals hi ha més àlbums. Mostra'n el nom i la quantitat d'àlbums de cadascun.
select a.Name, count(a2.AlbumId)
from Artist a 
join Album a2 on a2.ArtistId = a.ArtistId 
group by a.Name 
order by 2 desc 
limit 3;

-- Cerca el nom dels grups dels quals hi ha més de 8 àlbums. Mostra'n el nom i la quantitat d'àlbums de cadascun. Ordena els resultats de més a menys àlbums, i en cas d'empat pel nom del grup.
select a.Name, count(a2.AlbumId)
from Artist a 
join Album a2 on a2.ArtistId = a.ArtistId 
group by a.Name 
having count(a2.AlbumId) > 8
order by 2 desc, a.Name;

-- Cerca els 5 àlbums que tenen una durada mitjana de les seves pistes més gran. De cada àlbum en volem el seu nom, el nom del grup, i la durada mitjana de les seves pistes.
select a.Title, a2.Name, sum(t.Milliseconds)/count(t.TrackId) 
from Album a 
join Track t on a.AlbumId = t.AlbumId 
join Artist a2 on a2.ArtistId = a.ArtistId 
group by a.Title, a2.Name 
order by 3 DESC 
limit 5;

#-----------------------------------------------------------------------------------------------------
-- Cerca el nom dels tres gèneres dels quals en tenim més pistes, i la quantitat de pistes de cadascun d'ells.
select g.Name, count(t.TrackId)
from Genre g 
join Track t on t.GenreId = g.GenreId 
group by g.Name
order by 2 desc
limit 3

-- Obté l'identificador, el nom, i la quantitat de cançons de les llistes (Playlist) que contenen més de mil cançons. Ordena els resultats de més a menys cançons, i en cas d'empat, per l'identificador de la llista.
select p.PlaylistId, p.Name, count(t.TrackId)
from Playlist p 
join PlaylistTrack pt on pt.PlaylistId = p.PlaylistId 
join Track t on pt.TrackId = t.TrackId 
group by p.PlaylistId, p.Name 
having count(t.TrackId) > 1000
order by 3 desc, p.PlaylistId 

-- Cerca els noms de les cançons i els beneficis que ens han reportat d'aquelles cançons el benefici de les quals sigui de més de 2 dòlars. Ordena els resultats pels beneficis, de més a menys, i en cas d'empat, pel nom de la cançó. (Pista: poden haver cançons amb el mateix nom que estiguin en àlbums diferents ... i es consideren pistes diferents).
select t.Name, sum(il.UnitPrice)
from Track t
join InvoiceLine il on il.TrackId = t.TrackId 
group by t.TrackId, t.Name  
having sum(il.UnitPrice) > 2
order by 2 desc, t.Name

-- Volem una llista de l'identificador i els noms de les cançons, del nom de l'àlbum al qual pertanyen, i del nombre de llistes de reproducció on apareixen, de totes les cançons que apareixen a més de quatre llistes de reproducció.
select t.TrackId, t.Name, a.Title, count(pt.TrackId) 
from Track t 
join Album a on t.AlbumId = a.AlbumId 
join PlaylistTrack pt on t.TrackId = pt.TrackId 
join Playlist p on pt.PlaylistId = p.PlaylistId 
group by t.TrackId, t.Name, a.Title  
having count(p.PlaylistId) > 4

-- Mostra el nom dels artistes i la quantitat de cançons del gènere World que han creat, pels artistes que n'han creat més de 5.
select a.Name, count(t.TrackId)
from Artist a 
join Album a2 on a.ArtistId = a2.ArtistId 
join Track t on a2.AlbumId = t.AlbumId 
join Genre g on g.GenreId = t.GenreId 
where g.Name like 'World'
group by a.Name 
having count(t.TrackId) > 5

-- Troba l'artista preferit de l'Enrique Muñoz (l'artista del qual ha comprat més cançons), i quantes cançons n'ha comprat.
select a2.Name, count(t.TrackId)
from Customer c 
join Invoice i on i.CustomerId = c.CustomerId 
join InvoiceLine il on il.InvoiceId = i.InvoiceId 
join Track t on t.TrackId = il.TrackId 
join Album a on t.AlbumId = a.AlbumId 
join Artist a2 on a.ArtistId = a2.ArtistId
where c.FirstName like 'Enrique' and c.LastName like 'Muñoz'
group by a2.Name 
order by 2 DESC 
limit 1

-- Volem el nom i cognoms de cada empleat i la quantitat d'empleats que depenen d'ell.
select e.FirstName, e.LastName, count(e2.EmployeeId) 
from Employee e 
left join Employee e2 on e2.ReportsTo = e.EmployeeId 
group by e.FirstName, e.LastName 

-- Troba el nom dels artistes que coincideix amb part del nom d'alguna cançó d'un altre artista. Volem veure el nom dels dos artistes relacionats i el títol de la cançó. Si l'Artista creador i el que dóna nom a la cançó són el mateix, no han de sortir.
select a.Name, a2.Name as Creador, t.Name as Canço
from Artist a, Artist a2 
join Album a3 on a2.ArtistId = a3.ArtistId 
join Track t on t.AlbumId = a3.AlbumId 
where t.Name like concat('%', a.Name, '%') and a2.Name not like a.Name 

#---------------------------------------------------------------------------------------------------
-- Cerca quantes cançons hi ha que costin tant com la que costa més.
select count(t.TrackId)
from Track t, (
	select max(t2.UnitPrice) as preuMax
	from Track t2) sc1
where t.UnitPrice = sc1.preuMax

-- De les cançons de preu màxim, mostra el nom de les cinc últimes alfabèticament.
select t.Name 
from Track t, (
	select max(t2.UnitPrice) as preuMax
	from Track t2) sc1
where t.UnitPrice = sc1.preuMax
order by t.Name desc
limit 5

-- Mostra el nom i durada (en minuts) de les cançons que duren més de tres vegades la durada mitjana de totes les cançons. Ordena-les pel seu nom.
select t.Name, t.Milliseconds/60000
from Track t, ( 
	select (sum(t2.Milliseconds)/count(t2.TrackId)) as mitjana
	from Track t2) sc1
where t.Milliseconds > sc1.mitjana*3
order by t.Name, t.Milliseconds 

-- Cerca quines cançons tenen una durada superior a la que té la cançó Take the Celestra. De cada cançó mostra'n el seu nom i la durada, en format hora:minuts:segons (utilitza la funció SEC_TO_TIME). Ordena els resultats pel nom.
select t.Name, SEC_TO_TIME(t.Milliseconds/1000) 
from Track t, (
	select t2.Milliseconds as temps
	from Track t2
	where t2.Name like 'Take the Celestra') sc1
where t.Milliseconds > sc1.temps
order by t.Name 

-- Cerca els noms dels grups/artistes que tenen les 4 xifres més altes d'àlbums gravats. Mostra'n només el nom, ordenat alfabèticament.
select a3.Name, count(a.AlbumId)
from Album a
join Artist a3 on a.ArtistId = a3.ArtistId 
group by a3.Name 
having count(a.AlbumId) in (select * from(
	select count(a2.AlbumId)
	from Album a2
	group by a2.ArtistId 
	order by 1 desc
	limit 4) as consulta )
order by a3.Name asc

-- El preu total d'una factura es pot trobar de dues maneres a la base de dades: utilitzant la taula InvoiceLine, o mirant directament el Total a la taula Invoice. Fes una consulta que detecti qualsevol incongruència entre aquestes dues xifres, és a dir, que mostri les factures per les quals no coincideix aquest càlcul. Mostra l'id i el total de cada factura, i ordena-les pel seu id.
select i.InvoiceId, i.Total 
from Invoice i
where (i.Total, i.InvoiceId) not in (
	select sum(il.UnitPrice), il.InvoiceId 
	from InvoiceLine il
	group by il.InvoiceId
	order by 1 asc)
order by i.InvoiceId 
	
-- Cerca quins àlbums tenen tantes cançons com l'àlbum anomenat Live After Death. Mostra el seu títol i la quantitat de cançons que tenen, i ordena els resultats pel títol.
select a.Title, count(t.TrackId)
from Album a
join Track t on a.AlbumId = t.AlbumId 
group by a.AlbumId, a.Title 
having count(t.TrackId) = (
	select count(t2.TrackId)
	from Album a2
	join Track t2 on a2.AlbumId = t2.AlbumId
	where a2.Title like 'Live After Death')
order by 1

-- Cerca quins àlbums tenen menys cançons. Mostra'n el títol i la quantitat de cançons, i ordena els resultats pel títol.
select a.Title, count(t.TrackId)
from Album a
join Track t on a.AlbumId = t.AlbumId 
group by a.AlbumId, a.Title 
having count(t.TrackId) = (
	select count(t2.TrackId)
	from Album a2
	join Track t2 on a2.AlbumId = t2.AlbumId
	group by a2.AlbumId 
	order by 1 ASC
	limit 1)
order by 1

-- Cerca l'id, el nom i el cognom dels clients que han comprat tantes cançons com la clienta anomenada Camille Bernard. Mostra també la quantitat de cançons comprades, i ordena els resultats per l'id dels clients. (Les cançons que ha comprat són les que estiguin en les seves línies de factures).
select c.CustomerId, c.FirstName, c.LastName, sum(il.Quantity)
from Customer c 
join Invoice i on i.CustomerId = c.CustomerId 
join InvoiceLine il on il.InvoiceId = i.InvoiceId 
group by c.CustomerId, c.FirstName, c.LastName 
having sum(il.Quantity) = (
	select sum(il2.Quantity)
	from Customer c2
	join Invoice i2 on c2.CustomerId = i2.CustomerId
	join InvoiceLine il2 on il2.InvoiceId = i2.InvoiceId
	where c2.FirstName like 'Camille' and c2.LastName like 'Bernard'
	group by c2.CustomerId)
order by c.CustomerId

-- Cerca quins àlbums tenen una durada inferior a l'àlbum anomenat Bach: Goldberg Variations. Mostra el títol i la durada en minuts, i ordena els resultats pel títol.
select a.Title, sum(t.Milliseconds)/60000
from Album a 
join Track t on t.AlbumId = a.AlbumId 
group by a.AlbumId, a.Title 
having sum(t.Milliseconds) < (
	select sum(t2.Milliseconds)
	from Album a2
	join Track t2 on a2.AlbumId = t2.AlbumId
	where a2.Title like 'Bach: Goldberg Variations')
order by a.Title 

#-----------------------------------------------------------------------------------------------------
-- Cerca els àlbums la durada dels quals té una diferència de com a màxim un 10% amb la durada de l'àlbum Bach: Goldberg Variations. És a dir, si aquest àlbum dura, per exemple, 200 minuts, agafaríem tots els àlbums la durada dels quals estigui entre 180 i 220 minuts. Mostra el títol i la durada en minuts, i ordena els resultats pel títol.
select a.Title, sum(t.Milliseconds)/60000
from Album a 
join Track t on t.AlbumId = a.AlbumId 
group by a.AlbumId, a.Title 
having sum(t.Milliseconds) >= (
	select sum(t2.Milliseconds)
	from Album a2
	join Track t2 on a2.AlbumId = t2.AlbumId
	where a2.Title like 'Bach: Goldberg Variations')*0.9 and 
	sum(t.Milliseconds) <= (
	select sum(t2.Milliseconds)
	from Album a2
	join Track t2 on a2.AlbumId = t2.AlbumId
	where a2.Title like 'Bach: Goldberg Variations')*1.1
order by a.Title 

-- Cerca els clients que han fet menys comandes que tots els clients de Noruega (Norway). Mostra l'id, el nom i el cognom dels clients, i la quantitat de comandes que han fet. Ordena els resultats per l'id dels clients.
select c.CustomerId, c.FirstName, c.LastName, count(i.InvoiceId)
from Customer c
join Invoice i on i.CustomerId = c.CustomerId 
group by c.CustomerId, c.FirstName, c.LastName 
having count(i.InvoiceId) < (
	select count(i2.InvoiceId)
	from Invoice i2 
	where i2.BillingCountry like 'Norway'
	group by i2.BillingCountry)
order by c.CustomerId 

-- Cerca els clients que han gastat menys que tots els clients de Noruega (Norway). Mostra l'id, el nom i el cognom dels clients, i la quantitat total que han gastat. Ordena els resultats per l'id dels clients.
select c.CustomerId, c.FirstName, c.LastName, sum(i.Total)
from Customer c
join Invoice i on i.CustomerId = c.CustomerId 
group by c.CustomerId, c.FirstName, c.LastName 
having sum(i.Total) < (
	select sum(i2.Total)
	from Invoice i2 
	where i2.BillingCountry like 'Norway'
	group by i2.BillingCountry)
order by c.CustomerId 

-- Cerca els clients que han gastat un 20% més que almenys un dels clients de Noruega (Norway). Mostra l'id, el nom i el cognom dels clients, i la quantitat total que han gastat. Ordena els resultats per l'id dels clients.
select c.CustomerId, c.FirstName, c.LastName, sum(i.Total)
from Customer c
join Invoice i on i.CustomerId = c.CustomerId 
group by c.CustomerId, c.FirstName, c.LastName 
having sum(i.Total) >= (
	select sum(i2.Total)
	from Invoice i2 
	join Customer c2 on i2.CustomerId = c2.CustomerId
	where i2.BillingCountry like 'Norway'
	group by c2.CustomerId
	limit 1)*1.2
order by c.CustomerId 

-- Troba quins països tenen exactament la mateixa quantitat de factures (Invoice) que el país que en té menys. Mostra el nom del país i la quantitat de factures que té, i ordena els resultats pel nom del país.
select i.BillingCountry, count(i.InvoiceId)
from Invoice i 
group by i.BillingCountry 
having count(i.InvoiceId) = ( 
	select count(i2.InvoiceId)
	from Invoice i2
	group by i2.BillingCountry
	order by 1 ASC
	limit 1)
order by i.BillingCountry

-- Cerca els clients als quals se'ls ha facturat alguna vegada a una adreça de França. Utilitza EXISTS. Mostra l'id, el nom i el cognom dels clients, i ordena els resultats per l'id dels clients.
select c.CustomerId, c.FirstName, c.LastName 
from Customer c 
where EXISTS (
	select *
	from Invoice i 
	where i.BillingCountry like 'France' and i.CustomerId = c.CustomerId)
order by c.CustomerId 

-- Cerca els clients que mai han comprat una cançó composta per en Johann Sebastian Bach. Utilitza NOT EXISTS. Mostra l'id, el nom i el cognom dels clients, i ordena els resultats per l'id dels clients.
select c.CustomerId, c.FirstName, c.LastName 
from Customer c 
where NOT EXISTS (
	select *
	from InvoiceLine il 
	join Invoice i on i.InvoiceId = il.InvoiceId
	join Track t on il.TrackId = t.TrackId
	where t.Composer like 'Johann Sebastian Bach' and i.CustomerId = c.CustomerId)
order by c.CustomerId 




