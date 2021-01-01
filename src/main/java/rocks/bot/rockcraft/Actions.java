package rocks.bot.rockcraft;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Actions {
    public static void LightningStrike(Player player, Material material) {
        Material item = player.getInventory().getItemInMainHand().getType();
        if (item == material) {
            player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
        }
    }

    public static void ShootFirework(Player player, JavaPlugin plugin) {
        Material currentItemType = player.getInventory().getItemInMainHand().getType();
        if (currentItemType != Material.FIREWORK_ROCKET) return;

        try {
            World world = player.getWorld();
            FireworkEffect fireworkEffect = FireworkEffect.builder()
                    .withTrail()
                    .withColor(Color.BLUE)
                    .withFade(Color.LIME)
                    .with(FireworkEffect.Type.BURST)
                    .flicker(true)
                    .trail(true)
                    .build();
            Location location = player.getLocation();
            location.add(new Vector(5, 0, 0));
            Firework firework = (Firework)world.spawnEntity(location, EntityType.FIREWORK);
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.addEffect(fireworkEffect);
            fireworkMeta.setPower(12);
            firework.setFireworkMeta(fireworkMeta);
        } catch (Exception ex) {
            player.sendMessage("There was an error shooting the firework.");
            player.sendMessage(ex.getMessage());
        }
    }
}
