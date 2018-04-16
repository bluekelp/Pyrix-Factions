package net.pyrix.mc.factions.storage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import net.pyrix.mc.factions.player.FPlayer;

public class FPlayerStorage {

	private List<FPlayer> players = new ArrayList<FPlayer>();

	public void add(FPlayer player) {
		players.add(player);
	}

	public FPlayer get(Player player) {
		for (FPlayer p : players) {
			if (p.getPlayer().getName().equals(player.getName())) {
				return p;
			}
		}
		return null;
	}

	public boolean exists(Player player) {
		return (get(player) != null);
	}

	public boolean exists(FPlayer player) {
		return players.contains(player);
	}

}
