
/** This file is part of jSeduite::Database Schemas
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::DatabaseSchema is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::DatabaseSchema is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::DatabaseSchema; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Vincent Bonmalais      [vb.kouno@gmail.com]
 * @author      Main Yannick Tahora         [ytahora@gmail.com]
 * @contributor 2009 Mosser Sebastien [mosser@polytech.unice.fr]
**/


/*SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';
*/

USE `jSeduite`;

-- -----------------------------------------------------
-- Table `jSeduite`.`bus_lines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jSeduite`.`bus_lines` ;

CREATE  TABLE IF NOT EXISTS `jSeduite`.`bus_lines` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` TEXT NOT NULL ,
  `bus_steps` TEXT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jSeduite`.`bus_stops`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jSeduite`.`bus_stops` ;

CREATE  TABLE IF NOT EXISTS `jSeduite`.`bus_stops` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` TEXT NOT NULL ,
  `direction` TEXT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jSeduite`.`bus_periods`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jSeduite`.`bus_periods` ;

CREATE  TABLE IF NOT EXISTS `jSeduite`.`bus_periods` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `begin` DATE NOT NULL ,
  `end` DATE NOT NULL ,
  `name` TEXT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jSeduite`.`bus_line_steps_lnk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jSeduite`.`bus_line_steps_lnk` ;

CREATE  TABLE IF NOT EXISTS `jSeduite`.`bus_line_steps_lnk` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `line_id` INT NOT NULL ,
  `stop_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `FK_lineID_idLine`
    FOREIGN KEY (`line_id` )
    REFERENCES `jSeduite`.`bus_lines` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_stopID_idStop`
    FOREIGN KEY (`stop_id` )
    REFERENCES `jSeduite`.`bus_stops` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `jSeduite`.`bus_schedules`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jSeduite`.`bus_schedules` ;

CREATE  TABLE IF NOT EXISTS `jSeduite`.`bus_schedules` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `line_steps_id` INT NOT NULL ,
  `horary` TIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `FK_lineID_idLineSteps`
    FOREIGN KEY (`line_steps_id` )
    REFERENCES `jSeduite`.`bus_line_steps_lnk` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `jSeduite`.`bus_period_lnk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jSeduite`.`bus_period_lnk` ;

CREATE  TABLE IF NOT EXISTS `jSeduite`.`bus_period_lnk` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `period_id` INT NOT NULL ,
  `schedule_id` INT NOT NULL ,
  `day` ENUM('monday','tuesday','wednesday','thursday','friday','saturday','sunday') NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `FK_periodID_idPeriod`
    FOREIGN KEY (`period_id` )
    REFERENCES `jSeduite`.`bus_periods` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_ScheduleID_idSchedule`
    FOREIGN KEY (`schedule_id` )
    REFERENCES `jSeduite`.`bus_schedules` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

/*
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
*/