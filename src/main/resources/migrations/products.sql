DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS characteristic;

CREATE TABLE category (
    category_id SERIAL,
    name varchar(50) not null,
    max_price integer,
    min_price integer,

    PRIMARY KEY (category_id)
);

CREATE TABLE characteristic (
    characteristic_id SERIAL,
    width integer not null,
    height integer not null,
    length integer not null,
	weight integer not null,

    PRIMARY KEY (characteristic_id)
);

CREATE TABLE product (
    product_id SERIAL,
    category_id integer not null,
    characteristic_id integer not null,
    name varchar(100) not null,
    price integer not null,

    PRIMARY KEY (product_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE RESTRICT,
    FOREIGN KEY (characteristic_id) REFERENCES characteristic(characteristic_id) ON DELETE CASCADE
);