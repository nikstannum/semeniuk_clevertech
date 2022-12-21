package by.clevertech.service.factory;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.service.CheckSerializer;
import by.clevertech.service.impl.CheckStringSerializer;

public class SerializerFactory {

    public static final SerializerFactory INSTANCE = new SerializerFactory();

    private final Map<String, CheckSerializer> serializer;

    private SerializerFactory() {
        serializer = new HashMap<>();
        serializer.put("string", new CheckStringSerializer());
    }

    public <T> T getSerializer(String serializerStr) {
        return (T) serializer.get(serializerStr);
    }

}
