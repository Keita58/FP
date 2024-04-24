-- Obté la llista de pel·lícules que compleixen: tenen un preu de 3,99€ o un lloguer de 4 dies o menys i que inclouen Astronaut o Dentist a la descipció
select film_id, title, rental_rate, rental_duration, description 
from film 
where (rental_rate = 3.99 or rental_duration <= 4) and (description like '%astronaut%' or description like '%dentist%')
order by title;

-- Pregunta 2
select count(rental_id), min(rental_date) 
from rental 
group by return_date 
having return_date is null

-- Pregunta 3
select customer_id, count(payment_id), sum(amount)
from payment 
group by customer_id
having sum(amount) > 200
order by sum(amount) desc;

-- Pregunta 4
select film_id, title, description, `length` 
from film 
where title like '%mermaid%'
order by `length` ASC
limit 2;

-- Pregunta 5
select inventory_id 
from rental 
group by inventory_id 
having count(*) = 1
order by inventory_id desc;
