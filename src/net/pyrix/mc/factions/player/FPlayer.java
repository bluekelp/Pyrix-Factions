package net.pyrix.mc.factions.player;

import org.bukkit.entity.Player;

import net.pyrix.mc.factions.faction.Faction;
import net.pyrix.mc.factions.misc.SpigotMisc;
import net.pyrix.mc.factions.storage.StorageManager;

public class FPlayer {

	public enum FactionRole {
		Member, Moderator, Leader
	}

	private static Player player;
	private Faction faction;
	private FactionRole role = FactionRole.Member;

	public FPlayer(Player player, Faction faction) {
		FPlayer.player = player;
		this.faction = faction;
		StorageManager.get.FPlayerStorage.add(this);
	}

	public FPlayer(Player player, FactionRole role, Faction faction) {
		this(player, faction);
		this.role = role;
	}

	public Player getPlayer() {
		return player;
	}

	public void join(Faction faction) {
		this.faction = faction;
		faction.addMember(this);
	}

	public void quit() {
		this.faction.removeMember(this);
		StorageManager.get.FPlayerStorage.remove(this);
	}

	public SpigotMisc spigot() {
		return new SpigotMisc(player);
	}

	public void setRole(FactionRole role) {
		this.role = role;
	}

	public Faction getFaction() {
		return faction;
	}

}
