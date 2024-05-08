CREATE TABLE IF NOT EXISTS product_template(
    product_template_id UUID PRIMARY KEY,
    product_name varchar(200) NOT NULL,
    product_price double precision NOT NULL,
    evaporation_rate float NOT NULL,
    movement_id UUID REFERENCES movement(movement_id)
);