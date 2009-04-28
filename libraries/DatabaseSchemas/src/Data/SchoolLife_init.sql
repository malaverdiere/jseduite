INSERT INTO `teacher_absences` VALUES (NULL, "Sébastien Mosser","Conférence",
    NOW(), NOW() + INTERVAL 10 DAY);

INSERT INTO `summon_levels` VALUES (NULL,"Immédiatement");
INSERT INTO `summon_levels` VALUES (NULL,"Urgent");
INSERT INTO `summon_levels` VALUES (NULL,"Régularisation");
INSERT INTO `summon_levels` VALUES (NULL,"Information");

INSERT INTO `promos` VALUES(NULL,"cip1","Cycle Prépa 1A");
INSERT INTO `promos` VALUES(NULL,"cip2","Cycle Prépa 2A");
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

INSERT INTO `summonings` VALUES (NULL,"XXX xxx",2,"Sébastien Mosser",
    NULL, 3, TRUE);
