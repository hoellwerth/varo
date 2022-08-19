package eu.baumistlustig.varo.utils;

import eu.baumistlustig.varo.Varo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public class Timer {
    private boolean gameRunning;
    private int gameTime;

    public Timer (boolean gameRunning, int gameTime) {
        this.gameRunning = gameRunning;
        this.gameTime = gameTime;
        run();
    }

    public boolean isRunning() {
        return gameRunning;
    }

    public void setRunning(boolean running) {
        this.gameRunning = running;
    }

    public int getTime() {
        return gameTime;
    }

    public void setTime(int time) {
        this.gameTime = time;
    }

    public void sendActionBar(String displayTime) {
        int playerCount = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {

            if (!isRunning()) {
                continue;
            }

            if (p.getGameMode().equals(GameMode.SURVIVAL)) playerCount += 1;

            new ActionBar().sendActionBar(p, ChatColor.GRAY + "Läuft seit: "
                    + ChatColor.BOLD + ChatColor.AQUA + displayTime
                    + ChatColor.RESET + ChatColor.GREEN + " ┃ "
                    + ChatColor.GRAY + "Noch " + ChatColor.GREEN + ChatColor.BOLD + playerCount + ChatColor.RESET + ChatColor.GRAY + " Spieler übrig");
        }
    }

    private String getDisplayTime(int time) {

        int days = (int) TimeUnit.SECONDS.toDays(time);
        long hours = TimeUnit.SECONDS.toHours(time) - (days * 24L);

        long minutes = TimeUnit.SECONDS.toMinutes(time) -
                (TimeUnit.SECONDS.toHours(time)* 60);

        long seconds = TimeUnit.SECONDS.toSeconds(time) -
                (TimeUnit.SECONDS.toMinutes(time) *60);

        if (minutes == 0 && hours == 0 && days == 0) {
            return String.format("%02d", seconds);
        }

        if (hours == 0 && days == 0) {
            return String.format("%02d:%02d", minutes, seconds);
        }

        if (days == 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

        return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
    }

    void disablePeaceTime() {
        if (getTime() == 300) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(
                        ChatColor.GREEN.toString() + ChatColor.BOLD + "PeaceTime vorbei!",
                        ChatColor.GRAY + "Ihr könnt nun andere Spieler attackieren!"
                );
            }
        }
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Shrink World-border
                WorldBorder worldBorder = Varo.getPlugin().getWorldBorder();
                worldBorder.shrinkBorder(gameTime);

                sendActionBar(getDisplayTime(gameTime));

                disablePeaceTime();

                if (!isRunning()) {
                    return;
                }

                setTime(getTime() + 1);
            }
        }.runTaskTimer(Varo.getPlugin(), 20, 20);
    }
}
