package net.vitacraft.jmjda;
public class JMJDA {
    public static JMBot build(){
        return new JMBot();
    }

    public static JMBot build(String token){
        return new JMBot(token);
    }
}
