CREATE TABLE IF NOT EXISTS Price (
  price_list INT  AUTO_INCREMENT PRIMARY KEY,  
  brand_id VARCHAR(5) NOT NULL,
  start_date TIMESTAMP WITH TIME ZONE,
  end_date TIMESTAMP WITH TIME ZONE,
  product_id VARCHAR(255),
  priority SMALLINT,
  price DOUBLE,
  curr VARCHAR(3)
);

INSERT INTO Price (brand_id, start_date, end_date, product_id, priority, price, curr) VALUES
  ('1', '2020-06-14T00:00:00.000Z', '2020-12-31T22:59:59.000Z', '35455', 0, 35.50, 'EUR'),
  ('1', '2020-06-14T15:00:00.000Z', '2020-06-14T18:30:00.000Z', '35455', 1, 25.45, 'EUR'),
  ('1', '2020-06-15T00:00:00.000Z', '2020-06-15T11:00:00.000Z', '35455', 1, 30.50, 'EUR'),
  ('1', '2020-06-15T16:00:00.000Z', '2020-12-31T23:59:59.000Z', '35455', 1, 38.95, 'EUR');
