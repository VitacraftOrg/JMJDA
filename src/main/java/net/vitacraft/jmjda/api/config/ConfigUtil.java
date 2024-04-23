package net.vitacraft.jmjda.api.config;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ConfigUtil {
    private final File configFile;
    private final Yaml yaml;

    public ConfigUtil(String filePath) {
        this.configFile = new File(filePath);
        this.yaml = new Yaml();

        if (!configFile.exists()) {
            if (!copyConfigFromResources(filePath)) {
                createNewConfig();
            }
        }
    }

    private boolean copyConfigFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream != null) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader reader = new BufferedReader(inputStreamReader);
                 BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void createNewConfig() {
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void setString(String key, String value){
        updateConfig(key, value);
    }

    public void setBool(String key, boolean value) {
        updateConfig(key, String.valueOf(value));
    }

    public void setInt(String key, int value) {
        updateConfig(key, String.valueOf(value));
    }

    public void setLong(String key, long value) {
        updateConfig(key, String.valueOf(value));
    }

    public void setDouble(String key, double value) {
        updateConfig(key, String.valueOf(value));
    }

    private void updateConfig(String key, String value) {
        try (FileReader reader = new FileReader(configFile);
             FileWriter writer = new FileWriter(configFile)) {
            Map<String, Object> data = yaml.load(reader);
            if (data == null) {
                data = new java.util.HashMap<>();
            }
            data.put(key, value);
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
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
}

