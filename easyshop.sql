CREATE DATABASE  IF NOT EXISTS `onlineshoping` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `onlineshoping`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: onlineshoping
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_type` varchar(20) DEFAULT NULL,
  `client_name` varchar(50) DEFAULT NULL,
  `client_address` varchar(100) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `FK_clienttype` (`client_type`),
  CONSTRAINT `FK_clienttype` FOREIGN KEY (`client_type`) REFERENCES `client_type` (`client_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1001,'101','HMN','Germany',NULL,'5888877','hmn@goo.com',NULL,NULL),(1002,'102','Md. Jahangir Alam','Mirpur, Dhaka',NULL,'019885887','jahangiralam@yahoo.com',NULL,NULL),(1003,'101','Md. Jahangir Alam','Mirpur, Dhaka,,Dhaka','01923774442','7855','jahangir@gmail.com','jh_idb',NULL),(1004,'101','Hamidur','Mirpur, Dhaka,,Dhaka','01923774442','544','hamid@gmail.com','hamid',NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_type`
--

DROP TABLE IF EXISTS `client_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_type` (
  `client_type` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`client_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_type`
--

LOCK TABLES `client_type` WRITE;
/*!40000 ALTER TABLE `client_type` DISABLE KEYS */;
INSERT INTO `client_type` VALUES ('101','Buyer'),('102','Suplier');
/*!40000 ALTER TABLE `client_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `employee_name` varchar(45) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `join_date` date DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_line`
--

DROP TABLE IF EXISTS `order_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_line` (
  `order_serial` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`order_serial`),
  KEY `FK_orderid` (`order_id`),
  KEY `FK_individual_order_clientid` (`client_id`),
  KEY `FK_individual_order_productid` (`product_id`),
  CONSTRAINT `FK_individual_order_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_individual_order_productid` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FK_orderid` FOREIGN KEY (`order_id`) REFERENCES `order_table` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line`
--

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;
INSERT INTO `order_line` VALUES (11,1,1001,2,1,1200,'2014-04-24'),(12,1,1001,3,1,1500,'2014-04-24'),(13,1,1001,10004,1,2000,'2014-04-24'),(21,2,1001,2,1,1200,'2014-04-24'),(22,2,1001,3,1,1500,'2014-04-24'),(23,2,1001,100016,1,1000,'2014-04-24'),(31,3,1001,2,1,1200,'2014-04-24'),(32,3,1001,3,2,3000,'2014-04-24'),(33,3,1001,10004,2,4000,'2014-04-24'),(51,5,1003,2,1,1200,'2014-04-26'),(52,5,1003,3,1,1500,'2014-04-26'),(101,10,1003,100025,3,4500,'2014-05-23'),(102,10,1003,100020,1,2500,'2014-05-23'),(111,11,1003,2,1,1300,'2014-07-14'),(112,11,1003,10004,1,2000,'2014-07-14'),(113,11,1003,100018,2,1200,'2014-07-14'),(114,11,1003,100017,1,600,'2014-07-14');
/*!40000 ALTER TABLE `order_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_table` (
  `order_id` int(11) NOT NULL,
  `order_type` varchar(15) DEFAULT '101',
  `order_date` date DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `payment_method` varchar(50) DEFAULT NULL,
  `transaction_id` varchar(45) DEFAULT NULL,
  `pin_number` varchar(45) DEFAULT NULL,
  `delivery_address` varchar(200) DEFAULT NULL,
  `order_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_ordertype` (`order_type`),
  KEY `FK_clientid` (`client_id`),
  CONSTRAINT `fk_ordertable_clientid` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordertype` FOREIGN KEY (`order_type`) REFERENCES `order_type` (`order_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES (1,'101','2014-04-24',1001,4700,'bkash','fdfdfeee',NULL,'Mirpur, Dhaka ,  , Dhaka','Accepted'),(2,'101','2014-04-24',1001,3700,'bkash','sfgfgf',NULL,'Bangladesh , Dhaka , Dhaka','Accepted'),(3,'101','2014-04-24',1001,8200,'bkash','6757858',NULL,'Gulshan ,  , Dhaka','Accepted'),(5,'101','2014-04-26',1003,2700,'bkash','44474',NULL,'Rangpur ,  , Bang','Accepted'),(10,'101','2014-05-23',1003,7000,'bkash','65577',NULL,'Mirpur  ,  , Dhaka','Accepted'),(11,'101','2014-07-14',1003,5100,'bkash','456665',NULL,'15, 1st Floor,  , D Block , Dhaka','Accepted');
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_type`
--

DROP TABLE IF EXISTS `order_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_type` (
  `order_type` varchar(15) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_type`
--

LOCK TABLES `order_type` WRITE;
/*!40000 ALTER TABLE `order_type` DISABLE KEYS */;
INSERT INTO `order_type` VALUES ('101','Sale'),('102','Purchase');
/*!40000 ALTER TABLE `order_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_amount` double DEFAULT NULL,
  `due` double DEFAULT NULL,
  `paymentmethod` varchar(45) DEFAULT NULL,
  `transactionid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FK_payment_orderid` (`order_id`),
  CONSTRAINT `FK_payment_orderid` FOREIGN KEY (`order_id`) REFERENCES `order_table` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  `company_name` varchar(50) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `stock_id` int(11) DEFAULT NULL,
  `imageURL` varchar(150) DEFAULT NULL,
  `size` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK_product_groupid` (`group_id`),
  KEY `FK_product_stockid` (`stock_id`),
  CONSTRAINT `FK_product_groupid` FOREIGN KEY (`group_id`) REFERENCES `product_catagory` (`catagory_id`),
  CONSTRAINT `FK_product_stockid` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Full Black','Cates Eye',101,NULL,NULL,'45','Red'),(2,'Panjabi One','Abru',103,1002,'ps6_7637223090476656311.jpg','45','Black'),(3,'Anglico Blue','HMN',102,1003,'jns1_904986638990714719.jpg','66','Note'),(10004,'Long Panjabi','Sada Kalo',103,1004,'panjabi1_5586945703663656405.jpg','14','Blue'),(100016,'Tshirt One','Mycomns',102,10005,'tshirt1_5060334979857907219.jpg','4','Grass'),(100017,'Jeans Two','Jack and Jones',101,10006,'jns2_4404763696135176216.jpg','55',NULL),(100018,'Tshirt Flat','Ecstacy',101,10007,'tshirt2_8260081384308017770.jpg','55',NULL),(100019,'Tshirt Three','Jack and Jones',102,10008,'ts4_1409096282630922407.jpg','88',NULL),(100020,'Tshirt Five','Rang Dhanu',101,10009,'ts5_3948562615793379345.jpg','88',NULL),(100021,'Ftshirt','Mycom',102,10010,'ts3_3419089672620537106.jpg',NULL,NULL),(100022,'Jeans','HMN',101,10011,'jns5_705023695059583215.jpg',NULL,NULL),(100023,'Pnjabi Three','Sotabdi',103,10012,'ps4_7433360063938997854.jpg',NULL,NULL),(100024,'Fatua','Sotabdi',103,10013,'ps5_5825037727584184824.jpg',NULL,NULL),(100025,'Myshort','Sotabdi',101,10014,'jk1_2327924311238330485.jpg',NULL,NULL),(100026,'Bag','Casio',103,10015,'ts3_6733273916230780019.jpg',NULL,NULL),(100027,'Ftshirt','Casio',102,10016,'ts7_1755544098432214480.jpg',NULL,NULL),(100028,'Myshort','Bangla',102,10017,'jns3_112231516529152030.jpg',NULL,NULL),(100029,'Tshirt Six','Cates Eye',102,10018,'ts8_5441100389237605879.jpg',NULL,NULL),(100030,'Jeans','Anglico',103,10019,'jk1_2156078721128923237.jpg',NULL,NULL),(100031,'Jeans','Oral B',103,10020,'jns5_8811365994167759400.jpg',NULL,NULL),(100032,'Shoe','Bata',102,10021,'shoe_3040810028364747272.jpg',NULL,NULL),(100033,'tshirt','Richman',101,10022,'shirt 1_680243908441399775.jpg',NULL,NULL),(100034,'Black Tshirt','Abru',103,10023,'tshirt1_533905018968413599.jpg',NULL,NULL),(100035,'Pant','Anglico',103,10024,'jns1_5084549090085066260.jpg',NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_catagory`
--

DROP TABLE IF EXISTS `product_catagory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_catagory` (
  `catagory_id` int(11) NOT NULL,
  `catagory_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`catagory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_catagory`
--

LOCK TABLES `product_catagory` WRITE;
/*!40000 ALTER TABLE `product_catagory` DISABLE KEYS */;
INSERT INTO `product_catagory` VALUES (101,'Tshirt'),(102,'Jeans'),(103,'Panjabi');
/*!40000 ALTER TABLE `product_catagory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_order` (
  `porder_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`porder_id`),
  KEY `pur_clientID_idx` (`client_id`),
  KEY `pur_productID_idx` (`product_id`),
  CONSTRAINT `pur_clientID` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pur_productID` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=100044 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
INSERT INTO `purchase_order` VALUES (1,NULL,100017,5,2500,'2014-04-09'),(1023,NULL,2,4,2800,'2014-04-08'),(100001,1001,100017,5,1500,'2014-04-02'),(100002,NULL,100024,10,4000,'2014-04-08'),(100003,NULL,100017,5,2500,'2014-04-09'),(100004,NULL,2,5,3500,'2014-04-08'),(100005,NULL,100022,10,8000,'2014-04-08'),(100006,NULL,2,5,3500,'2014-04-08'),(100007,NULL,100030,5,2500,'2014-04-08'),(100008,NULL,10004,6,10200,'2014-04-08'),(100009,NULL,100034,10,9000,'2014-04-08'),(100010,NULL,100035,10,14550,'2014-04-08'),(100011,NULL,100026,5,3500,'2014-04-08'),(100012,NULL,100035,5,7275,'2014-04-08'),(100013,NULL,100026,5,3500,'2014-04-08'),(100014,NULL,100032,5,10000,'2014-04-08'),(100015,NULL,100034,5,4500,'2014-04-08'),(100016,NULL,100024,3,1200,'2014-04-08'),(100017,NULL,100024,3,1200,'2014-04-08'),(100018,NULL,100031,12,5340,'2014-04-08'),(100019,NULL,10004,7,11900,'2014-04-08'),(100020,NULL,100034,7,6300,'2014-04-08'),(100021,NULL,100023,3,4200,'2014-04-08'),(100022,NULL,100025,5,6000,'2014-04-08'),(100023,NULL,100022,5,4000,'2014-04-08'),(100024,NULL,100017,10,5000,'2014-04-08'),(100025,NULL,100018,5,2500,'2014-04-08'),(100026,NULL,100020,5,10000,'2014-04-08'),(100027,NULL,100030,5,2500,'2014-04-08'),(100028,NULL,100017,5,2500,'2014-04-08'),(100029,NULL,100035,5,7275,'2014-04-08'),(100030,NULL,100022,3,2400,'2014-04-08'),(100034,NULL,2,5,3500,'2014-04-08'),(100035,NULL,100022,2,1600,'2014-04-08'),(100036,NULL,100018,8,4000,'2014-04-08'),(100037,NULL,100018,5,2500,'2014-04-08'),(100038,NULL,100035,6,8730,'2014-04-08'),(100039,NULL,100035,2,2910,'2014-04-08'),(100041,NULL,100020,2,4000,'2014-04-10'),(100042,NULL,100035,0,0,'2014-04-11'),(100043,NULL,100020,5,10000,'2014-04-12');
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requested_order`
--

DROP TABLE IF EXISTS `requested_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requested_order` (
  `rorderid` int(11) NOT NULL,
  `rorder_date` date DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `payment_method` varchar(45) DEFAULT NULL,
  `transaction_id` varchar(45) DEFAULT NULL,
  `pin_number` varchar(45) DEFAULT NULL,
  `delivery_address` varchar(200) DEFAULT NULL,
  `order_status` varchar(45) DEFAULT 'pending',
  PRIMARY KEY (`rorderid`),
  KEY `rclientid_fk_idx` (`client_id`),
  CONSTRAINT `rorderclientid_fk` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requested_order`
--

LOCK TABLES `requested_order` WRITE;
/*!40000 ALTER TABLE `requested_order` DISABLE KEYS */;
INSERT INTO `requested_order` VALUES (1,'2014-04-24',1001,4700,'bkash','fdfdfeee',NULL,'Mirpur, Dhaka ,  , Dhaka','Accepted'),(2,'2014-04-24',1001,3700,'bkash','sfgfgf',NULL,'Bangladesh , Dhaka , Dhaka','Accepted'),(3,'2014-04-24',1001,8200,'bkash','6757858',NULL,'Gulshan ,  , Dhaka','Accepted'),(4,'2014-04-26',1003,2700,'bkash','44474',NULL,'Mirpur ,  , Dhaka','Canceled'),(5,'2014-04-26',1003,2700,'bkash','44474',NULL,'Rangpur ,  , Bang','Accepted'),(6,'2014-04-26',1004,3500,'bkash','lkkjhh',NULL,'Uttara ,  , Dhaka','pending'),(7,'2014-04-26',1003,3500,'bkash','124433',NULL,'Uttara ,  , Dhaka','pending'),(8,'2014-05-13',1003,2700,'bkash','hjh',NULL,'jhj , jhj , jh','pending'),(9,'2014-05-14',1003,9200,'bkash','55655',NULL,'Dhaka , Dhaka , Dhaka','pending'),(10,'2014-05-23',1003,7000,'bkash','65577',NULL,'Mirpur  ,  , Dhaka','Accepted'),(11,'2014-07-14',1003,5100,'bkash','456665',NULL,'15, 1st Floor,  , D Block , Dhaka','Accepted');
/*!40000 ALTER TABLE `requested_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requested_orderline`
--

DROP TABLE IF EXISTS `requested_orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requested_orderline` (
  `rorder_serial` int(11) NOT NULL,
  `rorder_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`rorder_serial`),
  KEY `rolrorderid_fk_idx` (`rorder_id`),
  KEY `rolclientid_fk_idx` (`client_id`),
  KEY `rolproductid_idx` (`product_id`),
  CONSTRAINT `rolclientid_fk` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rolproductid` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rolrorderid_fk` FOREIGN KEY (`rorder_id`) REFERENCES `requested_order` (`rorderid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requested_orderline`
--

LOCK TABLES `requested_orderline` WRITE;
/*!40000 ALTER TABLE `requested_orderline` DISABLE KEYS */;
INSERT INTO `requested_orderline` VALUES (11,1,1001,2,1,1200,'2014-04-24'),(12,1,1001,3,1,1500,'2014-04-24'),(13,1,1001,10004,1,2000,'2014-04-24'),(21,2,1001,2,1,1200,'2014-04-24'),(22,2,1001,3,1,1500,'2014-04-24'),(23,2,1001,100016,1,1000,'2014-04-24'),(31,3,1001,2,1,1200,'2014-04-24'),(32,3,1001,3,2,3000,'2014-04-24'),(33,3,1001,10004,2,4000,'2014-04-24'),(41,4,1003,2,1,1200,'2014-04-26'),(42,4,1003,3,1,1500,'2014-04-26'),(51,5,1003,2,1,1200,'2014-04-26'),(52,5,1003,3,1,1500,'2014-04-26'),(61,6,1004,3,1,1500,'2014-04-26'),(62,6,1004,10004,1,2000,'2014-04-26'),(71,7,1003,3,1,1500,'2014-04-26'),(72,7,1003,10004,1,2000,'2014-04-26'),(81,8,1003,2,1,1200,'2014-05-13'),(82,8,1003,3,1,1500,'2014-05-13'),(91,9,1003,2,1,1200,'2014-05-14'),(92,9,1003,10004,4,8000,'2014-05-14'),(101,10,1003,100025,3,4500,'2014-05-23'),(102,10,1003,100020,1,2500,'2014-05-23'),(111,11,1003,2,1,1300,'2014-07-14'),(112,11,1003,10004,1,2000,'2014-07-14'),(113,11,1003,100018,2,1200,'2014-07-14'),(114,11,1003,100017,1,600,'2014-07-14');
/*!40000 ALTER TABLE `requested_orderline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `stock_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `avail_stock` int(11) DEFAULT '0',
  `unit_cost` double DEFAULT NULL,
  `sales_price` double DEFAULT NULL,
  `reorder_level` int(11) DEFAULT NULL,
  `reorder_date` date DEFAULT NULL,
  PRIMARY KEY (`stock_id`),
  KEY `FK_stock_productid` (`product_id`),
  CONSTRAINT `stock.productIDfk` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1002,2,35,800,1300,20,'2014-01-15'),(1003,3,39,1000,1500,50,'2014-01-15'),(1004,10004,20,1500,2000,10,'2014-01-15'),(10005,100016,10,700,1000,NULL,'2014-02-10'),(10006,100017,20,500,600,NULL,'2014-10-10'),(10007,100018,25,500,600,NULL,'2014-10-09'),(10008,100019,12,50,70,NULL,'2014-03-13'),(10009,100020,32,2000,2500,NULL,'2014-03-13'),(10010,100021,10,500,700,NULL,'2014-03-13'),(10011,100022,50,800,1300,NULL,'2014-03-13'),(10012,100023,18,1400,2500,NULL,'2014-03-13'),(10013,100024,13,400,500,NULL,'2014-03-13'),(10014,100025,25,1200,1500,NULL,'2014-03-13'),(10015,100026,24,700,800,NULL,'2014-03-14'),(10016,100027,12,400,500,NULL,'2014-03-14'),(10017,100028,4,500,600,NULL,'2014-03-14'),(10018,100029,15,500,600,NULL,'2014-03-14'),(10019,100030,40,500,1000,NULL,'2014-03-16'),(10020,100031,24,445,770,NULL,'2014-03-16'),(10021,100032,10,2000,2500,NULL,'2014-04-01'),(10022,100033,12,900,1000,NULL,'2014-04-07'),(10023,100034,26,900,1200,NULL,'2014-04-07'),(10024,100035,28,1455,2100,NULL,'2014-04-08');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_name` varchar(15) NOT NULL,
  `role_name` varchar(15) NOT NULL,
  PRIMARY KEY (`user_name`,`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES ('hamid','customer'),('jh_idb','admin'),('shohag','staff');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_name` varchar(15) NOT NULL,
  `user_pass` varchar(15) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('hamid','123'),('jahangir','123'),('jh_idb','123'),('shohag','123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-15  0:18:08
