-- Mostra el títol de les pel·lícules on no ha participat cap actor.
select f.title 
from film f
left join film_actor fa on f.film_id = fa.film_id 
where fa.film_id is null;

-- Mostra el nom i cognom dels actors que no han participat a cap pel·lícula.
select a.first_name, a.last_name 
from actor a 
except
select distinct a2.first_name, a2.last_name 
from actor a2
join film_actor fa on fa.actor_id = a2.actor_id;

-- Mostra el nom dels idiomes que no tenen cap pel·lícula enregistrada.
select distinct l.name
from `language` l
left join film f on l.language_id = f.language_id 
where f.language_id is null;

-- Mostra el títol i longitud de les pel·lícules que tinguin una durada d'entre 90 i 100 minuts (inclosos) i de les quals no en tinguem còpies en cap magatzem (inventory). Mostra el resultat ordenat per títol.
select f.title, f.`length` 
from film f
left join inventory i on f.film_id = i.film_id 
where f.length between 90 and 100 and i.film_id is null
order by f.title;

-- Veure, per cada país, el nom del país i el nom de les seves ciutats enregistrades, ordenat pel nom del país i el nom de la ciutat.
select c.country, c2.city
from country c 
join city c2 on c.country_id = c2.country_id 
order by c.country_id, c2.city_id;

-- Veure, per cada país, el seu nom i el nom de les seves ciutats, i per cada adreça enregistrada de cada ciutat, els camps address, address2, i district. Ordena els resultats pel nom del país i nom de la ciutat.
select c.country, c2.city, a.address, a.address2, a.district 
from country c 
left join city c2 on c2.country_id = c.country_id 
left join address a on c2.city_id = a.city_id 
order by c.country, c2.city 

-- Veure el nom de les ciutats i del seu país, de les ciutats que no tenen cap adreça enregistrada. Ordena els resultats per nom del país i nom de la ciutat.
select c2.city, c.country 
from country c 
join city c2 on c.country_id = c2.country_id
except
select c2.city, c.country 
from country c 
join city c2 on c.country_id = c2.country_id 
join address a on a.city_id = c2.city_id;

-- Volem saber si tenim algun actor i client que tinguin el mateix nom i cognoms. Mostrar el nom i cognom de les coincidències ordenades per cognom i nom.
select a.first_name, a.last_name 
from actor a 
join customer c on c.first_name = a.first_name and c.last_name = a.last_name 
order by a.last_name, a.first_name;

#-------------------------------------------------------------------------------------------------------------
-- La pel·lícula "ALADDIN CALENDAR" té l'id 10. Cerca els id de tots els actors que han participat a la pel·lícula, i mostra'ls ordenats de major a menor.
select a.actor_id
from film f 
join film_actor fa on fa.film_id = f.film_id 
join actor a on fa.actor_id = a.actor_id 
where f.film_id = 10
order by a.actor_id desc

-- A partir de la llista d'ids que has obtingut a la pregunta anterior, cerca el nom i cognom de tots els actors i actrius que han participat a "ALADDIN CALENDAR". Ordena'ls pel cognom, i si dos tenen el mateix cognom, pel nom.
select a.first_name, a.last_name 
from actor a 
join film_actor fa on a.actor_id = fa.actor_id 
join film f on fa.film_id = f.film_id 
where f.film_id = 10
order by a.last_name, a.first_name 

-- Mostra una llista ordenada amb tots els id de pel·lícules de l'1 fins al 100, i al costat, la quantitat d'actors/actrius que han participat a cadascuna de les pel·lícules.
select f.film_id, count(a.actor_id)
from film f 
join film_actor fa on f.film_id = fa.film_id 
join actor a on fa.actor_id = a.actor_id 
group by f.film_id
order by f.film_id asc
limit 100

-- Cerca ara l'id de les pel·lícules a les quals han participat més de 8 actors o actrius. Ordena els resultats per la quantitat d'actors, de més a menys, i si dues pel·lícules tenen la mateixa quantitat d'actors, ordena-les pel seu id. Per cadascuna, mostra el seu id, i la quantitat d'actors.
select f.film_id, count(a.actor_id)
from film f 
join film_actor fa on f.film_id = fa.film_id 
join actor a on fa.actor_id = a.actor_id 
group by f.film_id
having count(a.actor_id) > 8
order by 2 desc, f.film_id 

-- Mostra el nom i cognom de tots els actors i actrius el nom i cognom dels quals té una consonant en segona posició. Ordena els resultats pel cognom, i en cas d'empat, pel nom.
select a.first_name, a.last_name 
from actor a 
where a.first_name rlike '^[A-Z][^aeiou]' and a.last_name rlike '^[A-Z][^aeiou]'
order by a.last_name, a.first_name

-- Per cada id de pel·lícula, mostra quants exemplars en tenim a cadascuna de les botigues. Per exemple, per la pel·lícula amb id 1, en tenim 4 exemplars a la botiga 1, i 4 més a la botiga 2. Ordena els resultats per l'id de la pel·lícula, i pels empats, per l'id de la botiga. La informació sobre els exemplars que tenim es troba a la taula inventory.
select f.film_id, count(i.inventory_id), i.store_id 
from film f 
join inventory i on i.film_id = f.film_id 
group by f.film_id, i.store_id 
order by f.film_id, i.store_id 

-- Amb dues consultes, mostra l’id i el número total de pel·lícules que han fet les actrius el nom de les quals és "Penelope". Primera consulta: Selecciona l’id de les actrius que es diguin "Penelope".
select a.actor_id 
from actor a 
where a.first_name like 'Penelope'

-- Amb dues consultes, mostra l’id i el número total de pel·lícules que han fet les actrius el nom de les quals és "Penelope". Segona consulta: Amb la dada obtinguda a la consulta anterior, mostra l'id de cada actriu i la quantitat de pel·lícules que ha fet cadascuna d'elles.
select a.actor_id, count(f.film_id)
from actor a
join film_actor fa on fa.actor_id = a.actor_id 
join film f on fa.film_id = f.film_id 
where a.first_name like 'Penelope'
group by a.actor_id 

-- De tots els lloguers que es van fer el 21 d'agost de 2005, mostra l'id del client, i la data de retorn, dels 5 que s'han retornat més tard. Tingues en compte que pot passar que algun d'ells encara no s'hagi retornat, i volem que surti a la llista.
select r.customer_id, r.return_date 
from rental r 
where date(r.rental_date) = date('2005-08-21')
order by -r.return_date ASC 
limit 5

-- Cerca els clients que han fet més de 40 lloguers. D'aquests clients, mostra'n el seu identificador, i la quantitat de lloguers que han fet. Ordena els resultats per l'identificador del client.
select c.customer_id, count(r.rental_id)
from rental r 
join customer c on r.customer_id = c.customer_id 
group by c.customer_id 
having count(r.rental_id) > 40
order by c.customer_id 