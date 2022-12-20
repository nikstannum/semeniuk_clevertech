package by.clevertech.data.factory;

public interface AbstractFactory {
    public <T> T getDao(Class<T> clazz);
}
