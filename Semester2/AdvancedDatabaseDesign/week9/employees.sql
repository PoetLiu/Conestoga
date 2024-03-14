-- create dataabse pdftest
DROP DATABASE IF EXISTS pdftest;

CREATE DATABASE pdftest;

USE pdftest;
--
-- Table structure for table `employees`
--
drop table if exists employees;

CREATE TABLE IF NOT EXISTS `employees` (
  `user_id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) NOT NULL,
  `email` varchar(1000) NOT NULL,
  `department` varchar(1000) NOT NULL,
  `role` varchar(100) NOT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`user_id`, `name`, `email`, `department`, `role`, `created_on`) VALUES
(5, 'alex', 'alex@gmail.com', 'Admin', 'admin', '2017-06-29 03:42:04'),
(6, 'john', 'john@gmail.com', 'Web Development', 'employee', '2017-06-29 03:42:20'),
(7, 'sunny', 'sunny@gmail.com','Web Development', 'employee', '2017-07-05 18:27:24'),
(8, 'lina', 'lina@gmail.com', 'Professor', 'employee', '2017-07-05 18:27:44'),
(9, 'sam', 'sam@gmail.com', 'Professor', 'employee', '2017-07-05 18:28:03');


