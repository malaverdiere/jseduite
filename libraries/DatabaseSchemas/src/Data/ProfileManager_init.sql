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
INSERT INTO `sources`    VALUES ('weather','MétÃé');
INSERT INTO `parameters` VALUES (NULL, 'weather', 'cityCode', '??', 'Code de la ville');
INSERT INTO `sources`    VALUES ('tv_shows','Programme Télé');
INSERT INTO `parameters` VALUES (NULL, 'tv_shows', 'provider', '', 'Fournisseur');
INSERT INTO `parameters` VALUES (NULL, 'tv_shows', 'period', '0', 'Delai de validité');
INSERT INTO `sources`    VALUES ('feeds','Lecteur de Flux RSS');
INSERT INTO `parameters` VALUES (NULL, 'feeds', 'feedName', '??', 'Nom du flux');
INSERT INTO `parameters` VALUES (NULL, 'feeds', 'cacheValidity', '??', 'Validité du cache');
INSERT INTO `sources`    VALUES ('breaking_news','Nouvelles en continu');
INSERT INTO `sources`    VALUES ('absences_profs','Absence des enseignants');
INSERT INTO `sources`    VALUES ('stud_summon','Convocation des étudiants');
INSERT INTO `parameters` VALUES (NULL, 'stud_summon', 'promoId', 'all', 'Promotion');
INSERT INTO `sources`    VALUES ('menu_source','Restaurant Universitaire');
INSERT INTO `sources`    VALUES ('calendar','Lecteur de calendrier iCal');
INSERT INTO `parameters` VALUES (NULL, 'calendar', 'url', '??', 'URL');
INSERT INTO `sources`    VALUES ('apal','Liaison APAL');
INSERT INTO `parameters` VALUES (NULL, 'apal', 'treshold', '5', 'Seuil');
INSERT INTO `sources`    VALUES ('hyperloc','Localisation des Enseignants');
INSERT INTO `parameters` VALUES (NULL, 'hyperloc', 'building', '??', 'Bâtiment');
INSERT INTO `sources`    VALUES ('timetable','Emploi du Temps');
INSERT INTO `parameters` VALUES (NULL, 'timetable', 'promo', '??', 'Code Promotion');