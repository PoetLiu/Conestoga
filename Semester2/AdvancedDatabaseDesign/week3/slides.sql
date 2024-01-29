use AdvDatabase;

DROP TABLE IF EXISTS bankaccounts;
CREATE TABLE bankaccounts(accountno varchar(20) PRIMARY
KEY NOT NULL, funds decimal(8,2));

INSERT INTO bankaccounts VALUES("ACC1", 1000);
INSERT INTO bankaccounts VALUES("ACC2", 1000);

START TRANSACTION; -- statement1
UPDATE bankaccounts SET funds=funds-100 WHERE
accountno='ACC1'; -- statement2
UPDATE bankaccounts SET funds=funds+100 WHERE
accountno='ACC2'; -- statement3
COMMIT; -- statement4

START TRANSACTION; -- statement1
UPDATE bankaccounts SET funds=funds-100 WHERE
accountno='ACC1'; -- statement2
UPDATE bankaccounts SET funds=funds+100 WHERE
accountno='ACC2'; -- statement3

ROLLBACK; -- statement4

SELECT * FROM AdvDatabase.bankaccounts;
SHOW VARIABLES LIKE 'AUTOCOMMIT';