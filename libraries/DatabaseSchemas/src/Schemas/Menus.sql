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
 * @author      Mireille Blay-Fornarino [blay@polytech.unice.fr]
 * @contributor 2009 Mosser Sebastien   [mosser@polytech.unice.fr]
**/

-- Represents a "course" inside the jSeduite database
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id`      INT(11)      NOT NULL auto_increment,
  `kind`    VARCHAR(50)  NOT NULL,
  `name`    VARCHAR(255) NOT NULL,
  PRIMARY KEY(`id`));

-- Represents a menu as a date and a set of courses
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`(
  `date`    DATETIME       NOT NULL,
  `courseId`  VARCHAR(255) NOT NULL,
  `typeMenu` varchar(255)  NOT NULL,
  PRIMARY KEY(`date`,`courseId`,`typeMenu`));
