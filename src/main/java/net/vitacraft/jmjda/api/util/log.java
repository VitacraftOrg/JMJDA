package net.vitacraft.jmjda.api.util;

import net.vitacraft.jmjda.api.config.Config;
import net.vitacraft.jmjda.api.config.ConfigUtil;
import net.vitacraft.jmjda.api.config.ConfigUtil.*;

public class log {

    static Config config = ConfigUtil.getConfig("config.yml", Filetype.YAML, PathType.RELATIVE);

    /**
     * Log a message to the console
     *
     * @param message the message to log
     */
    public static void console(String message){
        System.out.println(message);
    }

    /**
     * Log a message to the console
     *
     * @param message the message to log
     */
    public static void debug(String message){
        if(config.getBool("debug")){
            console("[DEBUG] > " + message);
        }
    }
}
