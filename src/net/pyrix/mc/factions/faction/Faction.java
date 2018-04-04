package net.pyrix.mc.factions.faction;

import java.util.ArrayList;
import java.util.List;

import net.pyrix.mc.factions.player.FPlayer;

public class Faction {

	private List<FPlayer> members = new ArrayList<FPlayer>();

	protected void addMember(FPlayer player) {
		members.add(player);
	}

}
