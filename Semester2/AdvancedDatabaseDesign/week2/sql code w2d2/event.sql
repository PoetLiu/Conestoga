use AdvDatabase;

SET GLOBAL event_scheduler = ON;

show VARIABLES LIKE "event_scheduler";

create table theMessages
( id INT AUTO_INCREMENT PRIMARY KEY,
userId INT NOT NULL,
message VARCHAR(255) NOT NULL,
updateDt DATETIME NOT NULL,
KEY(updateDt)
);
INSERT theMessages(userId,message,updateDt) VALUES (1,'message 123','2015-08-24 11:10:09');
INSERT theMessages(userId,message,updateDt) VALUES (7,'message 124','2015-08-29');
INSERT theMessages(userId,message,updateDt) VALUES (1,'message 125','2015-09-03 12:00:00');
INSERT theMessages(userId,message,updateDt) VALUES (1,'message 126','2015-09-03 14:00:00');

DROP EVENT IF EXISTS `Every_1_Minutes_Cleanup`;
DELIMITER $$
CREATE EVENT `Every_1_Minutes_Cleanup`
ON SCHEDULE EVERY 1 MINUTE STARTS '2024-01-01 00:00:00'
ON COMPLETION PRESERVE
DO BEGIN
DELETE FROM theMessages
WHERE TIMESTAMPDIFF(HOUR, updateDt, now())>168; -- messages over 1 week old (168 hours)
-- Other code here
END$$
DELIMITER ; -- check the theMessage table after 1 minutes

SHOW EVENTS FROM AdvDatabase;
SHOW EVENTS;