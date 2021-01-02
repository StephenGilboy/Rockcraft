package rocks.bot.rockcraft;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rockcraft extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginCommand shootCommand = this.getCommand("shoot");
        if (shootCommand != null) {
            shootCommand.setExecutor(new ShootCommandExecutor(this));
        }
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Hello " + event.getPlayer().getName() + "! We have the Rockcraft plugin enabled.");
    }

    /**
     * Bind Actions to the onPlayerInteract event
     * @param event
     */
    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        // Actions.LightningStrike(player, Material.FISHING_ROD);
        Actions.ShootFireball(player, Material.NETHERITE_SWORD, this);
        // Actions.ShootFirework(player, this);
    }

    @EventHandler
    public void onEntityShootBowEvent(EntityShootBowEvent event) {
        event.getEntity().sendMessage("You shot a " + event.getProjectile().getType());
        if (event.getProjectile().getType() != EntityType.FIREWORK) return;
        Firework fw = (Firework) event.getProjectile();
        FireworkEffect fireworkEffect = FireworkEffect.builder()
                .withTrail()
                .withColor(Color.BLUE)
                .withFade(Color.LIME)
                .with(FireworkEffect.Type.BURST)
                .flicker(true)
                .trail(true)
                .build();
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(fireworkEffect);
        fw.setFireworkMeta(fwMeta);
    }
}
