package heydon.database;

/**
 * Classe de test pour vérifier la connexion à la base de données.
 */
public class TestConnection {
    public static void main(String[] args) {
        System.out.println("Test de connexion à la base de données...");
        
        try {
            // Obtient l'instance de la connexion
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            
            // Teste la connexion
            if (dbConnection.testConnection()) {
                System.out.println("La connexion à la base de données fonctionne correctement !");
                
                // Teste une requête simple
                try {
                    dbConnection.getConnection().createStatement().executeQuery("SELECT 1");
                    System.out.println("Test de requête réussi !");
                } catch (Exception e) {
                    System.err.println("Erreur lors du test de requête : " + e.getMessage());
                }
            } else {
                System.err.println("Échec de la connexion à la base de données.");
            }
            
            // Ferme la connexion
            dbConnection.closeConnection();
            
        } catch (Exception e) {
            System.err.println("Erreur lors du test : " + e.getMessage());
            e.printStackTrace();
        }
    }
}