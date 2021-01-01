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
            Firework firework = (Firework)world.spawnEntity(location, EntityType.FIREWORK);
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.addEffect(fireworkEffect);
            fireworkMeta.setPower(4);
            firework.setFireworkMeta(fireworkMeta);
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    Location fwLocation = firework.getLocation();
                    // How far has it flow up?
                    double difference = fwLocation.getY() - location.getY();
                    // Keep it on the same level as it started
                    fwLocation.setY(location.getY());

                    // Move it in the x direction for now
                    fwLocation.setX(fwLocation.getX() + difference);
                    if (firework.getLocation().getX() > location.getX() + 50) {
                        firework.detonate();
                    }
                }
            }, 1);
        } catch (Exception ex) {
            player.sendMessage("There was an error shooting the firework.");
            player.sendMessage(ex.getMessage());
        }
    }
}
