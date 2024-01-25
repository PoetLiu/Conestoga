-- Name: Peng
-- StudentNumber: 8903532

create database liu32;
use liu32;

-- Part-1
DELIMITER //
CREATE PROCEDURE  tax_increase_peng()
BEGIN
DECLARE increase_ratio DECIMAL(3,2);
-- The last digit of my student number is 2, so increase ratio is 2%.
SET increase_ratio = 0.02;

-- Increse tax_rate by increase_ratio.
UPDATE sales_tax_rate SET tax_rate = tax_rate + increase_ratio
WHERE state LIKE 'A%';

END//
DELIMITER ;

call tax_increase_peng();
-- check the results if tax_rates were increased correctly.
SELECT * FROM sales_tax_rate WHERE state LIKE 'A%';

DROP PROCEDURE IF EXISTS tax_increase_peng;

-- Part-2
DELIMITER //
CREATE TRIGGER capt_peng BEFORE INSERT ON geo_provinces_ca
       FOR EACH ROW
       BEGIN
			-- convert the iso string to uppercase.
			SET NEW.iso = UPPER(NEW.iso);
       END;//
DELIMITER ;

-- Test cases for trigger capt_peng.
INSERT INTO geo_provinces_ca (id,printable_name,iso)
VALUES
(4,'New Brunswick','nb'),
(5,'Manitoba','mb'),
(6,'British Columbia','bc'),
(7,'Prince Edward Island','pe');

DROP TRIGGER IF EXISTS capt_peng;