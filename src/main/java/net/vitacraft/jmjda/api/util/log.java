package net.vitacraft.jmjda.api.util;

import net.vitacraft.jmjda.api.config.Config;
import net.vitacraft.jmjda.api.config.ConfigUtil;
import net.vitacraft.jmjda.api.config.Filetype;

public class log {
    static Config config = ConfigUtil.getConfig("config.yml", Filetype.YAML);

    public static void console(String message){
        System.out.println(message);
    }

    public static void debug(String message){
        if(config.getBool("debug")){
            console("[DEBUG] > " + message);
        }
    }
}
