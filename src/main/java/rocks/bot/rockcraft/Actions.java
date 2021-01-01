package rocks.bot.rockcraft;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Actions {
    public static void LightningStrike(Player player, Material material) {
        Material item = player.getInventory().getItemInMainHand().getType();
        player.sendMessage("You have a " + item + " in your main hand.");
        if (item == material) {
            player.sendMessage("Flash!");
            player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
        }
    }
}
