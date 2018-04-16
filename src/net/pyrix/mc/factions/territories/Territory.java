package net.pyrix.mc.factions.territories;

import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.pyrix.mc.factions.Factions;

public class Territory {

	private Player Owner;
	private Location loc1;
	private Location loc2;
	private String name;
	private Block[] blocks = null;

	public Territory(Player Owner, String name, Location loc1) {
		this.loc1 = loc1;
		this.Owner = Owner;
		this.name = name;
	}

	public Territory(Player Owner, String name, Location loc1, Location loc2) {
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.Owner = Owner;
		this.name = name;
		blocks = getVolume();
	}

	public Player getOwner() {
		return Owner;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFirstLocation(Location loc1) {
		this.loc1 = loc1;
		this.blocks = getVolume();
	}

	public void setSecondLocation(Location loc2) {
		this.loc2 = loc2;
		this.blocks = getVolume();
	}

	public boolean complete() {
		return loc1 != null && loc2 != null;
	}

	public Location getFirstLocation() {
		return loc1;
	}

	public Location getSecondLocation() {
		return loc2;
	}

	public Block[] getRegion() {
		if (blocks.length == 0) {
			Bukkit.broadcastMessage("No volume exists, calculating...");
			return getVolume();
		}
		return blocks;
	}

	private Block[] getVolume() {
		if ((loc1 == null || loc2 == null) && !(loc1.getWorld().equals(loc2.getWorld()))) {
			Bukkit.broadcastMessage("Both locations haven't been initialized, cancelling calculation...");
			return null;
		}
		World w = loc1.getWorld();
		LinkedList<Block> blocks = new LinkedList<Block>();
		for (int x = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX()); x <= (loc2.getBlockX() >= loc1.getBlockX() ? loc2.getBlockX() : loc1
				.getBlockX()); x++) {
			for (int y = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY()); y <= (loc2.getBlockY() >= loc1.getBlockY() ? loc2.getBlockY() : loc1
					.getBlockY()); y++) {
				for (int z = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ()); z <= (loc2.getBlockZ() >= loc1.getBlockZ() ? loc2.getBlockZ() : loc1
						.getBlockZ()); z++) {
					blocks.add(w.getBlockAt(x, y, z));
				}
			}
		}
		Bukkit.broadcastMessage("Calculated Blocks: " + blocks.size());
		return blocks.toArray(new Block[blocks.size()]);
	}

}
