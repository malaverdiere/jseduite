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

DROP TABLE IF EXISTS `sources`;
CREATE TABLE `sources` (
  `nickname`        VARCHAR(50)     NOT NULL,
  `name`            VARCHAR(255)    NOT NULL,
  PRIMARY KEY(`nickname`));

DROP TABLE IF EXISTS `parameters`;
CREATE TABLE `parameters` (
  `id`              INT(11)         NOT NULL AUTO_INCREMENT,
  `source`          VARCHAR(50)     NOT NULL REFERENCES `sources`,
  `name`            VARCHAR(50)     NOT NULL,
  `default_value`   VARCHAR(255)    NOT NULL,
  `pretty_name`     VARCHAR(255)    NOT NULL,
  PRIMARY KEY(`id`));


DROP TABLE IF EXISTS `devices`;
CREATE TABLE `devices` (
  `name`            VARCHAR(50)     NOT NULL,
  `location`        VARCHAR(255)    NOT NULL,
  PRIMARY KEY(`name`));

DROP TABLE IF EXISTS `device_subscription`;
CREATE TABLE `device_subscription` (
   `device`         VARCHAR(50)     NOT NULL REFERENCES `devices`,
   `source`         VARCHAR(50)     NOT NULL REFERENCES `sources`,
   `parameter_sets` INT             NOT NULL,
   PRIMARY KEY(`device`,`source`));

DROP TABLE IF EXISTS `device_parametrization`;
CREATE TABLE `device_parametrization` (
  `device_id`       VARCHAR(50)     NOT NULL REFERENCES `devices`,
  `param_id`        INT             NOT NULL REFERENCES `parameters`,
  `set_id`          INT             NOT NULL,
  `value`           VARCHAR(255)    NOT NULL,
  PRIMARY KEY (`device_id`,`param_id`, `set_id`));





