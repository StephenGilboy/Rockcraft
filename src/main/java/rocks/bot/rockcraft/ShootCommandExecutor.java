package rocks.bot.rockcraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShootCommandExecutor implements CommandExecutor {
    private final Rockcraft plugin;

    public ShootCommandExecutor(Rockcraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("shoot")) return false;
        sender.sendMessage("Bang!");
        return true;
    }
}
