CREATE TABLE IF NOT EXISTS station(
    station_id UUID PRIMARY KEY,
    station_name varchar(200) NOT NULL,
    location varchar(200) NOT NULL,
    contact varchar(50) NOT NULL,
    total_amount_station double precision NOT NULL
);