package net.pyrix.mc.factions.territories;

import java.io.File;
import java.util.LinkedList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.faction.Faction;
import net.pyrix.mc.factions.territories.animations.TerritorySymbolAnimation;
import net.pyrix.mc.factions.utils.C;

public class Territory {

	private Player Owner;
	private Location loc1;
	private Location loc2;
	private String name;
	private TerritorySymbolAnimation animation;
	private Block[] blocks = null;
	private File territoryFile;
	private Faction claimed;

	public Territory(Player Owner, String name, Location loc1) {
		this.loc1 = loc1;
		this.Owner = Owner;
		this.name = name;
		territoryFile = new File(Factions.getInstance().getDataFolder() + "/territories" + name + ".yml");
	}

	public Territory(Player Owner, String name, Location loc1, Location loc2) {
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.Owner = Owner;
		this.name = name;
		territoryFile = new File(Factions.getInstance().getDataFolder() + "/territories" + name + ".yml");
		blocks = getVolume();
		createIcon();
		TerritoryManager.i.storeTerritory(this);
	}

	public void setFactionClaim(Faction f) {
		this.claimed = f;
	}

	public Faction getFactionClaim() {
		return this.claimed;
	}

	public void save() {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(territoryFile);

		config.set("TerritoryName", name);
		config.set("Owner", Owner.getUniqueId());
		// store locations
		config.set("Locations.Point1.world", this.loc1.getWorld());
		config.set("Locations.Point1.x", this.loc1.getX());
		config.set("Locations.Point1.y", this.loc1.getY());
		config.set("Locations.Point1.z", this.loc1.getZ());

		config.set("Locations.Point2.world", this.loc2.getWorld());
		config.set("Locations.Point2.x", this.loc2.getX());
		config.set("Locations.Point2.y", this.loc2.getY());
		config.set("Locations.Point2.z", this.loc2.getZ());
	}

	public void destroy() {
		animation.stop();
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
		if (this.loc2 == null && loc2 != null) {
			TerritoryManager.i.storeTerritory(this);
		}
		this.loc2 = loc2;
		this.blocks = getVolume();
		createIcon();
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
		if (blocks == null) {
			return getVolume();
		}
		return blocks;
	}

	private Block[] getVolume() {
		if ((loc1 == null || loc2 == null) && !(loc1.getWorld().equals(loc2.getWorld()))) {
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
		return blocks.toArray(new Block[blocks.size()]);
	}

	public TerritorySymbolAnimation getSymbolAnimationMechanic() {
		return this.animation;
	}

	private void createIcon() {
		if (animation != null && (loc1 == null || loc2 == null) && !(loc1.getWorld().equals(loc2.getWorld()))) {
			return;
		}
		Location center = new Location(loc1.getWorld(), (loc1.getX() + loc2.getX()) / 2, (loc1.getY() + loc2.getY()) / 2, (loc1.getZ() + loc2.getZ()) / 2);
		ArmorStand as = loc1.getWorld().spawn(center, ArmorStand.class);
		as.setBasePlate(false);
		as.setRemoveWhenFarAway(false);
		as.setGravity(false);
		as.setInvulnerable(true);
		as.setCollidable(false);
		as.setCustomName(C.color("&9&l&o" + getName() + " Territory"));
		as.setCustomNameVisible(true);
		as.setVisible(false);

		/* Slime slime = loc1.getWorld().spawn(center, Slime.class);
		 * slime.setGravity(false); slime.setAI(false); slime.setInvulnerable(false);
		 * slime.setCollidable(false); slime.setSize(-8); new BukkitRunnable() { public
		 * void run() { ((CraftSlime) slime).getHandle().setInvisible(true); }
		 * }.runTaskLater(Factions.getInstance(), 1L); */
		center.setY(center.getY() - 1.5);
		ArmorStand head = loc1.getWorld().spawn(center, ArmorStand.class);
		head.setBasePlate(false);
		head.setRemoveWhenFarAway(false);
		head.setGravity(false);
		head.setInvulnerable(false);
		head.setCollidable(false);
		head.setVisible(false);
		head.setHelmet(new ItemStack(Material.BANNER, 1, (byte) 15));

		animation = new TerritorySymbolAnimation(head, as);
		animation.runTaskTimer(Factions.getInstance(), 0L, 1L);
	}

}
