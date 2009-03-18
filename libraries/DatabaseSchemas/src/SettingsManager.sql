-- author: <mosser@polytech.unice.fr>

-- User's accounts
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
    `login`     VARCHAR(255)    NOT NULL,
    `password`  VARCHAR(255)    NOT NULL,
    `name`      VARCHAR(255)    NOT NULL,
    PRIMARY KEY(`login`));

INSERT INTO `accounts` VALUES ('hall','llah','Plasma Hall');

-- Avalaible sources

DROP TABLE IF EXISTS `sources` ;
CREATE TABLE `sources` (
    `id`        INTEGER         NOT NULL AUTO_INCREMENT,
    `service`   VARCHAR(255)    NOT NULL,
    `operation` VARCHAR(255)    NOT NULL,
    PRIMARY KEY(`id`),
    UNIQUE (`service`,`operation`));

INSERT INTO `sources` VALUES (1,'CachedFeedReader','read');
INSERT INTO `sources` VALUES (2,'ICalReader','getToday');
INSERT INTO `sources` VALUES (3,'ICalReader','getNow');


-- Users preferences : binding between acount and sources
DROP TABLE IF EXISTS `preferences` ;
CREATE TABLE `preferences`(
    `id`        INTEGER         NOT NULL AUTO_INCREMENT,
    `login`     VARCHAR(255)    NOT NULL REFERENCES `accounts`,
    `source`    INTEGER         NOT NULL REFERENCES `sources`,
    PRIMARY KEY(`id`));

INSERT INTO `preferences`VALUES (1,'hall',1);
INSERT INTO `preferences`VALUES (2,'hall',2);

-- Users defined messages(set of parameters)
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `preference` INTEGER NOT NULL REFERENCES `profiles`,
    `callId`  INTEGER NOT NULL,
    `parameter` VARCHAR(255) NOT NULL,
    `value` VARCHAR(255),
    PRIMARY KEY (`id`));

INSERT INTO `messages` VALUES (1,1,1,'validity','30');
INSERT INTO `messages` VALUES (2,1,1,'name','TV5_une');
INSERT INTO `messages` VALUES (3,1,2,'validity','30');
INSERT INTO `messages` VALUES (4,1,2,'name','Antibes_tout');
INSERT INTO `messages`  VALUES (5,2,1,'calendar',
  'http://www.google.com/calendar/ical/polytech.sophia.jseduite%40gmail.com/public/basic.ics');

-- Settings global view, to make the all stuff more readable
DROP VIEW IF EXISTS `settings`;
CREATE VIEW `settings` AS
    SELECT  `accounts`.`login`      AS `login`,
            `sources`.`service`     AS `service`,
            `sources`.`operation`   AS `operation`,
            `messages`.`callId`     AS `callId`,
            `messages`.`parameter`  AS `parameter`,
            `messages`.`value`      AS `value`
    FROM    `accounts`, `sources`, `preferences`, `messages`
    WHERE   `accounts`.`login`      = `preferences`.`login` AND
            `sources`.`id`          = `preferences`.`source` AND
            `messages`.`preference` = `preferences`.`id`
    ORDER BY `service` DESC, `operation` DESC, `callId` ASC;

SELECT * FROM `settings`;
