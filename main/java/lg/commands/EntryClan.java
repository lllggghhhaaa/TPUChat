package lg.commands;

import lg.Core;
import lg.utils.ClanUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EntryClan implements CommandExecutor {
    public Core plugin;

    public EntryClan(Core pl)
    {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        ClanUtils clanUtils = new ClanUtils(plugin);
        switch (args[0])
        {
            case "entry":
                clanUtils.registerClan((Player) sender, ChatColor.translateAlternateColorCodes('&', args[1]));
                sender.sendMessage("Voce entrou no clan " + ChatColor.translateAlternateColorCodes('&', args[1]));
                break;
            case "exit":
                String clan = clanUtils.getClan((Player) sender);
                clanUtils.removeCLan((Player) sender);
                sender.sendMessage("Voce saiu do clan " + clan);
                break;
        }
        return true;
    }
}
