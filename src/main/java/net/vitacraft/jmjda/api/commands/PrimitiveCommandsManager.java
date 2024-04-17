package net.vitacraft.jmjda.api.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PrimitiveCommandsManager extends ListenerAdapter {
    private final HashMap<String, PrimitiveCommand> commands = new HashMap<>();
    private char prefix = '?';
    public PrimitiveCommandsManager(ShardManager shardManager){
        shardManager.addEventListener(this);
    }
    @Deprecated
    public PrimitiveCommandsManager(){}
    @Deprecated
    public void register(ShardManager shardManager){
        shardManager.addEventListener(this);
    }
    public void setPrefix(char prefix){
        this.prefix = prefix;
    }
    public void registerCommand(PrimitiveCommand command) {
        commands.put(command.getName().toLowerCase(),command);
    }
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String msg = event.getMessage().getContentRaw();
        String[] args = msg.split("\\s+");

        if (args.length < 1) return;
        String cmd = args[0];
        if (cmd.isEmpty() || cmd.charAt(0) != prefix) return;

        PrimitiveCommand command = commands.get(cmd.substring(1).toLowerCase());
        if (command == null) return;

        String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);

        Member member = event.getMember();
        if (member == null) return;
        for (Permission permission : command.getNeededPermissions()){
           if(member.hasPermission(permission)){
                command.execute(event, commandArgs);
                return;
            }
        }
        command.missingPermissions(event,args);
    }


    public List<PrimitiveCommand> getCommands(){ return commands.values().stream().toList(); }
    public char getPrefix(){ return prefix; }
}
