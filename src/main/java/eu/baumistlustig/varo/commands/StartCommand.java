package eu.baumistlustig.varo.commands;

import eu.baumistlustig.varo.Varo;
import eu.baumistlustig.varo.utils.Countdown;
import eu.baumistlustig.varo.utils.Timer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check for players
        Timer timer = Varo.getPlugin().getTimer();
        if (timer.isRunning()) {
            sender.sendMessage(ChatColor.GRAY + "");
            return true;
        }
        sender.sendMessage(ChatColor.GRAY + "");
        Countdown countdown = Varo.getPlugin().getCountdown();
        countdown.run();

        return false;
    }
}
