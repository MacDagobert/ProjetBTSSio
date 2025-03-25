package heydon.database.model;

import java.util.Date;
import java.sql.Time;

/**
 * Classe représentant une partie de jeu dans la base de données.
 */
public class Partie {
    private long idPartie;
    private Date datePartie;
    private Time tempsPartie;
    private String typePartie;
    private int idJoueur;
    private Joueur joueur;  // Référence au joueur
    
    public Partie() {
        this.datePartie = new Date();
    }
    
    public Partie(String typePartie, int idJoueur) {
        this.datePartie = new Date();
        this.typePartie = typePartie;
        this.idJoueur = idJoueur;
    }
    
    // Getters et Setters
    public long getIdPartie() {
        return idPartie;
    }
    
    public void setIdPartie(long idPartie) {
        this.idPartie = idPartie;
    }
    
    public Date getDatePartie() {
        return datePartie;
    }
    
    public void setDatePartie(Date datePartie) {
        this.datePartie = datePartie;
    }
    
    public Time getTempsPartie() {
        return tempsPartie;
    }
    
    public void setTempsPartie(Time tempsPartie) {
        this.tempsPartie = tempsPartie;
    }
    
    public String getTypePartie() {
        return typePartie;
    }
    
    public void setTypePartie(String typePartie) {
        this.typePartie = typePartie;
    }
    
    public int getIdJoueur() {
        return idJoueur;
    }
    
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }
    
    public Joueur getJoueur() {
        return joueur;
    }
    
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        if (joueur != null) {
            this.idJoueur = joueur.getIdJoueur();
        }
    }
    
    @Override
    public String toString() {
        return "Partie{" +
                "idPartie=" + idPartie +
                ", datePartie=" + datePartie +
                ", tempsPartie=" + tempsPartie +
                ", typePartie='" + typePartie + '\'' +
                ", idJoueur=" + idJoueur +
                '}';
    }
}