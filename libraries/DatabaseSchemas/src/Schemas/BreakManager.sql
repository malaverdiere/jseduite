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
 * @author      Main Zhao Yichen      [yichenzhao18@gmail.com]
 * @author      Main Qin Zhaobo       [Bienvenueqin@gmail.com]
 * @contributor 2009 Mosser Sebastien [mosser@polytech.unice.fr]
**/

DROP TABLE IF EXISTS `break_time_promos_lnk`;

CREATE TABLE `break_time_promos_lnk` (
    `break`         INT(11) NOT NULL REFERENCES `break_time`,
    `binding_alarm` INT(11) NOT NULL REFERENCES `promos`,
    PRIMARY KEY (`break`,`binding_alarm`));

DROP TABLE IF EXISTS `break_time`;

CREATE TABLE `break_time` (
    `break_id`  INT(11)     NOT NULL AUTO_INCREMENT ,
    `start`     VARCHAR(20) NOT NULL ,
    `end`       VARCHAR(20) NOT NULL ,
    `kind`      VARCHAR(10) NOT NULL ,
    `day`       VARCHAR(10) NOT NULL,
    PRIMARY KEY (`break_id`));

