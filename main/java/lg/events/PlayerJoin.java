package lg.events;

import lg.Core;
import lg.utils.TeamPrefix;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private final Core plugin;

    public PlayerJoin(Core pl)
    {
        plugin = pl;

        for(World world : Bukkit.getWorlds()) {
            for (Player player : world.getPlayers()) {
                new TeamPrefix(plugin).setTeamPrefix(player);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        new TeamPrefix(plugin).setTeamPrefix(event.getPlayer());
    }
}
