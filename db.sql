-- --------------------------------------------------------
-- Host:                         31.24.152.249
-- Versión del servidor:         10.6.5-MariaDB - MariaDB Server
-- SO del servidor:              Linux
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para WebCAE
DROP DATABASE IF EXISTS `WebCAE`;
CREATE DATABASE IF NOT EXISTS `WebCAE` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `WebCAE`;

-- Volcando estructura para tabla WebCAE.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `u_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla WebCAE.usuario: ~2 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `username`, `password`, `email`) VALUES
	(1, 'admin', '$2a$10$XIS3WsaI8i0TfOIE8C/4XuJ/ULeyEvtbMAcD3nXO7kkpvXakQyYAO', 'fcastroas@uoc.edu'),
	(2, 'user', '$2a$10$XIS3WsaI8i0TfOIE8C/4XuJ/ULeyEvtbMAcD3nXO7kkpvXakQyYAO', 'fcastroas@uoc.edu'),
	(3, 'user2', '$2a$10$XIS3WsaI8i0TfOIE8C/4XuJ/ULeyEvtbMAcD3nXO7kkpvXakQyYAO', 'fcastroas@uoc.edu');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Volcando estructura para tabla WebCAE.tipo_documento
DROP TABLE IF EXISTS `tipo_documento`;
CREATE TABLE IF NOT EXISTS `tipo_documento` (
  `id_tipo_documento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `vencimiento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla WebCAE.tipo_documento: ~0 rows (aproximadamente)
DELETE FROM `tipo_documento`;
/*!40000 ALTER TABLE `tipo_documento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_documento` ENABLE KEYS */;

-- Volcando estructura para tabla WebCAE.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `cif` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `responsable` varchar(50) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `id_cliente_usuario_fk` (`id_usuario`),
  CONSTRAINT `id_cliente_usuario_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla WebCAE.cliente: ~0 rows (aproximadamente)
DELETE FROM `cliente`;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Volcando estructura para tabla WebCAE.obra
DROP TABLE IF EXISTS `obra`;
CREATE TABLE IF NOT EXISTS `obra` (
  `id_obra` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_obra`),
  KEY `id_obra_cliente_fk` (`id_cliente`),
  CONSTRAINT `id_obra_cliente_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla WebCAE.obra: ~0 rows (aproximadamente)
DELETE FROM `obra`;
/*!40000 ALTER TABLE `obra` DISABLE KEYS */;
/*!40000 ALTER TABLE `obra` ENABLE KEYS */;

-- Volcando estructura para tabla WebCAE.obra_documento
DROP TABLE IF EXISTS `obra_documento`;
CREATE TABLE IF NOT EXISTS `obra_documento` (
  `id_obra` int(11) NOT NULL,
  `id_tipo_documento` int(11) NOT NULL,
  PRIMARY KEY (`id_obra`,`id_tipo_documento`),
  KEY `obra_documento_doc_fk` (`id_tipo_documento`),
  CONSTRAINT `obra_documento_doc_fk` FOREIGN KEY (`id_tipo_documento`) REFERENCES `tipo_documento` (`id_tipo_documento`),
  CONSTRAINT `obra_documento_obra_fk` FOREIGN KEY (`id_obra`) REFERENCES `obra` (`id_obra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla WebCAE.obra_documento: ~0 rows (aproximadamente)
DELETE FROM `obra_documento`;
/*!40000 ALTER TABLE `obra_documento` DISABLE KEYS */;
/*!40000 ALTER TABLE `obra_documento` ENABLE KEYS */;

-- Volcando estructura para tabla WebCAE.rol
DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_rol`),
  KEY `id_rol_usuario_fk` (`id_usuario`),
  CONSTRAINT `id_rol_usuario_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla WebCAE.rol: ~2 rows (aproximadamente)
DELETE FROM `rol`;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` (`id_rol`, `nombre`, `id_usuario`) VALUES
	(1, 'ROLE_ADMIN', 1),
	(2, 'ROLE_USER', 1),
	(3, 'ROLE_USER', 2),
	(4, 'ROLE_USER', 3);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;



/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
