package lg.utils;

import lg.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ChatSender
{
    private final Core plugin;
    private final int distanceLocalChat;

    public ChatSender(Core pl)
    {
        plugin = pl;
        distanceLocalChat = pl.getConfig().getInt("DistanceLocalChat");
    }

    public void sendToLocaleChat(Player sender, String message)
    {
        new BukkitRunnable() {
            @Override
            public void run() {
                List<Entity> nearbyE = sender.getNearbyEntities(distanceLocalChat, distanceLocalChat, distanceLocalChat);

                String finalMessage = plugin.getConfig().getString("Message");
                if(plugin.getConfig().getString("GroupsColors." + Core.getChat().getPrimaryGroup(sender)) != null)
                {
                    finalMessage = finalMessage.replace("%groupcolor%", plugin.getConfig().getString("GroupsColors." + Core.getChat().getPrimaryGroup(sender)));
                    finalMessage = ChatColor.translateAlternateColorCodes('&', finalMessage);
                }
                else {
                    finalMessage = finalMessage.replace("%groupcolor%", "");
                }
                if(finalMessage.replace("%group%", Core.getChat().getPrimaryGroup(sender)) != null) {
                    finalMessage = finalMessage.replace("%group%", Core.getChat().getPrimaryGroup(sender));
                }else {
                    finalMessage = finalMessage.replace("%group%", "");
                }
                finalMessage = finalMessage.replace("%player%", sender.getDisplayName());
                finalMessage = finalMessage.replace("%message%", message);

                List<Player> playersList = new ArrayList<>();

                for(Entity p : nearbyE)
                {
                    if (p instanceof Player) {
                        playersList.add((Player) p);
                    }
                }
                if(playersList.isEmpty())
                {
                    sender.sendMessage(ChatColor.YELLOW + "[L] " + ChatColor.GRAY + finalMessage);
                    sender.sendMessage(ChatColor.YELLOW + "Nenhum jogador próximo :(");
                }
                else {
                    for (Player p : playersList) {
                        sender.sendMessage(finalMessage);
                        p.sendMessage(finalMessage);
                    }
                }
            }
        }.runTaskLater(plugin, 5);
    }

    public void sendToGlobalChat(Player sender, String message)
    {
        String finalMessage = plugin.getConfig().getString("Message");
        if(plugin.getConfig().getString("GroupsColors." + Core.getChat().getPrimaryGroup(sender)) != null)
        {
            finalMessage = finalMessage.replace("%groupcolor%", plugin.getConfig().getString("GroupsColors." + Core.getChat().getPrimaryGroup(sender)));
            finalMessage = ChatColor.translateAlternateColorCodes('&', finalMessage);
        }
        else {
            finalMessage = finalMessage.replace("%groupcolor%", "");
        }
        if(finalMessage.replace("%group%", Core.getChat().getPrimaryGroup(sender)) != null) {
            finalMessage = finalMessage.replace("%group%", Core.getChat().getPrimaryGroup(sender));
        }else {
            finalMessage = finalMessage.replace("%group%", "");
        }
        finalMessage = finalMessage.replace("%player%", sender.getDisplayName());
        finalMessage = finalMessage.replace("%message%", message);
        finalMessage = ChatColor.translateAlternateColorCodes('&', finalMessage);

        for (Player p : Bukkit.getOnlinePlayers())
        {
            p.sendMessage(ChatColor.DARK_GRAY + "[G] " + ChatColor.GRAY + finalMessage);
        }
    }

    public void sendToStaffChat(Player sender, String message)
    {
        String finalMessage = plugin.getConfig().getString("Message");
        if(plugin.getConfig().getString("GroupsColors." + Core.getChat().getPrimaryGroup(sender)) != null)
        {
            finalMessage = finalMessage.replace("%groupcolor%", plugin.getConfig().getString("GroupsColors." + Core.getChat().getPrimaryGroup(sender)));
            finalMessage = ChatColor.translateAlternateColorCodes('&', finalMessage);
        }
        else {
            finalMessage = finalMessage.replace("%groupcolor%", "");
        }
        if(finalMessage.replace("%group%", Core.getChat().getPrimaryGroup(sender)) != null) {
            finalMessage = finalMessage.replace("%group%", Core.getChat().getPrimaryGroup(sender));
        }else {
            finalMessage = finalMessage.replace("%group%", "");
        }
        finalMessage = finalMessage.replace("%player%", sender.getDisplayName());
        finalMessage = finalMessage.replace("%message%", message);
        finalMessage = ChatColor.translateAlternateColorCodes('&', finalMessage);

        for (Player p : Bukkit.getOnlinePlayers())
        {
            if(p.hasPermission("TPUChat.StaffChat")) {
                p.sendMessage(ChatColor.DARK_RED + "[S] " + ChatColor.GRAY + finalMessage);
            }
        }
    }

    public void sendToTellChat(Player sender, Player receiver, String message) {
        String finalMessage = plugin.getConfig().getString("Message");
        if (plugin.getConfig().getString("GroupsColors." + Core.getChat().getPrimaryGroup(sender)) != null) {
            finalMessage = finalMessage.replace("%groupcolor%", plugin.getConfig().getString("GroupsColors." + Core.getChat().getPrimaryGroup(sender)));
            finalMessage = ChatColor.translateAlternateColorCodes('&', finalMessage);
        } else {
            finalMessage = finalMessage.replace("%groupcolor%", "");
        }
        if (finalMessage.replace("%group%", Core.getChat().getPrimaryGroup(sender)) != null) {
            finalMessage = finalMessage.replace("%group%", Core.getChat().getPrimaryGroup(sender));
        } else {
            finalMessage = finalMessage.replace("%group%", "");
        }
        finalMessage = finalMessage.replace("%player%", sender.getDisplayName());
        finalMessage = finalMessage.replace("%message%", message);
        finalMessage = ChatColor.translateAlternateColorCodes('&', finalMessage);

        sender.sendMessage(ChatColor.WHITE + "[T] " + ChatColor.GRAY + finalMessage);
        receiver.sendMessage(ChatColor.WHITE + "[T] " + ChatColor.GRAY + finalMessage);
    }
}
