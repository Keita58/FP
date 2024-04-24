-- Ex 1

create function suma(num1 integer, num2 integer) returns integer as $$
begin 
    return num1 + num2;
end;
$$ language plpgsql;

select suma(1, 2);

suma|
----+
   3|

-- Ex 2

create function contar(cadena1 text, cadena2 text) returns integer as $$
begin 
    raise info 'Longitud de la primera cadena %', length(cadena1);
    raise info 'Longitud de la segona cadena %', length(cadena2);
    return length(cadena1) + length(cadena2);
end;
$$ language plpgsql;

select contar('hola', 'mon')

Longitud de la primera cadena 4
Longitud de la segona cadena 3
contar|
------+
     7|

-- Ex 3

create function paisos(llengua text) returns integer as $$
declare
	quantity integer;
begin 
	select count(*) into quantity
	from countrylanguage cl
	where cl.language like llengua;
	return quantity;
end;
$$ language plpgsql;

select paisos('English')

paisos|
------+
    60|
    
-- Ex 4

create function afegir(nom text, codi bpchar, district text, poblacio int) returns integer as $$
declare
	ultimId integer;
begin
	select max(id) + 1 into ultimId
	from city;
	insert into city
	values(ultimId, nom, codi, district, poblacio);
	return ultimId;
end;
$$ language plpgsql;

select afegir('Barberà del Vallès', 'ESP', 'Catalonia', 32839)

afegir|
------+
  4082|

-- Ex 5

create function files() returns integer as $$
declare
	quantity integer;
begin
	select count(*) into quantity
	from city;
	select count(*)+quantity into quantity
	from country;
	select count(*)+quantity into quantity
	from countrylanguage;
	return quantity;
end;
$$ language plpgsql;

select files();

files|
-----+
 5305|
 
-- Ex 6
 
drop function pob(bpchar)

create function pob(codi bpchar) returns city as $$
declare
	c city;
begin
	select * into c
	from city
	where countrycode like codi
	order by population desc 
	limit 1;
	IF NOT FOUND THEN
    	RAISE EXCEPTION 'No existeix cap pais amb el codi %', codi;
  	END IF;
	return c;
end;
$$ language plpgsql;

select pob('ESP')

pob                            |
-------------------------------+
(653,Madrid,ESP,Madrid,2879502)|

-- Ex 7

drop function files2()

create function files2(out city integer, out country integer, out language integer) as $$
begin
	select count(*) into city
	from city;
	select count(*) into country
	from country;
	select count(*) into language
	from countrylanguage;
end;
$$ language plpgsql;

select * from files2()

city|country|language|
----+-------+--------+
4082|    239|     984|

-- Ex 8

drop function paisos2(text)

create function paisos2(idioma text) returns setof country as $$
declare
	num integer;
begin 
	return query select country.*
	from countrylanguage
	join country on code = countrycode
	where language like idioma;
	select count(country.*) into num
	from countrylanguage
	join country on code = countrycode
	where language like idioma;
	raise notice 'El total de països és: %', num;
end;
$$ language plpgsql;

select * from paisos2('Spanish')

El total de països és: 28

code|name                |continent    |region          |surfacearea|indepyear|population|lifeexpectancy|gnp       |gnpold    |localname                          |governmentform                              |headofstate                      |capital|code2|
----+--------------------+-------------+----------------+-----------+---------+----------+--------------+----------+----------+-----------------------------------+--------------------------------------------+---------------------------------+-------+-----+
AND |Andorra             |Europe       |Southern Europe |      468.0|     1278|     78000|          83.5|   1630.00|          |Andorra                            |Parliamentary Coprincipality                |                                 |     55|AD   |
ARG |Argentina           |South America|South America   |  2780400.0|     1816|  37032000|          75.1| 340238.00| 323310.00|Argentina                          |Federal Republic                            |Fernando de la Rúa               |     69|AR   |
BOL |Bolivia             |South America|South America   |  1098581.0|     1825|   8329000|          63.7|   8571.00|   7967.00|Bolivia                            |Republic                                    |Hugo Bánzer Suárez               |    194|BO   |
CHL |Chile               |South America|South America   |   756626.0|     1810|  15211000|          75.7|  72949.00|  75780.00|Chile                              |Republic                                    |Ricardo Lagos Escobar            |    554|CL   |
CRI |Costa Rica          |North America|Central America |    51100.0|     1821|   4023000|          75.8|  10226.00|   9757.00|Costa Rica                         |Republic                                    |Miguel Ángel Rodríguez Echeverría|    584|CR   |
DOM |Dominican Republic  |North America|Caribbean       |    48511.0|     1844|   8495000|          73.2|  15846.00|  15076.00|República Dominicana               |Republic                                    |Hipólito Mejía Domínguez         |    587|DO   |
ECU |Ecuador             |South America|South America   |   283561.0|     1822|  12646000|          71.1|  19770.00|  19769.00|Ecuador                            |Republic                                    |Gustavo Noboa Bejarano           |    594|EC   |
SLV |El Salvador         |North America|Central America |    21041.0|     1841|   6276000|          69.7|  11863.00|  11203.00|El Salvador                        |Republic                                    |Francisco Guillermo Flores Pérez |    645|SV   |
ESP |Spain               |Europe       |Southern Europe |   505992.0|     1492|  39441700|          78.8| 553233.00| 532031.00|España                             |Constitutional Monarchy                     |Juan Carlos I                    |    653|ES   |
GTM |Guatemala           |North America|Central America |   108889.0|     1821|  11385000|          66.2|  19008.00|  17797.00|Guatemala                          |Republic                                    |Alfonso Portillo Cabrera         |    922|GT   |
HND |Honduras            |North America|Central America |   112088.0|     1838|   6485000|          69.9|   5333.00|   4697.00|Honduras                           |Republic                                    |Carlos Roberto Flores Facussé    |    933|HN   |
COL |Colombia            |South America|South America   |  1138914.0|     1810|  42321000|          70.3| 102896.00| 105116.00|Colombia                           |Republic                                    |Andrés Pastrana Arango           |   2257|CO   |
CUB |Cuba                |North America|Caribbean       |   110861.0|     1902|  11201000|          76.2|  17843.00|  18862.00|Cuba                               |Socialistic Republic                        |Fidel Castro Ruz                 |   2413|CU   |
MEX |Mexico              |North America|Central America |  1958201.0|     1810|  98881000|          71.5| 414972.00| 401461.00|México                             |Federal Republic                            |Vicente Fox Quesada              |   2515|MX   |
NIC |Nicaragua           |North America|Central America |   130000.0|     1838|   5074000|          68.7|   1988.00|   2023.00|Nicaragua                          |Republic                                    |Arnoldo Alemán Lacayo            |   2734|NI   |
PAN |Panama              |North America|Central America |    75517.0|     1903|   2856000|          75.5|   9131.00|   8700.00|Panamá                             |Republic                                    |Mireya Elisa Moscoso Rodríguez   |   2882|PA   |
PRY |Paraguay            |South America|South America   |   406752.0|     1811|   5496000|          73.7|   8444.00|   9555.00|Paraguay                           |Republic                                    |Luis Ángel González Macchi       |   2885|PY   |
PER |Peru                |South America|South America   |  1285216.0|     1821|  25662000|          70.0|  64140.00|  65186.00|Perú/Piruw                         |Republic                                    |Valentin Paniagua Corazao        |   2890|PE   |
PRI |Puerto Rico         |North America|Caribbean       |     8875.0|         |   3869000|          75.6|  34100.00|  32100.00|Puerto Rico                        |Commonwealth of the US                      |George W. Bush                   |   2919|PR   |
URY |Uruguay             |South America|South America   |   175016.0|     1828|   3337000|          75.2|  20831.00|  19967.00|Uruguay                            |Republic                                    |Jorge Batlle Ibáñez              |   3492|UY   |
VEN |Venezuela           |South America|South America   |   912050.0|     1811|  24170000|          73.1|  95023.00|  88434.00|Venezuela                          |Federal Republic                            |Hugo Chávez Frías                |   3539|VE   |
BLZ |Belize              |North America|Central America |    22696.0|     1981|    241000|          70.9|    630.00|    616.00|Belize                             |Constitutional Monarchy                     |Elisabeth II                     |    185|BZ   |
USA |United States       |North America|North America   |  9363520.0|     1776| 278357000|          77.1|8510700.00|8110900.00|United States                      |Federal Republic                            |George W. Bush                   |   3813|US   |
VIR |Virgin Islands, U.S.|North America|Caribbean       |      347.0|         |     93000|          78.1|      0.00|          |Virgin Islands of the United States|US Territory                                |George W. Bush                   |   4067|VI   |
ABW |Aruba               |North America|Caribbean       |      193.0|         |    103000|          78.4|    828.00|    793.00|Aruba                              |Nonmetropolitan Territory of The Netherlands|Beatrix                          |    129|AW   |
FRA |France              |Europe       |Western Europe  |   551500.0|      843|  59225700|          78.8|1424285.00|1392448.00|France                             |Republic                                    |Jacques Chirac                   |   2974|FR   |
SWE |Sweden              |Europe       |Nordic Countries|   449964.0|      836|   8861400|          79.6| 226492.00| 227757.00|Sverige                            |Constitutional Monarchy                     |Carl XVI Gustaf                  |   3048|SE   |
CAN |Canada              |North America|North America   |  9970610.0|     1867|  31147000|          79.4| 598862.00| 625626.00|Canada                             |Constitutional Monarchy, Federation         |Elisabeth II                     |   1822|CA   |
