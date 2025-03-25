package heydon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe gérant la connexion à la base de données MySQL.
 * Utilise le pattern Singleton pour assurer une seule instance de connexion.
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    
    // Paramètres de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/tetriste?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    /**
     * Constructeur privé pour empêcher l'instanciation directe.
     */
    private DatabaseConnection() {
        try {
            // Charge le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL chargé avec succès");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de chargement du driver MySQL: " + e.getMessage());
            throw new RuntimeException("Driver MySQL non trouvé", e);
        }
    }
    
    /**
     * Obtient l'instance unique de la connexion (pattern Singleton).
     * @return L'instance de DatabaseConnection
     */
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    /**
     * Établit la connexion à la base de données.
     * @return La connexion établie
     * @throws SQLException si la connexion échoue
     */
    public Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                // Configure la connexion pour de meilleures performances
                connection.setAutoCommit(true);
                System.out.println("Connexion à la base de données établie avec succès");
            }
            return connection;
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données: " + e.getMessage());
            throw new SQLException("Impossible de se connecter à la base de données", e);
        }
    }
    
    /**
     * Ferme la connexion à la base de données.
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Connexion à la base de données fermée");
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion: " + e.getMessage());
            } finally {
                connection = null;
            }
        }
    }
    
    /**
     * Teste la connexion à la base de données.
     * @return true si la connexion est établie avec succès, false sinon
     */
    public boolean testConnection() {
        try {
            Connection testConn = getConnection();
            boolean isValid = testConn != null && !testConn.isClosed() && testConn.isValid(5);
            if (isValid) {
                System.out.println("Test de connexion réussi");
            } else {
                System.err.println("Test de connexion échoué: la connexion n'est pas valide");
            }
            return isValid;
        } catch (SQLException e) {
            System.err.println("Test de connexion échoué: " + e.getMessage());
            return false;
        }
    }
}