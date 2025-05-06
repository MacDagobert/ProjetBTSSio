package heydon.database;

import heydon.GrillePanel;
import java.sql.*;

/**
 * Gestionnaire simplifié des scores pour le jeu Tetris.
 */
/**
 * Classe utilitaire pour gérer les scores des joueurs, leur sauvegarde et leur récupération.
 */
public class ScoreManager {
    // Connexion à la base de données (singleton)
    private final DatabaseConnection dbConnection;
    // Pseudo du joueur actuellement connecté
    private String currentPlayerPseudo;
    // Score courant de la session en cours
    private int currentScore;
    // Identifiant du joueur courant (récupéré en base)
    private int idJoueur;
    // Niveau maximum atteint lors de la partie
    private int niveauMax;
    // Référence à la grille de jeu (pour récupérer le niveau)
    private GrillePanel grillePanel;

    /**
     * Constructeur par défaut. Initialise les champs avec des valeurs neutres.
     */
    public ScoreManager() {
        this.dbConnection = DatabaseConnection.getInstance();
        this.currentScore = 0;
        this.currentPlayerPseudo = "";
        this.idJoueur = -1;
        this.niveauMax = 0; // Initialisation par défaut
        this.grillePanel = null;
    }

    /**
     * Constructeur permettant d'associer un GrillePanel à ce ScoreManager.
     * @param grillePanel Composant graphique de la grille de jeu
     */
    public ScoreManager(GrillePanel grillePanel) {
        this();
        this.grillePanel = grillePanel;
    }

    // ------------------- Méthodes principales -------------------

    /**
     * Met à jour le score actuel (affiche aussi le score dans la console).
     * @param score Nouveau score à enregistrer
     */
    public void updateCurrentScore(int score) {
        this.currentScore = score;
        System.out.println("Score mis à jour : " + score);
    }

    /**
     * Sauvegarde le score et la partie dans la base de données.
     * @return true si la sauvegarde a réussi, false sinon
     */
    public boolean saveScore() {
        // Vérifie que le pseudo du joueur est bien défini
        if (currentPlayerPseudo == null || currentPlayerPseudo.trim().isEmpty()) {
            System.err.println("Erreur: Pseudo non défini");
            return false;
        }

        // Requête pour insérer une nouvelle partie
        String sqlPartie = "INSERT INTO partie (date_partie, id_joueur) VALUES (NOW(), (SELECT id_joueur FROM joueurs WHERE pseudo = ?))";
        // Requête pour insérer le score associé à la partie
        String sqlScore = "INSERT INTO score (total_points, niveau_max, id_partie) VALUES (?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmtPartie = conn.prepareStatement(sqlPartie, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmtScore = conn.prepareStatement(sqlScore)) {

            // Démarre une transaction
            conn.setAutoCommit(false);

            // Insertion de la partie
            pstmtPartie.setString(1, currentPlayerPseudo);
            int affectedRows = pstmtPartie.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Échec création partie");
            }

            // Récupère l'ID de la partie nouvellement créée
            try (ResultSet generatedKeys = pstmtPartie.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long idPartie = generatedKeys.getLong(1);

                    // Insertion du score associé à la partie
                    pstmtScore.setInt(1, currentScore);
                    if (grillePanel != null) {
                        pstmtScore.setInt(2, grillePanel.getNiveau());
                    } else {
                        pstmtScore.setNull(2, Types.INTEGER);
                    }
                    pstmtScore.setLong(3, idPartie);

                    affectedRows = pstmtScore.executeUpdate();
                    if (affectedRows > 0) {
                        conn.commit(); // Valide la transaction
                        return true;
                    }
                }
            }
            conn.rollback(); // Annule la transaction en cas d'échec
            return false;

        } catch (SQLException e) {
            System.err.println("Erreur sauvegarde score: " + e.getMessage());
            return false;
        }
    }

    /**
     * Récupère le meilleur score historique du joueur courant.
     * @return Meilleur score (entier) ou 0 si aucun score trouvé ou erreur
     */
    public int getBestScore() {
        String sql = "SELECT MAX(score.total_points) as best_score " +
                     "FROM score" +
                     "JOIN partie ON score.id_partie = partie.id_partie " +
                     "JOIN joueurs  ON partie.id_joueur = joueurs.id_joueur " +
                     "WHERE joueurs.pseudo = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, currentPlayerPseudo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("best_score");
            }
        } catch (SQLException e) {
            System.err.println("Erreur récupération best score: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Récupère l'identifiant du joueur courant depuis la base de données à partir de son pseudo.
     * @return true si la récupération a réussi, false sinon
     */
    public boolean acquireData() {
        String sql = "SELECT id_joueur FROM joueurs WHERE pseudo = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, currentPlayerPseudo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                this.idJoueur = rs.getInt("id_joueur");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erreur acquisition données: " + e.getMessage());
        }
        return false;
    }

    // ------------------- Getters et Setters -------------------

    /**
     * Définit le pseudo du joueur courant.
     * @param pseudo Le pseudo du joueur à définir
     */
    public void setPlayerPseudo(String pseudo) {
        this.currentPlayerPseudo = pseudo;
    }

    /**
     * Récupère le pseudo du joueur courant.
     * @return Le pseudo actuellement enregistré
     */
    public String getPlayerPseudo() {
        return this.currentPlayerPseudo;
    }

    /**
     * Met à jour le score courant.
     * @param score Le nouveau score à enregistrer
     */
    public void updateScore(int score) {
        this.currentScore = score;
    }

    /**
     * Récupère le score courant.
     * @return Le score courant
     */
    public int getCurrentScore() {
        return this.currentScore;
    }

    // D'autres méthodes ou logiques supplémentaires peuvent être ajoutées ici
}

