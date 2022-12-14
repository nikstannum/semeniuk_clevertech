package by.clevertech.service;

public interface CrudService<T, K> {
	public T findById(K id);

}
