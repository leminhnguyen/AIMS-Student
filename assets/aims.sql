-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema aims
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema aims
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `aims` DEFAULT CHARACTER SET latin1 ;
USE `aims` ;

-- -----------------------------------------------------
-- Table `aims`.`Media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`Media` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `price` INT(11) NOT NULL,
  `quantity` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aims`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`Book` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `author` VARCHAR(45) NOT NULL,
  `coverType` VARCHAR(45) NOT NULL,
  `publisher` VARCHAR(45) NOT NULL,
  `publishDate` DATETIME NOT NULL,
  `numOfPages` INT(11) NOT NULL,
  `language` VARCHAR(45) NOT NULL,
  `bookCategory` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_book_media`
    FOREIGN KEY (`id`)
    REFERENCES `aims`.`Media` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aims`.`CD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`CD` (
  `id` INT(11) NOT NULL,
  `artist` VARCHAR(45) NOT NULL,
  `recordLabel` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cd_media`
    FOREIGN KEY (`id`)
    REFERENCES `aims`.`Media` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aims`.`DVD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`DVD` (
  `id` INT(11) NOT NULL,
  `discType` VARCHAR(45) NOT NULL,
  `director` VARCHAR(45) NOT NULL,
  `runtime` INT(11) NOT NULL,
  `studio` VARCHAR(45) NOT NULL,
  `subtitle` VARCHAR(45) NOT NULL,
  `releasedDate` DATETIME NOT NULL,
  `filmType` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_dvd_media`
    FOREIGN KEY (`id`)
    REFERENCES `aims`.`Media` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aims`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`User` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aims`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`Order` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `userID` INT(11) NOT NULL,
  `shipping_fee` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user_idx` (`userID` ASC),
  CONSTRAINT `fk_order_user`
    FOREIGN KEY (`userID`)
    REFERENCES `aims`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aims`.`OrderMedia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`OrderMedia` (
  `mediaID` INT(11) NOT NULL,
  `orderID` INT(11) NOT NULL,
  PRIMARY KEY (`mediaID`, `orderID`),
  INDEX `fk_ordermedia_order_idx` (`orderID` ASC),
  CONSTRAINT `fk_ordermedia_media`
    FOREIGN KEY (`mediaID`)
    REFERENCES `aims`.`Media` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordermedia_order`
    FOREIGN KEY (`orderID`)
    REFERENCES `aims`.`Order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aims`.`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`Transaction` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `orderID` INT(11) NOT NULL,
  `createAt` DATETIME NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transaction_order_idx` (`orderID` ASC),
  CONSTRAINT `fk_transaction_order`
    FOREIGN KEY (`orderID`)
    REFERENCES `aims`.`Order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;