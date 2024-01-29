use AdvDatabase;
DROP TABLE IF EXISTS products;
CREATE TABLE products (
  `productID`    int(10) unsigned  NOT NULL AUTO_INCREMENT,
  `productCode`  char(3)           NOT NULL DEFAULT '',
  `name`         varchar(30)       NOT NULL DEFAULT '',
  `quantity`     int(10) unsigned  NOT NULL DEFAULT '0',
  `price`        decimal(7,2)      NOT NULL DEFAULT '99999.99',
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- Insert a row with all the column values
INSERT INTO products VALUES (1001, 'PEN', 'Pen Red', 5000, 1.23);

-- Insert multiple rows in one command
-- Inserting NULL to the auto_increment column results in max_value + 1
INSERT INTO products VALUES
         (NULL, 'PEN', 'Pen Blue',  8000, 1.25),
         (NULL, 'PEN', 'Pen Black', 2000, 1.25);
         
-- Insert value to selected columns
-- Missing value for the auto_increment column also results in max_value + 1
INSERT INTO products (productCode, name, quantity, price) VALUES
         ('PEC', 'Pencil 2B', 10000, 0.48),
         ('PEC', 'Pencil 2H', 8000, 0.49);

-- Command-1
-- SET autocommit = 0;
-- DELETE from products;

-- Command-2
-- SET autocommit = 1;
-- DELETE from products;

-- Command-3
-- DELETE from products;

