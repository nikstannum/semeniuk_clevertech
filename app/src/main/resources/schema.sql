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