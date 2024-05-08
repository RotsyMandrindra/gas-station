CREATE TABLE IF NOT EXISTS movement(
    movement_id UUID PRIMARY KEY,
    type TEXT CHECK(type IN ('entry', 'outlet')) NOT NULL,
    sell_quantity double precision,
    remaining_quantity double precision,
    supply_quantity double precision,
    date TIMESTAMP NOT NULL
);