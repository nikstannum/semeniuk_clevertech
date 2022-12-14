package by.clevertech.dao.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import by.clevertech.exception.AccessException;

public class ConfigManager {

	private final Properties properties;
	private static final String PROP_FILE = "/application.properties";
	public static final ConfigManager INSTANCE = new ConfigManager();

	private ConfigManager() {
		properties = new Properties();
		try (InputStream input = getClass().getResourceAsStream(PROP_FILE);) {
			properties.load(input);
		} catch (IOException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
