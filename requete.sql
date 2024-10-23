Best  score 10 globaux 

CREATE VIEW ten_best_score  AS SELECT s.total_points, j.pseudo, s.niveau_max FROM score s
natural JOIN partie p
NATURAL join joueurs j
order by s.total_points DESC 
LIMIT 10;

/* Best score par type de partie */

CREATE VIEW ten_best_score  AS SELECT s.total_points, j.pseudo, s.niveau_max FROM score s
natural JOIN partie p
NATURAL join joueurs j
WHERE p.type = 2
order by s.total_points DESC 
LIMIT 10;

/* Personnal Best par jioueur (visible que par utilisateur ) */

CREATE VIEW personnal_best  AS SELECT s.total_points, j.pseudo, s.niveau_max FROM score s
natural JOIN partie p
NATURAL join joueurs j
WHERE id_joueur= idjoueur
order by s.total_points DESC 
LIMIT 10;

/*Ici faudra que le id joueur  soit celui du joueur qui requete*/

CREATE VIEW info_joueur  AS SELECT * FROM joueur
WHERE id_joueur= id_joueur
Ici vue ou le joueur a acces a que ses infos

/*compte admin*/
  
GRANT ALL PRIVILEGES ON tetriste* TO 'admin'@'localhost' IDENTIFIED BY 'password';
FLUSH PRIVILEGES;

/*Top ten par mois*/ 

SELECT s.total_points, j.pseudo, s.niveau_max FROM score s
natural JOIN partie p
NATURAL join joueurs j
WHERE MONTH(p.date_partie) = MONTH(CURRENT_DATE())
AND YEAR(p.date_partie) = YEAR(CURRENT_DATE())
order by s.total_points DESC 
LIMIT 10;

Total temps joué par joueur 

SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(p.temps_partie))) AS temps_total
FROM partie p
NATURAL JOIN joueurs j
WHERE p.id_joueur = 2;

Total temps passé depusi inscription 

SELECT TIMESTAMPDIFF(YEAR, date_inscription, CURDATE()) AS annees,
    TIMESTAMPDIFF(MONTH, date_inscription, CURDATE()) % 12 AS mois,
    FLOOR(TIMESTAMPDIFF(DAY, date_inscription, CURDATE()) % 365.25) AS jours,
    pseudo
FROM joueurs;

Savoir combien de connexion ont été effectué depuis le même compte 

SELECT count(date_connexion), id_joueur from connexion
WHERE status = 0