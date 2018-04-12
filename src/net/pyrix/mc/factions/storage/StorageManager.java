package net.pyrix.mc.factions.storage;

import net.pyrix.mc.factions.Manager;
import net.pyrix.mc.factions.storage.files.LanguageStorage;

public class StorageManager extends Manager {

	public static StorageManager get;

	public FPlayerStorage FPlayerStorage;
	public FactionStorage FactionStorage;
	public ConfigurationStorage ConfigurationStorage;
	public LanguageStorage LanguageStorage;

	public StorageManager() {
		onEnable();
	}

	@Override
	public void onEnable() {
		get = this;
		FPlayerStorage = new FPlayerStorage();
		FactionStorage = new FactionStorage();
		ConfigurationStorage = new ConfigurationStorage();
		LanguageStorage = new LanguageStorage();
	}
}
