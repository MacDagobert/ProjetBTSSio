package heydon.database;

import heydon.GrillePanel;
import java.sql.*;

/**
 * Gestionnaire simplifié des scores pour le jeu Tetris.
 */
public class ScoreManager {
    private final DatabaseConnection dbConnection;
    private String currentPlayerPseudo;
    private int currentScore;
    private int idJoueur;
    private int niveauMax;

 
    private GrillePanel grillePanel;

    public ScoreManager() {
        this.dbConnection = DatabaseConnection.getInstance();
        this.currentScore = 0;
        this.currentPlayerPseudo = "";
        this.idJoueur = -1;
        this.niveauMax = 0; // Initialisation par défaut
        this.grillePanel = null;
    }

    public ScoreManager(GrillePanel grillePanel) {
        this();
        this.grillePanel = grillePanel;
    }

    // Méthodes existantes...

    /**
     * Met à jour le score actuel (version corrigée)
     * @param score Nouveau score
     */
    public void updateCurrentScore(int score) {
        this.currentScore = score;
        System.out.println("Score mis à jour : " + score);
    }

    /**
     * Sauvegarde le score dans la base de données
     * @return true si la sauvegarde réussit
     */
    public boolean saveScore() {
        if (currentPlayerPseudo == null || currentPlayerPseudo.trim().isEmpty()) {
            System.err.println("Erreur: Pseudo non défini");
            return false;
        }

        String sqlPartie = "INSERT INTO partie (date_partie, id_joueur) VALUES (NOW(), (SELECT id_joueur FROM joueurs WHERE pseudo = ?))";
        String sqlScore = "INSERT INTO score (total_points, niveau_max, id_partie) VALUES (?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmtPartie = conn.prepareStatement(sqlPartie, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmtScore = conn.prepareStatement(sqlScore)) {

            conn.setAutoCommit(false);

            // Insertion partie
            pstmtPartie.setString(1, currentPlayerPseudo);
            int affectedRows = pstmtPartie.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Échec création partie");
            }

            try (ResultSet generatedKeys = pstmtPartie.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long idPartie = generatedKeys.getLong(1);

                    // Insertion score
                    pstmtScore.setInt(1, currentScore);
                    if (grillePanel != null) {
                        pstmtScore.setInt(2, grillePanel.getNiveau());
                    } else {
                        pstmtScore.setNull(2, Types.INTEGER);
                    }
                    pstmtScore.setLong(3, idPartie);

                    affectedRows = pstmtScore.executeUpdate();
                    if (affectedRows > 0) {
                        conn.commit();
                        return true;
                    }
                }
            }
            conn.rollback();
            return false;

        } catch (SQLException e) {
            System.err.println("Erreur sauvegarde score: " + e.getMessage());
            return false;
        }
    }

    /**
     * Récupère le meilleur score historique
     * @return Meilleur score ou 0 si erreur
     */
    public int getBestScore() {
        String sql = "SELECT MAX(s.total_points) as best_score " +
                     "FROM score s " +
                     "JOIN partie p ON s.id_partie = p.id_partie " +
                     "JOIN joueurs j ON p.id_joueur = j.id_joueur " +
                     "WHERE j.pseudo = ?";

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
     * Récupère l'ID joueur depuis la base
     * @return true si réussi
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

    // Getters/Setters existants...


    /**
     * Définit le pseudo du joueur actuel.
     * @param pseudo Le pseudo du joueur
     */
    public void setPlayerPseudo(String pseudo) {
        this.currentPlayerPseudo = pseudo;
    }

    /**
     * Récupère le pseudo du joueur actuel.
     * @return Le pseudo du joueur
     */
    public String getPlayerPseudo() {
        return this.currentPlayerPseudo;
    }

    /**
     * Met à jour le score actuel.
     * @param score Le nouveau score
     */
    public void updateScore(int score) {
        this.currentScore = score;
    }

    /**
     * Récupère le score actuel.
     * @return Le score actuel
     */
    public int getCurrentScore() {
        return this.currentScore;
    }

    // Additional methods and logic remain unchanged
}
