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
INSERT INTO `sources`    VALUES ('weather', 'Météo');
INSERT INTO `parameters` VALUES (NULL, 'weather', 'cityCode', '??', 'Code de la ville');
INSERT INTO `sources` VALUES ('tv_shows','Programme TV');
INSERT INTO `parameters` VALUES (NULL, 'tv_shows', 'provider', '', 'Fournisseur');
INSERT INTO `parameters` VALUES (NULL, 'tv_shows', 'period', '0', 'Delai de Validite');
INSERT INTO `sources`    VALUES ('feed_reader','Lecteur de Flux RSS');
INSERT INTO `parameters` VALUES (NULL, 'feed_reader', 'tag', '??', 'Nom du flux');
INSERT INTO `parameters` VALUES (NULL, 'feed_reader', 'delta', '??', 'Validite du cache');
INSERT INTO `parameters` VALUES (NULL, 'feed_reader', 'treshold', '??', 'Seuil');
INSERT INTO `sources`    VALUES ('breaking_news','Nouvelles en continu');
INSERT INTO `sources`    VALUES ('absences_profs','Absence du Personnel');
INSERT INTO `sources`    VALUES ('stud_summon','Convocation des Etudiants');
INSERT INTO `parameters` VALUES (NULL, 'stud_summon', 'promoId', 'all', 'Promotion');
INSERT INTO `sources`    VALUES ('menu_source','Restaurant Universitaire');
INSERT INTO `sources`    VALUES ('calendar','Lecteur de calendrier iCal');
INSERT INTO `parameters` VALUES (NULL, 'calendar', 'url', '??', 'URL');
INSERT INTO `sources`    VALUES ('apal','Liaison APAL');
INSERT INTO `parameters` VALUES (NULL, 'apal', 'treshold', '5', 'Seuil');
INSERT INTO `sources`    VALUES ('hyperloc','Localisation des Enseignants');
INSERT INTO `parameters` VALUES (NULL, 'hyperloc', 'building', '??', 'Batiment');
INSERT INTO `sources`    VALUES ('timetable','Emploi du Temps');
INSERT INTO `parameters` VALUES (NULL, 'timetable', 'promo', '??', 'Code Promotion');
INSERT INTO `sources`    VALUES ('ephemeride','Ephemeride');
INSERT INTO `sources`    VALUES ('menu','Menu');
