CREATE DATABASE IF NOT EXISTS parcial_java
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;

USE parcial_java;

CREATE TABLE IF NOT EXISTS `persona` (
  `pers_id` INT(11) NOT NULL AUTO_INCREMENT,
  `pers_nombre` VARCHAR(100) NOT NULL,
  `pers_documento` INT(11) NOT NULL,
  PRIMARY KEY (`pers_id`),
  UNIQUE KEY `pers_documento_UNIQUE` (`pers_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE IF NOT EXISTS `libro` (
  `libr_id` INT(11) NOT NULL AUTO_INCREMENT,
  `libr_titulo` VARCHAR(100) NOT NULL,
  `libr_clasificacion` VARCHAR(100) NOT NULL,
  `libr_numero` INT(11) NOT NULL,
  PRIMARY KEY (`libr_id`),
  UNIQUE KEY `libro_libr_titulo_unique` (`libr_titulo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE IF NOT EXISTS `prestamo` (
  `pers_id` INT(11) NOT NULL,
  `libr_id` INT(11) NOT NULL,
  `prestamo_numero` INT(11) NOT NULL,
  `prestamo_dia` DATE NOT NULL,
  `prestamo_duracion` DATE NOT NULL,
  `prestamo_estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`pers_id`, `libr_id`),
  KEY `prestamo_libro_FK` (`libr_id`),
  CONSTRAINT `prestamo_libro_FK` FOREIGN KEY (`libr_id`) REFERENCES `libro` (`libr_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prestamo_persona_FK` FOREIGN KEY (`pers_id`) REFERENCES `persona` (`pers_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;