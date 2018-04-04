package net.pyrix.mc.factions;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.storage.StorageManager;

public class FPyrix {

	public static FPlayer getFPlayer(String name) {
		return StorageManager.get.FPlayerStorage.get(Bukkit.getPlayer(name));
	}

	public static FPlayer getFPlayer(UUID id) {
		return StorageManager.get.FPlayerStorage.get(Bukkit.getPlayer(id));
	}

	public static FPlayer getFPlayer(Player player) {
		return StorageManager.get.FPlayerStorage.get(player);
	}

	public static FPlayer convertFromPlayer(Player player) {
		if (!StorageManager.get.FPlayerStorage.exists(player))
			return new FPlayer(player);
		return getFPlayer(player);
	}

}
