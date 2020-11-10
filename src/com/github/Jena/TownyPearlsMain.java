package com.github.Jena;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class TownyPearlsMain extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEnderPearl(ProjectileLaunchEvent event) throws NotRegisteredException {
        if (event.getEntity() instanceof EnderPearl) {
            if (event.getEntity().getShooter() instanceof Player) {
                Player p = (Player) event.getEntity().getShooter();
                Resident resident = TownyUniverse.getInstance().getDataSource().getResident(p.getName());
                boolean itemuse = PlayerCacheUtil.getCachePermission(p, event.getLocation(), Material.ENDER_PEARL, TownyPermission.ActionType.ITEM_USE);
                Town town = TownyAPI.getInstance().getTownBlock(p.getLocation()).getTown();

                if (p.hasPermission("townypearls") && itemuse && resident.getTown() == town) {
                    BukkitTask task = new BukkitTaskTownyPearls(p).runTaskLater(this, 20);
                }
            }
        }
    }
}
