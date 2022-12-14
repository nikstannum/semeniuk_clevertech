/*
CREATE DATABASE clevertech;
DROP DATABASE clevertech;
*/
/*
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS discount_card CASCADE;
*/
/*
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS discount_card;
*/
CREATE TABLE IF NOT EXISTS products (
	product_id BIGSERIAL PRIMARY KEY,
	"name" varchar (30) NOT NULL,
	price NUMERIC (6, 2),
	discount BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS discount_card (
	card_id BIGSERIAL PRIMARY KEY,
	discount_size NUMERIC (3, 1)
);

/*
TRUNCATE TABLE product CASCADE;
TRUNCATE TABLE discount_card CASCADE;
*/


INSERT INTO products ("name", price, discount)
VALUES ('milk', 0.99, false),
		('yogurt', 0.87, false),
		('bread', 0.7, false),
		('cookie', 1, true),
		('butter', 1.05, false),
		('deodorant', 2.18, true),
		('soap', 0.89, true),
		('shower gel', 2.94, true),
		('toothbrush', 1.03, true),
		('toothpaste', 1.79, true),
		('candies', 3.18, true);
		
INSERT INTO discount_card(discount_size)
VALUES (1.5),
		(5),
		(3);
		
	
	SELECT * FROM discount_card;
	SELECT * FROM products;