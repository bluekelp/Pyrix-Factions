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

	public FPlayer(Player player) {
		FPlayer.player = player;
		StorageManager.get.FPlayerStorage.add(this);
	}

	public FPlayer(Player player, FactionRole role) {
		this(player);
		this.role = role;
	}

	public FPlayer(Player player, FactionRole role, Faction faction) {
		this(player, role);
		this.faction = faction;
	}

	public Player getPlayer() {
		return player;
	}

	public void join(Faction faction) {
		this.faction = faction;
		faction.addMember(this);
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
