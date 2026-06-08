package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            PROPS.load(is);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Cannot load config.properties: " + e.getMessage());
        }
    }

    public static String getBaseUrl() {
        return PROPS.getProperty("base.url");
    }

    public static String getApiKey() {
        String env = System.getenv("TRELLO_API_KEY");
        return (env != null && !env.isBlank()) ? env : PROPS.getProperty("api.key");
    }

    public static String getApiToken() {
        String env = System.getenv("TRELLO_API_TOKEN");
        return (env != null && !env.isBlank()) ? env : PROPS.getProperty("api.token");
    }
}