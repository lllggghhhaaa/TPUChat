package lg.commands;

import lg.Core;
import lg.utils.ChatSender;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {
    private Core plugin;

    public StaffChat(Core pl)
    {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("TPUChat.StaffChat"))
        {
            sender.sendMessage(ChatColor.RED + "Você não tem permissão para executar este comando");
            return false;
        }

        String message = "";
        for (String arg : args)
        {
            message += arg + " ";
        }

        new ChatSender(plugin).sendToStaffChat((Player) sender, message);
        return true;
    }
}
