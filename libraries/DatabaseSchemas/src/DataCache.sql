
DROP TABLE IF EXISTS `cache`;

CREATE TABLE `cache` (
  `key`    varchar(255) NOT NULL,
  `stamp` timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP
                                 ON UPDATE CURRENT_TIMESTAMP,
  `value`     longtext  NOT NULL,
  PRIMARY KEY  (`key`));
