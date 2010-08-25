CREATE TABLE IF NOT EXISTS `pictograms` (
  `id` int(11) NOT NULL auto_increment,
  `start` datetime NOT NULL default '0000-00-00 00:00:00',
  `end` datetime NOT NULL default '0000-00-00 00:00:00',
  `picture1` varchar(255) NOT NULL,
  `picture2` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
);
