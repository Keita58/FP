USE miniwind;

CREATE VIEW profit_per_product AS
	select p.product_code, p.product_name, p.category, ifnull(sum(od.quantity), 0) as Quantitat, ifnull(sum(od.quantity*od.unit_price), 0) as Benefici
	from products p 
	left join order_details od on od.product_id = p.id
	group by p.product_code, p.product_name, p.category;

select ppp.category, sum(ppp.Benefici)
from profit_per_product ppp
where ppp.category like 'Condiments' or ppp.category like 'Beverages' or ppp.category like 'Pasta'
group by ppp.category;

CREATE VIEW customer_products AS
	select c.id, c.first_name, c.last_name, GROUP_CONCAT(distinct p.product_name /*order by p.product_name asc*/) as Productes
	from customers c 
	left join orders o on c.id = o.customer_id
	left join order_details od on o.id = od.order_id
	left join products p on p.id = od.product_id
	group by c.id, c.first_name, c.last_name;

select cp.first_name, cp.last_name 
from customer_products cp 
where cp.Productes is NULL;

CREATE VIEW order_list AS
	select c.first_name, c.last_name, p.product_code, p.product_name, o.order_date, od.quantity, (od.quantity*od.unit_price) as Preu
	from customers c 
	join orders o on c.id = o.customer_id
	join order_details od on o.id = od.order_id
	join products p on p.id = od.product_id
	group by c.first_name, c.last_name, p.product_code, p.product_name, o.order_date, od.quantity, od.unit_price; /*No cal posar-ho*/

select ol.first_name, ol.last_name, sum(ol.quantity)
from order_list ol 
where ol.order_date between '2006-01-01' and '2006-03-31' 
group by ol.id, ol.first_name, ol.last_name;