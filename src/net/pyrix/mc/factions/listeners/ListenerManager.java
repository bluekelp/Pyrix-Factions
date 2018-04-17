package net.pyrix.mc.factions.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.Manager;

public class ListenerManager extends Manager {

	public static ListenerManager get;

	public RightClickCheck RightClickCheck;
	public FactionChecks FactionChecks;

	@Override
	public void onEnable() {
		get = this;
		this.FactionChecks = new FactionChecks();
		this.RightClickCheck = new RightClickCheck();
		System.out.println("Registering events...");
		registerEvents(RightClickCheck, FactionChecks);

	}

	private void registerEvents(Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getPluginManager().registerEvents(listener, Factions.getInstance());
		}
	}

}
