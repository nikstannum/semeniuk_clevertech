package by.clevertech.data.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private final Properties properties;
    private static final String PROP_FILE = "/application.properties";
    public static final ConfigManager INSTANCE = new ConfigManager();

    private ConfigManager() {
        properties = new Properties();
        try (InputStream input = getClass().getResourceAsStream(PROP_FILE);) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e.getCause()); // TODO to add logging and to log
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
