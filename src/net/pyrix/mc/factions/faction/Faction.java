package net.pyrix.mc.factions.faction;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import edu.emory.mathcs.backport.java.util.Arrays;
import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.storage.StorageManager;

public class Faction {

	private List<FPlayer> members = new ArrayList<FPlayer>();

	private Player owner;
	private String name;

	public Faction(Player owner, String name) {
		this.name = name;
		this.owner = owner;
		FPlayer fplayer = StorageManager.get.FPlayerStorage.get(owner);
		if (fplayer == null) {
			fplayer = new FPlayer(owner);
		}
		addMember(fplayer);
		StorageManager.get.FactionStorage.add(this);
	}

	public String getName() {
		return name;
	}

	public Player getOwner() {
		return owner;
	}

	public void addMember(FPlayer player) {
		members.add(player);
	}

	public FPlayer[] getMembers() {
		return members.toArray(new FPlayer[members.size()]);
	}

	public boolean equals(Faction faction) {
		return this.name.equals(faction.getName()) && this.owner.getName().equals(faction.getOwner().getName()) && members.containsAll(Arrays.asList(faction.getMembers()));
	}

}
