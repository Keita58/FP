-- Ex 1

ALTER TABLE rental ADD COLUMN max_return_date DATE DEFAULT NULL;

create or replace function dataMaxReturn() returns trigger as $$
declare 
	dies int;
begin 
	select f.rental_duration into dies
	from inventory i
	join film f on i.film_id = f.film_id
	where new.inventory_id = inventory_id;
	raise notice '%', dies;
	new.max_return_date = cast(new.rental_date + dies*'1 day'::interval as date);
	raise notice '%', new.max_return_date;
	return new;
end
$$ language plpgsql;

drop trigger dataMax on rental;

create trigger dataMax before insert or update on rental for each row execute procedure dataMaxReturn();

insert into rental(rental_date, inventory_id, customer_id, staff_id, last_update)
values(now(), 1, 1, 1, now());

select *
from rental 
where inventory_id = 1 and customer_id = 1

rental_id|rental_date                  |inventory_id|customer_id|return_date|staff_id|last_update                  |max_return_date|
---------+-----------------------------+------------+-----------+-----------+--------+-----------------------------+---------------+
    16065|2024-03-05 16:32:47.740 +0100|           1|          1|           |       1|2024-03-05 16:32:47.740 +0100|     2024-03-11|

-- Ex 2
    
create type tipus as enum ('avaliable', 'rent', 'maintenance', 'lost', 'others');

alter table inventory add column status tipus;

alter table inventory
drop column status;

update inventory i
	set status = 'rent'
	where i.inventory_id in (select distinct inventory_id from rental where return_date is null);
	
update inventory i
	set status = 'avaliable'
	where i.inventory_id not in (select distinct inventory_id from rental where return_date is null);
	
create trigger noLloguer before insert on rental for each row execute procedure nuuh();

create or replace function nuuh() returns trigger as $$
begin 
	if exists (select * from inventory where new.inventory_id = inventory_id and status = 'rent') then 
		raise exception 'No es pot llogar la peli, està llogada actualment';
	else
		update inventory 	
			set status = 'rent'
			where new.inventory_id = inventory_id;
		return new;
	end if;
	return null;
end;
$$ language plpgsql; 

insert into rental(rental_date, inventory_id, customer_id, staff_id, last_update)
values(now(), 1, 1, 1, now());

-- SQL Error [P0001]: ERROR: No es pot llogar la peli, està llogada actualment

insert into rental(rental_date, inventory_id, customer_id, staff_id, last_update)
values(now(), 2, 1, 1, now());

select *
from rental 
where inventory_id = 2 and max_return_date is not null;

rental_id|rental_date                  |inventory_id|customer_id|return_date|staff_id|last_update                  |max_return_date|
---------+-----------------------------+------------+-----------+-----------+--------+-----------------------------+---------------+
    16068|2024-03-05 17:18:12.793 +0100|           2|          1|           |       1|2024-03-05 17:18:12.793 +0100|     2024-03-11|
    
select status
from inventory
where inventory_id = 2;

status|
------+
rent  |

create trigger noLloguer2 before update on rental for each row execute procedure nuuh2();

create or replace function nuuh2() returns trigger as $$
begin 
	if exists (select * from rental where old.return_date is not null) then 
		raise exception 'No es pot retornar la peli, actualment no està llogada';
	else
		update inventory 	
			set status = 'avaliable'
			where new.inventory_id = inventory_id;
		return new;
	end if;
	return null;
end;
$$ language plpgsql;

update rental 
	set return_date = now()
	where inventory_id = 3524 and customer_id = 195; 

-- SQL Error [P0001]: ERROR: No es pot retornar la peli, actualment no està llogada

update rental 
	set return_date = now()
	where inventory_id = 2 and customer_id = 1; 

select status
from inventory
where inventory_id = 2;

status   |
---------+
avaliable|

select *
from rental 
where inventory_id = 2 and max_return_date is not null;

rental_id|rental_date                  |inventory_id|customer_id|return_date                  |staff_id|last_update                  |max_return_date|
---------+-----------------------------+------------+-----------+-----------------------------+--------+-----------------------------+---------------+
    16070|2024-03-05 17:32:21.223 +0100|           2|          1|2024-03-05 17:48:15.264 +0100|       1|2024-03-05 17:48:15.264 +0100|     2024-03-11|