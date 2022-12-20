package by.clevertech.data.factory;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.data.repository.CardRepository;
import by.clevertech.data.repository.CrudRepository;
import by.clevertech.data.repository.ProductRepository;
import by.clevertech.data.repository.impl.CardRepositoryInMemoryImpl;
import by.clevertech.data.repository.impl.ProductRepositoryInMemoryImpl;

public class InMemoryDaoFactory implements AbstractFactory {

    public static final InMemoryDaoFactory INSTANCE = new InMemoryDaoFactory();
    private final Map<Class<?>, CrudRepository<?, ?>> map;

    private InMemoryDaoFactory() {
        map = new HashMap<>();
        map.put(CardRepository.class, new CardRepositoryInMemoryImpl());
        map.put(ProductRepository.class, new ProductRepositoryInMemoryImpl());
    }

    @Override
    public <T> T getDao(Class<T> clazz) {
        return (T) map.get(clazz);
    }

}
