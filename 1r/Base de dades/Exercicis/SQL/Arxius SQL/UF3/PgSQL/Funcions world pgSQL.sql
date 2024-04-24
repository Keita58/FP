-- Ex 1

drop function pob2(bpchar)

create function pob2(codi bpchar, out poblacio integer, out esperanca float) as $$
begin
	select population into strict poblacio
	from country
	where code like codi;
	select lifeexpectancy into strict esperanca
	from country
	where code like codi;
EXCEPTION
	WHEN NO_DATA_FOUND THEN
	  RAISE EXCEPTION 'No hi ha cap resultat';
    WHEN TOO_MANY_ROWS THEN
	  RAISE EXCEPTION 'La consulta retorna més d`una fila';
end;
$$ language plpgsql;

select * from pob2('ESP')

poblacio|esperanca        |
--------+-----------------+
39441700|78.80000305175781|

-- Ex 2

drop function pais(text)

create function pais(districte text) returns text as $$
begin
	return distinct country.name strict
	from city
	join country on code = countrycode
	where district like districte;
EXCEPTION
	WHEN NO_DATA_FOUND THEN
	  RAISE EXCEPTION 'No hi ha cap resultat';
    WHEN TOO_MANY_ROWS THEN
	  RAISE EXCEPTION 'La consulta retorna més d`una fila';
end;
$$ language plpgsql;

select pais('Gelderland')

pais       |
-----------+
Netherlands|

-- Ex 3

create function mod(codi bpchar, poblacio integer) returns void as $$
declare
	num integer;
begin
	select population into num
	from country
	where code like codi;
	if num < poblacio*0.80 or num > poblacio*1.20 or not found then
		raise exception 'La població que s`ha passat per paràmetre passa els límits';
	elsif num >= poblacio*0.80 and num <= poblacio*1.20 and found then 
		update country set population = poblacio where code like codi;
	end if;
end;
$$ language plpgsql;

select mod('ESP', 40000000)

mod|
---+
   |

select population from country where code like 'ESP'

population|
----------+
  40000000|

-- Ex 4
  
drop function crear();
  
CREATE FUNCTION crear(OUT ciutatsabans integer, OUT ciutatsara integer) AS $$
declare
	idciutat integer;
begin
	select count(city.id) into ciutatsabans
	from country
	join city on countrycode = code
	where country.name like 'Spain';
	select max(id) into idciutat
	from city;
	insert into city
	values(idciutat+1, 'Nova ciutat', 'ESP', 'Nou districte', 10000),
	(idciutat+2, 'Nova ciutat', 'ESP', 'Nou districte', 10000),
	(idciutat+3, 'Nova ciutat', 'ESP', 'Nou districte', 10000),
	(idciutat+4, 'Nova ciutat', 'ESP', 'Nou districte', 10000),
	(idciutat+5, 'Nova ciutat', 'ESP', 'Nou districte', 10000);
	select count(city.id) into ciutatsara
	from country
	join city on countrycode = code
	where country.name like 'Spain';
end;
$$ language plpgsql;

select * from crear();

ciutatsabans|ciutatsara|
------------+----------+
          59|        64|
          
-- Ex 5

drop function eliminar();
          
create function eliminar() returns integer as $$
declare 
	ciutatsabans integer;
	ciutatsdespres integer;
begin
	select count(city.id) into ciutatsabans
	from country
	join city on countrycode = code
	where country.name like 'Spain';
	delete from city
	where district like 'Nou districte';
	select count(city.id) into ciutatsdespres
	from country
	join city on countrycode = code
	where country.name like 'Spain';
	return ciutatsabans-ciutatsdespres;
end;
$$ language plpgsql;

select eliminar();

eliminar|
--------+
       5|

-- Ex 6
       
drop function afegir(text, bpchar, float)

create function afegir(idioma text, codi bpchar, percentatge float) returns void as $$
declare
	suma float;
begin 
	select sum(percentage) into suma
	from countrylanguage
	where countrycode like codi;
	if exists (select from countrylanguage where countrycode like codi) then 
		if exists (select from countrylanguage where language like idioma) then 
			if percentatge > 0 then 
				if not exists (select from countrylanguage where countrycode like codi and language like idioma) then 
					if suma+percentatge < 100.0 then 
						insert into countrylanguage values(codi, idioma, false, percentatge);
					else
						raise exception 'La suma dels percentatges amb l`afegit és major a 100%%';
					end if;
				else
					raise exception 'Ja existeix una relació entre l`idioma i el país';
				end if;
			else
				raise exception 'El percentatge que s`ha passat per paràmetre és menor a 0';
			end if;
		else
			raise exception 'L`idioma que es vol afegir en la relació no existeix en la base de dades';
		end if;
	else
		raise exception 'El país que es vol afegir en la relació no existeix en la base de dades';
	end if;
end;
$$ language plpgsql;

select sum(percentage)
from countrylanguage
where countrycode like 'ESP';

sum |
----+
99.3|

select afegir('English', 'ESP', 0.6)

afegir|
------+
      |

select sum(percentage)
from countrylanguage
where countrycode like 'ESP';

sum |
----+
99.9|

-- Ex 7

drop function elimina(text, bpchar)

create function elimina(idioma text, codi bpchar) returns setof text as $$
begin
	if exists (select from countrylanguage where countrycode like codi) then 
		if exists (select from countrylanguage where language like idioma) then 
			if exists (select from countrylanguage where countrycode like codi and language like idioma) then 
				delete from countrylanguage where countrycode like codi and language like idioma;
			else
				raise exception 'No existeix la relació entre el país i l`idioma';
			end if;
		else
			raise exception 'L`idioma que es vol eliminar de la relació no existeix en la base de dades';
		end if;
	else
		raise exception 'El país no existeix en la base de dades';
	end if;	
	return query select language
	from countrylanguage
	where countrycode like codi;
end;
$$ language plpgsql;

select language
from countrylanguage
where countrycode like 'ESP';

language|
--------+
Spanish |
Catalan |
Galecian|
Basque  |
English |

select elimina('English', 'ESP')

elimina |
--------+
Spanish |
Catalan |
Galecian|
Basque  |