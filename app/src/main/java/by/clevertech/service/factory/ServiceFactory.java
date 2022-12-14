package by.clevertech.service.factory;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.data.connection.ConfigManager;
import by.clevertech.data.factory.AbstractFactory;
import by.clevertech.data.factory.DbDaoFactory;
import by.clevertech.data.factory.InMemoryDaoFactory;
import by.clevertech.data.repository.CardRepository;
import by.clevertech.data.repository.ProductRepository;
import by.clevertech.service.CheckService;
import by.clevertech.service.impl.CheckServiceImpl;

/**
 * Factory for providing service class implementation
 * 
 * @author Nikita Semeniuk
 *
 */
public class ServiceFactory {

    public static final ServiceFactory INSTANCE = new ServiceFactory();
    private final Map<Class<?>, CheckService> map;

    private ServiceFactory() {
        AbstractFactory factory;
        boolean useDb = Boolean.parseBoolean(ConfigManager.INSTANCE.getProperty("use.db"));
        factory = useDb ? DbDaoFactory.INSTANCE : InMemoryDaoFactory.INSTANCE;
        map = new HashMap<>();
        map.put(CheckService.class,
                new CheckServiceImpl(factory.getDao(ProductRepository.class), factory.getDao(CardRepository.class)));
    }

    /**
     * provides a service class
     * 
     * @param <T>   return type
     * @param clazz key
     * @return service implementation
     */
    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> clazz) {
        return (T) map.get(clazz);
    }

}
