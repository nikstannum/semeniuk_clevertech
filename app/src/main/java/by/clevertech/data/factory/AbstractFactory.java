package by.clevertech.data.factory;

/**
 * Abstract factory for getting repository implementation
 * 
 * @author Nikita Semeniuk
 *
 */

public interface AbstractFactory {
    /**
     * provides repository implementation
     * 
     * @param <T>   return type
     * @param clazz implementation type
     * @return repository implementation
     */
    public <T> T getDao(Class<T> clazz);
}
