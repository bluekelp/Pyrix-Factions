package net.pyrix.mc.factions.storage;

import net.pyrix.mc.factions.Manager;

public class StorageManager extends Manager {

	public static StorageManager get;

	public FPlayerStorage FPlayerStorage;
	public FactionStorage FactionStorage;

	public StorageManager() {
		onEnable();
	}

	@Override
	public void onEnable() {
		get = this;
		FPlayerStorage = new FPlayerStorage();
		FactionStorage = new FactionStorage();

	}
}
