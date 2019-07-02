-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_locadora
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_locadora
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_locadora` DEFAULT CHARACTER SET utf8 ;
USE `db_locadora` ;

-- -----------------------------------------------------
-- Table `db_locadora`.`carros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_locadora`.`carros` (
  `id_carro` INT(11) NOT NULL AUTO_INCREMENT,
  `placa` VARCHAR(7) NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `categoria` CHAR(2) NOT NULL,
  PRIMARY KEY (`id_carro`, `placa`),
  UNIQUE INDEX `placa_UNIQUE` (`placa` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_locadora`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_locadora`.`clientes` (
  `id_cliente` INT(11) NOT NULL AUTO_INCREMENT,
  `cpf_cliente` VARCHAR(11) NOT NULL,
  `nome_cliente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_cliente`, `cpf_cliente`),
  UNIQUE INDEX `cpf_cliente_UNIQUE` (`cpf_cliente` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_locadora`.`locacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_locadora`.`locacao` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_carro` INT(11) NOT NULL,
  `id_cliente` INT(11) NOT NULL,
  `data_inicio` DATETIME NOT NULL,
  `data_fim` DATETIME NOT NULL,
  `status` TINYINT(4) NULL DEFAULT NULL,
  `valor` DECIMAL(13,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `data_inicio`),
  INDEX `cpf_cliente_idx` (`id_cliente` ASC, `id_carro` ASC) VISIBLE,
  INDEX `id_idx` (`id_carro` ASC) VISIBLE,
  CONSTRAINT `id_carro`
    FOREIGN KEY (`id_carro`)
    REFERENCES `db_locadora`.`carros` (`id_carro`),
  CONSTRAINT `id_cliente`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `db_locadora`.`clientes` (`id_cliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
