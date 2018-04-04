package net.pyrix.mc.factions.storage;

public class StorageManager {

	public static StorageManager get;

	public FPlayerStorage FPlayerStorage;
	public FactionStorage FactionStorage;

	public StorageManager() {
		onEnable();
	}

	void onEnable() {
		get = this;
		FPlayerStorage = new FPlayerStorage();
		FactionStorage = new FactionStorage();

	}
}
