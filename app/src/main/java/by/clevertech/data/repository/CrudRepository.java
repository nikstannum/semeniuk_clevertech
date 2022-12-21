package by.clevertech.data.repository;

import java.util.List;
import java.util.Optional;

/**
 * Abstract interface for performing CRUD-operations with data.
 *
 * @param <T> return type
 * @param <K> data access key
 * 
 * @author Nikita Semeniuk
 */
public interface CrudRepository<T, K> {

    /**
     * serializes an object to the database
     * 
     * @param entity the serializable object
     * @return Optional the same object from the database
     */
    public Optional<T> create(T entity);

    /**
     * serializes an object from the database
     * 
     * @param id the object id
     * @return Optional this object
     */
    public Optional<T> findById(K id);

    /**
     * serializes all objects in the database
     * 
     * @return serialized list of objects
     */
    public List<T> findAll();

    /**
     * updates an object in the database
     * 
     * @param entity the object itself
     * @return Optional this updated object
     */
    public Optional<T> update(T entity);

    /**
     * removes an object from the database
     * 
     * @param id object id
     * @return result of deletion
     */
    public boolean delete(K id);

}
