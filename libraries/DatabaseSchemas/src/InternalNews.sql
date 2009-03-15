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



INSERT INTO `internal_news_target` VALUES (NULL, "polytech");
INSERT INTO `internal_news_target` VALUES (NULL, "templiers");
INSERT INTO `internal_news_target` VALUES (NULL, "lucioles");
INSERT INTO `internal_news_target` VALUES (NULL, "profs");
INSERT INTO `internal_news_target` VALUES (NULL, "etudiants");

INSERT INTO `internal_news` VALUES (NULL,1,"Sébastien Mosser",NOW(),
    NOW() + INTERVAL 1 DAY, "Système d'Information",
    "Le nouveau système d'info de Polytech est (enfin) sur pied !");

INSERT INTO `internal_news` VALUES (NULL,2,"Jeanine Gennari",NOW(),
    NOW() + INTERVAL 10 DAY, "Accès au parking des templiers", 
    "Il est rappelé aux étudiants de se garer sur les parkings de l'université en bas de la copropriété");
