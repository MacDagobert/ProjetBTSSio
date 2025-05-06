package heydon.database.dao;

import heydon.database.DatabaseConnection;
import heydon.database.model.Joueur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour la gestion des joueurs dans la base de données.
 */
/**
 * Classe DAO (Data Access Object) pour l'entité Joueur.
 * Gère les opérations CRUD (Create, Read, Update, Delete) avec la base de données.
 */
public class JoueurDAO implements DAO<Joueur, Integer> {
    private final DatabaseConnection dbConnection; // Connexion à la base de données
    
    /**
     * Constructeur qui initialise la connexion à la base de données.
     */
    public JoueurDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    
    /**
     * Crée un nouveau joueur dans la base de données.
     * @param joueur Le joueur à créer
     * @return Le joueur créé avec son ID généré
     * @throws SQLException En cas d'erreur SQL
     */
    @Override
    public Joueur create(Joueur joueur) throws SQLException {
        String sql = "INSERT INTO joueurs (pseudo, email, date_inscription, password) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            // Remplissage des paramètres de la requête
            pstmt.setString(1, joueur.getPseudo());
            pstmt.setString(2, joueur.getEmail());
            pstmt.setDate(3, new java.sql.Date(joueur.getDateInscription().getTime()));
            pstmt.setString(4, joueur.getPassword());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("La création du joueur a échoué, aucune ligne affectée.");
            }
            
            // Récupération de l'ID généré
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    joueur.setIdJoueur(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("La création du joueur a échoué, aucun ID obtenu.");
                }
            }
        }
        
        return joueur;
    }
    
    /**
     * Recherche un joueur par son ID.
     * @param id L'identifiant du joueur
     * @return Le joueur trouvé ou null
     * @throws SQLException En cas d'erreur SQL
     */
    @Override
    public Joueur findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM joueurs WHERE id_joueur = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractJoueurFromResultSet(rs);
                }
            }
        }
        
        return null;
    }
    
    /**
     * Récupère tous les joueurs de la base de données.
     * @return Liste de tous les joueurs
     * @throws SQLException En cas d'erreur SQL
     */
    @Override
    public List<Joueur> findAll() throws SQLException {
        List<Joueur> joueurs = new ArrayList<>();
        String sql = "SELECT * FROM joueurs";
        
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                joueurs.add(extractJoueurFromResultSet(rs));
            }
        }
        
        return joueurs;
    }
    
    /**
     * Met à jour un joueur existant dans la base de données.
     * @param joueur Le joueur à mettre à jour
     * @return true si la mise à jour a réussi
     * @throws SQLException En cas d'erreur SQL
     */
    @Override
    public boolean update(Joueur joueur) throws SQLException {
        String sql = "UPDATE joueurs SET pseudo = ?, email = ?, password = ? WHERE id_joueur = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, joueur.getPseudo());
            pstmt.setString(2, joueur.getEmail());
            pstmt.setString(3, joueur.getPassword());
            pstmt.setInt(4, joueur.getIdJoueur());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    /**
     * Supprime un joueur de la base de données.
     * @param id L'identifiant du joueur à supprimer
     * @return true si la suppression a réussi
     * @throws SQLException En cas d'erreur SQL
     */
    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM joueurs WHERE id_joueur = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    /**
     * Recherche un joueur par son pseudo.
     * @param pseudo Le pseudo à rechercher
     * @return Le joueur trouvé ou null
     * @throws SQLException En cas d'erreur SQL
     */
    public Joueur findByPseudo(String pseudo) throws SQLException {
        String sql = "SELECT * FROM joueurs WHERE pseudo = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, pseudo);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractJoueurFromResultSet(rs);
                }
            }
        }
        
        return null;
    }
    
    /**
     * Authentifie un joueur avec son pseudo et mot de passe.
     * Attention : Le mot de passe est stocké en clair dans cette implémentation.
     * @param pseudo Le pseudo du joueur
     * @param password Le mot de passe en clair
     * @return Le joueur authentifié ou null
     * @throws SQLException En cas d'erreur SQL
     */
    public Joueur authenticate(String pseudo, String password) throws SQLException {
        String sql = "SELECT * FROM joueurs WHERE pseudo = ? AND password = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, pseudo);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractJoueurFromResultSet(rs);
                }
            }
        }
        
        return null;
    }
    
    /**
     * Convertit un ResultSet en objet Joueur.
     * @param rs Le ResultSet à convertir
     * @return Un objet Joueur peuplé
     * @throws SQLException En cas d'erreur SQL
     */
    private Joueur extractJoueurFromResultSet(ResultSet rs) throws SQLException {
        Joueur joueur = new Joueur();
        joueur.setIdJoueur(rs.getInt("id_joueur"));
        joueur.setPseudo(rs.getString("pseudo"));
        joueur.setEmail(rs.getString("email"));
        joueur.setDateInscription(rs.getDate("date_inscription"));
        joueur.setPassword(rs.getString("password"));
        return joueur;
    }
}
