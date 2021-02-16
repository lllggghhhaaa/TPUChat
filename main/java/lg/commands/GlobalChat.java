package lg.commands;

import lg.Core;
import lg.utils.ChatSender;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalChat implements CommandExecutor {
    private final Core plugin;

    public GlobalChat(Core pl)
    {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("TPUChat.GlobalChat"))
        {
            sender.sendMessage(ChatColor.RED + "Você não tem permissão para executar este comando");
            return false;
        }
        String message = "";
        for (String arg : args)
        {
            message += arg + " ";
        }
        new ChatSender(plugin).sendToGlobalChat((Player) sender, message);
        return true;
    }
}
