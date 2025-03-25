package heydon.database.model;

import java.util.Date;

/**
 * Classe représentant une connexion d'un joueur dans la base de données.
 */
public class Connexion {
    private int idConnexion;
    private int idJoueur;
    private Date dateConnexion;
    private boolean status;
    private Joueur joueur;  // Référence au joueur
    
    public Connexion() {
        this.dateConnexion = new Date();
        this.status = false;
    }
    
    public Connexion(int idJoueur) {
        this.idJoueur = idJoueur;
        this.dateConnexion = new Date();
        this.status = true;
    }
    
    // Getters et Setters
    public int getIdConnexion() {
        return idConnexion;
    }
    
    public void setIdConnexion(int idConnexion) {
        this.idConnexion = idConnexion;
    }
    
    public int getIdJoueur() {
        return idJoueur;
    }
    
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }
    
    public Date getDateConnexion() {
        return dateConnexion;
    }
    
    public void setDateConnexion(Date dateConnexion) {
        this.dateConnexion = dateConnexion;
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
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
        return "Connexion{" +
                "idConnexion=" + idConnexion +
                ", idJoueur=" + idJoueur +
                ", dateConnexion=" + dateConnexion +
                ", status=" + status +
                '}';
    }
}