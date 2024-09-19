package codes.bed.perplayertag;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;

public class NameTagManager {

    private static NameTagManager instance;
    private final Map<Player, Scoreboard> playerScoreboards = new HashMap<>();

    public static NameTagManager getInstance() {
        if (instance == null) {
            instance = new NameTagManager();
        }
        return instance;
    }

    // Set a custom name tag visible only to the viewer
    public void setTagForPlayer(Player viewer, Player target, String characterName) {
        Scoreboard scoreboard = playerScoreboards.computeIfAbsent(viewer, v -> Bukkit.getScoreboardManager().getNewScoreboard());

        Team team = scoreboard.getTeam(target.getName());
        if (team == null) {
            team = scoreboard.registerNewTeam(target.getName());
        }

        team.setPrefix(characterName + " ");
        team.addEntry(target.getName());

        // Set this scoreboard only for the viewer
        viewer.setScoreboard(scoreboard);
    }
}