package net.pyrix.mc.factions.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.Manager;

public class ListenerManager extends Manager {

	public static ListenerManager get;

	public RightClickCheck RightClickCheck;

	@Override
	public void onEnable() {
		get = this;
		this.RightClickCheck = new RightClickCheck();
		System.out.println("Registering events...");
		registerEvents(RightClickCheck);

	}

	private void registerEvents(Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getPluginManager().registerEvents(listener, Factions.getInstance());
		}
	}

}
