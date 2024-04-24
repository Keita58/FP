-- Seleccionar de la taula employees tots els empleats de Washington. S'han d'ordenar els resultats pel cognom. Les dades que s'han de mostrar són l'identificador d'empleat, el cognom, i la posició laboral (job_title) que ocupa.
select id, last_name, job_title 
from employees 
where city = 'Washington'
order by last_name asc;

-- Mostra l'identificador, el nom i el cognom dels empleats el cognom dels quals comenci per "J".
select id, first_name, last_name 
from employees
where last_name like 'J%';

-- Mostra els cognoms de la taula employees que tinguin una "e" en la segona posició.
select last_name 
from employees
where last_name like '_e%';

-- Mostra els noms de la taula employees que comencin per "T" i que continguin una "e".
select first_name 
from employees 
where first_name like 'T%e%';

-- Mostra el cognom dels empleats la posició dels quals és "Sales Manager", "Sales Representative", o "Sales Coordinator".
select last_name 
from employees
where job_title in ('Sales Manager', 'Sales Representative', 'Sales Coordinator');

-- Mostra els cognoms dels empleats que no ocupin cap de les tres posicions anteriors.
select last_name 
from employees
where job_title not in ('Sales Manager', 'Sales Representative', 'Sales Coordinator');

-- Mostra una llista dels empleats de Washington que sàpiguen francès (es diu a les notes), o d'Atlanta que no en sàpiguen. Volem veure el seu identificador, el nom, el cognom, la ciutat, i les notes. (Si al camp notes no es menciona aquest idioma o no posa res és que l'empleat no el sap).
select id, first_name, last_name, city, notes 
from employees
where (city = 'Washington' and notes like '%French%') or (city = 'Atlanta' and notes not like '%French%')

-- Mostra el nom i el proveïdor dels productes de la taula products que siguin només del proveïdor (supplier_ids) número 4 o només del 10.
select p.product_name, p.supplier_ids 
from products p
where p.supplier_ids in ('4', '10');

-- Mostra el nom i el proveïdor dels productes de la taula products que NO siguin només del proveïdor 4 ni només del 10.
select product_name, supplier_ids 
from products
where supplier_ids not in ('4', '10');

-- Obté el nom i el preu dels productes el preu dels quals (list_price) està comprès entre 10 i 20 (inclosos).
select product_name, list_price 
from products 
where list_price between 10 and 20;

-- Obté el nom i el preu dels productes el preu dels quals NO està comprès entre 10 i 20.
select product_name, list_price 
from products
where list_price not between 10 and 20;

-- Obté el nom dels productes, el preu i el número del proveïdor que tinguin un preu superior a 20 i que siguin només del proveïdor número 4 o només del número 10.
select product_name, list_price, supplier_ids 
from products
where list_price > 20 and supplier_ids in ('4', '10');

-- Mostra el nom, cost i preu dels productes pels quals la diferència entre el preu de cost (standard_cost) i el preu de venta sigui de més de 10.
select product_name, standard_cost, list_price 
from products
where (list_price - standard_cost) > 10;

-- Selecciona la llista de productes (identificador i nom del producte) que contenen la paraula 'soup' en el seu nom. Escriu la consulta de manera que funcioni correctament independentment de si la paraula 'soup' s'escriu en majúscules o en minúscules.
select id, product_name 
from products
where product_name like '%soup%';

-- Fes una llista de les comandes (taula orders) que es van servir al client número 4 i que, a més, han tingut un cost d'enviament (shipping_fee) de més de 4 dòlars. Mostra l'identificador i la data de la comanda, i el cost d'enviament.
select id, order_date, shipping_fee 
from orders
where customer_id = 4 and shipping_fee > 4;

#-----------------------------------------------------------------------------------------------------------------------

-- Obtenir la data de la comanda, la data d'enviament i l'adreça d'enviament de totes les comandes enviades a la ciutat de New Orleans. Ordena els resultats per la data en què s'ha fet la comanda, de més recents a més antigues.
select order_date, shipped_date, ship_address 
from orders
where ship_city = 'New Orleans' and status_id = 3
order by order_date desc;

-- Obtenir l'identificador, el nom de la persona receptora de l'enviament, i l'identificador de client, de totes les comandes per a les quals no consta una data d'enviament. Ordena els resultats per l'identificador del client.
select id, ship_name, customer_id 
from orders 
where date(shipped_date) is null
order by customer_id;

-- Obtenir l'identificador, la data de la comanda, i l'identificador de client, de totes les comandes que es van fer entre el febrer i el maig de 2006 (inclosos), i ordena-les per la data de la comanda.
select id, order_date, customer_id 
from orders 
where date(order_date) between '2006-02-01 00:00:00.000' and '2006-05-31 23:59:59.999'
order by order_date;

-- Obté l'identificador, la data de la comanda, i la ciutat de destí de totes les comandes les despeses d'enviament de les quals siguin superiors a 100 dòlars i que o bé s'hagin pagat amb un xec o bé siguin posteriors a febrer de 2006 (order date) (sense incloure'l).
select id, order_date, ship_city 
from orders 
where shipping_fee > 100 and (payment_type = 'Check' or date(paid_date) > '2006-02-31 23:59:59.999');

-- Obté l'identificador, i l'adreça i ciutat d'enviament de totes les comandes que siguin posteriors al 15 de maig de 2006 i que tinguin un enviament gratuït.
select id, ship_address, ship_city 
from orders 
where date(order_date) > '2006-05-15 23:59:59.999' and shipping_fee = 0;

-- Obté l'identificador, el nom, i el cognom de tots els clients el nom dels quals comença per A i que viuen a una ciutat el nom de la qual conté almenys una 'a'.
select id, first_name, last_name 
from customers
where city like '%a%' and first_name like 'A%'

-- Obté l'identificador del producte, la quantitat de productes comprats i el preu de cada unitat, de la comanda amb número 51 (utilitza la taula order_details). Ordena el resultat per la quantitat, de gran a petit, i si de dos productes se n'ha comprat la mateixa quantitat, pel preu unitari (de molt a poc).
select product_id, quantity, unit_price 
from order_details
where order_id = 51
order by quantity desc, unit_price desc;

-- Obté l'identificador de totes les comandes per a les quals hi hagi almenys un dels productes comprats que no s'hagin facturat. Els productes facturats tenen un 2 a la columna status_id de la taula order_details. Evita resultats repetits i ordena els resultats de menor a major id.
select distinct order_id 
from order_details
where status_id in ('0', '1')
order by order_id asc;

-- Obté l'identificador, l'identificador de la comanda, i l'identificador del producte de totes les comandes (a order_details) que no estiguin associades a cap ítem d'inventari, o que estiguin associades a un ítem d'inventari de número superior a 130.
select id, order_id, product_id 
from order_details
where quantity = 0 or inventory_id > 130;

-- Obté l'identificador, l'identificador de la comanda, l'identificador del producte, la quantitat demanada, el preu unitari i l'import total de les línies de detall que tinguin aquest import total superior a 1000 però inferior a 10000.
select id, order_id, product_id, quantity, unit_price, (quantity*unit_price) as import_total
from order_details
where quantity*unit_price between 1001 and 10000 

-- Obté l'identificador, el codi de producte i el nom del producte, dels productes que no siguin begudes ( categoria "Beverages") i que tinguin un preu de venta de menys de 10 dòlars. Ordena els resultats pel nom del producte.
select id, product_code, product_name 
from products
where category not like 'Beverages' and list_price < 10
order by product_name;

-- Obté l'identificador, l'identificador del client i la data de comanda, de totes les comandes pagades amb targeta de crèdit i enviades als estats de California (CA) o Florida (FL).
select id, customer_id, order_date 
from orders
where payment_type like 'Credit Card' and ship_state_province in ('CA', 'FL');

-- Mostra el nom, cognom, adreça i ciutat de residència de tots els clients, ordenats pel cognom, de l'A a la Z, després pel nom, també d'A a la Z, i finalment per la ciutat de residència, de la Z a la A.
select first_name, last_name, address, city 
from customers
order by last_name asc, first_name asc, city desc;

-- Selecciona totes les comandes que encara no s'han completat (status_id val 3 si s'ha completat), i que es van fer entre el 15 de maig de 2006 i el 15 de juny de 2006. D'aquestes comandes en volem saber l'identificador, l'identificador del client i de l'empleat, la data de la comanda, i el nom a qui s'han enviat. Ordena els resultats per l'identificador del client primer, i en segon lloc per l'identificador de l'empleat.
select id, customer_id, employee_id, order_date, ship_name 
from orders
where status_id != 3 and date(order_date) between '2006-05-15' and '2006-06-15'
order by customer_id, employee_id;

-- Obté l'identificador, el codi de producte i el nom del producte, dels productes que continguin la combinació 'ea' en el seu nom, però que no hi tinguin més de tres erres.
select id, product_code, product_name 
from products
where product_name like '%ea%' and product_name not like '%r%r%r%r%'

#---------------------------------------------------------------------------------------------------------------------

-- Obté l'identificador, el codi de producte i el nom del producte, dels productes el preu de venda dels quals sigui menys d'un 30% superior al preu de cost.
select id, product_code, product_name 
from products 
where (((list_price/standard_cost)-1)*100) < 30;

-- Obté l'identificador, el nom, i el cognom, de tots els clients que tenen el nom o el cognom acabat en 'e', però no els dos. Ordena els resultats pel cognom.
select id, first_name, last_name 
from customers
where (first_name like '%e' and last_name not like '%e') or (first_name not like '%e' and last_name like '%e')
order by last_name;

-- Mostra, sense repeticions i ordenats de menor a major, els codis dels productes que ens han demanat a les comandes i que ja hem facturat.
select distinct product_id 
from order_details
where status_id = 2
order by product_id asc;

-- Mostra el codi del client i el codi de les comandes que ja hem hem enviat però encara no ens han pagat.
select customer_id, id
from orders 
where status_id = 2 and payment_type is null;

-- Obté l'identificador i el nom dels productes pels quals la quantitat en stock per sota de la qual s'ha de fer una comanda al proveïdor (reorder_level) no coincideix amb la quantitat mínima que s'ha de demanar (minimum_reorder_quantity). Tingues en compte que si només un dels dos camps val null s'ha de considerar com que no coincideixen.
select id, product_name 
from products 
where reorder_level != minimum_reorder_quantity or reorder_level is null or minimum_reorder_quantity is null;

-- Mostra, sense repeticions, el codi dels empleats que eren els responsables de les comandes que hem trigat més de 2 dies en enviar ordenats de major a menor codi d'empleat.
select distinct employee_id 
from orders
where DATEDIFF(shipped_date, order_date) > 2
order by employee_id desc;

-- Visualitza el codi i la data en què s'ha demanat de les comandes que encara no hem enviat o de les que sí hem enviat ja però encara no hem cobrat. S'han de mostrar ordenades de més antiga a més nova i si són del mateix dia a més a més per codi de comanda.
select id, order_date
from orders
where shipped_date is null or (status_id = 2 and payment_type is null)
order by order_date asc, id;

#---------------------------------------------------------------------------------------------------------------

-- Mostra quants productes hi ha de cada categoria. S'ha de mostrar també el nom de la categoria.
select count(category), category 
from products 
group by category;

-- Mostra quants productes hi ha de cada categoria, només d'aquelles categories que tinguin més de 4 productes. S'ha de mostrar també el nom de la categoria.
select count(category), category
from products
group by category
having count(category) > 4;

-- Mostra quants empleats tenim en total.
select count(id)
from employees

-- Mostra quants empleats tenim en total el nom dels quals comença per T (first_name).
select count(id)
from employees 
where first_name like ('T%')

-- Mostrar la mitjana del preu de venta (list_price) per cada categoria. Mostra el nom de la categoria i la mitjana.
select category, (sum(list_price)/count(category))
from products 
group by category;

-- Mostra el total de les despeses d'enviament (shipping_fee) de les comandes que s'han pagat amb targeta de crèdit.
select sum(shipping_fee)
from orders 
group by payment_type 
having payment_type = 'Credit Card';

-- Compta les comandes que encara no s'han pagat (paid_date).
select count(id)
from orders
group by paid_date
having paid_date is null;

-- Mostra quants clients són de l'estat de Nova York.
select count(id)
from customers
group by state_province 
having state_province like 'NY';

-- Mostra els diferents llocs de treball dels clients, ordenats descendentment.
select DISTINCT job_title 
from customers 
order by job_title desc;

-- Mostra la quantitat d'unitats venudes de cadascun dels productes en totes les comandes. Cal mostrar l'identificador de cada producte i la quantitat.
select product_id, sum(quantity)
from order_details
group by product_id;

-- Mostra per cada comanda la quantitat que s'ha pagat.
select sum(quantity*unit_price), order_id 
from order_details
group by order_id;

#-----------------------------------------------------------------------------------------
-- Mostra el nom i cognom dels clients que han fet alguna comanda.
select distinct c.first_name, c.last_name 
from customers c 
join orders o on o.customer_id = c.id;

-- Mostra el nom i cognom dels clients que han fet alguna comanda. També ha de sortir la data de la comanda (order_date) i l'identificador dels productes de la comanda. Ordena el resultat pel cognom del client, per l'identificador del producte, i per la data de la comanda.
select distinct c.first_name, c.last_name, o.order_date, p.id 
from customers c 
join orders o on o.customer_id = c.id
join order_details od on o.id = od.order_id 
join products p on p.id = od.product_id 
order by c.last_name, p.id, o.order_date;

-- Mostra el número de comanda, l'email de l'empleat que ha fet al comanda i el nom de la companyia que ha fet la comanda, ordenats per número de comanda
select o.id, e.email_address, c.company c 
from orders o
join employees e on e.id = o.employee_id 
join customers c on c.id = o.customer_id 
order by o.id

-- Mostra número de comanda, el nom i cognom de l'empleat que ha fet la comanda i el nom i cognom del client que ha fet la comanda, ordenat per número de comanda.
select o.id, e.first_name, e.last_name, c.first_name as clientNom, c.last_name as cognomClient 
from orders o
join employees e on e.id = o.employee_id 
join customers c on c.id = o.customer_id 
order by o.id

-- Una variació sobre la consulta anterior. Volem saber el nom i cognom de l'empleat i el nom i cognom del client que s'han relacionat alguna vegada a través d'una comanda, sense repeticions i ordenat per cognom i nom de l'empleat
select distinct e.first_name, e.last_name, c.first_name as clientNom, c.last_name as cognomClient 
from orders o
join employees e on e.id = o.employee_id 
join customers c on c.id = o.customer_id 
order by e.last_name, e.first_name 

-- Volem saber el nom de les ciutats on tenim clients i empleats (a la vegada), sense repeticions i ordenades alfabèticament
select distinct city 
from employees
intersect
select distinct city
from customers
order by city asc;

-- Volen veure les dades de les factures (invoice) dels clients que siguin de les ciutats de 'Fresno' o 'Indianapolis' fetes a partir del dia 23 de març de 2006
select i.*
from customers c 
join orders o on o.customer_id = c.id
join invoices i on i.order_id = o.id 
where (c.city like 'Fresno' or c.city like 'Indianapolis') and i.invoice_date > '2006-03-23';

-- Volem saber, sense repeticions, el nom i cognom dels clients que han comprat alguna vegada productes de la categoria 'Beverages'
select distinct c.first_name, c.last_name 
from customers c 
join orders o on o.customer_id = c.id 
join order_details od on o.id = od.order_id 
join products p on od.product_id = p.id 
where p.category like 'Beverages';

#------------------------------------------------------------------------------------
-- Mostra el nom dels productes i el seu identificador del productes que s'han demanat alguna vegada (order_details). Mostra el resultat sense repeticions i ordenat pel nom.
select distinct p.product_name, p.id 
from products p 
join order_details od on od.product_id = p.id
order by p.product_name;

-- Mostra l'identificador, el nom i la quantitat de producte que s'ha demanat a cada comanda (order_details). La consulta també ha de mostrar l'id de la comanda que ha demanat el producte. Ordena el resultat per l'identificador de producte, en cas d'empat, per l'id de la comanda.
select p.id, p.product_name, od.quantity, od.order_id  
from products p 
join order_details od on od.product_id = p.id
order by p.id, od.order_id;

-- Mostra l'identificador i el nom de tots els productes. D'aquells que s'ha fet comanda també ha de sortir la quantitat que s'ha demanat per comanda i l'id de la comanda (si no s'ha fet comanda, aquest id ha de sortir com a 0). Ordena el resultat per l'identificador del producte, i en cas d'empat, per l'id de la comanda
select p.id, p.product_name, od.quantity, IFNULL(od.order_id, 0) 
from products p 
left join order_details od on p.id = od.product_id 
order by p.id, od.order_id;

-- Mostra el nom i cognom dels empleats que han servit alguna comanda. S'ha de mostrar sense repeticions.
select distinct e.first_name, e.last_name 
from employees e 
join orders o on o.employee_id = e.id;

-- Mostra el nom i cognom dels empleats que NO han servit cap comanda.
select e.first_name, e.last_name 
from employees e
EXCEPT 
select distinct e.first_name, e.last_name 
from employees e
join orders o on o.employee_id = e.id;

-- Mostra totes les dades de les comandes de les quals se n'ha generat la factura.
select o.*
from orders o 
join invoices i on o.id = i.order_id;

-- Mostra l'identificador de la comanda i l'identificador de la factura de totes les comandes. Per les comandes sense factura, mostrarem NULL com identificador de la factura. Ordena el resultat per l'id de comanda.
select o.id, IFNULL(i.id, NULL) 
from orders o
left join invoices i on o.id = i.order_id
order by o.id;

-- Mostra l'identificador de la comanda, l'identificador de la factura, i el nom i cognom del client, de totes les comandes. Per les comandes sense factura, l'id serà NULL. Ordena el resultat per l'id de comanda.
select o.id, IFNULL(i.id, NULL), c.first_name, c.last_name 
from orders o
left join invoices i on o.id = i.order_id
join customers c on o.customer_id = c.id
order by o.id;

#-----------------------------------------------------------------------------------------
-- Mostra el nom i cognom dels clients que han fet alguna comanda.
select distinct c.first_name, c.last_name 
from customers c 
join orders o on o.customer_id = c.id 

-- Mostra el nom, cognoms, i la quantitat de comandes que han fet els 6 clients que han fet més comandes. Ordena els resultats pel nombre de comandes, i entre els clients que tenen el mateix nombre de comandes, pel seu cognom.
select c.first_name, c.last_name, count(o.id)
from customers c 
join orders o on o.customer_id = c.id 
group by c.first_name, c.last_name 
order by 3 desc, c.last_name 
limit 6

-- Mostra el nom, cognoms, i la quantitat de comandes que han fet tots els clients. Els que no han fet encara cap comanda també han de sortir.
select c.first_name, c.last_name, count(o.id)
from customers c 
left join orders o on o.customer_id = c.id
group by c.first_name, c.last_name 

-- Mostra l'id, nom i cognom dels clients, i la quantitat de productes, dels clients que han comprat més de 5 vegades qualsevol producte. Ordenar pel nom dels clients.
select c.id, c.first_name, c.last_name, count(od.product_id)
from customers c 
join orders o on o.customer_id = c.id 
join order_details od on od.order_id = o.id 
group by c.id, c.first_name, c.last_name 
having count(od.id) > 5
order by c.first_name 

-- Mostra el nom del producte i la quantitat de vegades que s'han demanat en alguna comanda. Ordena el resultat per la quantitat de manera que es vegin primer els productes que més s'han demanat. En cas d'empat, ordenar per nom de producte
select p.product_name, count(od.product_id)
from order_details od 
join products p on p.id = od.product_id 
group by p.product_name 
order by 2 desc, p.product_name 

-- Mostra l'id, nom i cognoms de tots els empleats i l'import total de les vendes (orders_details) per cada empleat.
select e.id, e.first_name, e.last_name, sum(od.unit_price*od.quantity)
from employees e 
left join orders o on o.employee_id = e.id 
left join order_details od on od.order_id = o.id 
group by e.id, e.first_name, e.last_name 

-- Mostra els noms dels productes dels que estiguin ja facturats en alguna de les seves comandes. (Invoiced)
select distinct p.product_name 
from products p 
join order_details od on od.product_id = p.id 
join order_details_status ods on od.status_id = ods.id 
where ods.status_name like 'Invoiced'

#------------------------------------------------------------------------------------------------------
-- Mostra id, nom, preu (list_price) arrodonit a l'alza, el preu de cost arrodonit a la baixa (standard_cost) dels productes de la categoria 'Soups'
select p.id, p.product_name, ceil(p.list_price), floor(p.standard_cost)
from products p
where p.category like 'Soups';

-- Mostra id, nom, preu de venda, el preu de cost, i la diferència de dels dos preus amb dos decimals com a màxim, dels productes amb més de 2 euros de diferència entre el preu de venda i preu de cost. Ordena els resultats per aquesta diferència descendentment i en cas d'empat per codi de producte
select p.id, p.product_name, p.list_price, p.standard_cost, round((list_price-standard_cost), 2)
from products p 
where (list_price - standard_cost) > 2
order by 5 desc, p.product_code 

-- Mostra en una única columna el cognom i nom dels empleats, separat per una coma i un únic espai, ordenats per cognom (exemple: Polet, Seila)
select concat(last_name, ', ', first_name)
from employees 
order by last_name

-- Mostra les dues primeres lletres del nom i les dues primeres lletres del cognom en una única columna, dels clients, ordenats alfabèticament
select concat(left(first_name, 2), left(last_name, 2))
from customers
order by left(first_name, 2) asc, left(last_name, 2) asc

-- Mostra en majúscules tots els cognoms i en minúscules tots els noms dels clients. Mostra també el seu email i si aquest email acaba en .edu substitueix-lo per .cat Ordena el resultat per cognom i nom
select UPPER(last_name), LOWER(first_name), if(RIGHT(email_address, 3) like 'edu', concat(trim(trailing 'edu' from customers.email_address), 'cat'), customers.email_address)
from customers
order by last_name, first_name 

-- Mostra, el nom i cognom dels clients que tenen un nom amb una longitud superior a 5 o la combinació de nom i cognom tingui una longitud superior a 11, i, a més, el seu cognom tingui longitud superior a 5 Ordena per nom i cognom
select first_name, last_name 
from customers
where (CHAR_LENGTH(first_name) > 5 or CHAR_LENGTH(concat(first_name, last_name)) > 11) and CHAR_LENGTH(last_name) > 5
order by first_name, last_name 

-- A la taula productes, de les categories que tenen més d'una paraula, retorna sense repeticions la primera paraula. Ordena el resultat per categoria
select distinct trim(trailing SUBSTRING(category, locate(' ', category)) from products.category)
from products
where locate(' ', category) != 0
order by category 

-- Mostra el nom dels productes sense el prefix comú 'Norhwind Traders ' (observa que tots els productes ho porten). Fes "terrorisme", i observa que aquest prefix correspon a les 19 primeres posicions del nom Ordena el resultat alfabèticament
select trim(LEADING 'Northwind Traders ' from products.product_name)
from products
order by product_name 

-- Mostra l'identificador, la data i la data d'enviament de les comandes. Si una comanda no ha estat enviada encara, sortirà "PENDENT" com a data d'enviament. Cas contrari sortirà la data d'enviament. Ordena per identificador de la comanda
select id, order_date, ifnull(shipped_date, 'PENDENT')
from orders
order by id

-- Mostra l'identificador, el codi, el preu de cost, el preu de venda dels productes i una nova columna que dirà 'MARGE ALT' si la diferència entre preu de venda i preu de cost supera els 3 euros i 'MARGE NORMAL' cas contrari
select id, product_code, standard_cost, list_price, if((list_price - standard_cost) > 3, 'MARGE ALT', 'MARGE NORMAL')
from products

-- Mostra l'identificador, el codi, el preu de cost, el preu de venda dels productes i una nova columna que dirà 'MARGE MOLT ALT' si la diferència entre preu de venda i preu de cost supera 5 euros 'MARGE ALT' si la diferència està entre 5 i 3 euros (sense incloure) 'MARGE NORMAL' si està entre 3 i 2 (sense incloure) 'MARGE BAIX' cas contrari Ordena per identificador
select id, product_code, standard_cost, list_price, if((list_price - standard_cost) > 3, if((list_price - standard_cost) > 5, 'MARGE MOLT ALT', 'MARGE ALT'), if((list_price - standard_cost) <= 2, 'MARGE BAIX', 'MARGE NORMAL'))
from products
