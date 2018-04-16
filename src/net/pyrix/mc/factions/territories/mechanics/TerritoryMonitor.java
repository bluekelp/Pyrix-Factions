package net.pyrix.mc.factions.territories.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.territories.Territory;
import net.pyrix.mc.factions.territories.TerritoryManager;

public class TerritoryMonitor extends BukkitRunnable {

	@Override
	public void run() {
		for (FPlayer FPlayer : Factions.getAllOnlineFPlayers()) {
			Territory territory = getTerritorialArea(FPlayer);
			if (territory != null) {
				FPlayer.spigot().sendActionBarMessage("&2You've entered the, &2" + territory.getName() + "&a territory!");
			}
		}
	}

	private Territory getTerritorialArea(FPlayer player) {
		Location location = player.getPlayer().getLocation().getBlock().getLocation();
		for (Territory territory : TerritoryManager.i.getStoredTerritories()) {
			for (Block block : territory.getRegion()) {
				if (block.getLocation().equals(location)) {
					Bukkit.broadcastMessage("Inside territory!");
					return territory;
				}
			}
		}
		return null;
	}

}
