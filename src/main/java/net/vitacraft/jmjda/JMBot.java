package net.vitacraft.jmjda;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.vitacraft.jmjda.api.commands.PrimitiveCommandsManager;
import net.vitacraft.jmjda.api.config.ConfigUtil;
import net.vitacraft.jmjda.api.util.log;

import java.util.Objects;

public class JMBot {
    private final ConfigUtil configUtil = new ConfigUtil("config.yml");
    private ShardManager shardManager;
    private PrimitiveCommandsManager commandsManager;

    @Deprecated
    public JMBot(){
    }

    public JMBot(String token){
        login(token);
    }

    @Deprecated
    public void login(String token){
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT).enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setStatus(OnlineStatus.ONLINE);
        try {
            shardManager = builder.build();
            log.console("Successfully enabled");
        } catch (InvalidTokenException e) {
            log.console("Invalid Token! Disabling...");
        }
    }

    public PrimitiveCommandsManager getPrimitiveCommandsManager(){
        return Objects.requireNonNullElseGet(commandsManager, () -> commandsManager = new PrimitiveCommandsManager(shardManager));
    }

    public ShardManager getShardManager(){
        return shardManager;
    }

    public void setStatus(OnlineStatus status){
        shardManager.setStatus(status);
    }

    public void setActivity(Activity activity){
        shardManager.setActivity(activity);
    }

    public void shutdown(){
        shardManager.shutdown();
    }

    public ConfigUtil getConfig(){
        return configUtil;
    }
}
