-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CritterChronologer
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `CritterChronologer` ;

-- -----------------------------------------------------
-- Schema CritterChronologer
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CritterChronologer` DEFAULT CHARACTER SET utf8 ;
USE `CritterChronologer` ;

-- -----------------------------------------------------
-- Table `CritterChronologer`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CritterChronologer`.`Customer` ;

CREATE TABLE IF NOT EXISTS `CritterChronologer`.`Customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `notes` VARCHAR(450) NULL,
  `phone_number` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CritterChronologer`.`Pet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CritterChronologer`.`Pet` ;

CREATE TABLE IF NOT EXISTS `CritterChronologer`.`Pet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `birth_date` DATE NULL,
  `notes` VARCHAR(4500) NULL,
  `Customer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Pet_Customer1_idx` (`Customer_id` ASC),
  CONSTRAINT `fk_Pet_Customer1`
    FOREIGN KEY (`Customer_id`)
    REFERENCES `CritterChronologer`.`Customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CritterChronologer`.`Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CritterChronologer`.`Employee` ;

CREATE TABLE IF NOT EXISTS `CritterChronologer`.`Employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CritterChronologer`.`Schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CritterChronologer`.`Schedule` ;

CREATE TABLE IF NOT EXISTS `CritterChronologer`.`Schedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `day` DATE NULL,
  `customer_id` INT NULL,
  `activity` VARCHAR(45) NULL,
  `Pet_id` INT NOT NULL,
  `Employee_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Schedule_Pet_idx` (`Pet_id` ASC),
  INDEX `fk_Schedule_Employee1_idx` (`Employee_id` ASC),
  CONSTRAINT `fk_Schedule_Pet`
    FOREIGN KEY (`Pet_id`)
    REFERENCES `CritterChronologer`.`Pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Schedule_Employee1`
    FOREIGN KEY (`Employee_id`)
    REFERENCES `CritterChronologer`.`Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CritterChronologer`.`Week`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CritterChronologer`.`Week` ;

CREATE TABLE IF NOT EXISTS `CritterChronologer`.`Week` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CritterChronologer`.`Week_has_Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CritterChronologer`.`Week_has_Employee` ;

CREATE TABLE IF NOT EXISTS `CritterChronologer`.`Week_has_Employee` (
  `Week_days_id` INT NOT NULL,
  `Employees_id` INT NOT NULL,
  PRIMARY KEY (`Week_days_id`, `Employees_id`),
  INDEX `fk_Week_has_Employee_Employee1_idx` (`Employees_id` ASC),
  INDEX `fk_Week_has_Employee_Week1_idx` (`Week_days_id` ASC),
  CONSTRAINT `fk_Week_has_Employee_Week1`
    FOREIGN KEY (`Week_days_id`)
    REFERENCES `CritterChronologer`.`Week` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Week_has_Employee_Employee1`
    FOREIGN KEY (`Employees_id`)
    REFERENCES `CritterChronologer`.`Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS user1;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'user1' IDENTIFIED BY 'user1';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `CritterChronologer`.* TO 'user1';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `CritterChronologer`.`Customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `CritterChronologer`;
INSERT INTO `CritterChronologer`.`Customer` (`id`, `name`, `notes`, `phone_number`) VALUES (1, 'name', 'notes', '814-661-0397');

COMMIT;


-- -----------------------------------------------------
-- Data for table `CritterChronologer`.`Pet`
-- -----------------------------------------------------
START TRANSACTION;
USE `CritterChronologer`;
INSERT INTO `CritterChronologer`.`Pet` (`id`, `type`, `name`, `birth_date`, `notes`, `Customer_id`) VALUES (1, 'Dog', 'doggy', '1970-01-01', 'goes woof', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `CritterChronologer`.`Employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `CritterChronologer`;
INSERT INTO `CritterChronologer`.`Employee` (`id`, `name`) VALUES (1, 'name');

COMMIT;


-- -----------------------------------------------------
-- Data for table `CritterChronologer`.`Schedule`
-- -----------------------------------------------------
START TRANSACTION;
USE `CritterChronologer`;
INSERT INTO `CritterChronologer`.`Schedule` (`id`, `day`, `customer_id`, `activity`, `Pet_id`, `Employee_id`) VALUES (1, '2020-12-25', 1, 'jumping', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `CritterChronologer`.`Week`
-- -----------------------------------------------------
START TRANSACTION;
USE `CritterChronologer`;
INSERT INTO `CritterChronologer`.`Week` (`id`, `name`) VALUES (1, 'Monday');
INSERT INTO `CritterChronologer`.`Week` (`id`, `name`) VALUES (2, 'Tuesday');
INSERT INTO `CritterChronologer`.`Week` (`id`, `name`) VALUES (3 , 'Wednesday');
INSERT INTO `CritterChronologer`.`Week` (`id`, `name`) VALUES (4, 'Thursday');
INSERT INTO `CritterChronologer`.`Week` (`id`, `name`) VALUES (5, 'Friday');
INSERT INTO `CritterChronologer`.`Week` (`id`, `name`) VALUES (6, 'Saturday');
INSERT INTO `CritterChronologer`.`Week` (`id`, `name`) VALUES (7, 'Sunday');

COMMIT;


-- -----------------------------------------------------
-- Data for table `CritterChronologer`.`Week_has_Employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `CritterChronologer`;
INSERT INTO `CritterChronologer`.`Week_has_Employee` (`Week_days_id`, `Employees_id`) VALUES (1, 1);
INSERT INTO `CritterChronologer`.`Week_has_Employee` (`Week_days_id`, `Employees_id`) VALUES (2, 1);
INSERT INTO `CritterChronologer`.`Week_has_Employee` (`Week_days_id`, `Employees_id`) VALUES (3, 1);
INSERT INTO `CritterChronologer`.`Week_has_Employee` (`Week_days_id`, `Employees_id`) VALUES (4, 1);
INSERT INTO `CritterChronologer`.`Week_has_Employee` (`Week_days_id`, `Employees_id`) VALUES (5, 1);
INSERT INTO `CritterChronologer`.`Week_has_Employee` (`Week_days_id`, `Employees_id`) VALUES (6, 1);
INSERT INTO `CritterChronologer`.`Week_has_Employee` (`Week_days_id`, `Employees_id`) VALUES (7, 1);

COMMIT;

