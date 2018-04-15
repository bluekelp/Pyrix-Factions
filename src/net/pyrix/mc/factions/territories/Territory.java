package net.pyrix.mc.factions.territories;

import java.util.LinkedList;

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
	private Block[] blocks = null;

	public Territory(Player Owner, Location loc1) {
		this.loc1 = loc1;
		this.Owner = Owner;
	}

	public Territory(Player Owner, Location loc1, Location loc2) {
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.Owner = Owner;
		blocks = getVolume();
	}

	public Player getOwner() {
		return Owner;
	}

	public void setFirstLocation(Location loc1) {
		this.loc1 = loc1;
		getVolume();
	}

	public void setSecondLocation(Location loc2) {
		this.loc2 = loc2;
		getVolume();
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
		return blocks;
	}

	private Block[] getVolume() {
		if ((loc1 == null || loc2 == null) && !(loc1.getWorld().equals(loc2.getWorld()))) {
			return null;
		}
		World w = loc1.getWorld();
		LinkedList<Block> blocks = new LinkedList<Block>();
		new BukkitRunnable() {
			public void run() {
				for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
					for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++) {
						for (int z = loc1.getBlockZ(); y <= loc2.getBlockZ(); z++) {
							blocks.add(w.getBlockAt(x, y, z));
						}
					}
				}
			}
		}.runTaskAsynchronously(Factions.getInstance());
		return blocks.toArray(new Block[blocks.size()]);
	}

}
