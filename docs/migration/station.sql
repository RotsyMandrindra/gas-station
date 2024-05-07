CREATE TABLE IF NOT EXISTS station(
    station_id UUID PRIMARY KEY,
    station_name varchar(200) NOT NULL,
    localisation varchar(200) NOT NULL,
    contact varchar(50) NOT NULL,
    employee_number int NOT NULL,
    total_amount_station double precision NOT NULL,
    date TIMESTAMPTZ NOT NULL,
    product_id UUID REFERENCES product(product_id)
);