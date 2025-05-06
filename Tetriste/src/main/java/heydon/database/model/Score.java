package heydon.database.model;

/**
 * Classe représentant un score de partie dans la base de données.
 */
/**
 * Classe représentant un score de partie dans la base de données.
 */
public class Score {
    // J'ai ajouté des commentaires pour les toutes les lignes afin de facilité la lecture
    
    
    // Identifiant unique du score (clé primaire en base de données)
    private long idScore;
    // Nombre total de points obtenus lors de la partie
    private long totalPoints;
    // Niveau maximum atteint lors de la partie
    private long niveauMax;
    // Identifiant de la partie associée (clé étrangère)
    private long idPartie;
    // Référence à l'objet Partie associé (relation avec la classe Partie)
    private Partie partie;

    // Constructeur par défaut (obligatoire pour certains frameworks ou outils)
    public Score() {}

    /**
     * Constructeur pour créer un score avec les points, le niveau max et l'id de la partie.
     * @param totalPoints nombre total de points
     * @param niveauMax niveau maximum atteint
     * @param idPartie identifiant de la partie associée
     */
    public Score(long totalPoints, long niveauMax, long idPartie) {
        this.totalPoints = totalPoints;
        this.niveauMax = niveauMax;
        this.idPartie = idPartie;
    }

    // ------------------- Getters et Setters -------------------

    // Retourne l'identifiant du score
    public long getIdScore() {
        return idScore;
    }

    // Définit l'identifiant du score
    public void setIdScore(long idScore) {
        this.idScore = idScore;
    }

    // Retourne le total de points
    public long getTotalPoints() {
        return totalPoints;
    }

    // Définit le total de points
    public void setTotalPoints(long totalPoints) {
        this.totalPoints = totalPoints;
    }

    // Retourne le niveau maximum atteint
    public long getNiveauMax() {
        return niveauMax;
    }

    // Définit le niveau maximum atteint
    public void setNiveauMax(long niveauMax) {
        this.niveauMax = niveauMax;
    }

    // Retourne l'identifiant de la partie associée
    public long getIdPartie() {
        return idPartie;
    }

    // Définit l'identifiant de la partie associée
    public void setIdPartie(long idPartie) {
        this.idPartie = idPartie;
    }

    // Retourne la référence à la partie associée
    public Partie getPartie() {
        return partie;
    }

    // Définit la partie associée et met à jour l'idPartie si la partie n'est pas nulle
    public void setPartie(Partie partie) {
        this.partie = partie;
        if (partie != null) {
            this.idPartie = partie.getIdPartie();
        }
    }

    /**
     * Redéfinition de la méthode toString pour un affichage lisible du score.
     */
    @Override
    public String toString() {
        return "Score{" +
                "idScore=" + idScore +
                ", totalPoints=" + totalPoints +
                ", niveauMax=" + niveauMax +
                ", idPartie=" + idPartie +
                '}';
    }
}
