package by.clevertech.data.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.log4j.Log4j2;

/**
 * This class provides access to application configuration settings stored in
 * the properties file.
 * 
 * @author Nikita Semeniuk
 *
 */
@Log4j2
public class ConfigManager {

    private final Properties properties;
    private static final String PROP_FILE = "/application.properties";
    public static final ConfigManager INSTANCE = new ConfigManager();

    private ConfigManager() {
        properties = new Properties();
        try (InputStream input = getClass().getResourceAsStream(PROP_FILE);) {
            properties.load(input);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    /**
     * returns property by key from properties file
     * 
     * @param key
     * @return value
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
