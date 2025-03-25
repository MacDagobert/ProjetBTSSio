package heydon.database.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface générique pour les opérations CRUD de base.
 * @param <T> Le type d'entité
 * @param <K> Le type de la clé primaire
 */
public interface DAO<T, K> {
    /**
     * Crée une nouvelle entité dans la base de données.
     * @param entity L'entité à créer
     * @return L'entité créée avec sa clé primaire générée
     * @throws SQLException En cas d'erreur SQL
     */
    T create(T entity) throws SQLException;
    
    /**
     * Récupère une entité par sa clé primaire.
     * @param id La clé primaire de l'entité
     * @return L'entité trouvée ou null si non trouvée
     * @throws SQLException En cas d'erreur SQL
     */
    T findById(K id) throws SQLException;
    
    /**
     * Récupère toutes les entités.
     * @return Liste de toutes les entités
     * @throws SQLException En cas d'erreur SQL
     */
    List<T> findAll() throws SQLException;
    
    /**
     * Met à jour une entité existante.
     * @param entity L'entité à mettre à jour
     * @return true si la mise à jour a réussi, false sinon
     * @throws SQLException En cas d'erreur SQL
     */
    boolean update(T entity) throws SQLException;
    
    /**
     * Supprime une entité par sa clé primaire.
     * @param id La clé primaire de l'entité à supprimer
     * @return true si la suppression a réussi, false sinon
     * @throws SQLException En cas d'erreur SQL
     */
    boolean delete(K id) throws SQLException;
}