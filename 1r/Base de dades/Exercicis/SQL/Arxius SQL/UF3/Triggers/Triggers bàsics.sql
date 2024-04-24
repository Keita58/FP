-- Ex 1

alter table city add column last_updated timestamp;

create or replace function add_change() returns trigger as $$
begin 
	new.last_updated = now();
	return new;
end;
$$ language plpgsql;

drop trigger alterar on city;

create trigger alterar before update on city for each row execute procedure add_change();

update city 
	set district = 'Catalonia'
	where name = 'Barberà del Vallès';
	
select * from city where name = 'Barberà del Vallès';

id  |name              |countrycode|district |population|last_updated           |
----+------------------+-----------+---------+----------+-----------------------+
4082|Barberà del Vallès|ESP        |Catalonia|     32839|2024-03-01 17:00:02.005|

-- Ex 2

alter table city add column created_at timestamp;

create or replace function afegeix_data() returns trigger as $$
begin 
	new.created_at = now();
	return new;
end;
$$ language plpgsql;

create trigger insertar before insert on city for each row execute procedure afegeix_data();

insert into city (id, name, countrycode, district, population)
values((select max(id)+1 from city), 'Terrassa', 'ESP', 'Catalonia', 218535);

select *
from city c 
where c."name" = 'Terrassa'

id  |name    |countrycode|district |population|last_updated|created_at             |
----+--------+-----------+---------+----------+------------+-----------------------+
4083|Terrassa|ESP        |Catalonia|    218535|            |2024-03-04 16:15:33.074|
 686|Terrassa|ESP        |Katalonia|    169145|            |                       |
 
-- Ex 3

create or replace function suma_poblacio() returns trigger as $$
declare 
	poblacio int;
begin 
	select population into poblacio
	from country
	where new.countrycode = code;
	update country 
		set population = population + new.population
		where new.countrycode = code;
	return new;
end;
$$ language plpgsql;

create trigger sumar before insert on city for each row execute procedure suma_poblacio();

select population
from country c 
where code = 'ESP'

population|
----------+
  40000000|

insert into city (id, name, countrycode, district, population)
values((select max(id)+1 from city), 'Rubi', 'ESP', 'Catalonia', 76423);

select population
from country c 
where code = 'ESP'

population|
----------+
  40076423|

-- Ex 4
  
create or replace function canviar_poblacio() returns trigger as $$
begin 
	if old.countrycode not like new.countrycode then 
		update country 
			set population = population - new.population
			where old.countrycode = code;
		update country 
			set population = population + new.population
			where new.countrycode = code;
	else
		update country 
		set population = population + new.population
		where new.countrycode = code;
	end if;
	return new; 
end
$$ language plpgsql;

create trigger canvis before update on city for each row execute procedure canviar_poblacio();

select population
from country c 
where code = 'ESP'

population|
----------+
  40076423|
  
select population
from country c 
where code = 'AND'

population|
----------+
     78000|

update city 
	set countrycode = 'AND'
	where name = 'Barcelona' and countrycode = 'ESP';
	
select population
from country c 
where code = 'ESP'

population|
----------+
  38572522|
  
select population
from country c 
where code = 'AND'

population|
----------+
   1581901|
  
-- Ex 5
  
create or replace function resta() returns trigger as $$
begin 
	update country 
		set population = population - old.population
		where old.countrycode = code;
	return old;
end;
$$ language plpgsql;

create trigger esborra before delete on city for each row execute procedure resta();

select population
from country c 
where code = 'ESP';

population|
----------+
  38791057|
  
delete from city
	where name = 'Terrassa' and countrycode = 'ESP';
	
select population
from country c 
where code = 'ESP';

population|
----------+
  38572522|

-- Ex 6
  
alter table country add column ncities integer;  
  
create or replace function conta() returns trigger as $$
declare 
	nums int;
begin 
	select count(*) into nums
	from city
	where countrycode = new.countrycode;
	update country 
		set ncities = nums
		where new.countrycode = code;
	return new;
end
$$ language plpgsql;

create or replace function conta2() returns trigger as $$
declare 
	nums int;
begin 
	select count(*) into nums
	from city
	where countrycode = old.countrycode;
	update country 
		set ncities = nums
		where old.countrycode = code;
	return old;
end
$$ language plpgsql;

drop trigger ciutatsDe on city

create trigger ciutatsInUp after insert or update on city for each row execute procedure conta();

create trigger ciutatsDe after delete on city for each row execute procedure conta2();

update country
	set ncities = (select count(*) from city where countrycode = code)
	from city 
	where code = countrycode;
	
select ncities
from country c 
where c.code = 'ESP';

ncities|
-------+
     60|
     
delete from city 
	where name = 'Rubi' and countrycode = 'ESP'

select ncities
from country c 
where c.code = 'ESP';

ncities|
-------+
     59|
     
insert into city (id, name, countrycode, district, population)
values((select max(id)+1 from city), 'Rubi', 'ESP', 'Catalonia', 76423);

select ncities
from country c 
where c.code = 'ESP';

ncities|
-------+
     60|
     
select *
from city c
where name = 'Rubi'

id  |name|countrycode|district |population|last_updated|created_at             |
----+----+-----------+---------+----------+------------+-----------------------+
4082|Rubi|ESP        |Catalonia|     76423|            |2024-03-05 15:41:19.948|