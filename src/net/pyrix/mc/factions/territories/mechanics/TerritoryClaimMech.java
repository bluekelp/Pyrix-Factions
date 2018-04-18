package net.pyrix.mc.factions.territories.mechanics;

import org.bukkit.scheduler.BukkitRunnable;

import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.territories.Territory;
import net.pyrix.mc.factions.utils.C;

public class TerritoryClaimMech extends BukkitRunnable {

	private Territory territory;
	private FPlayer player;

	public TerritoryClaimMech(FPlayer player, Territory territory) {
		this.player = player;
		this.territory = territory;
	}

	private String getMessage() {
		String message = "";
		String ColorCode = "&f";
		if (player.getFaction().getName().equalsIgnoreCase("Red")) {
			ColorCode = "&c";
		} else if (player.getFaction().getName().equalsIgnoreCase("Blue")) {
			ColorCode = "&9";
		}

		if (territory.getFactionClaim() == null) {
			message = ColorCode + "||||||||||||||&7|||||||||||||&f|||||||||||||";
		}
		return C.color(message);
	}

	@Override
	public void run() {
		String message = getMessage();
		for (int c = message.length() - 1; c >= 0; c--) {
			message = message.substring(0, c);

		}

	}

}
