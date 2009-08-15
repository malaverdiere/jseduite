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
INSERT INTO `parameters` VALUES (NULL, 'internal_news', 'target', 'all', 'Cible');
INSERT INTO `sources`    VALUES ('twitter','Twitter');
INSERT INTO `parameters` VALUES (NULL, 'twitter', 'username', '???', 'Utilisateur');
INSERT INTO `parameters` VALUES (NULL, 'twitter', 'password', '???', 'Mot de passe');
INSERT INTO `parameters` VALUES (NULL, 'twitter', 'treshold', '10', 'Seuil');
INSERT INTO `sources`    VALUES ('picture_albums','Albums Photos');
INSERT INTO `parameters` VALUES (NULL, 'picture_albums', 'treshold', '10', 'Seuil');
INSERT INTO `sources`    VALUES ('image_scraper','Folksonomie Image');
INSERT INTO `parameters` VALUES (NULL, 'image_scraper', 'tags', '??', 'Tag');
INSERT INTO `parameters` VALUES (NULL, 'image_scraper', 'treshold', '10', 'Seuil');
INSERT INTO `sources`    VALUES ('weather','Météo');
INSERT INTO `parameters` VALUES (NULL, 'weather', 'cityCode', '??', 'Code de la ville');
INSERT INTO `sources`    VALUES ('tv_shows','Programme Télé');
INSERT INTO `sources`    VALUES ('feeds','Lecteur de Flux RSS');
INSERT INTO `parameters` VALUES (NULL, 'feeds', 'feedName', '??', 'Nom du flux');
INSERT INTO `parameters` VALUES (NULL, 'feeds', 'cacheValidity', '??', 'Validité du cache');
INSERT INTO `sources`    VALUES ('breaking_news','Nouvelles en continu');
INSERT INTO `sources`    VALUES ('bus','Horaires des Bus');
INSERT INTO `parameters` VALUES (NULL, 'bus', 'lineId', '??', 'Ligne');
INSERT INTO `sources`    VALUES ('icalendar','Lecteur de Calendrier');
INSERT INTO `parameters` VALUES (NULL, 'icalendar', 'url', '??', 'URL');
INSERT INTO `sources`    VALUES ('absences_profs','Absence des enseignants');
INSERT INTO `sources`    VALUES ('stud_summon','Convocation des étudiants');
INSERT INTO `parameters` VALUES (NULL, 'stud_summon', 'promoId', 'all', 'Promotion');
INSERT INTO `sources`    VALUES ('absences_stud','Absences des étudiants');
INSERT INTO `parameters` VALUES (NULL, 'absences_stud', 'promoId', 'all', 'Promotion');
INSERT INTO `sources`    VALUES ('hyperbousin','Hyperbousin Wrapper');
INSERT INTO `parameters` VALUES (NULL, 'hyperbousin', 'promoId', 'all', 'Promotion');


-- Devices: hall_templiers
--INSERT INTO `devices` VALUES ('hall_templiers','Plasma du Hall des Templiers');
--INSERT INTO `device_subscription` VALUES ('hall_templiers', 'internal_news', 2);
--INSERT INTO `device_parametrization` VALUES ('hall_templiers',1,1,'profs');
--INSERT INTO `device_parametrization` VALUES ('hall_templiers',1,2,'students');
--INSERT INTO `device_subscription` VALUES ('hall_templiers', 'twitter', 1);
--INSERT INTO `device_parametrization` VALUES ('hall_templiers',2,1,'polytechnsa');
--INSERT INTO `device_parametrization` VALUES ('hall_templiers',3,1,'???');

-- Devices: prof_templiers
--INSERT INTO `devices` VALUES ('profs_templiers','Ecran du 4eme etage Templiers');
--INSERT INTO `device_subscription` VALUES ('profs_templiers', 'internal_news', 1);
--INSERT INTO `device_parametrization` VALUES ('profs_templiers',1,1,'profs');
