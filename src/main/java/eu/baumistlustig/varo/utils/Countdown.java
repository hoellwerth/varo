package eu.baumistlustig.varo.utils;

import eu.baumistlustig.varo.Varo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown {

    private int time = 15;

    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                sendChat();

                if (time < 0) cancel();

                time--;
            }
        }.runTaskTimer(Varo.getPlugin(), 20, 20);
    }

    private void sendChat() {
        Timer timer = Varo.getPlugin().getTimer();

        switch (time) {
            case 15:
                Bukkit.broadcastMessage(
                        ChatColor.GRAY + "[" + ChatColor.GREEN + "VARO" + ChatColor.GRAY + "]" +
                                " Spiel startet in " + ChatColor.GREEN + time + ChatColor.GRAY + " Sekunden!"
                );
                Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.NOTE_PIANO, 2,1));
                break;
            case 5:
            case 3:
            case 4:
            case 2: {
                Bukkit.broadcastMessage(
                        ChatColor.GRAY + "[" + ChatColor.GREEN + "VARO" + ChatColor.GRAY + "]" +
                                " Nur noch " + ChatColor.GREEN + time + ChatColor.GRAY + " Sekunden!"
                );
                Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.NOTE_PIANO, 2,1));
                break;
            }
            case 1: {
                Bukkit.broadcastMessage(
                        ChatColor.GRAY + "[" + ChatColor.GREEN + "VARO" + ChatColor.GRAY + "]" +
                                " Nur noch " + ChatColor.GREEN + time + ChatColor.GRAY + " Sekunde!"
                );
                Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.NOTE_PIANO, 2,1));
                break;
            }
            case 0:
                Bukkit.broadcastMessage(
                        ChatColor.GRAY + "[" + ChatColor.GREEN + "VARO" + ChatColor.GRAY + "]" +
                        ChatColor.GREEN + " Spiel startet " + ChatColor.BOLD +  "jetzt" + ChatColor.RESET + "" + ChatColor.GRAY + "!"
                );
                Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.NOTE_PIANO, 3,2));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle(ChatColor.GREEN.toString() + ChatColor.BOLD + "Let's Go!", ChatColor.GRAY + "Varo startet jetzt!");
                }
                // Initialise WorldBorder
                WorldBorder worldBorder = Varo.getPlugin().getWorldBorder();
                worldBorder.initBorder();

                // Start Timer
                timer.setRunning(true);
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.setExp(0);
                    player.setLevel(0);
                });
                break;
        }

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setLevel(time);
            player.setExp(time / 15f);
        });
    }
}
