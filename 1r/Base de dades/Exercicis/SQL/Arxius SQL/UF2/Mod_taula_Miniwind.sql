USE miniwind;

CREATE TABLE ProductsSuppliers (
	ProductId	INT NOT NULL,
	SupplierId	INT NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

INSERT INTO ProductsSuppliers
select p.id, p.supplier_ids
from products p
where p.supplier_ids not like '%;%';

INSERT INTO ProductsSuppliers 
select p.id, SUBSTRING_INDEX(p.supplier_ids, ';', 1) 
from products p 
where p.supplier_ids like '%;%';

INSERT INTO ProductsSuppliers 
select p.id, SUBSTRING_INDEX(p.supplier_ids, ';', -1) 
from products p 
where p.supplier_ids like '%;%';

ALTER TABLE products DROP COLUMN supplier_ids; 

ALTER TABLE ProductsSuppliers ADD CONSTRAINT PRIMARY KEY (ProductId, SupplierId);

ALTER TABLE ProductsSuppliers ADD CONSTRAINT FOREIGN KEY (ProductId) REFERENCES products (id);

ALTER TABLE ProductsSuppliers ADD CONSTRAINT FOREIGN KEY (SupplierId) REFERENCES suppliers (id);

-- 

ALTER TABLE products ADD COLUMN IVA INT DEFAULT 10;

UPDATE products p
	set IVA = 4 
	where p.category like 'Cereal' or p.category like 'Grains';
	
UPDATE products p
	set IVA = 21 
	where p.product_name like '%Beer%';

ALTER TABLE products ADD COLUMN price_before_IVA Decimal(19,4) DEFAULT (list_price/(1+IVA/100));

ALTER TABLE order_details ADD COLUMN IVA INT;

UPDATE order_details od
	inner join products p on od.product_id = p.id
	set od.IVA = p.IVA
	where od.product_id = p.id;

ALTER TABLE order_details ADD COLUMN unit_price_before_IVA Decimal(19,4) DEFAULT (unit_price/(1+IVA/100));