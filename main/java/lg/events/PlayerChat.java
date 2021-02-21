package lg.events;

import lg.Core;
import lg.utils.ChatSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
    private final Core plugin;

    public PlayerChat(Core pl)
    {
        plugin = pl;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        if(plugin.getConfig().getBoolean("ChatEnabled")) {
            new ChatSender(plugin).sendToLocaleChat(event.getPlayer(), event.getMessage());
            event.setCancelled(true);
        }
    }
}
