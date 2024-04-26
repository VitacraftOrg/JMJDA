package net.vitacraft.jmjda.api.util;

import net.vitacraft.jmjda.api.config.ConfigUtil;

public class log {
    static ConfigUtil config = new ConfigUtil("config.yml");
    public static void console(String message){
        System.out.println(message);
    }
    public static void debug(String message){
        if(config.getBool("debug")){
            console("[DEBUG] > " + message);
        }
    }
}
