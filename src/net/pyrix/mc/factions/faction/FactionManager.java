package net.pyrix.mc.factions.faction;

import net.pyrix.mc.factions.storage.StorageManager;

public class FactionManager {

	public static Faction[] getFactions() {
		return StorageManager.get.FactionStorage.getFactions();
	}

}
