package net.vitacraft.jmjda;
public class JMJDA {

    /**
     * Build a new JMBot instance
     * @return A new JMBot instance
     */
    @Deprecated
    public static JMBot build(){
        return new JMBot();
    }

    /**
     * Build a new JMBot instance
     * @param token The token of the bot
     * @return A new JMBot instance
     */
    public static JMBot build(String token){
        return new JMBot(token);
    }

    /**
     * Get the version of JMJDA
     * @return The version of JMJDA
     */
    public static String getVersion(){
        return "1.2.1";
    }
}
