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
 * @contributor 2008 Clémentine Delerce-Mauris [delercm@polytech.unice.fr]
 * @contributor 2008 Lionel Palacin            [lionel.palacin@gmail.com]
 * @contributor 2008 Stéphane Martarello       [stephane.martarello@gmail.com]
**/


-- author: <mosser@polytech.unice.fr>
-- @depends 'DataCache.sql'


DROP TABLE IF EXISTS `feed_class`;
CREATE TABLE `feed_class` (
    `id`    int(11)      NOT NULL AUTO_INCREMENT,
    `class` varchar(255) NOT NULL,
    PRIMARY KEY  (`id`));

DROP TABLE IF EXISTS `feed_registry` ;
CREATE TABLE `feed_registry` (
    `provider_dns`  varchar(255)    NOT NULL,
    `class_id`      int(11)         NOT NULL,
    `nickname`      varchar(255)    NOT NULL,
    `feed_url`      varchar(255)    NOT NULL,
    PRIMARY KEY  (`provider_dns`,`feed_url`),
    UNIQUE KEY `nickname` (`nickname`));
