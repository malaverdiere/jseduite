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


DROP TABLE IF EXISTS `internal_news_target`;
CREATE TABLE `internal_news_target` (
    `id`     INT(11)        NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(50)    NOT NULL,
    PRIMARY KEY  (`id`));

DROP TABLE IF EXISTS `internal_news`;
CREATE TABLE `internal_news` (
    `id`        INT(11)      NOT NULL AUTO_INCREMENT,
    `target`    INT(11)      NOT NULL REFERENCES `internal_news_target`,
    `author`    VARCHAR(50)  NOT NULL,
    `start`     TIMESTAMP    NOT NULL DEFAULT NOW(),
    `end`       TIMESTAMP    NOT NULL,
    `title`     VARCHAR(255) NOT NULL,
    `content`   LONGTEXT     NOT NULL,
    PRIMARY KEY  (`id`));

DROP VIEW IF EXISTS `current_internal_news`;
CREATE VIEW `current_internal_news` AS
    SELECT
        `internal_news`.`author`,  `internal_news`.`start`,
        `internal_news`.`end`,     `internal_news`.`title`,
        `internal_news`.`content`, `internal_news_target`.`name` AS `target`
    FROM
        `internal_news`, `internal_news_target`
   WHERE
        `internal_news`.`target` = `internal_news_target`.`id` AND
        `start` <= NOW() AND `end` >= NOW() ;

