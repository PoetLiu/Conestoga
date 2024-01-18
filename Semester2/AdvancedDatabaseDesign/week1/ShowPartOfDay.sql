DELIMITER //
CREATE PROCEDURE show_part_of_day()
BEGIN
DECLARE cur_time, day_part TEXT;
SET cur_time = CURTIME();
IF cur_time < '12:00:00' THEN
SET day_part = 'morning';
ELSEIF cur_time = '12:00:00' THEN
SET day_part = 'noon';
ELSE
SET day_part = 'afternoon or night';
END IF;
SELECT cur_time, day_part;
END//
DELIMITER ; # for output, call show_part_of_day()