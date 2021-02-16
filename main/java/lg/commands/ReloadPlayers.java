package lg.commands;

import lg.Core;
import lg.utils.TeamPrefix;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadPlayers implements CommandExecutor {
    private Core plugin;

    public ReloadPlayers(Core pl)
    {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(!sender.hasPermission("TPUTab.ReloadTabCommand"))
        {
            sender.sendMessage(ChatColor.RED + "Você não tem permissão para executar este comando");
            return false;
        }
        plugin.reloadConfig();
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            new TeamPrefix(plugin).setTeamPrefix(player);
        }
        sender.sendMessage(ChatColor.GREEN + "Lista de jogadores recarregada com sucesso");
        return true;
    }
}
