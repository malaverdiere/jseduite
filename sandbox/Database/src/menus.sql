DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `kind`    VARCHAR(50)  NOT NULL,
  `name`    VARCHAR(255) NOT NULL,
  PRIMARY KEY(`name`));

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`(
  `date`    DATE         NOT NULL,
  `course`  VARCHAR(255) NOT NULL REFERENCES `course`,
  PRIMARY KEY(`date`,`course`));
