package rocks.bot.rockcraft;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Actions {
    public static void LightningStrike(Player player, Material material) {
        Material item = player.getInventory().getItemInMainHand().getType();
        if (item == material) {
            player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
        }
    }
}
