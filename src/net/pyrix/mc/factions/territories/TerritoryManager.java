package net.pyrix.mc.factions.territories;

import java.util.ArrayList;

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

	public boolean removeTerritories(Territory... territories) {
		boolean removed = false;
		for (Territory t : territories) {
			if (t == null) {
				continue;
			}
			removed = removeTerritories(t.getName());
		}
		return removed;
	}

	public boolean removeTerritories(String... territories) {
		boolean removed = false;
		for (Territory t : storedTerritories) {
			if (t == null) {
				continue;
			}
			for (String name : territories) {
				if (t.getName().equalsIgnoreCase(name)) {
					t.remove();
					removed = true;
				}
			}

		}
		return removed;
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
