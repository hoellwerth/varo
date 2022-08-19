package eu.baumistlustig.varo;

import eu.baumistlustig.varo.commands.Start;
import eu.baumistlustig.varo.commands.TimerCommand;
import eu.baumistlustig.varo.utils.Countdown;
import eu.baumistlustig.varo.utils.Timer;
import org.bukkit.plugin.java.JavaPlugin;

public final class Varo extends JavaPlugin {

    private static Varo plugin;

    private Timer timer;
    private Countdown countdown;

    public static Varo getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        // Timer init
        timer = new Timer(false, 0);
        countdown = new Countdown();

        // Commands
        getCommand("start").setExecutor(new Start());
        getCommand("timer").setExecutor(new TimerCommand());

        // Events
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Timer getTimer() {
        return timer;
    }

    public Countdown getCountdown() {
        return countdown;
    }
}
