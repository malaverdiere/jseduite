DROP TABLE IF EXISTS `breaking_news`;
CREATE TABLE `breaking_news` (
  `id`     INT(11)          NOT NULL AUTO_INCREMENT,
  `author`  VARCHAR(50)     NOT NULL,
  `stamp`   TIMESTAMP       NOT NULL,
  `content` VARCHAR(255)    NOT NULL,
  PRIMARY KEY  (`id`));


