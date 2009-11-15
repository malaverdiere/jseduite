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
 * @author Main Mosser Sebastien [mosser@polytech.unice.fr]
 * @contributor 2009 Colombié Steve [colombie@polytech.unice.fr]
**/

DROP TABLE IF EXISTS `summon_levels`;
DROP TABLE IF EXISTS `summonings`;
DROP VIEW IF EXISTS `valid_summonings`;
DROP TABLE IF EXISTS `teacher_absences`;
DROP TABLE IF EXISTS `promo_planning`;
DROP TABLE IF EXISTS `promo_groups`;
DROP TABLE IF EXISTS `promos`;


CREATE TABLE `summon_levels` (
    `id`    INT(11)         NOT NULL AUTO_INCREMENT,
    `name`  VARCHAR(255)    NOT NULL,
     PRIMARY KEY  (`id`));


CREATE TABLE `promos` (
    `id`    INT(11)         NOT NULL AUTO_INCREMENT,
    `code`  VARCHAR(50)     NOT NULL,
    `name`  VARCHAR(255)    NOT NULL,
     PRIMARY KEY  (`id`));


CREATE TABLE `summonings` (
    `id`        INT(11)         NOT NULL AUTO_INCREMENT,
    `student`   VARCHAR(255)    NOT NULL,
    `promo`     INT(11)         NOT NULL REFERENCES `promos`,
    `owner`     VARCHAR(50)     NOT NULL,
    `stamp`     TIMESTAMP       NOT NULL DEFAULT NOW(),
    `level`     INT(11)         NOT NULL REFERENCES `summon_levels`,
    `valid`     BOOLEAN         NOT NULL DEFAULT TRUE,
    PRIMARY KEY  (`id`));


CREATE VIEW `valid_summonings` AS
    SELECT
        `summonings`.`student`, `summonings`.`owner`, `promos`.`code` AS `p_code`,
        `promos`.`name` AS `promo`, `summonings`.`stamp` AS `date`,
        `summon_levels`.`name` AS `level`, `summonings`.`id`
    FROM
        `summonings`, `summon_levels`, `promos`
    WHERE
        `summonings`.`level` = `summon_levels`.`id` AND `summonings`.`valid`
        AND `summonings`.`promo` = `promos`.`id`;


CREATE TABLE `teacher_absences` (
    `id`        INT(11)         NOT NULL AUTO_INCREMENT,
    `teacher`   VARCHAR(255)    NOT NULL,
    `reason`    VARCHAR(255)    NULL,
    `from`      TIMESTAMP       NOT NULL DEFAULT NOW(),
    `until`     TIMESTAMP       NOT NULL,
    PRIMARY KEY(`id`));


CREATE TABLE `promo_planning` (
    `id`        INT(11)         NOT NULL,
    `planning`  VARCHAR(255)    NOT NULL,
     PRIMARY KEY  (`id`),

    CONSTRAINT `fk_promo_planning_id`
    FOREIGN KEY (`id`)
    REFERENCES `promos` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE `promo_groups` (
    `id`        INT(11)         NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(255)    NOT NULL,
    `planning`  VARCHAR(255)    NOT NULL,
    `promo_id`  INT(11)         NOT NULL,
     PRIMARY KEY  (`id`),

    CONSTRAINT `fk_promo_id`
    FOREIGN KEY (`promo_id` )
    REFERENCES `promos` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
