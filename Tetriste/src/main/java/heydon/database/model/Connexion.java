package heydon.database.model;

import java.util.Date;

/**
 * Classe représentant une connexion d'un joueur dans la base de données.
 */
/**
 * Classe représentant une connexion d'un joueur à l'application.
 * Permet de suivre les connexions des utilisateurs (historique, statut, etc.).
 */
public class Connexion {
    // Identifiant unique de la connexion (clé primaire en base de données)
    private int idConnexion;
    // Identifiant du joueur associé à cette connexion (clé étrangère)
    private int idJoueur;
    // Date et heure de la connexion
    private Date dateConnexion;
    // Statut de la connexion (true = connectée, false = déconnectée)
    private boolean status;
    // Référence à l'objet Joueur associé à cette connexion
    private Joueur joueur;

    /**
     * Constructeur par défaut.
     * Initialise la date de connexion à la date courante et le statut à déconnecté (false).
     */
    public Connexion() {
        this.dateConnexion = new Date();
        this.status = false;
    }

    /**
     * Constructeur permettant de créer une connexion pour un joueur donné.
     * Initialise la date à maintenant et le statut à connecté (true).
     * @param idJoueur identifiant du joueur qui se connecte
     */
    public Connexion(int idJoueur) {
        this.idJoueur = idJoueur;
        this.dateConnexion = new Date();
        this.status = true;
    }

    // ------------------- Getters et Setters -------------------

    /**
     * Retourne l'identifiant de la connexion.
     * @return idConnexion
     */
    public int getIdConnexion() {
        return idConnexion;
    }

    /**
     * Définit l'identifiant de la connexion.
     * @param idConnexion identifiant à définir
     */
    public void setIdConnexion(int idConnexion) {
        this.idConnexion = idConnexion;
    }

    /**
     * Retourne l'identifiant du joueur associé à cette connexion.
     * @return idJoueur
     */
    public int getIdJoueur() {
        return idJoueur;
    }

    /**
     * Définit l'identifiant du joueur associé à cette connexion.
     * @param idJoueur identifiant du joueur à définir
     */
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    /**
     * Retourne la date de la connexion.
     * @return dateConnexion
     */
    public Date getDateConnexion() {
        return dateConnexion;
    }

    /**
     * Définit la date de la connexion.
     * @param dateConnexion date à définir
     */
    public void setDateConnexion(Date dateConnexion) {
        this.dateConnexion = dateConnexion;
    }

    /**
     * Retourne le statut de la connexion (true = connecté, false = déconnecté).
     * @return status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Définit le statut de la connexion.
     * @param status true pour connecté, false pour déconnecté
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Retourne la référence à l'objet Joueur associé à cette connexion.
     * @return joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Définit l'objet Joueur associé à cette connexion et met à jour l'idJoueur si le joueur n'est pas null.
     * @param joueur l'objet Joueur à associer
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        if (joueur != null) {
            this.idJoueur = joueur.getIdJoueur();
        }
    }

    /**
     * Affiche les informations principales de la connexion sous forme de chaîne de caractères.
     * @return représentation textuelle de la connexion
     */
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
