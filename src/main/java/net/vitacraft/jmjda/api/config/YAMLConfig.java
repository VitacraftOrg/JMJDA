package net.vitacraft.jmjda.api.config;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class YAMLConfig implements Config {
    private final File configFile;
    private final Yaml yaml;
    private Map<String, Object> data;

    public YAMLConfig(File file) {
        this.configFile = file;
        this.yaml = new Yaml();
        loadDataFromFile();
    }

    private void loadDataFromFile() {
        try (FileReader reader = new FileReader(configFile)) {
            data = yaml.load(reader);
        } catch (IOException e) {
            data = null;
        }
    }

    @Override
    public String getName() {
        return configFile.getName();
    }

    @Override
    public String getString(String key) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Object value = navigateMap(data, keys);
            return value != null ? String.valueOf(value) : null;
        }
        return null;
    }

    @Override
    public int getInt(String key) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Object value = navigateMap(data, keys);
            return value instanceof Integer ? (int) value : 0;
        }
        return 0;
    }

    @Override
    public List<String> getStringList(String key) {
        if (data != null) {
            return (List<String>) data.get(key);
        }
        return null;
    }

    @Override
    public long getLong(String key) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Object value = navigateMap(data, keys);
            return value instanceof Long ? (long) value : 0L;
        }
        return 0L;
    }

    @Override
    public double getDouble(String key) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Object value = navigateMap(data, keys);
            return value instanceof Double ? (double) value : 0.0;
        }
        return 0.0;
    }

    @Override
    public boolean getBool(String key) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Object value = navigateMap(data, keys);
            return value instanceof Boolean && (boolean) value;
        }
        return false;
    }

    @Override
    public void setString(String key, String value) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Map<String, Object> targetMap = navigateMapForMap(data, keys, true);
            targetMap.put(keys[keys.length - 1], value);
            saveChanges();
        }
    }

    @Override
    public void setBool(String key, boolean value) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Map<String, Object> targetMap = navigateMapForMap(data, keys, true);
            targetMap.put(keys[keys.length - 1], value);
            saveChanges();
        }
    }

    @Override
    public void setInt(String key, int value) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Map<String, Object> targetMap = navigateMapForMap(data, keys, true);
            targetMap.put(keys[keys.length - 1], value);
            saveChanges();
        }
    }

    @Override
    public void setLong(String key, long value) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Map<String, Object> targetMap = navigateMapForMap(data, keys, true);
            targetMap.put(keys[keys.length - 1], value);
            saveChanges();
        }
    }

    @Override
    public void setDouble(String key, double value) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Map<String, Object> targetMap = navigateMapForMap(data, keys, true);
            targetMap.put(keys[keys.length - 1], value);
            saveChanges();
        }
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

    private Map<String, Object> navigateMapForMap(Map<String, Object> map, String[] keys, boolean createIfNotExist) {
        Map<String, Object> currentMap = map;
        for (int i = 0; i < keys.length - 1; i++) {
            if (currentMap.containsKey(keys[i]) && currentMap.get(keys[i]) instanceof Map) {
                currentMap = (Map<String, Object>) currentMap.get(keys[i]);
            } else {
                if (createIfNotExist) {
                    Map<String, Object> newMap = new LinkedHashMap<>();
                    currentMap.put(keys[i], newMap);
                    currentMap = newMap;
                } else {
                    return null;
                }
            }
        }
        return currentMap;
    }

    private void saveChanges() {
        try (FileWriter writer = new FileWriter(configFile)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}