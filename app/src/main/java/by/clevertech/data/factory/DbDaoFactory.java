package by.clevertech.data.factory;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.data.connection.DataSource;
import by.clevertech.data.repository.CardRepository;
import by.clevertech.data.repository.CrudRepository;
import by.clevertech.data.repository.ProductRepository;
import by.clevertech.data.repository.impl.CardRepositoryImpl;
import by.clevertech.data.repository.impl.ProductRepositoryImpl;

public class DbDaoFactory implements AbstractFactory {

    public static final DbDaoFactory INSTANCE = new DbDaoFactory();
    private final Map<Class<?>, CrudRepository<?, ?>> map;

    private DbDaoFactory() {
        DataSource dataSource = DataSource.INSTANCE;
        map = new HashMap<>();
        map.put(CardRepository.class, new CardRepositoryImpl(dataSource));
        map.put(ProductRepository.class, new ProductRepositoryImpl(dataSource));
    }

    @Override
    public <T> T getDao(Class<T> clazz) {
        return (T) map.get(clazz);
    }

}
