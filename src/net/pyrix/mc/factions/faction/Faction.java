package net.pyrix.mc.factions.faction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;

import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.storage.StorageManager;
import net.pyrix.mc.factions.utils.C;

public class Faction {

	private List<FPlayer> members = new ArrayList<FPlayer>();

	private Player owner;
	private String name;

	private String ColorCode = "&f";

	public Faction(Player owner, String name) {
		this.name = name;
		this.owner = owner;
		FPlayer fplayer = StorageManager.get.FPlayerStorage.get(owner);
		if (fplayer == null) {
			fplayer = new FPlayer(owner, this);
		}
		addMember(fplayer);
		StorageManager.get.FactionStorage.add(this);
	}

	public String getName() {
		return name;
	}

	public void setColorCode(String s) {
		this.ColorCode = s;
	}

	public String getColorCode() {
		return this.ColorCode;
	}

	public Player getOwner() {
		return owner;
	}

	public void removeMember(FPlayer player) {
		members.remove(player);
	}

	public void addMember(FPlayer player) {
		members.add(player);
	}

	public FPlayer[] getMembers() {
		return members.toArray(new FPlayer[members.size()]);
	}

	public void destroy() {
		for (FPlayer player : members) {
			StorageManager.get.FPlayerStorage.remove(player);
			Player p = player.getPlayer();
			p.sendMessage(C.color("&c&oYou've been kicked from your faction for it no longer exists!"));
		}
		StorageManager.get.FactionStorage.remove(this);
	}

	public boolean equals(Faction faction) {
		return this.name.equals(faction.getName()) &&
				this.owner.getName().equals(faction.getOwner().getName()) &&
				members.containsAll(Arrays.asList(faction.getMembers()));
	}

}
