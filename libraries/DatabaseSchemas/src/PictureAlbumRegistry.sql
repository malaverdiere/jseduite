DROP TABLE IF EXISTS `picture_album_registry`;

CREATE TABLE `picture_album_registry` (
    `id`                    INTEGER      NOT NULL AUTO_INCREMENT,
    `album_name`            VARCHAR(255) NOT NULL,
    `repository`            VARCHAR(10)  NOT NULL,
    `repository_user_id`    VARCHAR(255) NOT NULL,
    `repository_album_name` VARCHAR(255) NOT NULL,
    `start`                 DATE         NOT NULL,
    `duration`              INT          NOT NULL,
    PRIMARY KEY (`id`));

INSERT INTO `picture_album_registry` VALUES (1, 'Pot de départ PB', 'picasa',
    'polytech.nsa', 'PotRetraitePB','2009-02-10',100);

-- Album photo nuit de l'info
INSERT INTO `picture_album_registry` VALUES (2, "Nuit de l'Info 2008", 'flickr',
    '28557691@N07', '72157614715595501','2009-02-10',100);


