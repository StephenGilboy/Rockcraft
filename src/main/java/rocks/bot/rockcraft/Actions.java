package rocks.bot.rockcraft;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Actions {
    public static void LightningStrike(Player player, Material material) {
        Material item = player.getInventory().getItemInMainHand().getType();
        if (item == material) {
            player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
        }
    }

    public static void ShootFirework(Player player, PlayerInteractEvent event) {
        Material currentItemType = player.getInventory().getItemInMainHand().getType();
        player.sendMessage("You have a " + currentItemType + " in your main hand.");
        if (currentItemType != Material.FIREWORK_ROCKET) return;

        try {
            Firework firework = (Firework) player.getInventory().getItemInMainHand();
/*
            ArrayList<Color> fireworkColors = new ArrayList();
            fireworkColors.add(Color.BLUE);
            fireworkColors.add(Color.PURPLE);
            World world = player.getWorld();
            FireworkEffect fireworkEffect = FireworkEffect.builder()
                    .withTrail()
                    .withColor(fireworkColors)
                    .flicker(true)
                    .build();
*/
            Vector playerVector = player.getVelocity();
            firework.setVelocity(new Vector(playerVector.getX() + 10, playerVector.getY() + 10 , playerVector.getZ()));
            firework.isShotAtAngle();
            event.setUseItemInHand(Event.Result.ALLOW);
        } catch (Exception ex) {
            player.sendMessage("There was an error shooting the firework.");
            player.sendMessage(ex.getMessage());
        }
    }
}
