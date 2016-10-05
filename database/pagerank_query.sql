-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema pagerank_schema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `pagerank_schema` ;

-- -----------------------------------------------------
-- Schema pagerank_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pagerank_schema` DEFAULT CHARACTER SET utf8 ;
USE `pagerank_schema` ;

-- -----------------------------------------------------
-- Table `pagerank_schema`.`sites`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pagerank_schema`.`sites` ;

CREATE TABLE IF NOT EXISTS `pagerank_schema`.`sites` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(200) NOT NULL,
  `html` LONGTEXT CHARACTER SET 'utf8' NOT NULL,
  `pagerank` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `url_UNIQUE` (`url` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pagerank_schema`.`sites_have_links`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pagerank_schema`.`sites_have_links` ;

CREATE TABLE IF NOT EXISTS `pagerank_schema`.`sites_have_links` (
  `id_out` INT NOT NULL,
  `id_in` INT NOT NULL,
  PRIMARY KEY (`id_out`, `id_in`),
  INDEX `fk_sites_has_sites_sites1_idx` (`id_in` ASC),
  INDEX `fk_sites_has_sites_sites_idx` (`id_out` ASC),
  CONSTRAINT `fk_sites_has_sites_sites`
    FOREIGN KEY (`id_out`)
    REFERENCES `pagerank_schema`.`sites` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sites_has_sites_sites1`
    FOREIGN KEY (`id_in`)
    REFERENCES `pagerank_schema`.`sites` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
