CREATE DATABASE  IF NOT EXISTS `agencia_turismo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `agencia_turismo`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: agencia_turismo
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `disponible_desde` date DEFAULT NULL,
  `disponible_hasta` date DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `tipo_de_habitacion` varchar(255) DEFAULT NULL,
  `hotel_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk3l154yy3cd6te71b3vc7wlp7` (`hotel_id`),
  CONSTRAINT `FKk3l154yy3cd6te71b3vc7wlp7` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacion`
--

LOCK TABLES `habitacion` WRITE;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` VALUES (1,'2024-02-15','2024-03-09',630,'Doble',1),(2,'2024-01-15','2024-03-09',830,'Triple',1),(3,'2024-02-15','2024-03-09',543,'Single',2),(4,'2024-01-15','2024-04-09',720,'Doble',3),(5,'2024-04-15','2024-05-09',579,'Doble',4),(6,'2024-02-15','2024-04-09',415,'Single',5),(7,'2024-03-15','2024-06-09',390,'Single',6),(8,'2024-01-15','2024-06-09',584,'Doble',6),(9,'2024-05-15','2024-06-09',702,'Doble',7),(10,'2024-01-15','2024-09-09',860,'Multiple',8),(11,'2024-07-15','2024-09-09',660,'Doble',9),(12,'2024-06-15','2024-09-09',1960,'Multiple',10);
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_hotel` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'AR-3949','Atlantis Resort','Miami'),(2,'RC-0929','Ritz-Carlton','Buenos Aires'),(3,'RC-1188','Ritz-Carlton','Medellín'),(4,'GH-8124','Grand Hyatt','Madrid'),(5,'GH-8069','Grand Hyatt','Buenos Aires'),(6,'HIL-8922','Hilton','Barcelona'),(7,'MAR-4731','Marriott','Barcelona'),(8,'SHE-5761','Sheraton','Madrid'),(9,'SHE-3887','Sheraton','Iguazú'),(10,'INT-5658','InterContinental','Cartagena');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_hotel`
--

DROP TABLE IF EXISTS `reserva_hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_hotel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_hotel` varchar(255) DEFAULT NULL,
  `fecha_desde` date DEFAULT NULL,
  `fecha_hasta` date DEFAULT NULL,
  `noches` int NOT NULL,
  `numero_huespedes` int NOT NULL,
  `precio` double NOT NULL,
  `tipo_de_habitacion` varchar(255) DEFAULT NULL,
  `hotel_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfwrc1woy4rdh0vwgdgx6tugll` (`hotel_id`),
  KEY `FKt8qslpgp268mviat0h4acvttd` (`user_id`),
  CONSTRAINT `FKfwrc1woy4rdh0vwgdgx6tugll` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
  CONSTRAINT `FKt8qslpgp268mviat0h4acvttd` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_hotel`
--

LOCK TABLES `reserva_hotel` WRITE;
/*!40000 ALTER TABLE `reserva_hotel` DISABLE KEYS */;
INSERT INTO `reserva_hotel` VALUES (1,'AR-3949','2024-02-15','2024-03-09',23,2,14490,'Doble',1,1),(2,'RC-0929','2024-02-15','2024-03-01',15,1,8145,'Single',2,2),(3,'RC-1188','2024-01-15','2024-01-16',1,2,720,'Doble',3,3),(4,'GH-8124','2024-04-15','2024-05-09',24,2,13896,'Doble',4,4),(5,'SHE-5761','2024-01-15','2024-09-09',238,6,204680,'Multiple',8,5);
/*!40000 ALTER TABLE `reserva_hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_vuelo`
--

DROP TABLE IF EXISTS `reserva_vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_vuelo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_vuelo` varchar(255) DEFAULT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `origen` varchar(255) DEFAULT NULL,
  `peopleq` int DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `reservado` bit(1) NOT NULL,
  `tipo_de_asiento` varchar(255) DEFAULT NULL,
  `id_vuelo` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpauoxdcvff5e2ahpfnqhw4ia4` (`id_vuelo`),
  CONSTRAINT `FKpauoxdcvff5e2ahpfnqhw4ia4` FOREIGN KEY (`id_vuelo`) REFERENCES `vuelo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_vuelo`
--

LOCK TABLES `reserva_vuelo` WRITE;
/*!40000 ALTER TABLE `reserva_vuelo` DISABLE KEYS */;
INSERT INTO `reserva_vuelo` VALUES (1,'BAMI-9337','Miami','2024-01-15','Barcelona',2,1300,_binary '','Economy',1),(2,'MIMA-0402','Madrid','2024-02-15','Miami',1,4320,_binary '','Business',2),(3,'MIMA-3675','Madrid','2024-02-15','Miami',2,4640,_binary '','Economy',3),(4,'BABU-9961','Buenos Aires','2024-03-15','Barcelona',2,1440,_binary '','Economy',4),(5,'BUBA-4999','Barcelona','2024-02-23','Buenos Aires',1,1220,_binary '','Economy',5);
/*!40000 ALTER TABLE `reserva_vuelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `edad` int NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `pasaporte` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Gomez','ARgome@example.com',20,'Ariel','AB45124578'),(2,'Villa','Alvilla@example.com',25,'Aldana','AB78561478'),(3,'Villa','JUvilla@example.com',24,'Juan','AB78548478'),(4,'Pardo','FePar@example.com',78,'Federico','AB78548778'),(5,'Vinotori','FRAVi@example.com',55,'Francesco','AB78548778');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuelo`
--

DROP TABLE IF EXISTS `vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vuelo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `destino` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `fecha` date DEFAULT NULL,
  `numero_vuelo` varchar(255) DEFAULT NULL,
  `origen` varchar(255) DEFAULT NULL,
  `precio_vuelo` double NOT NULL,
  `tipo_de_asiento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelo`
--

LOCK TABLES `vuelo` WRITE;
/*!40000 ALTER TABLE `vuelo` DISABLE KEYS */;
INSERT INTO `vuelo` VALUES (1,'Miami',_binary '','2024-01-15','BAMI-9337','Barcelona',650,'Economy'),(2,'Madrid',_binary '','2024-02-15','MIMA-0402','Miami',4320,'Business'),(3,'Madrid',_binary '','2024-05-15','MIMA-3675','Miami',2320,'Economy'),(4,'Buenos Aires',_binary '','2024-03-15','BABU-9961','Barcelona',720,'Economy'),(5,'Barcelona',_binary '','2024-02-23','BUBA-4999','Buenos Aires',1220,'Business'),(6,'Barcelona',_binary '\0','2024-03-23','IGBA-9985','Iguazú',520,'Economy'),(7,'Cartagena',_binary '\0','2024-04-23','BOCA-2613','Bogotá',820,'Economy'),(8,'Medellín',_binary '\0','2024-06-23','CAME-1837','Cartagena',780,'Economy'),(9,'Iguazú',_binary '\0','2024-02-23','BOIG-9570','Bogotá',570,'Business'),(10,'Madrid',_binary '\0','2024-06-23','BOMA-4490','Bogotá',370,'Economy'),(11,'Miami',_binary '\0','2024-03-23','MEMI-4878','Medellín',1370,'Business');
/*!40000 ALTER TABLE `vuelo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-13 17:17:31
