package lg.commands;

import lg.Core;
import lg.utils.ChatSender;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TellChat implements CommandExecutor {
    private Core plugin;

    public TellChat(Core pl)
    {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("TPUChat.TellChat")) {
            sender.sendMessage(ChatColor.RED + "Você não tem permissão para executar este comando");
            return false;
        }
        Player receiver = Bukkit.getPlayer(args[0]);

        ArrayUtils.removeElement(args, 0);

        String message = "";
        for (String arg : args) {
            message += arg + " ";
        }

        new ChatSender(plugin).sendToTellChat((Player) sender, receiver, message);
        return true;
    }
}
