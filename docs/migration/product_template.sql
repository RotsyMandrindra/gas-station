CREATE TABLE IF NOT EXISTS product_template(
    product_template_id UUID PRIMARY KEY,
    product_name varchar(200) NOT NULL,
    product_price double precision NOT NULL,
    quantity_sold double precision NOT NULL,
    remaining_quantity double precision NOT NULL,
    product_amount double precision NOT NULL,
    evaporation_rate float NOT NULL,
    supplied_quantity double precision NOT NULL
);