package eu.baumistlustig.varo.utils;

import org.bukkit.Bukkit;

public class WorldBorder {
    int counter = 1500;
    org.bukkit.WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();

    public void initBorder() {
        // Set center to (0 | 0)
        worldBorder.setCenter(0, 0);

        // Set start size to 5000
        worldBorder.setSize(10000);
    }

    public void shrinkBorder(int time) {
        if (time % 86400 != 0 || time == 0) return;

        if (worldBorder.getSize() <= 1500) return;

        worldBorder.setSize(worldBorder.getSize() - counter);

        if (counter <= 500) return;
        counter -= 250;
    }
}
