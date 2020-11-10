package com.github.Jena;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BukkitTaskTownyPearls extends BukkitRunnable {
    public Player p;

    public BukkitTaskTownyPearls(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
    }
}
