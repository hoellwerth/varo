package eu.baumistlustig.varo.events;

import eu.baumistlustig.varo.Varo;
import eu.baumistlustig.varo.utils.Timer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class TimeEvents implements Listener {

    // Peacetime
    @EventHandler(ignoreCancelled = true)
    public void onAttack(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Timer timer = Varo.getPlugin().getTimer();
        if (timer.getTime() <= 300 && e.getDamager() instanceof Player) e.setCancelled(true);
    }
}
