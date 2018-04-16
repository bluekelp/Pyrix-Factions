package net.pyrix.mc.factions.territories;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.Player;

import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.Manager;
import net.pyrix.mc.factions.territories.mechanics.TerritoryMonitor;

public class TerritoryManager extends Manager {

	public static TerritoryManager i;

	private static ArrayList<Territory> storedTerritories = new ArrayList<Territory>();

	@Override
	public void onEnable() {
		i = this;
		new TerritoryMonitor().runTaskTimerAsynchronously(Factions.getInstance(), 5L, 10L);
	}

	public void storeTerritory(Territory territory) {
		storedTerritories.add(territory);
	}

	public void removeTerritories(Territory... territories) {
		storedTerritories.removeAll(Arrays.asList(territories));
	}

	public ArrayList<Territory> getStoredTerritories() {
		return storedTerritories;
	}

	public Territory getTerritoryFromOwner(Player Owner) {
		for (Territory territory : storedTerritories) {
			if (territory.getOwner().getUniqueId() == Owner.getUniqueId()) {
				return territory;
			}
		}
		return null;
	}

}
