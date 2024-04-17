package net.vitacraft.jmjda.api.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface PrimitiveCommand {
    String getName();
    String getDescription();
    Permission[] getNeededPermissions();
    Channel[] getPermittedChannels();
    void execute(MessageReceivedEvent event, String[] args);
    void missingPermissions(MessageReceivedEvent event, String[] args);
}
