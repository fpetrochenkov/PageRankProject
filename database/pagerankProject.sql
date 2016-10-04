-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema pagerankProject
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `pagerankProject` ;

-- -----------------------------------------------------
-- Schema pagerankProject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pagerankProject` DEFAULT CHARACTER SET utf8 ;
USE `pagerankProject` ;

-- -----------------------------------------------------
-- Table `pagerankProject`.`sites`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pagerankProject`.`sites` ;

CREATE TABLE IF NOT EXISTS `pagerankProject`.`sites` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `url` LONGTEXT NOT NULL,
  `html` LONGTEXT CHARACTER SET 'utf8' NOT NULL,
  `pagerank` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pagerankProject`.`sites_have_links`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pagerankProject`.`sites_have_links` ;

CREATE TABLE IF NOT EXISTS `pagerankProject`.`sites_have_links` (
  `id_out` INT NOT NULL,
  `id_in` INT NOT NULL,
  PRIMARY KEY (`id_out`, `id_in`),
  INDEX `fk_sites_has_sites_sites1_idx` (`id_in` ASC),
  INDEX `fk_sites_has_sites_sites_idx` (`id_out` ASC),
  CONSTRAINT `fk_sites_has_sites_sites`
    FOREIGN KEY (`id_out`)
    REFERENCES `pagerankProject`.`sites` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sites_has_sites_sites1`
    FOREIGN KEY (`id_in`)
    REFERENCES `pagerankProject`.`sites` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
