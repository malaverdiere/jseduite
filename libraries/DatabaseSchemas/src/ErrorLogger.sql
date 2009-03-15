
DROP TABLE IF EXISTS `errors`;
CREATE TABLE `errors` (
    `id`        INT(11)         NOT NULL AUTO_INCREMENT,
    `stamp`     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
                                ON UPDATE CURRENT_TIMESTAMP,
    `trigger`   VARCHAR(255)    NOT NULL,
    `level`     VARCHAR(50)     NOT NULL,
    `message`   LONGTEXT        NOT NULL,
    PRIMARY KEY  (`id`));


-- INSERT INTO `errors` VALUES (NULL,NOW(),'bla','bla','bla')