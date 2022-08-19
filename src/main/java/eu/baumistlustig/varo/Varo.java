package eu.baumistlustig.varo;

import eu.baumistlustig.varo.commands.StartCommand;
import eu.baumistlustig.varo.commands.TimerCommand;
import eu.baumistlustig.varo.events.TimeEvents;
import eu.baumistlustig.varo.utils.Countdown;
import eu.baumistlustig.varo.utils.Timer;
import eu.baumistlustig.varo.utils.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Varo extends JavaPlugin {

    private static Varo plugin;

    private Timer timer;
    private Countdown countdown;
    private WorldBorder worldborder;

    public static Varo getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        // Timer init
        timer = new Timer(false, 0);

        // Worldborder init
        worldborder = new WorldBorder();

        // Countdown init
        countdown = new Countdown();

        // Commands
        getCommand("start").setExecutor(new StartCommand());
        getCommand("timer").setExecutor(new TimerCommand());

        // Events
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new TimeEvents(), this);
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

    public WorldBorder getWorldBorder() {
        return worldborder;
    }
}
