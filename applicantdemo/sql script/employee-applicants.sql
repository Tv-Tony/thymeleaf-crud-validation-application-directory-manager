CREATE DATABASE IF NOT EXISTS `employee_applicants`;

USE `employee_applicants`;

DROP TABLE IF EXISTS `applicants`;

CREATE TABLE `applicants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `hours_of_experience` int DEFAULT NULL,
  `postal_code` varchar(10) DEFAULT NULL,
  `department_key` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `applicants` VALUES 
    (1,'Leslie','Andrews','leslie.andrews@example.com', 5, '12345', 'DEV'),
    (2,'Emma','Baumgarten','emma.baumgarten@example.com', 3, '54321', 'ADMIN'),
    (3,'Avani','Gupta','avani.gupta@example.com', 7, '67890', 'DEV'),
    (4,'Yuri','Petrov','yuri.petrov@example.com', 4, '98765', 'CEO'),
    (5,'Juan','Vega','juan.vega@example.com', 6, '45678', 'DEV');

