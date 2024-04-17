package net.vitacraft.jmjda.api.util;

public class log {
    public static void console(String message){
        System.out.println(message);
    }
    public static void debug(String message){
        console("[DEBUG] > " + message);
    }
}
