package net.vitacraft.jmjda.api.config;

import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.List;
import java.util.Map;

public class YAMLConfig implements Config{
    private final File configFile;
    private final Yaml yaml;

    public YAMLConfig(File file) {
        this.configFile = file;
        this.yaml = new Yaml();
    }

    @Override
    public String getName() {
        return configFile.getName();
    }

    @Override
    public String getString(String key) {
        try (FileReader reader = new FileReader(configFile)) {
            Map<String, Object> data = yaml.load(reader);
            if (data != null) {
                // Split the key by dot
                String[] keys = key.split("\\.");
                Object value = navigateMap(data, keys);
                return value != null ? String.valueOf(value) : null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getInt(String key) {
        try (FileReader reader = new FileReader(configFile)) {
            Map<String, Object> data = yaml.load(reader);
            if (data != null) {
                // Split the key by dot
                String[] keys = key.split("\\.");
                Object value = navigateMap(data, keys);
                return value instanceof Integer ? (int) value : 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<String> getStringList(String key) {
        try (FileReader reader = new FileReader(configFile)) {
            Map<String, Object> data = yaml.load(reader);
            if (data != null) {
                return (List<String>) data.get(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getLong(String key) {
        try (FileReader reader = new FileReader(configFile)) {
            Map<String, Object> data = yaml.load(reader);
            if (data != null) {
                // Split the key by dot
                String[] keys = key.split("\\.");
                Object value = navigateMap(data, keys);
                return value instanceof Long ? (long) value : 0L;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public double getDouble(String key) {
        try (FileReader reader = new FileReader(configFile)) {
            Map<String, Object> data = yaml.load(reader);
            if (data != null) {
                // Split the key by dot
                String[] keys = key.split("\\.");
                Object value = navigateMap(data, keys);
                return value instanceof Double ? (double) value : 0.0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public boolean getBool(String key) {
        try (FileReader reader = new FileReader(configFile)) {
            Map<String, Object> data = yaml.load(reader);
            if (data != null) {
                // Split the key by dot
                String[] keys = key.split("\\.");
                Object value = navigateMap(data, keys);
                return value instanceof Boolean && (boolean) value;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setString(String key, String value){

    }

    @Override
    public void setBool(String key, boolean value) {

    }

    @Override
    public void setInt(String key, int value) {

    }
    @Override
    public void setLong(String key, long value) {

    }

    @Override
    public void setDouble(String key, double value) {

    }

    private Object navigateMap(Map<String, Object> map, String[] keys) {
        Object value = map;
        for (String key : keys) {
            if (value instanceof Map) {
                value = ((Map<String, Object>) value).get(key);
            } else {
                return null;
            }
        }
        return value;
    }
}

