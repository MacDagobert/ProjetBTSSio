package heydon.database.model;

/**
 * Classe représentant un score de partie dans la base de données.
 */
public class Score {
    private long idScore;
    private long totalPoints;
    private long niveauMax;
    private long idPartie;
    private Partie partie;  // Référence à la partie associée
    
    public Score() {}
    
    public Score(long totalPoints, long niveauMax, long idPartie) {
        this.totalPoints = totalPoints;
        this.niveauMax = niveauMax;
        this.idPartie = idPartie;
    }
    
    // Getters et Setters
    public long getIdScore() {
        return idScore;
    }
    
    public void setIdScore(long idScore) {
        this.idScore = idScore;
    }
    
    public long getTotalPoints() {
        return totalPoints;
    }
    
    public void setTotalPoints(long totalPoints) {
        this.totalPoints = totalPoints;
    }
    
    public long getNiveauMax() {
        return niveauMax;
    }
    
    public void setNiveauMax(long niveauMax) {
        this.niveauMax = niveauMax;
    }
    
    public long getIdPartie() {
        return idPartie;
    }
    
    public void setIdPartie(long idPartie) {
        this.idPartie = idPartie;
    }
    
    public Partie getPartie() {
        return partie;
    }
    
    public void setPartie(Partie partie) {
        this.partie = partie;
        if (partie != null) {
            this.idPartie = partie.getIdPartie();
        }
    }
    
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