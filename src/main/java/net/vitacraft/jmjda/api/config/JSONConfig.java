package net.vitacraft.jmjda.api.config;

import java.io.File;
import java.util.List;

public class JSONConfig implements Config{

    private final File configFile;

    public JSONConfig(File file){
        this.configFile = file;
    }

    @Override
    public String getName() {
        return configFile.getName();
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public int getInt(String key) {
        return 0;
    }

    @Override
    public boolean getBool(String key) {
        return false;
    }

    @Override
    public double getDouble(String key) {
        return 0;
    }

    @Override
    public long getLong(String key) {
        return 0;
    }

    @Override
    public List<String> getStringList(String key) {
        return null;
    }

    @Override
    public void setString(String key, String value) {

    }

    @Override
    public void setInt(String key, int value) {

    }

    @Override
    public void setBool(String key, boolean value) {

    }

    @Override
    public void setDouble(String key, double value) {

    }

    @Override
    public void setLong(String key, long value) {

    }
}
