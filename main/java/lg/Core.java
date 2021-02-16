package lg;

import lg.commands.GlobalChat;
import lg.commands.ReloadPlayers;
import lg.events.PlayerChat;
import lg.events.PlayerExit;
import lg.events.PlayerJoin;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private static Chat chat = null;

    @Override
    public void onEnable() {
        setupChat();
        RegisterEvents();
        RegisterCommands();
        LoadConfiguration();
    }

    public void LoadConfiguration()
    {
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

    public void RegisterEvents()
    {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerChat(this), this);
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerExit(this), this);
    }

    public void RegisterCommands()
    {
        getCommand("reloadplayerstab").setExecutor(new ReloadPlayers(this));
        getCommand("global").setExecutor(new GlobalChat(this));
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Chat getChat() {
        return chat;
    }
}
