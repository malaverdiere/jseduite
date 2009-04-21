DROP TABLE IF EXISTS `promos`;
CREATE TABLE `promos` (
`promos_id` INT(11) NOT NULL AUTO_INCREMENT ,
`code` VARCHAR(50) NOT NULL ,
`name` VARCHAR(255) NOT NULL ,
PRIMARY KEY (`promos_id`)
);


DROP TABLE IF EXISTS `break_time`;
CREATE TABLE `break_time` (
`break_id` INT(11) NOT NULL AUTO_INCREMENT ,
`promo`    INT(11) NOT NULL REFERENCES `promos`,
`start` VARCHAR(20) NOT NULL ,
`end` VARCHAR(20) NOT NULL ,
`kind` VARCHAR(10) NOT NULL ,
PRIMARY KEY (`break_id`)
);


DROP TABLE IF EXISTS `break_time_days_lnk`;
CREATE TABLE `break_time_days_lnk` (
 `break_id` INT(11) NOT NULL REFERENCES `break_time`,
 `day` VARCHAR(10) NOT NULL,
  PRIMARY KEY(`break_id`,`day`)
  );

  

DROP VIEW IF EXISTS `break_time_today`;
 CREATE VIEW `break_time_today` AS
 SELECT
`promos`.`code` AS `p_code`, `promos`.`name` AS `promo`, `break_time`.`start` AS  `start_time`, `break_time`.`end` AS `end_time`,`break_time`.`kind` AS `break_type`,`day`
FROM
 `break_time`, `promos`,`break_time_days_lnk`
 WHERE
 `break_time_days_lnk`.`day`= dayname(curdate()) AND `break_time_days_lnk`.`break_id`=`break_time`.`break_id` AND
`promos`.`promos_id`=`break_time`.`promo`;


 INSERT INTO `promos` VALUES( NULL,"cip1","Cycle Prpa 1A");
 INSERT INTO `promos` VALUES(NULL,"cip2","Cycle Prpa 2A");
 INSERT INTO `promos` VALUES(NULL,"si3","Sciences Info 3A");
 INSERT INTO `promos` VALUES(NULL,"si4","Sciences Info 4A");
 INSERT INTO `promos` VALUES(NULL,"si5","Sciences Info 5A");
 INSERT INTO `promos` VALUES(NULL,"elec3","Electronique 3A");

 INSERT INTO `promos` VALUES(NULL,"elec4","Electronique 4A");
 INSERT INTO `promos` VALUES(NULL,"elec5","Electronique 5A");
 INSERT INTO `promos` VALUES(NULL,"mam3","Maths Appli. 3A");
 INSERT INTO `promos` VALUES(NULL,"mam4","Maths Appli. 4A");
 INSERT INTO `promos` VALUES(NULL,"mam5","Maths Appli. 5A");
 INSERT INTO `promos` VALUES(NULL,"bio3","Biologie 3A");
 INSERT INTO `promos` VALUES(NULL,"bio4","Biologie 4A");

 INSERT INTO `promos` VALUES(NULL,"bio5","Biologie 5A");
 INSERT INTO `promos` VALUES(NULL,"hydro3","Hydro 3A");
 INSERT INTO `promos` VALUES(NULL,"hydro4","Hydro 4A");
 INSERT INTO `promos` VALUES(NULL,"hydro5","Hydro 5A");

 INSERT INTO `break_time` VALUES (NULL,1,"8:45","8:55","short");
INSERT INTO `break_time` VALUES (NULL,2,"9:40","10:00","short");
INSERT INTO `break_time` VALUES (NULL,3,"10:45","10:55","short");
INSERT INTO `break_time` VALUES (NULL,1,"11:40","2:00","lunch");
INSERT INTO `break_time` VALUES (NULL,2,"14:45","14:55","short");
INSERT INTO `break_time` VALUES (NULL,5,"15:40","16:00","short");
INSERT INTO `break_time` VALUES (NULL,3,"16:45","16:55","short");

INSERT INTO `break_time_days_lnk` VALUES (1,"Monday");
INSERT INTO `break_time_days_lnk` VALUES (1,"Tuesday");
INSERT INTO `break_time_days_lnk` VALUES (1,"Wednesday");
INSERT INTO `break_time_days_lnk` VALUES (1,"Thursday");
INSERT INTO `break_time_days_lnk` VALUES (1,"Friday");
INSERT INTO `break_time_days_lnk` VALUES (1,"Saturday");
INSERT INTO `break_time_days_lnk` VALUES (1,"Sunday"); 