package heydon.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO pour gérer les opérations de base de données du jeu Tetris.
 * Utilise le pattern DAO pour séparer la logique d'accès aux données.
 */
public class TetrisDAO {
    private final DatabaseConnection dbConnection;
    
    /**
     * Constructeur du DAO.
     */
    public TetrisDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    
    /**
     * Sauvegarde un nouveau score dans la base de données.
     * @param playerName Nom du joueur
     * @param score Score obtenu
     * @param level Niveau atteint
     * @param linesCleared Nombre de lignes complétées
     * @return true si la sauvegarde a réussi, false sinon
     */
    public boolean saveScore(String playerName, int score, int level, int linesCleared) {
        String sql = "INSERT INTO scores (player_name, score, level, lines_cleared, date_played) VALUES (?, ?, ?, ?, NOW())";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, playerName);
            pstmt.setInt(2, score);
            pstmt.setInt(3, level);
            pstmt.setInt(4, linesCleared);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la sauvegarde du score: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Récupère les meilleurs scores.
     * @param limit Nombre de scores à récupérer
     * @return Liste des meilleurs scores
     */
    public List<ScoreRecord> getTopScores(int limit) {
        List<ScoreRecord> scores = new ArrayList<>();
        String sql = "SELECT * FROM scores ORDER BY score DESC LIMIT ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                scores.add(new ScoreRecord(
                    rs.getString("player_name"),
                    rs.getInt("score"),
                    rs.getInt("level"),
                    rs.getInt("lines_cleared"),
                    rs.getTimestamp("date_played")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des scores: " + e.getMessage());
        }
        
        return scores;
    }
    
    /**
     * Récupère le meilleur score d'un joueur.
     * @param playerName Nom du joueur
     * @return Le meilleur score du joueur ou null si aucun score trouvé
     */
    public ScoreRecord getPlayerBestScore(String playerName) {
        String sql = "SELECT * FROM scores WHERE player_name = ? ORDER BY score DESC LIMIT 1";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new ScoreRecord(
                    rs.getString("player_name"),
                    rs.getInt("score"),
                    rs.getInt("level"),
                    rs.getInt("lines_cleared"),
                    rs.getTimestamp("date_played")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du meilleur score: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Met à jour le score d'un joueur si le nouveau score est meilleur.
     * @param scoreId ID du score à mettre à jour
     * @param newScore Nouveau score
     * @param newLevel Nouveau niveau
     * @param newLinesCleared Nouveau nombre de lignes complétées
     * @return true si la mise à jour a réussi, false sinon
     */
    public boolean updateScore(int scoreId, int newScore, int newLevel, int newLinesCleared) {
        String sql = "UPDATE scores SET score = ?, level = ?, lines_cleared = ? WHERE id = ? AND score < ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, newScore);
            pstmt.setInt(2, newLevel);
            pstmt.setInt(3, newLinesCleared);
            pstmt.setInt(4, scoreId);
            pstmt.setInt(5, newScore);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du score: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Classe interne représentant un enregistrement de score.
     */
    public static class ScoreRecord {
        private final String playerName;
        private final int score;
        private final int level;
        private final int linesCleared;
        private final Timestamp datePlayed;
        
        public ScoreRecord(String playerName, int score, int level, int linesCleared, Timestamp datePlayed) {
            this.playerName = playerName;
            this.score = score;
            this.level = level;
            this.linesCleared = linesCleared;
            this.datePlayed = datePlayed;
        }
        
        // Getters
        public String getPlayerName() { return playerName; }
        public int getScore() { return score; }
        public int getLevel() { return level; }
        public int getLinesCleared() { return linesCleared; }
        public Timestamp getDatePlayed() { return datePlayed; }
        
        @Override
        public String toString() {
            return String.format(
                "Score{player='%s', score=%d, level=%d, lines=%d, date=%s}",
                playerName, score, level, linesCleared, datePlayed
            );
        }
    }
}