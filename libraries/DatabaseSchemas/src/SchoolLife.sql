DROP TABLE IF EXISTS `summon_levels`;
CREATE TABLE `summon_levels` (
    `id`    INT(11)         NOT NULL AUTO_INCREMENT,
    `name`  VARCHAR(255)    NOT NULL,
     PRIMARY KEY  (`id`));

DROP TABLE IF EXISTS `promos`;
CREATE TABLE `promos` (
    `id`    INT(11)         NOT NULL AUTO_INCREMENT,
    `code`  VARCHAR(50)     NOT NULL,
    `name`  VARCHAR(255)    NOT NULL,
     PRIMARY KEY  (`id`));

DROP TABLE IF EXISTS `summonings`;
CREATE TABLE `summonings` (
    `id`        INT(11)         NOT NULL AUTO_INCREMENT,
    `student`   VARCHAR(255)    NOT NULL,
    `promo`     INT(11)         NOT NULL REFERENCES `promos`,
    `owner`     VARCHAR(50)     NOT NULL,
    `stamp`     TIMESTAMP       NOT NULL DEFAULT NOW(),
    `level`     INT(11)         NOT NULL REFERENCES `summon_levels`,
    `valid`     BOOLEAN         NOT NULL DEFAULT TRUE,
    PRIMARY KEY  (`id`));

DROP VIEW IF EXISTS `valid_summonings`;
CREATE VIEW `valid_summonings` AS
    SELECT
        `summonings`.`student`, `summonings`.`owner`, `promos`.`code` AS `p_code`,
        `promos`.`name` AS `promo`, `summonings`.`stamp` AS `date`,
        `summon_levels`.`name` AS `level`
    FROM
        `summonings`, `summon_levels`, `promos`
    WHERE
        `summonings`.`level` = `summon_levels`.`id` AND `summonings`.`valid`
        AND `summonings`.`promo` = `promos`.`id`;

DROP TABLE IF EXISTS `teacher_absences`;
CREATE TABLE `teacher_absences` (
    `id`        INT(11)         NOT NULL AUTO_INCREMENT,
    `teacher`   VARCHAR(255)    NOT NULL,
    `reason`    VARCHAR(255)    NULL,
    `from`      TIMESTAMP       NOT NULL DEFAULT NOW(),
    `until`     TIMESTAMP       NOT NULL,
    PRIMARY KEY(`id`));

INSERT INTO `teacher_absences` VALUES (NULL, "Sébastien Mosser","Conférence",
    NOW(), NOW() + INTERVAL 10 DAY);

INSERT INTO `summon_levels` VALUES (NULL,"Immédiatement");
INSERT INTO `summon_levels` VALUES (NULL,"Urgent");
INSERT INTO `summon_levels` VALUES (NULL,"Régularisation");
INSERT INTO `summon_levels` VALUES (NULL,"Information");

INSERT INTO `promos` VALUES(NULL,"cip1","Cycle Prépa 1A");
INSERT INTO `promos` VALUES(NULL,"cip2","Cycle Prépa 2A");
INSERT INTO `promos` VALUES(NULL,"si3","Sciences Info 3A");
INSERT INTO `promos` VALUES(NULL,"si4","Sciences Info 4A");
INSERT INTO `promos` VALUES(NULL,"si5","Sciences Info 5A");
INSERT INTO `promos` VALUES(NULL,"elec3","Electronique 3A");
INSERT INTO `promos` VALUES(NULL,"elec4","Electronique 4A");
INSERT INTO `promos` VALUES(NULL,"elec5","Electronique 5A");
INSERT INTO `promos` VALUES(NULL,"mam3","Maths Appli. 3A");
INSERT INTO `promos` VALUES(NULL,"mam4","Maths Appli. 4A");
INSERT INTO `promos` VALUES(NULL,"mam5","Maths Appli. 5A");
INSERT INTO `promos` VALUES(NULL,"bio3","Biologie 3A");
INSERT INTO `promos` VALUES(NULL,"bio4","Biologie 4A");
INSERT INTO `promos` VALUES(NULL,"bio5","Biologie 5A");
INSERT INTO `promos` VALUES(NULL,"hydro3","Hydro 3A");
INSERT INTO `promos` VALUES(NULL,"hydro4","Hydro 4A");
INSERT INTO `promos` VALUES(NULL,"hydro5","Hydro 5A");

INSERT INTO `summonings` VALUES (NULL,"XXX xxx",2,"Sébastien Mosser",
    NULL, 3, TRUE);

