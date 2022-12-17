package by.clevertech.data.repository;

import java.util.List;

public interface CrudRepository<T, K> {

	public T create(T entity);

	public T findById(K id);

	public List<T> findAll();

	public T update(T entity);

	public boolean delete(K id);

}
