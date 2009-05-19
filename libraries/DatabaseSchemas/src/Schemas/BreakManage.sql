
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




