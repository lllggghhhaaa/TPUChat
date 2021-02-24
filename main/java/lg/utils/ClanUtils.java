package lg.utils;

import lg.Core;
import org.bukkit.entity.Player;

import java.util.Map;

public class ClanUtils {
    private Core plugin;

    public ClanUtils(Core pl)
    {
        plugin = pl;
    }

    public void registerClan(Player player, String clan)
    {
        plugin.clanConfig.set(player.getUniqueId().toString(), clan);
    }

    public String getClan(Player player)
    {
        if(plugin.clanConfig.getString(player.getUniqueId().toString()) != null)
            return plugin.clanConfig.getString(player.getUniqueId().toString());
        return null;
    }

    public void removeCLan(Player player)
    {
        plugin.clanConfig.set(player.getUniqueId().toString(), null);
    }
}
