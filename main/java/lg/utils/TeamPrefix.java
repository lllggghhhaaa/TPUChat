package lg.utils;

import lg.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TeamPrefix {
    private final Core plugin;

    public TeamPrefix(Core pl)
    {
        plugin = pl;
    }

    public void setTeamPrefix(Player p)
    {
        List<String> groups = Arrays.asList(Core.getChat().getGroups());
        Collections.reverse(groups);
        int index = groups.indexOf(Core.getChat().getPrimaryGroup(p));

        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team color = board.getTeam(index + Core.getChat().getPrimaryGroup(p));
        if (color == null) {
            color = board.registerNewTeam(index + Core.getChat().getPrimaryGroup(p));
        }

        String rankcolor = "";

        try {
            if(plugin.getConfig().getString("GroupsColors." + Core.getChat().getPrimaryGroup(p)) != null)
            {
                rankcolor = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("GroupsColors." + Core.getChat().getPrimaryGroup(p)));
            }
            else
            {
                rankcolor = ChatColor.WHITE.toString();
            }
        }catch (Exception err) { rankcolor = ChatColor.WHITE.toString(); }

        String rank = rankcolor + "[" + Core.getChat().getPrimaryGroup(p) + "] " + ChatColor.GRAY;

        color.setPrefix(rank);
        color.addPlayer(p);
        p.setScoreboard(board);
    }

    public void unregisterTeamPrefix(Player p)
    {
        List<String> groups = Arrays.asList(Core.getChat().getGroups());
        int index = groups.indexOf(Core.getChat().getPrimaryGroup(p));

        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        board.getTeam(index + Core.getChat().getPrimaryGroup(p)).unregister();
    }
}
