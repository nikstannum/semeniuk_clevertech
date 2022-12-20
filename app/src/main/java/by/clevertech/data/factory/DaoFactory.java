package by.clevertech.data.factory;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.data.repository.CardRepository;
import by.clevertech.data.repository.CrudRepository;
import by.clevertech.data.repository.ProductRepository;
import by.clevertech.data.storage.HandleCardRepoImpl;
import by.clevertech.data.storage.HandleProductRepoImpl;

public class DaoFactory implements AbstractFactory {

    public static final DaoFactory INSTANCE = new DaoFactory();
    private final Map<Class<?>, CrudRepository<?, ?>> map;

    private DaoFactory() {
        map = new HashMap<>();
        map.put(CardRepository.class, new HandleCardRepoImpl());
        map.put(ProductRepository.class, new HandleProductRepoImpl());
    }

    @Override
    public <T> T getDao(Class<T> clazz) {
        return (T) map.get(clazz);
    }

}
