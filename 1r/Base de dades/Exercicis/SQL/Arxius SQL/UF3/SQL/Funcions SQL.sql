-- Base de dades world

-- Ex 1

drop function poblacio(city)

create function poblacio(ciu city) returns numeric as $$
	select (ciu.population::numeric/c.population::numeric)*100 as percentatge
	from country c
	where ciu.countrycode = c.code
$$ language sql;

select poblacio(c.*)
from city c 
join country co on c.countrycode = co.code 
where c."name" like 'Barcelona' and c.countrycode like 'ESP';

poblacio              |
----------------------+
3.81297205749245088300|

-- Ex 2

drop function pais(bpchar)

create function pais(codi bpchar) returns country as $$
	select *
	from country
	where code like codi
$$ language sql;

select pais(c.code)
from country c
where c."name" like 'Spain';

pais                                                                                                                                |
------------------------------------------------------------------------------------------------------------------------------------+
(ESP,Spain,Europe,Southern Europe,505992.0,1492,39441700,78.8,553233.0,532031.0,España,Constitutional Monarchy,Juan Carlos I,653,ES)|

-- Ex 3

create function pais2(codi bpchar, out nomfora text, out nomlocal text) as $$
	select "name", localname
	from country
	where code like codi
$$ language sql;

select *
from pais2('ESP');

name |localname|
-----+---------+
Spain|España   |

-- Ex 4

create function idioma(idioma text) returns setof text as $$
	select c."name"
	from countrylanguage cl
	join country c on c.code = cl.countrycode
	where language like idioma
$$ language sql;

select idioma('Spanish');

idioma              |
--------------------+
Andorra             |
Argentina           |
Aruba               |
Belize              |
Bolivia             |
Chile               |
Costa Rica          |
Dominican Republic  |
Ecuador             |
El Salvador         |
Spain               |
Guatemala           |
Honduras            |
Canada              |
Colombia            |
Cuba                |
Mexico              |
Nicaragua           |
Panama              |
Paraguay            |
Peru                |
Puerto Rico         |
France              |
Sweden              |
Uruguay             |
Venezuela           |
United States       |
Virgin Islands, U.S.|

-- Base de dades hotel

-- Ex 1

create function hab(tipus varchar, data date) returns setof rooms as $$
	select r.*
	from roomtypes rt
	join rooms r on r.roomtypeid = rt.id
	where rt.name not in (select bc.roomnumber
	from bookingcalendar bc
	where b.bookeddate = data)
$$ language sql;

select hab('Double room with shared bathroom', '2022-03-10');

hab|
---+

-- Ex 2

create function host(hab integer, data date) returns setof hosts as $$
	select h.*
	from stays s
	join stayhosts sh on sh.stayid = s.id
	join hosts h on h.id = sh.hostid
	join bookings b on b.id = s.bookingid
	join bookingcalendar bc on b.id = bc.bookingid
	where s.roomnumber = hab and bc.bookeddate = data;
$$ language sql;

select host(3, '2022-01-01')

host|
----+
   3|
   
-- Ex 3

create function moneyyyyy(data date, out hostes numeric, out diners numeric, out reserves numeric) as $$ 
	select count(*), (select sum(totalprice) from stays where date(paymentdatetime) = data), (select count(*) from bookings where date(reservationdatetime) = data)
	from stays s 
	join stayhosts sh on s.id = sh.stayid
	where s.checkin <= data and (s.checkout > data or s.checkout is null)
$$ language sql;

select * from moneyyyyy('2022-02-02') 

hostes|diners |reserves|
------+-------+--------+
    51|2752.72|      43|


