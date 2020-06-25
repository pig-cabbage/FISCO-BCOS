-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: score_market
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bank_business`
--

DROP TABLE IF EXISTS `bank_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bank_business` (
  `idbank_business_id` int(11) NOT NULL AUTO_INCREMENT,
  `business_id` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`idbank_business_id`),
  KEY `business1_idx` (`business_id`),
  CONSTRAINT `business1` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_business`
--

LOCK TABLES `bank_business` WRITE;
/*!40000 ALTER TABLE `bank_business` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank_business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business`
--

DROP TABLE IF EXISTS `business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `business` (
  `business_id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `surplus` int(11) DEFAULT NULL,
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business`
--

LOCK TABLES `business` WRITE;
/*!40000 ALTER TABLE `business` DISABLE KEYS */;
INSERT INTO `business` VALUES (1,'京东',21440),(2,'苏宁易购',18000);
/*!40000 ALTER TABLE `business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deal`
--

DROP TABLE IF EXISTS `deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `deal` (
  `user_id` int(11) NOT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `deal_time` datetime DEFAULT NULL,
  `deal_num` int(11) DEFAULT NULL,
  `way_of_deal` tinyint(1) DEFAULT NULL,
  `deal_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`deal_id`),
  KEY `goods_idx` (`goods_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `user_information` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deal`
--

LOCK TABLES `deal` WRITE;
/*!40000 ALTER TABLE `deal` DISABLE KEYS */;
INSERT INTO `deal` VALUES (1,2,'2020-06-18 06:31:26',20,0,1),(123456,3,'2020-06-19 09:29:20',10,0,1),(123456,3,'2020-06-19 09:30:05',10,0,2);
/*!40000 ALTER TABLE `deal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_url` varchar(150) DEFAULT NULL,
  `goods_name` varchar(100) DEFAULT NULL,
  `price_money` int(11) DEFAULT NULL,
  `price_score` int(11) DEFAULT NULL,
  `business_id` int(11) DEFAULT NULL,
  `time_of_grounding` datetime DEFAULT NULL,
  `time_of_undergrounding` datetime DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `business_id_idx` (`business_id`),
  KEY `priceScore` (`price_score`),
  CONSTRAINT `business_id` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (2,'cscsjkb','fnbfj',60,60,1,'2020-06-18 04:29:44','2020-06-18 06:37:08',480),(3,'sdasdas','test',12,12,1,'2020-06-18 04:30:52',NULL,480),(4,'sdasdas','pops',12,12,1,'2020-06-18 04:31:23',NULL,500),(5,'sdasdas','pops',12,120,1,'2020-06-18 04:31:31',NULL,500),(6,'sdasdas','test',12,120,1,'2020-06-18 04:31:42',NULL,500),(7,'sdasdas','qwwe',12,120,1,'2020-06-18 04:31:49',NULL,500);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_information`
--

DROP TABLE IF EXISTS `user_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_information` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `user_account` varchar(45) DEFAULT NULL,
  `user_secret` varchar(200) DEFAULT NULL,
  `surplus` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_information`
--

LOCK TABLES `user_information` WRITE;
/*!40000 ALTER TABLE `user_information` DISABLE KEYS */;
INSERT INTO `user_information` VALUES (1,'test','123456','123456',-300),(2,'jaskdas','23131','2131241',10000),(123456,'test','123456','abc',1260);
/*!40000 ALTER TABLE `user_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user_score`
--

DROP TABLE IF EXISTS `user_user_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_user_score` (
  `send_id` int(11) NOT NULL,
  `get_id` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`send_id`,`get_id`),
  KEY `userid1_idx` (`get_id`),
  CONSTRAINT `userid` FOREIGN KEY (`send_id`) REFERENCES `user_information` (`user_id`),
  CONSTRAINT `userid1` FOREIGN KEY (`get_id`) REFERENCES `user_information` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user_score`
--

LOCK TABLES `user_user_score` WRITE;
/*!40000 ALTER TABLE `user_user_score` DISABLE KEYS */;
INSERT INTO `user_user_score` VALUES (2,1,100,'2020-06-18 18:57:13'),(123456,2,100,'2020-06-19 09:31:57');
/*!40000 ALTER TABLE `user_user_score` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-19 22:38:19
