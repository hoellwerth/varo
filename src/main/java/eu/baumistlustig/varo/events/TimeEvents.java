package eu.baumistlustig.varo.events;

import eu.baumistlustig.varo.Varo;
import eu.baumistlustig.varo.utils.Timer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class TimeEvents implements Listener {

    Timer timer = Varo.getPlugin().getTimer();

    // Peacetime
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        if (timer.getTime() <= 300 && e.getDamager() instanceof Player) e.setCancelled(true);
    }

    // BlockEvent
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!timer.isRunning() || player.isOp() || player.hasPermission("*")) e.setCancelled(true);
    }
}
