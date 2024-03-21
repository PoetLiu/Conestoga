-- Name: Peng Liu
-- StudentNumber: 8903532

CREATE DATABASE liu32;
USE liu32;

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_title` varchar(45) NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `term`;
CREATE TABLE IF NOT EXISTS `term` (
  `term_name` varchar(6) NOT NULL,
  PRIMARY KEY (`term_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `enrollment`;
CREATE TABLE IF NOT EXISTS `enrollment` (
  `student_id` int NOT NULL,
  `course_id` int NOT NULL,
  `grade` varchar(2),
  `term_name` varchar(6) NOT NULL,
  CONSTRAINT `fk_enrollment_student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `student` (`student_id`),
  CONSTRAINT `fk_enrollment_course_id`
    FOREIGN KEY (`course_id`)
    REFERENCES `course` (`course_id`),
  CONSTRAINT `fk_enrollment_term_name`
    FOREIGN KEY (`term_name`)
    REFERENCES `term` (`term_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

INSERT INTO `student` (`student_id`, `first_name`, `last_name`, `email`) VALUES
(8907777, 'Lionel', 'Messi', 'LMessi123@conestogac.on.ca'),
(8903532, 'Peng', 'Liu', 'Pliu3532@conestogac.on.ca'),
(8901010, 'Cristiano', 'Ronaldo', 'CRonaldo999@conestogac.on.ca');

INSERT INTO `course` (`course_title`) VALUES
('Algorithms and Complexity'),
('Computer Graphics'),
('Machine Learning'),
('Software Engineering');

INSERT INTO `term` (`term_name`) VALUES
('S2023'),
('F2023'),
('W2024');

INSERT INTO `enrollment` (`student_id`, `course_id`, `grade`, `term_name`) VALUES
('8907777', '1', 'A', 'S2023'),
('8907777', '2', 'B+', 'F2023'),
('8907777', '3', 'B', 'F2023'),
('8907777', '4', NULL, 'W2024'),
('8903532', '1', 'B', 'S2023'),
('8903532', '2', 'A', 'F2023'),
('8903532', '3', 'B+', 'F2023'),
('8903532', '4', 'A+', 'F2023'),
('8901010', '3', 'B', 'S2023'),
('8901010', '2', 'A', 'F2023'),
('8901010', '1', NULL, 'W2024'),
('8901010', '4', 'C', 'F2023');