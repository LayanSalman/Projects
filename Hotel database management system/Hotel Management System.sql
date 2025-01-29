-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.35 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for hotel management system
CREATE DATABASE IF NOT EXISTS `hotel management system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel management system`;

-- Dumping structure for table hotel management system.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `Eid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0',
  `Fname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Lname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Jop_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `House_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Street` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Postal_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Sup_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`Eid`),
  KEY `FK_employee_employee` (`Sup_id`),
  CONSTRAINT `FK_employee_employee` FOREIGN KEY (`Sup_id`) REFERENCES `employee` (`Eid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel management system.employee: ~4 rows (approximately)
INSERT INTO `employee` (`Eid`, `Fname`, `Lname`, `Jop_title`, `Phone`, `House_no`, `Street`, `Postal_code`, `Sup_id`) VALUES
	('1123569786', 'Fahad', 'Naser', 'Reception', '0546378599', '32', 'Saad st', '12345', '1165387854'),
	('1135738958', 'Amal', 'Mohmoud', 'Reception', '0567438843', '12', 'Bader st', '56322', '1165387854'),
	('1165387854', 'Maha', 'Abdullah', 'Manager', '056453892', '20', 'Ahmad st', '65740', NULL),
	('1173674556', 'Waleed', 'Khalid', 'Chef', '0533348695', '24', 'Sultan st', '23768', '1165387854');

-- Dumping structure for table hotel management system.guest
CREATE TABLE IF NOT EXISTS `guest` (
  `Id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Fname` varchar(20) DEFAULT NULL,
  `Lname` varchar(20) DEFAULT NULL,
  `Phone` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Date_of_birth` date DEFAULT NULL,
  `Sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Country` varchar(20) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `Postal_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel management system.guest: ~3 rows (approximately)
INSERT INTO `guest` (`Id`, `Fname`, `Lname`, `Phone`, `Email`, `Date_of_birth`, `Sex`, `Country`, `City`, `Postal_code`) VALUES
	('123456456', 'Mohammad', 'Khalid', '053009988', 'Mohammad@hotmail.com', '1973-01-21', 'M', 'Saudi Arabia', 'Riyadh', '11461'),
	('123456789', 'Hind', 'Ibrahim', '051234567', 'Hind@gmail.com', '1992-12-12', 'F', 'Saudi Arabia', 'Riyadh', '11461'),
	('124578900', 'Lama', 'Ahmad', '050512345', 'Lama@gmail.com', '1988-04-23', 'F', 'Saudi Arabia', 'Jeddah', '22230');

-- Dumping structure for table hotel management system.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `Invo_num` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Total` int DEFAULT '0',
  `Tax` int DEFAULT '0',
  `Type` varchar(100) DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `Invo_date` date DEFAULT NULL,
  `Res_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Gid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`Invo_num`),
  KEY `FK_invoice_guest` (`Gid`),
  CONSTRAINT `FK_invoice_guest` FOREIGN KEY (`Gid`) REFERENCES `guest` (`Id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel management system.invoice: ~3 rows (approximately)
INSERT INTO `invoice` (`Invo_num`, `Total`, `Tax`, `Type`, `Status`, `Invo_date`, `Res_no`, `Gid`) VALUES
	('4041', 8000, 1200, 'visa', 'paid', '2023-12-10', '417448586', '123456789'),
	('4312', 7500, 1124, 'visa', 'paid', '2023-11-25', '449847306', '123456456'),
	('5870', 9800, 1470, 'cash', 'unpaid', '2023-11-20', '437492555', '124578900');

-- Dumping structure for table hotel management system.reservation
CREATE TABLE IF NOT EXISTS `reservation` (
  `Reservation_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `No_of_adult` varchar(3) DEFAULT NULL,
  `No_of_children` varchar(3) DEFAULT NULL,
  `No_of_rooms` varchar(3) DEFAULT NULL,
  `Eid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Gid` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`Reservation_no`),
  KEY `FK_reservation_employee` (`Eid`),
  KEY `FK_reservation_guest` (`Gid`),
  CONSTRAINT `FK_reservation_employee` FOREIGN KEY (`Eid`) REFERENCES `employee` (`Eid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_reservation_guest` FOREIGN KEY (`Gid`) REFERENCES `guest` (`Id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel management system.reservation: ~3 rows (approximately)
INSERT INTO `reservation` (`Reservation_no`, `No_of_adult`, `No_of_children`, `No_of_rooms`, `Eid`, `Gid`) VALUES
	('0123456789', '2', '1', '1', '1123569786', '123456789'),
	('1123456789', '3', '1', '2', '1123569786', '124578900'),
	('2123456789', '2', '0', '1', '1135738958', '123456456');

-- Dumping structure for table hotel management system.room
CREATE TABLE IF NOT EXISTS `room` (
  `Room_no` varchar(50) NOT NULL DEFAULT '0',
  `Type` varchar(50) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `Check_in` datetime DEFAULT NULL,
  `Check_out` datetime DEFAULT NULL,
  `Res_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`Room_no`),
  KEY `FK_room_reservation` (`Res_no`),
  CONSTRAINT `FK_room_reservation` FOREIGN KEY (`Res_no`) REFERENCES `reservation` (`Reservation_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel management system.room: ~6 rows (approximately)
INSERT INTO `room` (`Room_no`, `Type`, `Status`, `Check_in`, `Check_out`, `Res_no`) VALUES
	('101', 'Suite', 'Not Available', '2023-11-20 12:00:00', '2023-11-26 15:00:00', '1123456789'),
	('202', 'Single', 'Not Available', '2023-11-20 12:00:00', '2023-11-26 15:00:00', '1123456789'),
	('303', 'Single', 'Available', NULL, NULL, NULL),
	('404', 'Double', 'Not Available', '2023-11-25 14:00:00', '2023-12-02 12:00:00', '2123456789'),
	('505', 'Suite', 'Not Available', '2023-12-10 15:00:00', '2023-12-19 14:00:00', '0123456789'),
	('606', 'Double', 'Available', NULL, NULL, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
