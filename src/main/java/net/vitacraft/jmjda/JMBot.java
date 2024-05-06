package net.vitacraft.jmjda;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.vitacraft.jmjda.api.commands.PrimitiveCommandsManager;
import net.vitacraft.jmjda.api.config.*;
import net.vitacraft.jmjda.api.util.log;
import net.vitacraft.jmjda.api.config.ConfigUtil.*;


import java.util.Objects;

public class JMBot {
    private final YAMLConfig configUtil = (YAMLConfig) ConfigUtil.getConfig("config.yml", Filetype.YAML, PathType.RELATIVE);
    private ShardManager shardManager;
    private PrimitiveCommandsManager commandsManager;

    /**
     * Create a new JMBot instance
     */
    @Deprecated
    public JMBot(){
    }

    /**
     * Create a new JMBot instance
     *
     * @param token the token to login with
     */
    public JMBot(String token){
        login(token);
    }

    /**
     * Login with a token
     *
     * @param token the token to login with
     */
    @Deprecated
    public void login(String token){
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT).enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setStatus(OnlineStatus.ONLINE);
        try {
            shardManager = builder.build();
            log.console("Successfully enabled");
        } catch (InvalidTokenException e) {
            log.console("Invalid Token! Disabling...");
        } catch (Exception exception){
            log.console("An unexpected Error accrued! Disabling...");
        }
    }

    /**
     * Get the PrimitiveCommandsManager
     *
     * @return the PrimitiveCommandsManager
     */
    public PrimitiveCommandsManager getPrimitiveCommandsManager(){
        return Objects.requireNonNullElseGet(commandsManager, () -> commandsManager = new PrimitiveCommandsManager(shardManager));
    }

    /**
     * Get the ShardManager
     *
     * @return the ShardManager
     */
    public ShardManager getShardManager(){
        return shardManager;
    }

    /**
     * Set the status of the bot
     *
     * @param status the status to set
     */
    public void setStatus(OnlineStatus status){
        shardManager.setStatus(status);
    }

    /**
     * Set the activity of the bot
     *
     * @param activity the activity to set
     */
    public void setActivity(Activity activity){
        shardManager.setActivity(activity);
    }

    /**
     * Shutdown the bot
     */
    public void shutdown(){
        shardManager.shutdown();
    }

    /**
     * Get the version of the bot
     *
     * @return the version of the bot
     */
    public Config getConfig(){
        return configUtil;
    }
}
