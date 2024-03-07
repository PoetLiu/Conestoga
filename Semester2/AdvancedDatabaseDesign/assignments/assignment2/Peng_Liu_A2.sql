-- Name: Peng
-- StudentNumber: 8903532

create database liu32;
use liu32;

-- Part-1
-- it withdraws money(amount) from account(acc_number) if there is enough money in it.
DELIMITER //
CREATE PROCEDURE balance_peng(IN acc_number CHAR(5), IN amount DOUBLE)
BEGIN
	DECLARE cur_balance DOUBLE;
	SELECT balance INTO cur_balance FROM account WHERE account_number = acc_number;
    -- check whether the acc_number exists, if not we couldn't start transaction.
	IF (cur_balance IS NULL) THEN
		SELECT "account number doesn't exist";
	ELSE
		START TRANSACTION;
        if (cur_balance >= amount) THEN
			UPDATE account SET balance = balance - amount WHERE account_number = acc_number;
            COMMIT;
            SELECT "transaction is successful.";
        ELSE
			ROLLBACK;
            SELECT "transaction is unsuccessful.";
        END IF;
	END IF;
END//
DELIMITER ;

-- output: account number dosen't exit.
call balance_peng("B-101", 100);
-- output: transaction is successful.
call balance_peng("A-101", 100);
-- output: transaction is unsuccessful.
call balance_peng("A-102", 1000);

DROP PROCEDURE IF EXISTS balance_peng;

-- Part-2
CREATE VIEW uv_peng AS SELECT Country FROM Customers;
-- output: 5 row(s) affected
UPDATE uv_peng SET Country = "CA" WHERE Country = "Canada";

-- outupt: Error Code: 1423. Field of view 'liu32.uv_peng' underlying table doesn't have a default value
-- Because the CustomerID field in underlying table Customers doesnt' have a default value.
-- And this view dosent' have this field.
INSERT INTO uv_peng(Country) values("China");
DROP VIEW IF EXISTS uv_peng;

CREATE VIEW nuv_peng AS SELECT DISTINCT Country FROM Customers;

-- Error Code: 1288. The target table nuv_peng of the UPDATE is not updatable
UPDATE nuv_peng SET Country = "CA" WHERE Country = "Canada";

-- Error Code: 1471. The target table nuv_peng of the INSERT is not insertable-into
INSERT INTO nuv_peng(Country) values("China");

DROP VIEW IF EXISTS nuv_peng;
