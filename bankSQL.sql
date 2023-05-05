CREATE DATABASE  IF NOT EXISTS `bank`;
USE `bank`;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `customer_id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `first_name`varchar(15) DEFAULT NULL,
  `last_name` varchar(15) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `cash` int,
  `login_password` int,
  `account_number` VARCHAR(30);
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;

CREATE TABLE `transactions` (
  `customer_id` int NOT NULL,
  `transaction_id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `transaction_amount` DOUBLE,
  `transaction_date` DATETIME,
  `transaction_kind` VARCHAR(45);

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
