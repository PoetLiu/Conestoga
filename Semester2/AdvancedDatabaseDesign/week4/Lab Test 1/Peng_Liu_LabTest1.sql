-- Name: Peng
-- StudentNumber: 8903532

create database liu32;

use liu32;

-- Part-1
DELIMITER //
CREATE PROCEDURE daysinCanada_liu(firstDay char(255))
BEGIN
	DECLARE weeks integer;
    -- convert to days to weeks, if days are less than 7, the weeks would be 0.
    SET weeks = DATEDIFF(CURDATE(), firstDay) / 7;
    SELECT weeks;
END//
DELIMITER ;

CALL daysinCanada_liu("2023-08-02");
DROP PROCEDURE IF EXISTS daysinCanada_liu;

-- Part-2
DELIMITER //
CREATE EVENT `Every_Month_Cleanup`
ON SCHEDULE EVERY 1 MONTH STARTS '2024-02-02 18:00:00'
ON COMPLETION PRESERVE
DO BEGIN

DELETE FROM punch_data
WHERE punch_time > "15:00:00";

END//
DELIMITER ;

DROP EVENT IF EXISTS `Every_Month_Cleanup`;