DROP TABLE IF EXISTS `alarms`;
CREATE TABLE `alarms` (
`alarm_id` INT(11) NOT NULL AUTO_INCREMENT ,
`binding`  INT(11) NOT NULL REFERENCES `break_time`,
`content` VARCHAR(255) NOT NULL ,
PRIMARY KEY (`alarm_id`)
);


INSERT INTO `alarms` VALUES (NULL,1,"you have to change the classrome 222 in the next class ");
INSERT INTO `alarms` VALUES (NULL,2,"Because your teacher is ill, you will be teached by professor qin"); 
INSERT INTO `alarms` VALUES (NULL,3,"Because the H1N1 flu, you are free"); 
INSERT INTO `alarms` VALUES (NULL,4,"free");

