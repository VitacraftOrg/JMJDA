package net.vitacraft.jmjda.api.config;

import net.vitacraft.jmjda.api.util.log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigUtil {

    /**
     * File types supported by the ConfigUtil
     */
    public enum Filetype {
        YAML,
        JSON,
        XML,
        TOML
    }

    /**
     * Path types supported by the ConfigUtil
     */
    public enum PathType {
        ABSOLUTE,
        RELATIVE
    }

    /**
     * Get a config file of any type using the path and file type
     *
     * @param path a file path
     * @param filetype a file type
     * @return Configuration
     */
    public static Config getConfig(String path, Filetype filetype, PathType pathType){
        File configFile;

        if (pathType.equals(PathType.ABSOLUTE)){
            configFile = new File(path);
        } else if (pathType.equals(PathType.RELATIVE)){
            configFile = new File(System.getProperty("user.dir") + "/" + path);
        } else {
            return null;
        }

        if (!configFile.exists()){
            if (!copyConfigFromResources(path, pathType)){
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

    /**
     * Copy a config file from resources to the specified path
     *
     * @param path a file path
     * @param pathType a path type
     * @return boolean
     */
    private static boolean copyConfigFromResources(String path, PathType pathType) {
        String fileName = getFileNameFromAbsolutePath(path, pathType);
        ClassLoader classLoader = ConfigUtil.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream != null) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader reader = new BufferedReader(inputStreamReader);
                 BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
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

    /**
     * Create a new config file
     *
     * @param configFile a file
     */
    private static void createNewConfig(File configFile) {
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the file name from the absolute path
     *
     * @param absolutePath a file path
     * @param pathType a path type
     * @return String
     */
    private static String getFileNameFromAbsolutePath(String absolutePath, PathType pathType) {
        if(pathType.equals(PathType.ABSOLUTE)){
            Path path = Paths.get(absolutePath);
            return path.getFileName().toString();
        } else {
            return absolutePath;
        }
    }

}