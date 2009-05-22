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

-- NEW DATA NEEDDED ...
 INSERT INTO `break_time` VALUES (NULL,"8:45","8:55","short","Monday");
 INSERT INTO `break_time` VALUES (NULL,"9:40","10:00","short","Monday");
 INSERT INTO `break_time` VALUES (NULL,"10:45","10:55","short","Monday");
 INSERT INTO `break_time` VALUES (NULL,"11:40","2:00","lunch","Monday");
 INSERT INTO `break_time` VALUES (NULL,"14:45","14:55","short","Monday");
 INSERT INTO `break_time` VALUES (NULL,"15:40","16:00","short","Monday");
 INSERT INTO `break_time` VALUES (NULL,"16:45","16:55","short","Monday");
 INSERT INTO `break_time` VALUES (NULL,"8:45","8:55","short","Friday");
 INSERT INTO `break_time` VALUES (NULL,"9:40","10:00","short","Friday");
 INSERT INTO `break_time` VALUES (NULL,"10:45","10:55","short","Friday");
 INSERT INTO `break_time` VALUES (NULL,"11:40","2:00","lunch","Friday");


 INSERT INTO `break_time_promos_lnk` values(1,1);
 INSERT INTO `break_time_promos_lnk` values(1,2);
 INSERT INTO `break_time_promos_lnk` values(2,2);
 INSERT INTO `break_time_promos_lnk` values(2,3);
 INSERT INTO `break_time_promos_lnk` values(3,4);