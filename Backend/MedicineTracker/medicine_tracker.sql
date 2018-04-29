/*
MySQL Data Transfer
Source Host: localhost
Source Database: medicine_tracker
Target Host: localhost
Target Database: medicine_tracker
Date: 20-03-2018 12:49:41
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for medicine_prescription
-- ----------------------------
DROP TABLE IF EXISTS `medicine_prescription`;
CREATE TABLE `medicine_prescription` (
  `medicine_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctor_name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `medicine_name` varchar(255) DEFAULT NULL,
  `morning_time_medicine` varchar(255) DEFAULT NULL,
  `afternoon_time_medicine` varchar(255) DEFAULT NULL,
  `night_time_medicine` varchar(255) DEFAULT NULL,
  `appointment_date` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `patient_email` varchar(255) DEFAULT NULL,
  `patient_ref_email` varchar(255) DEFAULT NULL,
  `days` int(255) DEFAULT NULL,
  `notify` int(255) DEFAULT NULL,
  PRIMARY KEY (`medicine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for patient_detail
-- ----------------------------
DROP TABLE IF EXISTS `patient_detail`;
CREATE TABLE `patient_detail` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_name` varchar(255) DEFAULT NULL,
  `patient_contact_no` varchar(255) DEFAULT NULL,
  `patient_email` varchar(255) DEFAULT NULL,
  `patient_password` varchar(255) DEFAULT NULL,
  `patient_reference` varchar(255) DEFAULT NULL,
  `patient_ref_name` varchar(255) DEFAULT NULL,
  `patient_ref_email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `medicine_prescription` VALUES ('1', 'Dr. Shubham', 'Tablet', 'Crocin', 'yes', 'no', 'yes', '2018-01-25', '1', 'xy@gmail.com', 't@gmail.com', '2', '1');
INSERT INTO `medicine_prescription` VALUES ('2', 'Dr. Shubham', 'Syrup', 'Cough Syrup', 'no', 'yes', 'no', '2018-01-25', '1', 'xy@gmail.com', 't@gmail.com', '1', '1');
INSERT INTO `medicine_prescription` VALUES ('3', 'Dr. Subha', 'Syrup', 'Cough Syrup', 'no', 'yes', 'no', '2018-01-25', '1', 'xy@gmail.com', 't@gmail.com', '2', '1');
INSERT INTO `medicine_prescription` VALUES ('4', 'DR SHAKSHI', 'Tablet', 'xyz', 'yes', 'yes', 'no', '2018-02-22', '3', 'nayana@gmail.com', 'sonone.nayana.12it5014@gmail.com', '1', '1');
INSERT INTO `medicine_prescription` VALUES ('5', 'DR SHAKSHI', 'Tablet', 'xyz', 'yes', 'yes', 'yes', '2018-02-22', '3', 'nayana@gmail.com', 'sonone.nayana.12it5014@gmail.com', '1', '1');
INSERT INTO `medicine_prescription` VALUES ('6', 'DR SHAKSHI', 'Syrup', 'vt', 'yes', 'yes', 'yes', '2018-02-22', '3', 'nayana@gmail.com', 'sonone.nayana.12it5014@gmail.com', '2', '1');
INSERT INTO `medicine_prescription` VALUES ('7', 'DR SHAKSHI', 'Tablet', 'sh', 'yes', 'yes', 'no', '2018-02-22', '3', 'nayana@gmail.com', 'sonone.nayana.12it5014@gmail.com', '2', '1');
INSERT INTO `medicine_prescription` VALUES ('8', 'DR SHAKSHI', 'Tablet', 'jj', 'yes', 'yes', 'no', '2018-02-22', '3', 'nayana@gmail.com', 'sonone.nayana.12it5014@gmail.com', '2', '1');
INSERT INTO `medicine_prescription` VALUES ('9', 'edicineServlet\nA Highcharts demos |\nSolr Admin\nand\nDR SHAKSHI', 'Tablet', 'haja', 'yes', 'yes', 'yes', '2018-02-22', '3', 'nayana@gmail.com', 'sonone.nayana.12it5014@gmail.com', '2', '1');
INSERT INTO `medicine_prescription` VALUES ('10', 'DR Sandhya', 'Tablet', 'Crocin', 'yes', 'yes', 'no', '2018-03-07', '6', 'abc@gmail.com', 'xyz@gmail.com', '1', '1');
INSERT INTO `medicine_prescription` VALUES ('11', 'DR Sandhya', 'Tablet', 'xyz', 'yes', 'yes', 'yes', '2018-03-07', '6', 'abc@gmail.com', 'xyz@gmail.com', '2', '1');
INSERT INTO `medicine_prescription` VALUES ('12', 'DR.nayana', 'Tablet', 'crocin', 'yes', 'yes', 'no', '2018-03-20', '7', 's@gmail.com', 'g@gmail.com', '1', '1');
INSERT INTO `patient_detail` VALUES ('1', 'xyz', '2356895623', 'xy@gmail.com', '567', 'Relative', 'ty', 't@gmail.com', null);
INSERT INTO `patient_detail` VALUES ('2', 'nayana', '8879856085', 'sonone.nayana.12it5014@gmail.com', '12345', 'Parent', 'sagar', 'sonone.nayana.12it5014@gmail.com', null);
INSERT INTO `patient_detail` VALUES ('3', 'nayana', '8879856086', 'nayana@gmail.com', '123', 'Parent', 'DR.neha', 'sonone.nayana.12it5014@gmail.com', null);
INSERT INTO `patient_detail` VALUES ('4', 'nayana', '887985683', 'nayana@gmail.com', '123', 'Relative', 'neha', 's@gmail.com', null);
INSERT INTO `patient_detail` VALUES ('5', 'nayana', '8879856083', 'nayana@gmail.com', '12345', 'Parent', 'akash', 'akash@gmail.com', null);
INSERT INTO `patient_detail` VALUES ('6', 'abc', '5689562356', 'abc@gmail.com', '123', 'Relative', 'xyz', 'xyz@gmail.com', null);
INSERT INTO `patient_detail` VALUES ('7', 'nayana', '8879856083', 's@gmail.com', '12345', 'Parent', 'nayana', 'g@gmail.com', 'airoli');
