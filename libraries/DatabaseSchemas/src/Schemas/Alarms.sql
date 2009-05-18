
DROP TABLE IF EXISTS `alarms`;
CREATE TABLE `alarms` (
`alarm_id` INT(11) NOT NULL AUTO_INCREMENT,
`content`  VARCHAR(255) NOT NULL REFERENCES `alarms`,
PRIMARY KEY (`alarm_id`)
);


DROP TABLE IF EXISTS `break_alarm_lnk`;
CREATE TABLE `break_alarm_lnk` (
`break` INT(11) NOT NULL REFERENCES `break_time`,
`binding_alarm`  INT(11) NOT NULL REFERENCES `alarms`,
PRIMARY KEY (`break`,`binding_alarm`)
);
