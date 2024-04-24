-- Insercions
-- Ex1
INSERT INTO actor(first_name, last_name)
	VALUES('Charlize', 'Theron');
	
-- Ex2
INSERT INTO film(title, description, release_year, language_id, original_language_id, rental_duration, rental_rate,
`length`, replacement_cost, rating, special_features, last_update)
select 'MONSTER', description, 2003, language_id, original_language_id, rental_duration, rental_rate,
`length`, replacement_cost, rating, special_features, last_update
from film 
where title like 'BOOGIE AMELIE'

-- Ex3
set @Actriu = (select actor_id from actor where first_name like 'Charlize' and last_name like 'Theron');

set @Peli = (select film_id from film where title like 'MONSTER');

INSERT INTO film_actor (actor_id, film_id)
	values(@Actriu, @Peli)

-- Ex4
set @Peli = (select film_id from film where title like 'MONSTER');

set @Drama = (select category_id from category where name like 'Drama');

INSERT INTO film_category(film_id, category_id)
	values(@Peli, @Drama)

-- Ex5
INSERT INTO film(title, description, release_year, language_id, original_language_id, rental_duration, rental_rate,
`length`, replacement_cost, rating, special_features, last_update)
select 'Cars', description, release_year, language_id, original_language_id, rental_duration, rental_rate, `length`, 
replacement_cost, rating, special_features, last_update
from film f 
where f.`length` = (select min(`length`) from film)
limit 1

-- Ex6
INSERT INTO category (name)
values('Noir');

-- Ex7
set @Ciutat = (SELECT c2.city
from customer c 
join address a on c.address_id = a.address_id 
join city c2 on a.city_id = c2.city_id 
group by c2.city_id, c2.city 
having count(c2.city_id) = (select count(c2.city_id) 
	from customer c 
	join address a on c.address_id = a.address_id 
	join city c2 on a.city_id = c2.city_id 
	group by c2.city_id 
	order by 1 desc 
	limit 1)
limit 1);

set @Adreca = (select a.address_id from address a where a.city_id = (select city_id from city where city=@Ciutat) limit 1);

INSERT INTO customer(store_id, first_name, last_name, email, address_id, active, create_date)
values(1, 'Jaime', 'Altozano', 'jaltozano@ies-sabadell.cat', @Adreca, 1, now())

-- Ex8
set @Id_Client = LAST_INSERT_ID();

set @Inventory = (select i.inventory_id from inventory i limit 1);

set @Treballador = (select s.staff_id from staff s limit 1);

INSERT INTO rental(rental_date, inventory_id, customer_id, staff_id)
values(now(), @Inventory, @Id_Client, @Treballador);

-- Actualitzacions
-- Ex1
UPDATE film f
	join film_category fc on f.film_id = fc.film_id 
	join category c on fc.category_id = c.category_id 
	set f.`length` = f.`length` + 2
	where c.name like 'Action';

-- Ex2
set @Adreca_Id = (SELECT address_id 
	from customer 
	WHERE first_name like 'Carol' and last_name like 'Garcia');

UPDATE address a
	set a.address = '150 Blue Avenue'
	where a.address_id = @Adreca_Id;

-- Ex3
UPDATE payment 
	set amount = (amount*0.9)
	where payment_date BETWEEN '2005-07-01' and '2005-07-31';

-- Eliminacions
-- Ex1
set @Id_Client = (select c.customer_id 
		from customer c 
		join rental r on r.customer_id = c.customer_id 
		group by c.customer_id 
		having count(r.rental_id) = 1);

DELETE 
	from rental  
	where customer_id = @Id_Client;

DELETE 
	from customer 
	where customer_id = @Id_Client;

-- Ex2
set @Id_Peli = (select film_id
	from film 
	where `length` = (select min(`length`) from film) limit 1);

DELETE 
	from rental 
	where inventory_id in (select inventory_id from inventory where film_id = @Id_Peli)

DELETE 
	from inventory 
	where film_id = @Id_Peli
	
DELETE 
	from film_actor  
	where film_id = @Id_Peli
	
DELETE 
	from film_category  
	where film_id = @Id_Peli

DELETE 
	from film 
	where film_id = @Id_Peli

-- Ex3
set @Id_Categoria = (select category_id from category where name like 'Travel');

UPDATE film_category 
	set category_id = 6 -- Per a no esborrar les pelis, he canviat la seva categoria, no se si està bé així o volies que esborrés tot com l'apartat anterior, sinó seria el mateix que abans realment
	where category_id = @Id_Categoria
	
DELETE 
	from category 
	where name like 'Travel'