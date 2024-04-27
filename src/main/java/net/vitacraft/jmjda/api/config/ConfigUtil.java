package net.vitacraft.jmjda.api.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigUtil {

    /**
     * Get a config file of any type using the path and file type
     *
     * @param path a file path
     * @param filetype a file type
     * @return Configuration
     */
    public static Config getConfig(String path, Filetype filetype){
        File configFile = new File(path);
        if (!configFile.exists()){
            if (!copyConfigFromResources(path)){
                createNewConfig(configFile);
            }
        }

        switch (filetype){
            case YAML -> {
                return new YAMLConfig(configFile);
            }
            case JSON -> {
                return new JSONConfig(configFile);
            }
            default -> {
                return null;
            }
        }
    }

    private static boolean copyConfigFromResources(String fileName) {
        ClassLoader classLoader = ConfigUtil.class.getClassLoader();
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

    private static void createNewConfig(File configFile) {
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}