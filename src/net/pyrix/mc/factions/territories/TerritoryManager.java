package net.pyrix.mc.factions.territories;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import net.pyrix.mc.factions.Manager;

public class TerritoryManager extends Manager {

	public static TerritoryManager i;

	private static ArrayList<Territory> storedTerritories = new ArrayList<Territory>();

	@Override
	public void onEnable() {
		i = this;
	}

	public void storeTerritory(Territory territory) {
		storedTerritories.add(territory);
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
