package com.org.rental.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Simple utility to load application.properties from classpath and expose typed getters.
 */
public final class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (in != null) {
                props.load(in);
            } else {
                System.err.println("warning: application.properties not found on classpath");
            }
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private Config() {}

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        String v = props.getProperty(key);
        if (v == null) return defaultValue;
        try {
            return Integer.parseInt(v.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String v = props.getProperty(key);
        if (v == null) return defaultValue;
        return Boolean.parseBoolean(v.trim());
    }

    public static Properties all() {
        return props;
    }
}
