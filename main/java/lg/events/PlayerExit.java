package lg.events;

import lg.Core;
import lg.utils.TeamPrefix;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerExit implements Listener {
    private final Core plugin;

    public PlayerExit(Core pl)
    {
        plugin = pl;
    }

    @EventHandler
    public void onPlayerExit(PlayerQuitEvent event)
    {
        new TeamPrefix(plugin).unregisterTeamPrefix(event.getPlayer());
    }
}
