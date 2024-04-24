-- Ex 1

alter table rental alter column rental_date set not null;

create or replace function dies(id int) returns int as $$
declare
	diesLloguer int;
	diaLloguer timestamp;
	diaRetorn timestamp;
	resta int;
begin
	if (select return_date from rental where rental_id = id) is null then
		raise exception 'El lloguer demanat no existeix';
	else 
		select f.rental_duration into diesLloguer
		from rental r
		join inventory i on r.inventory_id = i.inventory_id
		join film f on i.film_id = f.film_id
		where r.rental_id = id;
		select rental_date into diaLloguer
		from rental
		where rental_id = id;
		select return_date into diaRetorn
		from rental
		where rental_id = id;
		if(date_part('day', diaRetorn - diaLloguer) <= diesLloguer) then
			return 0;
		else 
			raise info '%', diesLloguer;
			return date_part('day', diaRetorn - diaLloguer) - diesLloguer;
		end if;
	end if;
end;
$$ language plpgsql;

select dies(5)