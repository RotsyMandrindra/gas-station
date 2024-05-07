CREATE TABLE IF NOT EXISTS product(
    product_id UUID PRIMARY KEY,
    supplied_date TIMESTAMPTZ NOT NULL,
    product_template_id UUID REFERENCES product_template(product_template_id)
);
