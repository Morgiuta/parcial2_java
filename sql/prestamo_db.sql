-- Crea la base de datos si no existe
CREATE DATABASE IF NOT EXISTS parcial_java
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;

-- Selecciona la base de datos para trabajar
USE parcial_java;

-- Tabla persona
CREATE TABLE `persona` (
  `pers_id` INT(11) NOT NULL AUTO_INCREMENT,
  `pers_nombre` VARCHAR(100) NOT NULL,
  `pers_documento` INT(11) NOT NULL,
  PRIMARY KEY (`pers_id`),
  UNIQUE KEY `pers_documento_UNIQUE` (`pers_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla libro
CREATE TABLE `libro` (
  `libr_id` INT(11) NOT NULL AUTO_INCREMENT,
  `libr_nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`libr_id`),
  UNIQUE KEY `libro_libr_nombre_unique` (`libr_nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla pers_libr (asociación entre persona y libro)
CREATE TABLE `pers_libr` (
  `pers_id` INT(11) NOT NULL,
  `libr_id` INT(11) NOT NULL,
  PRIMARY KEY (`pers_id`,`libr_id`),
  KEY `pers_libr_libro_FK` (`libr_id`),
  CONSTRAINT `pers_libr_libro_FK` FOREIGN KEY (`libr_id`) REFERENCES `libro` (`libr_id`),
  CONSTRAINT `pers_libr_persona_FK` FOREIGN KEY (`pers_id`) REFERENCES `persona` (`pers_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
