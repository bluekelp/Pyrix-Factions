package net.pyrix.mc.factions.player;

import org.bukkit.entity.Player;

import net.pyrix.mc.factions.faction.Faction;
import net.pyrix.mc.factions.misc.SpigotMisc;
import net.pyrix.mc.factions.storage.StorageManager;

public class FPlayer extends Faction {

	private static Player player;

	public FPlayer(Player player) {
		FPlayer.player = player;
		StorageManager.get.FPlayerStorage.add(this);
	}

	public Player getPlayer() {
		return player;
	}

	public void join(Faction faction) {
		super.addMember(this);
	}

	public SpigotMisc spigot() {
		return new SpigotMisc(player);
	}

}
