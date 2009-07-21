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
 * @author      Main Steve Colombié      [colombie@polytech.unice.fr]
**/

DROP TABLE IF EXISTS `alarms`;
CREATE TABLE `alarms` (
    `id`        INT(11)         NOT NULL AUTO_INCREMENT,
    `kind`      VARCHAR(10)     NOT NULL,
    `message`   VARCHAR(255)    NOT NULL,
    `sound`     VARCHAR(255)    NOT NULL,
    PRIMARY KEY  (`id`));

DROP TABLE IF EXISTS `alarm_breaks`;
CREATE TABLE `alarm_breaks` (
    `alarm_id`  INT(11)         NOT NULL
                                REFERENCES `alarms` (`id` )
                                ON DELETE CASCADE
                                ON UPDATE CASCADE,
    `break_id`  INT(11)         NOT NULL
                                REFERENCES `break_time` (`id` )
                                ON DELETE CASCADE
                                ON UPDATE CASCADE,
    PRIMARY KEY (`alarm_id`,`break_id`));
