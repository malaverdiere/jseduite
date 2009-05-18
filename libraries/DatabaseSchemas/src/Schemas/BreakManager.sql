DROP TABLE IF EXISTS `promos`;
CREATE TABLE `promos` (
`promos_id` INT(11) NOT NULL AUTO_INCREMENT ,
`code` VARCHAR(50) NOT NULL ,
`name` VARCHAR(255) NOT NULL ,
PRIMARY KEY (`promos_id`)
);

DROP TABLE IF EXISTS `break_time_promos_lnk`;
CREATE TABLE `break_time_promos_lnk` (
`break` INT(11) NOT NULL REFERENCES `break_time`,
`binding_alarm` INT(11) NOT NULL REFERENCES `promos`,
PRIMARY KEY (`break`,`binding_alarm`)
);

DROP TABLE IF EXISTS `break_time`;
CREATE TABLE `break_time` (
`break_id` INT(11) NOT NULL AUTO_INCREMENT ,
`start` VARCHAR(20) NOT NULL ,
`end` VARCHAR(20) NOT NULL ,
`kind` VARCHAR(10) NOT NULL ,
`day` VARCHAR(10) NOT NULL,
PRIMARY KEY (`break_id`)
);


DROP VIEW IF EXISTS `break_time_today`;
 CREATE VIEW `break_time_today` AS
 SELECT
`break_time`.`break_id` AS `break_id`,`promos`.`name` AS `promo`,`break_time`.`start` AS  `start_time`, `break_time`.`end` AS `end_time`,`break_time`.`kind` AS `break_type`,`break_time`.`day`
FROM
 `break_time`, `promos`
 WHERE
 `break_time`.`day`= dayname(curdate()) AND `promos`.`promos_id`=`break_time`.`promo`;



