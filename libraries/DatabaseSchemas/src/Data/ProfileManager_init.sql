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

-- Sources:
INSERT INTO `sources`    VALUES ('internal_news','News Interne');
INSERT INTO `parameters` VALUES (NULL, 'internal_news', 'target', 'all');
INSERT INTO `sources`    VALUES ('twitter','Twitter');
INSERT INTO `parameters` VALUES (NULL, 'twitter', 'username', '???');
INSERT INTO `parameters` VALUES (NULL, 'twitter', 'password', '???');

-- Devices: hall_templiers
INSERT INTO `devices` VALUES ('hall_templiers','Plasma du Hall des Templiers');
INSERT INTO `device_subscription` VALUES ('hall_templiers', 'internal_news', 1);
INSERT INTO `device_subscription` VALUES ('hall_templiers', 'twitter', 1);
INSERT INTO `device_parametrization` VALUES ('hall_templiers',2,1,'polytechnsa');
INSERT INTO `device_parametrization` VALUES ('hall_templiers',3,1,'???');

-- Devices: prof_templiers
INSERT INTO `devices` VALUES ('profs_templiers','Ecran du 4eme etage Templiers');
INSERT INTO `device_subscription` VALUES ('profs_templiers', 'internal_news', 1);
INSERT INTO `device_parametrization` VALUES ('profs_templiers',1,1,'profs');
