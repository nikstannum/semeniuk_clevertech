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

    public Optional<T> create(T entity);

    public Optional<T> findById(K id);

    public List<T> findAll();

    public Optional<T> update(T entity);

    public boolean delete(K id);

}
