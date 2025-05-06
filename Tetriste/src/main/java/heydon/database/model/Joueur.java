package heydon.database.model;

import java.util.Date;

/**
 * Classe représentant un joueur dans la base de données.
 */
/**
 * Classe représentant un joueur dans l'application.
 * Permet de stocker les informations principales d'un utilisateur.
 */
public class Joueur {
    // Identifiant unique du joueur (clé primaire en base de données)
    private int idJoueur;
    // Pseudo du joueur (nom d'utilisateur)
    private String pseudo;
    // Adresse email du joueur
    private String email;
    // Date d'inscription du joueur
    private Date dateInscription;
    // Mot de passe du joueur (généralement stocké sous forme hachée)
    private String password;

    /**
     * Constructeur par défaut.
     * Nécessaire pour certains frameworks et pour la création d'objets vides.
     */
    public Joueur() {}

    /**
     * Constructeur permettant d'initialiser un joueur avec pseudo, email et mot de passe.
     * La date d'inscription est automatiquement initialisée à la date courante.
     * @param pseudo Le pseudo du joueur
     * @param email L'adresse email du joueur
     * @param password Le mot de passe du joueur (à hacher avant stockage)
     */
    public Joueur(String pseudo, String email, String password) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.dateInscription = new Date(); // Date d'inscription = maintenant
    }

    // ------------------- Getters et Setters -------------------

    /**
     * Retourne l'identifiant du joueur.
     * @return idJoueur
     */
    public int getIdJoueur() {
        return idJoueur;
    }

    /**
     * Définit l'identifiant du joueur.
     * @param idJoueur identifiant à définir
     */
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    /**
     * Retourne le pseudo du joueur.
     * @return pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Définit le pseudo du joueur.
     * @param pseudo pseudo à définir
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Retourne l'adresse email du joueur.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit l'adresse email du joueur.
     * @param email email à définir
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne la date d'inscription du joueur.
     * @return dateInscription
     */
    public Date getDateInscription() {
        return dateInscription;
    }

    /**
     * Définit la date d'inscription du joueur.
     * @param dateInscription date à définir
     */
    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    /**
     * Retourne le mot de passe du joueur (généralement haché).
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le mot de passe du joueur (à hacher avant stockage).
     * @param password mot de passe à définir
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Affiche les informations principales du joueur sous forme de chaîne de caractères.
     * @return représentation textuelle du joueur
     */
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
