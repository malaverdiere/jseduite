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

DROP TABLE IF EXISTS `break_time_promotions`;
DROP TABLE IF EXISTS `break_time_days`;
DROP TABLE IF EXISTS `break_time`;
CREATE TABLE `break_time` (
    `id`        INT(11)         NOT NULL AUTO_INCREMENT,
    `start`     TIME            NOT NULL,
    `end`       TIME            NOT NULL,
    `building`  VARCHAR(10)     NOT NULL ,
    `kind`      VARCHAR(10)     NOT NULL,
    PRIMARY KEY  (`id`));



CREATE TABLE `break_time_promotions` (
    `break_id`  INT(11)         NOT NULL,
    `promo_id`  INT(11)         NOT NULL,
    PRIMARY KEY (`break_id`,`promo_id`),

    CONSTRAINT `fk_break_id_BM`
    FOREIGN KEY (`break_id` )
    REFERENCES `break_time` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    CONSTRAINT `fk_alarm_id`
    FOREIGN KEY (`promo_id` )
    REFERENCES `promos` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE `break_time_days`(
    `break_id`  INT(11)         NOT NULL,
    `day` ENUM('monday','tuesday','wednesday','thursday','friday','saturday','sunday') NOT NULL ,
    PRIMARY KEY (`break_id`,`day`),

    CONSTRAINT `fk_break_id_BMD`
    FOREIGN KEY (`break_id` )
    REFERENCES `break_time` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
