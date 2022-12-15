package by.clevertech.dao.factory;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.dao.connection.DataSource;
import by.clevertech.dao.repository.CardRepository;
import by.clevertech.dao.repository.CrudRepository;
import by.clevertech.dao.repository.ProductRepository;
import by.clevertech.dao.repository.impl.CardRepositoryImpl;
import by.clevertech.dao.repository.impl.ProductRepositoryImpl;

public class DaoFactory {
	public static final DaoFactory INSTANCE = new DaoFactory();
	private final Map<Class<?>, CrudRepository<?, ?>> map;

	private DaoFactory() {
		DataSource dataSource = DataSource.INSTANCE;
		map = new HashMap<>();
		map.put(CardRepository.class, new CardRepositoryImpl(dataSource));
		map.put(ProductRepository.class, new ProductRepositoryImpl(dataSource));
	}

	public <T> T getDao(Class<T> clazz) {
		return (T) map.get(clazz);
	}

}
