/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : patient_monitoring_warning

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 03/11/2024 19:25:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `hospital_id` int NOT NULL AUTO_INCREMENT COMMENT 'Hospital ID (PK)',
  `hospital_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Hospital Name',
  `hospital_level` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Hospital Level (High/Medium/Low)',
  `hospital_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Hospital Address',
  `hospital_contact` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Hospital Contact',
  PRIMARY KEY (`hospital_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of hospital
-- ----------------------------
BEGIN;
INSERT INTO `hospital` (`hospital_id`, `hospital_name`, `hospital_level`, `hospital_address`, `hospital_contact`) VALUES (1, 'H1', 'High', 'H1 Street', '0-00000000');
INSERT INTO `hospital` (`hospital_id`, `hospital_name`, `hospital_level`, `hospital_address`, `hospital_contact`) VALUES (2, 'H2', 'Low', 'H2 Street', '1-11111111');
INSERT INTO `hospital` (`hospital_id`, `hospital_name`, `hospital_level`, `hospital_address`, `hospital_contact`) VALUES (3, 'H3', 'Medium', 'H3 Street', '2-22222222');
INSERT INTO `hospital` (`hospital_id`, `hospital_name`, `hospital_level`, `hospital_address`, `hospital_contact`) VALUES (4, 'H4', 'High', 'H4 Street', '3-33333333');
INSERT INTO `hospital` (`hospital_id`, `hospital_name`, `hospital_level`, `hospital_address`, `hospital_contact`) VALUES (5, 'H5', 'Medium', 'H5 Street', '4-44444444');
INSERT INTO `hospital` (`hospital_id`, `hospital_name`, `hospital_level`, `hospital_address`, `hospital_contact`) VALUES (6, 'H6', 'Low', 'H6 Street', '5-55555555');
INSERT INTO `hospital` (`hospital_id`, `hospital_name`, `hospital_level`, `hospital_address`, `hospital_contact`) VALUES (7, 'H7', 'Medium', 'H7 Street', '6-66666666');
INSERT INTO `hospital` (`hospital_id`, `hospital_name`, `hospital_level`, `hospital_address`, `hospital_contact`) VALUES (8, 'H8', 'Low', 'H8 Street', '7-77777777');
INSERT INTO `hospital` (`hospital_id`, `hospital_name`, `hospital_level`, `hospital_address`, `hospital_contact`) VALUES (9, 'H9', 'High', 'H9 Street', '8-88888888');
INSERT INTO `hospital` (`hospital_id`, `hospital_name`, `hospital_level`, `hospital_address`, `hospital_contact`) VALUES (10, 'H10', 'High', 'H10 Street', '9-99999999');
COMMIT;

-- ----------------------------
-- Table structure for monitor
-- ----------------------------
DROP TABLE IF EXISTS `monitor`;
CREATE TABLE `monitor` (
  `monitor_id` int NOT NULL AUTO_INCREMENT COMMENT 'Monitor ID (PK)',
  `user_hospital_id` int NOT NULL COMMENT 'User-hospital ID (FK)',
  `monitor_time` datetime NOT NULL COMMENT 'Monitor Time',
  `height` decimal(10,2) NOT NULL COMMENT 'Height',
  `weight` decimal(10,2) NOT NULL COMMENT 'Weight',
  `body_temperature` decimal(10,2) NOT NULL COMMENT 'Body Temperature',
  `heart_rate` int NOT NULL COMMENT 'Heart rate',
  `blood_pressure_high` int NOT NULL COMMENT 'Blood Pressure High',
  `blood_pressure_low` int NOT NULL COMMENT 'Blood Pressure Low',
  `blood_oxygen` decimal(10,2) NOT NULL COMMENT 'Blood Oxygen (Rate)',
  `blood_glucose` decimal(10,2) NOT NULL COMMENT 'Bllod Glucose',
  `blood_lipid` decimal(10,2) NOT NULL COMMENT 'Blood Lipid',
  PRIMARY KEY (`monitor_id`),
  KEY `user_hospital_id_fk1` (`user_hospital_id`),
  CONSTRAINT `user_hospital_id_fk1` FOREIGN KEY (`user_hospital_id`) REFERENCES `user_hospital` (`user_hospital_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of monitor
-- ----------------------------
BEGIN;
INSERT INTO `monitor` (`monitor_id`, `user_hospital_id`, `monitor_time`, `height`, `weight`, `body_temperature`, `heart_rate`, `blood_pressure_high`, `blood_pressure_low`, `blood_oxygen`, `blood_glucose`, `blood_lipid`) VALUES (1, 2, '2024-10-26 08:00:00', 1.70, 60.00, 36.90, 68, 130, 75, 98.00, 4.50, 3.40);
INSERT INTO `monitor` (`monitor_id`, `user_hospital_id`, `monitor_time`, `height`, `weight`, `body_temperature`, `heart_rate`, `blood_pressure_high`, `blood_pressure_low`, `blood_oxygen`, `blood_glucose`, `blood_lipid`) VALUES (2, 2, '2024-10-26 14:00:00', 1.70, 60.00, 37.00, 68, 130, 75, 98.00, 4.50, 3.40);
INSERT INTO `monitor` (`monitor_id`, `user_hospital_id`, `monitor_time`, `height`, `weight`, `body_temperature`, `heart_rate`, `blood_pressure_high`, `blood_pressure_low`, `blood_oxygen`, `blood_glucose`, `blood_lipid`) VALUES (3, 2, '2024-10-26 20:00:00', 1.70, 60.00, 36.80, 68, 128, 72, 99.00, 4.50, 3.40);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT 'User ID (PK)',
  `user_role` varchar(8) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'User role (Admin/Patient)',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'User Name',
  `user_password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'User Password',
  `user_gender` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'User Gender (Male/Female/Unknown)',
  `user_dob` date NOT NULL COMMENT 'User DOB (YYYY/MM/DD)',
  `user_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'User Address',
  `user_contact` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'User Contact',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (1, 'Admin', 'H1Admin', 'H1Admin2024', 'Male', '2000-01-01', 'H Company', '1-12345678');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (2, 'Patient', 'Alan', 'Alan2025', 'Male', '2002-12-21', 'A3 Street', '6-66666666');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (3, 'Patient', 'Bob', 'Bob2024', 'Male', '2001-01-10', 'A2 Street', '5-55555555');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (4, 'Patient', 'Cindy', 'Cindyccc', 'Female', '2002-12-20', 'A3 Street', '7-12323421');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (5, 'Patient', 'Dave', 'Daved#', 'Male', '2003-09-13', 'A2 Street', '3-54935847');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (6, 'Patient', 'Alice', 'ALICECECE@123', 'Female', '2004-12-20', 'A4 Street', '8-24294728');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (7, 'Patient', 'Eve', 'eeeevv01vveeee', 'Male', '2001-06-27', 'A3 Street', '9-93478531');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (8, 'Patient', 'Frank', 'Frankfrk2024', 'Female', '2002-12-20', 'A3 Street', '1-39457837');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (9, 'Patient', 'Doe', 'Doe123ddd2004', 'Male', '2004-11-19', 'A4 Street', '3-98984875');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (10, 'Patient', 'John', 'johnsmith@cuhk', 'Female', '2004-12-20', 'A4 Street', '2-39457386');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (11, 'Patient', 'Joe', 'joejoejoe2005', 'Male', '2005-07-13', 'A5 Street', '2-24953098');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (12, 'Patient', 'Peter', 'Frankfrk2024', 'Male', '2002-12-20', 'A5 Street', '4-29834731');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (13, 'Patient', 'Lily', 'Doe123ddd2004', 'Female', '2004-11-19', 'A6 Street', '6-22937587');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (14, 'Patient', 'Wien', 'WIENwww06#', 'Female', '2006-10-23', 'A5 Street', '7-29498231');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (15, 'Admin', 'H2Admin', 'H1AAA39481', 'Male', '2001-01-01', 'H Company', '1-12345679');
INSERT INTO `user` (`user_id`, `user_role`, `user_name`, `user_password`, `user_gender`, `user_dob`, `user_address`, `user_contact`) VALUES (16, 'Admin', 'H3Admin', 'H3AAA24930', 'Female', '2002-01-01', 'H Company', '1-12345680');
COMMIT;

-- ----------------------------
-- Table structure for user_hospital
-- ----------------------------
DROP TABLE IF EXISTS `user_hospital`;
CREATE TABLE `user_hospital` (
  `user_hospital_id` int NOT NULL AUTO_INCREMENT COMMENT 'User-hospital ID (PK)',
  `user_id` int NOT NULL COMMENT 'User ID (FK)',
  `hospital_id` int NOT NULL COMMENT 'Hospital ID (FK)',
  `hospitalization_date` date NOT NULL COMMENT 'Hospitalization Date',
  `discharge_date` date DEFAULT NULL COMMENT 'Discharge Date',
  PRIMARY KEY (`user_hospital_id`) USING BTREE,
  KEY `user_id_fk1` (`user_id`),
  KEY `hospital_id_fk2` (`hospital_id`),
  CONSTRAINT `hospital_id_fk2` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`hospital_id`),
  CONSTRAINT `user_id_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user_hospital
-- ----------------------------
BEGIN;
INSERT INTO `user_hospital` (`user_hospital_id`, `user_id`, `hospital_id`, `hospitalization_date`, `discharge_date`) VALUES (1, 1, 1, '2024-10-24', '2024-10-31');
INSERT INTO `user_hospital` (`user_hospital_id`, `user_id`, `hospital_id`, `hospitalization_date`, `discharge_date`) VALUES (2, 2, 3, '2024-10-26', NULL);
INSERT INTO `user_hospital` (`user_hospital_id`, `user_id`, `hospital_id`, `hospitalization_date`, `discharge_date`) VALUES (3, 3, 2, '2024-10-28', '2024-10-31');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
