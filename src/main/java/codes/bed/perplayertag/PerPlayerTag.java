package codes.bed.perplayertag;

import codes.bed.perplayertag.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class PerPlayerTag extends JavaPlugin {

    public static PerPlayerTag Instance;


    @Override
    public void onEnable() {
        Instance = this;

        Logger.log("&aPerPlayerTag has been enabled");
    }

    @Override
    public void onDisable() {
        Logger.log("&cPerPlayerTag has been disabled");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setcharactername")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (args.length < 2) {
                    player.sendMessage("Usage: /setcharactername <player> <characterName>");
                    return true;
                }

                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage("Player not found.");
                    return true;
                }

                String characterName = args[1];
                for (int i = 2; i < args.length; i++) {
                    characterName += " " + args[i];
                }

                // Show the name tag to the sender only
                setCharacterTag(player, target, characterName);
                return true;
            }
        }
        return false;
    }

    // Method to set the custom character name visible only to the sender
    private void setCharacterTag(Player viewer, Player target, String characterName) {
        NameTagManager.getInstance().setTagForPlayer(viewer, target, characterName);
    }
}
