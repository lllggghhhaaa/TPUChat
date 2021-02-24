package lg;

import lg.commands.*;
import lg.events.PlayerChat;
import lg.events.PlayerExit;
import lg.events.PlayerJoin;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Core extends JavaPlugin {

    private static Chat chat = null;
    public File clanData;
    public FileConfiguration clanConfig;

    @Override
    public void onEnable() {
        LoadConfiguration();
        setupChat();
        RegisterEvents();
        RegisterCommands();
    }

    public void LoadConfiguration()
    {
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();

        clanData = new File(getDataFolder() + File.separator + "clan.yml");
        if(!clanData.exists())
        {
            try {
                clanData.createNewFile();
            }catch (Exception err) { }
        }
        clanConfig = YamlConfiguration.loadConfiguration(clanData);
    }

    public void RegisterEvents()
    {
        PluginManager pm = Bukkit.getPluginManager();
        if(getConfig().getBoolean("ChatEnabled"))
        {
            pm.registerEvents(new PlayerChat(this), this);
        }
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerExit(this), this);
    }

    public void RegisterCommands()
    {
        getCommand("reloadplayerstab").setExecutor(new ReloadPlayers(this));
        getCommand("tell").setExecutor(new TellChat(this));
        getCommand("staffchat").setExecutor(new StaffChat(this));
        getCommand("clan").setExecutor(new EntryClan(this));
        if(getConfig().getBoolean("ChatEnabled")) {
            getCommand("global").setExecutor(new GlobalChat(this));
        }
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
