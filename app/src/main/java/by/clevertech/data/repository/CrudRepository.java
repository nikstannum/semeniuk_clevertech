package by.clevertech.data.repository;

import java.util.List;

/**
 * Abstract interface for performing CRUD-operations with data.
 *
 * @param <T> return type
 * @param <K> data access key
 * 
 * @author Nikita Semeniuk
 */
public interface CrudRepository<T, K> {

    public T create(T entity);

    public T findById(K id);

    public List<T> findAll();

    public T update(T entity);

    public boolean delete(K id);

}
