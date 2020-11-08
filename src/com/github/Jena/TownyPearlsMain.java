package com.github.Jena;

import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TownyPearlsMain extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEnderPearl(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof EnderPearl) {
            if (event.getEntity().getShooter() instanceof Player) {
                Player p = (Player) event.getEntity().getShooter();
                boolean itemuse = PlayerCacheUtil.getCachePermission(p, event.getLocation(), Material.ENDER_PEARL, TownyPermission.ActionType.ITEM_USE);
                if (p.hasPermission("townypearls") && itemuse) {
                    p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
                }
            }
        }
    }
}
