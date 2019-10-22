package ru.amm.fileexplorer.server.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values = new Properties();
    /**
     * Логирование.
     */
    private final static Logger LOG = LogManager.getLogger(Config.class);

    public Config() {
        this.init();
    }

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
            values.load(in);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
