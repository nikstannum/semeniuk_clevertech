package by.clevertech.service.factory;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.service.CheckSerializer;
import by.clevertech.service.impl.CheckStringSerializer;

/**
 * Factory to provide tools for serialization
 * 
 * @author Nikita Semeniuk
 *
 */
public class SerializerFactory {

    public static final SerializerFactory INSTANCE = new SerializerFactory();

    private final Map<String, CheckSerializer> serializer;

    private SerializerFactory() {
        serializer = new HashMap<>();
        serializer.put("string", new CheckStringSerializer());
    }

    /**
     * provides a serializer
     * 
     * @param <T>           return type
     * @param serializerStr key
     * @return serializer implementation
     */
    @SuppressWarnings("unchecked")
    public <T> T getSerializer(String serializerStr) {
        return (T) serializer.get(serializerStr);
    }

}
