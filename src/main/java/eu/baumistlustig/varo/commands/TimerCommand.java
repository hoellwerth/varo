package eu.baumistlustig.varo.commands;

import eu.baumistlustig.varo.Varo;
import eu.baumistlustig.varo.utils.Timer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Timer timer = Varo.getPlugin().getTimer();

        if (args.length == 0) {
            sendUsage(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "pause": {
                if (!timer.isRunning()) {
                    timer.setRunning(true);
                    sender.sendMessage(ChatColor.GRAY + "Timer has been " + ChatColor.GREEN + "resumed" + ChatColor.GRAY + "!");
                    break;
                }

                timer.setRunning(false);
                sender.sendMessage(ChatColor.GRAY + "Timer has been " + ChatColor.GREEN + "paused" + ChatColor.GRAY + "!");
                break;
            }
            case "resume": {
                if (timer.isRunning()) {
                    sender.sendMessage(ChatColor.GRAY + "Timer is already " + ChatColor.RED + "running" + ChatColor.GRAY + "!");
                    break;
                }

                timer.setRunning(true);
                sender.sendMessage(ChatColor.GRAY + "Timer has been " + ChatColor.GREEN + "paused" + ChatColor.GRAY + "!");
                break;
            }
            case "stop": {
                if (!timer.isRunning()) {
                    sender.sendMessage(ChatColor.GRAY + "Timer is not " + ChatColor.RED + "running" + ChatColor.GRAY + "!");
                    break;
                }

                timer.setTime(0);
                timer.setRunning(false);
                sender.sendMessage(ChatColor.GRAY + "Timer has been " + ChatColor.GREEN + "stopped" + ChatColor.GRAY + "!");
                break;
            }
            case "time":
            case "set": {
                if (!timer.isRunning()) {
                    sender.sendMessage(ChatColor.GRAY + "Timer is not " + ChatColor.RED + "running" + ChatColor.GRAY + "!");
                    break;
                }

                int time;
                try {
                    time = Integer.parseInt(args[1]);
                }
                catch (NumberFormatException e) {
                    time = timer.getTime();
                    sendUsage(sender);
                }

                timer.setTime(Integer.parseInt(args[1]));
                sender.sendMessage(ChatColor.GRAY + "Timer's time has been set to " + ChatColor.GREEN + time);
                break;
            }
            default: {
                sendUsage(sender);
                break;
            }
        }
        return false;
    }

    private void sendUsage(CommandSender s) {
        s.sendMessage(ChatColor.GRAY + "Usage" +
                ChatColor.DARK_GRAY + ": " +
                ChatColor.GREEN + "/timer resume" +
                ChatColor.GRAY + ", " +
                ChatColor.GREEN + "/timer pause" +
                ChatColor.GRAY + ", " +
                ChatColor.GREEN + "/timer stop" +
                ChatColor.GRAY + ", " +
                ChatColor.GREEN + "/timer (time/set) <time>"
        );
    }
}
