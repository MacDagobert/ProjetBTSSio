package heydon.database.model;

import java.util.Date;

/**
 * Classe représentant un joueur dans la base de données.
 */
public class Joueur {
    private int idJoueur;
    private String pseudo;
    private String email;
    private Date dateInscription;
    private String password;
    
    public Joueur() {}
    
    public Joueur(String pseudo, String email, String password) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.dateInscription = new Date();
    }
    
    // Getters et Setters
    public int getIdJoueur() {
        return idJoueur;
    }
    
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }
    
    public String getPseudo() {
        return pseudo;
    }
    
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getDateInscription() {
        return dateInscription;
    }
    
    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Joueur{" +
                "idJoueur=" + idJoueur +
                ", pseudo='" + pseudo + '\'' +
                ", email='" + email + '\'' +
                ", dateInscription=" + dateInscription +
                '}';
    }
}