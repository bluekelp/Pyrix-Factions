package net.pyrix.mc.factions.faction;

import net.pyrix.mc.factions.Manager;
import net.pyrix.mc.factions.storage.StorageManager;

public class FactionManager extends Manager {

	public static Faction[] getFactions() {
		return StorageManager.get.FactionStorage.getFactions();
	}

	@Override
	public void onEnable() {
		// TODO Add some stuff here
	}

}
