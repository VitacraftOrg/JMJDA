package net.vitacraft.jmjda.api.config;

import java.io.File;
import java.util.List;

public class JSONConfig implements Config {

    private final File configFile;

    /**
     * Create a new JSONConfig instance
     *
     * @param file the file to load the configuration from
     */
    public JSONConfig(File file){
        this.configFile = file;
    }

    /**
     * Get the name of the configuration file
     *
     * @return the name of the configuration file
     */
    @Override
    public String getName() {
        return configFile.getName();
    }

    /**
     * Get a string value from the configuration
     *
     * @param key the key to get the value from
     * @return the string value
     */
    @Override
    public String getString(String key) {
        return null;
    }

    /**
     * Get an integer value from the configuration
     *
     * @param key the key to get the value from
     * @return the integer value
     */
    @Override
    public int getInt(String key) {
        return 0;
    }

    /**
     * Get a boolean value from the configuration
     *
     * @param key the key to get the value from
     * @return the boolean value
     */
    @Override
    public boolean getBool(String key) {
        return false;
    }

    /**
     * Get a double value from the configuration
     *
     * @param key the key to get the value from
     * @return the double value
     */
    @Override
    public double getDouble(String key) {
        return 0;
    }

    /**
     * Get a long value from the configuration
     *
     * @param key the key to get the value from
     * @return the long value
     */
    @Override
    public long getLong(String key) {
        return 0;
    }

    /**
     * Get a list of strings from the configuration
     *
     * @param key the key to get the value from
     * @return the list of strings
     */
    @Override
    public List<String> getStringList(String key) {
        return null;
    }

    /**
     * Set a string value in the configuration
     *
     * @param key the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setString(String key, String value) {

    }

    /**
     * Set an integer value in the configuration
     *
     * @param key the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setInt(String key, int value) {

    }

    /**
     * Set a boolean value in the configuration
     *
     * @param key the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setBool(String key, boolean value) {

    }

    /**
     * Set a double value in the configuration
     *
     * @param key the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setDouble(String key, double value) {

    }

    /**
     * Set a long value in the configuration
     *
     * @param key the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setLong(String key, long value) {

    }
}
