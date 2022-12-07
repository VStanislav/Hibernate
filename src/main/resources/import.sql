DROP TABLE products IF EXISTS ;
CREATE TABLE IF NOT EXISTS products (id bigserial, name VARCHAR(255), price bigint , PRIMARY KEY (id));
INSERT INTO products (name,price) VALUES ('Apple',99),('Sony',100),('JBL',101);

DROP TABLE customers IF EXISTS ;
CREATE TABLE IF NOT EXISTS customers (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO customers (name) VALUES ('Alex'),('Vlad'),('Ignat');

DROP TABLE orders IF EXISTS ;
CREATE TABLE IF NOT EXISTS orders (id bigserial PRIMARY KEY, date VARCHAR(255), product_id bigint REFERENCES products (id), customer_id bigint  REFERENCES  customers (id));
INSERT INTO orders (product_id , customer_id ) VALUES (1,1),(2,2),(3,3),(1,3),(2,1),(3,1),(3,2);
