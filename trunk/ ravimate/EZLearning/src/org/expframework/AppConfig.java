package org.expframework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Contains the configuration information of application.
 * 
 * @author ShriKant
 * @version 1.0
 */
public class AppConfig {

    /**
     * Instance of AppConfig
     */
    private static final AppConfig config = new AppConfig();

    /**
     * Contains all <code>properties</code> in a Properties instance
     */
    private Properties properties;

    /**
     * Creates an AppConfig instance.
     */
    private AppConfig() {
        properties = new Properties();

        InputStream stream = AppConfig.class
                .getResourceAsStream("/app.properties");

        if (stream == null) {
            throw new IllegalArgumentException(
                    "Could not load app.properties. Please make sure that it is in CLASSPATH.");
        }

        try {
            properties.load(stream);
        } catch (IOException e) {
            IllegalStateException ex = new IllegalStateException(
                    "An error occurred when reading from the input stream");
            ex.initCause(e);
            throw ex;
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                IllegalStateException ex = new IllegalStateException(
                        "An I/O error occured while closing the stream");
                ex.initCause(e);
                throw ex;
            }
        }
    }

    /**
     * Gets the instance of <code>AppConfig</code>.
     * 
     * @return instance of AppConfig
     */
    public static AppConfig getInstance() {
        return config;
    }

    /**
     * Gets the value of a property based on key provided.
     * 
     * @param key
     *            The key for which value needs to be retrieved from
     * @return The value for the key in <code>app.properties</code>. Returns
     *         null if the value does not exist in the property file.
     *  
     */
    public String get(String key) {
        if (properties == null || key == null)
            return null;
        return (String) properties.get(key);
    }
}