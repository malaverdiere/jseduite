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

-- some tuples

INSERT INTO `feed_class` VALUES (1,'weather');
INSERT INTO `feed_class` VALUES (2,'news');
INSERT INTO `feed_class` VALUES (3,'tv');


INSERT INTO `feed_registry` VALUES ('www.antibes.maville.com',1, 'Meteo_Antibes',
    '/flux/rss/meteo.php?xtor=RSS-18&code=at');
INSERT INTO `cache` VALUES ('Meteo_Antibes',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.antibes.maville.com',2,'Antibes_tout',
    '/flux/rss/actu.php?xtor=RSS-18&code=at');
INSERT INTO `cache` VALUES ('Antibes_tout',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.antibes.maville.com',2,'Antibes_locale',
    '/flux/rss/actu.php?xtor=RSS-18&c=loc&code=at');
INSERT INTO `cache` VALUES ('Antibes_locale',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.antibes.maville.com',2,
    'Antibes_departementale', '/flux/rss/actu.php?xtor=RSS-18&c=dep&code=at');
INSERT INTO `cache` VALUES ('Antibes_departementale',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.antibes.maville.com',2,'Antibes_sports',
    '/flux/rss/actu.php?xtor=RSS-18&c=spo&code=at');
INSERT INTO `cache` VALUES ('Antibes_sports',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.antibes.maville.com',2,
    'Antibes_derniere_minute', '/flux/rss/dma.php?xtor=RSS-18&code=at');
INSERT INTO `cache` VALUES ('Antibes_derniere_minute',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('programme-tv.krinein.com',3,'tv-krinien.com',
    '/programmes.rss');
INSERT INTO `cache` VALUES ('tv-krinien.com',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.my-meteo.fr',1,'Meteo_Nice',
    '/meteo+rss+nice.html');
INSERT INTO `cache` VALUES ('Meteo_Nice',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_une',
    '/TV5Site/rss/actualites.php?rub=1');
INSERT INTO `cache` VALUES ('TV5_une',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_monde',
    '/TV5Site/rss/actualites.php?rub=2');
INSERT INTO `cache` VALUES ('TV5_monde',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_france',
    '/TV5Site/rss/actualites.php?rub=3');
INSERT INTO `cache` VALUES ('TV5_france',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_francophone',
    '/TV5Site/rss/actualites.php?rub=4');
INSERT INTO `cache` VALUES ('TV5_francophone',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_litterature',
    '/TV5Site/rss/actualites.php?rub=5');
INSERT INTO `cache` VALUES ('TV5_litterature',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_afrique',
    '/TV5Site/rss/actualites.php?rub=6');
INSERT INTO `cache` VALUES ('TV5_afrique',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_eco_finance',
    '/TV5Site/rss/actualites.php?rub=7');
INSERT INTO `cache` VALUES ('TV5_eco_finance',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_politique',
    '/TV5Site/rss/actualites.php?rub=8');
INSERT INTO `cache` VALUES ('TV5_politique',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_sport',
    '/TV5Site/rss/actualites.php?rub=9');
INSERT INTO `cache` VALUES ('TV5_sport',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_culture',
    '/TV5Site/rss/actualites.php?rub=10');
INSERT INTO `cache` VALUES ('TV5_culture',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_le_dossier',
    '/TV5Site/rss/actualites.php?rub=11');
INSERT INTO `cache` VALUES ('TV5_le_dossier',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_technos',
    '/TV5Site/rss/actualites.php?rub=12');
INSERT INTO `cache` VALUES ('TV5_technos',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_media',
    '/TV5Site/rss/actualites.php?rub=13');
INSERT INTO `cache` VALUES ('TV5_media',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_sciences',
    '/TV5Site/rss/actualites.php?rub=14');
INSERT INTO `cache` VALUES ('TV5_sciences',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_medecine',
    '/TV5Site/rss/actualites.php?rub=15');
INSERT INTO `cache` VALUES ('TV5_medecine',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_les_gens',
    '/TV5Site/rss/actualites.php?rub=16');
INSERT INTO `cache` VALUES ('TV5_les_gens',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_insolite',
    '/TV5Site/rss/actualites.php?rub=17');
INSERT INTO `cache` VALUES ('TV5_insolite',CURRENT_TIMESTAMP(), '');

INSERT INTO `feed_registry` VALUES ('www.tv5.org',2,'TV5_animations',
    '/TV5Site/rss/actualites.php?rub=18');
INSERT INTO `cache` VALUES ('TV5_animations',CURRENT_TIMESTAMP(), '');
