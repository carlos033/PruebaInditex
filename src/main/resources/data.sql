CREATE TABLE Price (
  id INT AUTO_INCREMENT PRIMARY KEY,
  brand_id VARCHAR(5) NOT NULL,
  start_date DATETIME(6),
  end_date DATETIME(6),
  price_list INT,
  product_id VARCHAR(255),
  priority SMALLINT,
  price DOUBLE,
  curr VARCHAR(3)
);

INSERT INTO Price (brand_id, start_date, end_date, price_list, product_id, priority, price, curr) VALUES
  ('1', '2020-06-14 00:00:00.000000', '2020-12-31 22:59:59.000000', 1, '35455', 0, 35.50, 'EUR'),
  ('1', '2020-06-14 15:00:00.000000', '2020-06-14 18:30:00.000000', 2, '35455', 1, 25.45, 'EUR'),
  ('1', '2020-06-15 00:00:00.000000', '2020-06-15 11:00:00.000000', 3, '35455', 1, 30.50, 'EUR'),
  ('1', '2020-06-15 16:00:00.000000', '2020-12-31 23:59:59.000000', 4, '35455', 1, 38.95, 'EUR');