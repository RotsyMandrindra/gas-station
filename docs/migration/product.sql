CREATE TABLE IF NOT EXISTS product(
    product_id UUID PRIMARY KEY,
    product_template_id UUID REFERENCES product_template(product_template_id),
    station_id UUID REFERENCES station(station_id)
);
