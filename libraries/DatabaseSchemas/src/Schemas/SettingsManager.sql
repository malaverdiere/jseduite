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
**/

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
    `login`     VARCHAR(255)    NOT NULL,
    `password`  VARCHAR(255)    NOT NULL,
    `name`      VARCHAR(255)    NOT NULL,
    PRIMARY KEY(`login`));

-- Avalaible sources

DROP TABLE IF EXISTS `sources` ;
CREATE TABLE `sources` (
    `id`        INTEGER         NOT NULL AUTO_INCREMENT,
    `service`   VARCHAR(255)    NOT NULL,
    `operation` VARCHAR(255)    NOT NULL,
    PRIMARY KEY(`id`),
    UNIQUE (`service`,`operation`));

-- Users preferences : binding between acount and sources
DROP TABLE IF EXISTS `preferences` ;
CREATE TABLE `preferences`(
    `id`        INTEGER         NOT NULL AUTO_INCREMENT,
    `login`     VARCHAR(255)    NOT NULL REFERENCES `accounts`,
    `source`    INTEGER         NOT NULL REFERENCES `sources`,
    PRIMARY KEY(`id`));

-- Users defined messages(set of parameters)
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
    `id`            INTEGER NOT NULL AUTO_INCREMENT,
    `preference`    INTEGER NOT NULL REFERENCES `preferences`,
    `callId`        INTEGER NOT NULL,
    `parameter`     VARCHAR(255) NOT NULL,
    `value`         VARCHAR(255),
    PRIMARY KEY (`id`));

-- Settings global view, to make the all stuff more readable
DROP VIEW IF EXISTS `settings`;
CREATE VIEW `settings` AS
    SELECT  `accounts`.`login`      AS `login`,
            `sources`.`service`     AS `service`,
            `sources`.`operation`   AS `operation`,
            `messages`.`callId`     AS `callId`,
            `messages`.`parameter`  AS `parameter`,
            `messages`.`value`      AS `value`
    FROM    `accounts`, `sources`, `preferences`, `messages`
    WHERE   `accounts`.`login`      = `preferences`.`login` AND
            `sources`.`id`          = `preferences`.`source` AND
            `messages`.`preference` = `preferences`.`id`
    ORDER BY `service` DESC, `operation` DESC, `callId` ASC;

