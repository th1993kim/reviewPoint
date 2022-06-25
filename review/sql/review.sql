CREATE DATABASE  IF NOT EXISTS `review` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `review`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: review
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `event_id` int NOT NULL AUTO_INCREMENT COMMENT '리뷰이벤트ID',
  `type` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '글타입',
  `action` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '행위',
  `review_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '리뷰ID',
  `content` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '리뷰내용',
  `attached_photo_ids` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '리뷰사진ID들',
  `user_ai_id` int DEFAULT NULL COMMENT '유저ID',
  `place_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '여행장소ID',
  `reg_dtm` datetime DEFAULT NULL COMMENT '등록일',
  `upd_dtm` datetime DEFAULT NULL COMMENT '수정일',
  `is_first` bit(1) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `review_index` (`review_id`),
  KEY `user_place_index` (`user_ai_id`,`place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='리뷰이벤트';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point_history`
--

DROP TABLE IF EXISTS `point_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_history` (
  `point_history_id` int NOT NULL AUTO_INCREMENT COMMENT '포인트히스토리ID',
  `point` int DEFAULT NULL COMMENT '포인트값',
  `user_ai_id` int DEFAULT NULL COMMENT '유저ID',
  `reg_dtm` datetime DEFAULT NULL COMMENT '등록일',
  PRIMARY KEY (`point_history_id`),
  KEY `user_index` (`user_ai_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='포인트기록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point_history`
--

LOCK TABLES `point_history` WRITE;
/*!40000 ALTER TABLE `point_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `point_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_ai_id` int NOT NULL AUTO_INCREMENT COMMENT '포인트기본키',
  `user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '유저ID',
  `point` int DEFAULT NULL COMMENT '포인트총점',
  `reg_dtm` datetime DEFAULT NULL COMMENT '등록일',
  `upd_dtm` datetime DEFAULT NULL COMMENT '수정일',
  PRIMARY KEY (`user_ai_id`),
  KEY `user_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='유저테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-25 17:01:10
