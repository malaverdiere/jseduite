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
 * @author      Main Christophe Desclaux      [desclaux@polytech.unice.fr]
**/

DROP TABLE IF EXISTS `break_screen_days`;
DROP TABLE IF EXISTS `break_screen`;
CREATE TABLE `break_screen` (
    `id`        INT(11)         NOT NULL AUTO_INCREMENT,
    `start`     TIME            NOT NULL,
    `end`       TIME            NOT NULL,
    `building`  VARCHAR(10)     NOT NULL ,
    `content`   VARCHAR(255)    NOT NULL,
    PRIMARY KEY  (`id`));

CREATE TABLE `break_screen_days`(
    `break_id`  INT(11)         NOT NULL,
    `day` ENUM('monday','tuesday','wednesday','thursday','friday','saturday','sunday') NOT NULL ,
    PRIMARY KEY (`break_id`,`day`),

    CONSTRAINT `fk_break_id_BMD`
    FOREIGN KEY (`break_id` )
    REFERENCES `break_screen` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
