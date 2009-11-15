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

DROP TABLE IF EXISTS `picture_album_registry`;

CREATE TABLE `picture_album_registry` (
    `id`                    INTEGER      NOT NULL AUTO_INCREMENT,
    `album_name`            VARCHAR(255) NOT NULL,
    `repository`            VARCHAR(10)  NOT NULL,
    `repository_user_id`    VARCHAR(255) NOT NULL,
    `repository_album_name` VARCHAR(255) NOT NULL,
    `start`                 DATE         NOT NULL,
    `duration`              INT          NOT NULL,
    `auth_key`              VARCHAR(255),
    PRIMARY KEY (`id`));

