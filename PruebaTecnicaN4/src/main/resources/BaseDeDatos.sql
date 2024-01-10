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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacion`
--

LOCK TABLES `habitacion` WRITE;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` VALUES (1,'2024-02-10','2024-03-20',630,'Doble',1),(3,'2024-02-10','2024-03-23',820,'Triple',1),(4,'2024-02-10','2024-03-19',543,'Single',2),(5,'2024-02-12','2024-04-17',720,'Doble',3),(6,'2024-04-17','2024-05-23',579,'Doble',4),(7,'2024-01-10','2024-02-19',415,'Single',5),(8,'2024-01-23','2024-11-23',390,'Single',6),(9,'2024-01-23','2024-10-15',584,'Doble',6),(10,'2024-02-15','2024-03-27',702,'Doble',7),(11,'2024-03-01','2024-04-17',860,'Multiple',8),(13,'2024-04-17','2024-06-12',937,'Multiple',10),(14,'2024-04-17','2024-06-12',100,'Multiple',11);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'AR-2213','Atlantis Resort','Miami'),(2,'RC-0181','Alvear','Buenos Aires'),(3,'RC-9912','Ritz Carlton','Medellin'),(4,'GH-5679','Grand Hyatt','Madrid'),(5,'GH-2242','Alvear','Buenos Aires'),(6,'HIL-4571','Hilton','Barcelona'),(7,'MAR-6823','Marriot','Barcelona'),(8,'SHE-3980','Sheraton','Madrid'),(10,'INT-4669','Intercontinental','Cartagena'),(11,'INT-9597','Intercontinental','Paris');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_hotel`
--

LOCK TABLES `reserva_hotel` WRITE;
/*!40000 ALTER TABLE `reserva_hotel` DISABLE KEYS */;
INSERT INTO `reserva_hotel` VALUES (1,'INT-4669','2024-05-17','2024-06-12',26,3,24362,'Multiple',10,1),(2,'SHE-3980','2024-03-11','2024-04-11',31,1,26660,'Multiple',8,2),(3,'RC-9912','2024-02-12','2024-03-17',34,2,24480,'Doble',3,3);
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
  `id_user` bigint DEFAULT NULL,
  `id_vuelo` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmsjabt8cao4xe2rss0xf83ws2` (`id_user`),
  KEY `FKpauoxdcvff5e2ahpfnqhw4ia4` (`id_vuelo`),
  CONSTRAINT `FKmsjabt8cao4xe2rss0xf83ws2` FOREIGN KEY (`id_user`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKpauoxdcvff5e2ahpfnqhw4ia4` FOREIGN KEY (`id_vuelo`) REFERENCES `vuelo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_vuelo`
--

LOCK TABLES `reserva_vuelo` WRITE;
/*!40000 ALTER TABLE `reserva_vuelo` DISABLE KEYS */;
INSERT INTO `reserva_vuelo` VALUES (1,'BOIG-5917','Iguazu','2024-02-15','Bogota',2,1140,_binary '','Bussines',NULL,9),(2,'BAMI-2335','Miami','2025-01-15','Barcelona',2,1300,_binary '','Economy',NULL,1),(4,'MIMA-3183','Madrid','2024-02-10','Miami',2,8640,_binary '','Bussines',NULL,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'garcia','@example.com',25,'santaogo','AB66666'),(2,'Loro','PEdro@gmail.com',45,'Pedro','XX-46165156'),(3,'Gomez','ARgome@example.com',20,'Ariel','AB5555');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelo`
--

LOCK TABLES `vuelo` WRITE;
/*!40000 ALTER TABLE `vuelo` DISABLE KEYS */;
INSERT INTO `vuelo` VALUES (1,'Miami',_binary '','2024-02-15','BAMI-2335','Barcelona',650,'Economy'),(2,'Madrid',_binary '','2024-02-10','MIMA-3183','Miami',4320,'Bussines'),(3,'Madrid',_binary '\0','2024-02-10','MIMA-7093','Miami',2573,'Economy'),(4,'Buenos Aires',_binary '\0','2024-02-10','BABU-6780','Barcelona',723,'Economy'),(5,'Barcelona',_binary '\0','2024-02-12','BUBA-1062','Buenos Aires',1253,'Bussines'),(6,'Barcelona',_binary '\0','2024-01-08','IGBA-4449','iguazu',540,'Economy'),(7,'Cartagena',_binary '\0','2024-01-23','BOCA-0079','Bogota',800,'Economy'),(8,'Medellin',_binary '\0','2024-01-23','CAME-2043','Cartagena',780,'Economy'),(9,'Iguazu',_binary '','2024-02-15','BOIG-5917','Bogota',570,'Bussines');
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

-- Dump completed on 2024-01-09 22:21:08
