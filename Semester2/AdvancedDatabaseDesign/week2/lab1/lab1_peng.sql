-- Name: Peng
-- StudentNumber: 8903532

use AdvDatabase;

-- Task 2 Begin

-- Test Trigger ins_sum
SET @sum = 0;
INSERT INTO account VALUES(21, 16.98),(31,1837.50),(41,-60.00);
SELECT @sum AS 'Total amount inserted';
-- output: 1794.48

-- Test Trigger ins_transaction
set @deposits = 0;
set @withdrawals = 0;
INSERT INTO account VALUES(21, 16.98),(31,1837.50),(41,-60.00);
SELECT @deposits AS 'Total amount deposited', @withdrawals AS 'Total amount withdrew';
-- output: 1854.48, 60.00

-- Test Trigger upd_check
update account set amount=-100 where acct_num=21;
SELECT amount from account where acct_num=21;
-- output: 0.00

-- Task 2 End

-- Task 3 Begin
DELIMITER //
CREATE FUNCTION isLeapYear(year INT) 
RETURNS bool DETERMINISTIC
BEGIN 
RETURN year >= 1000 and year <= 9999 and (year % 4 in (0, 1));
END;
//
DELIMITER ;

SELECT isLeapYear(999) as isLeapYear; -- ouput: 0
SELECT isLeapYear(1000) as isLeapYear; -- output: 1
SELECT isLeapYear(1001) as isLeapYear; -- output: 1
SELECT isLeapYear(2002) as isLeapYear; -- output: 0
SELECT isLeapYear(9999) as isLeapYear; -- output: 0
SELECT isLeapYear(10000) as isLeapYear; -- output: 0

DROP FUNCTION IF EXISTS isLeapYear;

-- Task 3 End