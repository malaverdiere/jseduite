INSERT INTO `internal_news_target` VALUES (NULL, "polytech");
INSERT INTO `internal_news_target` VALUES (NULL, "templiers");
INSERT INTO `internal_news_target` VALUES (NULL, "lucioles");
INSERT INTO `internal_news_target` VALUES (NULL, "profs");
INSERT INTO `internal_news_target` VALUES (NULL, "etudiants");

INSERT INTO `internal_news` VALUES (NULL,1,"Sébastien Mosser",NOW(),
    NOW() + INTERVAL 100 DAY, "Système d'Information",
    "Le nouveau système d'info de Polytech est (enfin) sur pied !");

INSERT INTO `internal_news` VALUES (NULL,2,"Jeanine Gennari",NOW(),
    NOW() + INTERVAL 100 DAY, "Accès au parking des templiers",
    "Il est rappelé aux étudiants de se garer sur les parkings de l'université en bas de la copropriété");
