package by.clevertech.service.factory;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.dao.factory.DaoFactory;
import by.clevertech.dao.repository.CardRepository;
import by.clevertech.dao.repository.ProductRepository;
import by.clevertech.service.CheckService;
import by.clevertech.service.CrudService;
import by.clevertech.service.impl.CheckServiceImpl;

public class ServiceFactory {

	public static final ServiceFactory INSTANCE = new ServiceFactory();
	private final Map<Class<?>, CrudService<?, ?>> map;

	private ServiceFactory() {
		map = new HashMap<>();
		map.put(CheckService.class, new CheckServiceImpl(DaoFactory.INSTANCE.getDao(ProductRepository.class),
						DaoFactory.INSTANCE.getDao(CardRepository.class)));
	}

	public <T> T getService(Class<T> clazz) {
		return (T) map.get(clazz);
	}

}
