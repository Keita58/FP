-- Base de dades World
-- Ex 1

drop function ciutats(text);

create function ciutats(pais text) returns integer as $$
	select count(c2.id)
	from country c
	join city c2 on c2.countrycode = c.code
	where c.name like pais
$$ language sql;

select ciutats('Spain');

ciutats|
-------+
     59|
     
-- Ex 2
     
create function idioma(text) returns integer as $$
	select count(*)
	from countrylanguage
	where language like $1
$$ language sql;

select idioma('English');

idioma|
------+
    60|

-- Ex 3
    
drop function inserir(text, bpchar, text, int);

create function inserir(ciutat text, codi bpchar, disc text, quant int) returns integer as $$
	insert into city
	values((select max(id) + 1 from city), ciutat, codi, disc, quant)
	returning id;
$$ language sql;

select inserir('Sabadell', 'ESP', 'Catalonia', 211734);

inserir|
-------+
   4081|

-- Ex 4

drop function mod(int, int, int);

create function mod(id int, nous int, fora int) returns integer as $$
	update city
	set population = population + (nous-fora)
	where id = mod.id
	returning population;
$$ language sql;

select mod(4080, 500, 50);

mod   |
------+
212634|

-- Base de dades Hotel
-- Ex 5

drop function hostes(date);

create function hostes(dates date) returns integer as $$ 
	select sum(b.nhosts)
	from stays s
	join bookings b on s.bookingid = b.id
	where s.checkout > dates and s.checkin <= dates
$$ language sql;

select hostes('2022-01-01');

hostes|
------+
   111|

-- Ex 6
   
drop function hab(varchar, varchar);
   
create function hab(tipus varchar, num varchar) returns integer as $$
	select s.roomnumber 
	from hotel.hosts h
	join hotel.stayhosts sh on h.id = sh.hostid
	join hotel.stays s on s.id = sh.stayid
	where h.doctype like tipus and h.docnumber like num and s.checkout is null
$$ language sql;

select hab('National ID', '949712956541');

hab|
---+
   |

-- Ex 7
   
drop function numhab(int, date, date)
	
create function numhab(idhab int, dataentrada date, datasortida date) returns integer as $$
	(select roomnumber
	from rooms
	where roomtypeid = idhab) 
	except (select r.roomnumber
	from bookings b
	join bookingcalendar bc on bc.bookingid = b.id 
	join rooms r on r.roomnumber = bc.assignedroom
	where b.checkin >= dataentrada and b.checkout <= datasortida and r.roomtypeid = idhab and b.state = 'Reserved')
	order by 1 asc
$$ language sql;

select numhab(1, '2022-01-01', '2022-01-10');

numhab|
------+
    28|
    
-- Ex 8

create function preu(tempo varchar, hab varchar, preu numeric) returns numeric as $$
	update priceseasons 
	set price = preu
	from seasons s, roomtypes r 
	where s.name like tempo and r.name like hab and s.id = seasonid and roomtypeid = r.id
	returning price;
$$ language sql;

select preu('Autumn 2021', 'Triple room with shared bathroom', 90);

preu |
-----+
90.00|

select p.price 
from priceseasons p 
join seasons s on s.id = p.seasonid 
join roomtypes r on r.id = p.roomtypeid 
where s."name" like 'Autumn 2021' and r."name" like 'Triple room with shared bathroom';

price|
-----+
90.00|

-- Ex 9

drop function carac(varchar, varchar);

create function carac(hab varchar, carac varchar) returns boolean as $$
	select case when count(*)=0 then false else true end
	from roomtypes r
	join roomtypefacilities rt on r.id = rt.roomtypeid
	join facilities f on f.id = rt.facilityid
	where r.name like hab and f.name like carac
$$ language sql;

select carac('Triple room with shared bathroom', 'Sofa');

carac|
-----+
true |

-- Ex 10

drop function nits(varchar, varchar)

create function nits(tipus varchar, num varchar) returns integer as $$
	select coalesce(sum(DATE_PART('day', b.checkout::timestamp  - b.checkin::timestamp)), 0)
	from stays s
	join stayhosts sh on s.id = sh.stayid
	join hosts h on h.id = sh.hostid
	join bookings b on b.id = s.bookingid
	where h.doctype like tipus and h.docnumber like num 
$$ language sql;

select nits('National ID', '949712956541');

nits|
----+
  14|