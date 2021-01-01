package rocks.bot.rockcraft;

import org.bukkit.Material;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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
        Actions.ShootFireball(player, Material.FISHING_ROD, this);
        Actions.ShootFirework(player, this);
    }
}
