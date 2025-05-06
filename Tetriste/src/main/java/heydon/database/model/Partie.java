package heydon.database.model;

import java.util.Date;
import java.sql.Time;

/**
 * Classe représentant une partie de jeu dans la base de données.
 */
/**
 * Classe représentant une partie jouée, utilisée pour stocker les informations d'une session de jeu.
 */
public class Partie {
    // Identifiant unique de la partie (clé primaire en base de données)
    private long idPartie;
    // Date à laquelle la partie a été jouée
    private Date datePartie;
    // Durée de la partie (temps écoulé)
    private Time tempsPartie;
    // Type de partie (ex: "classique", "chrono", etc.)
    private String typePartie;
    // Identifiant du joueur ayant joué la partie (clé étrangère)
    private int idJoueur;
    // Référence à l'objet Joueur associé à la partie
    private Joueur joueur;

    
    // J'ai ajouté des commentaires pour les toutes les lignes afin de facilité la lecture
    
    
    /**
     * Constructeur par défaut.
     * Initialise la date de la partie à la date courante.
     */
    public Partie() {
        this.datePartie = new Date();
    }

    /**
     * Constructeur permettant de créer une partie avec son type et l'id du joueur.
     * La date de la partie est initialisée à la date courante.
     * @param typePartie Le type de la partie (mode de jeu)
     * @param idJoueur L'identifiant du joueur
     */
    public Partie(String typePartie, int idJoueur) {
        this.datePartie = new Date();
        this.typePartie = typePartie;
        this.idJoueur = idJoueur;
    }

    // ------------------- Getters et Setters -------------------

    // Retourne l'identifiant de la partie
    public long getIdPartie() {
        return idPartie;
    }

    // Définit l'identifiant de la partie
    public void setIdPartie(long idPartie) {
        this.idPartie = idPartie;
    }

    // Retourne la date de la partie
    public Date getDatePartie() {
        return datePartie;
    }

    // Définit la date de la partie
    public void setDatePartie(Date datePartie) {
        this.datePartie = datePartie;
    }

    // Retourne le temps écoulé de la partie
    public Time getTempsPartie() {
        return tempsPartie;
    }

    // Définit le temps écoulé de la partie
    public void setTempsPartie(Time tempsPartie) {
        this.tempsPartie = tempsPartie;
    }

    // Retourne le type de la partie (mode de jeu)
    public String getTypePartie() {
        return typePartie;
    }

    // Définit le type de la partie (mode de jeu)
    public void setTypePartie(String typePartie) {
        this.typePartie = typePartie;
    }

    // Retourne l'identifiant du joueur associé à la partie
    public int getIdJoueur() {
        return idJoueur;
    }

    // Définit l'identifiant du joueur associé à la partie
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    // Retourne la référence à l'objet Joueur associé
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Définit le joueur associé à la partie et met à jour l'idJoueur si le joueur n'est pas null.
     * @param joueur L'objet Joueur à associer
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        if (joueur != null) {
            this.idJoueur = joueur.getIdJoueur();
        }
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations de la partie.
     * @return Chaîne de caractères représentant la partie
     */
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
