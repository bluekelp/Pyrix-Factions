package net.pyrix.mc.factions.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.misc.ItemManager;
import net.pyrix.mc.factions.territories.Territory;
import net.pyrix.mc.factions.territories.TerritoryManager;

public class RightClickCheck implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler

	public void onRightClickWithWand(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		if (action == Action.RIGHT_CLICK_BLOCK && hasWand(player) && e.getHand() == EquipmentSlot.HAND) {
			ItemStack item = player.getInventory().getItemInMainHand();
			if (isWand(item)) {
				Block block = e.getClickedBlock();
				TerritoryManager.i.storeTerri tory(new Territory(player, block.getLocation()));
				new BukkitRunnable() {
					public void run() {
						player.sendBlockChange(block.getLocation(), Material.WOOL, (byte) 0);
					}
				}.runTaskLater(Factions.getInstance(), 1L);

			}

		}
	}

	@EventHandler
	public void onWandDrop(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		Item i = e.getItemDrop();
		ItemStack item = i.getItemStack();
		if (isWand(item)) {
			Location iLoc = i.getLocation();
			player.getWorld().playSound(iLoc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
			player.getWorld().spawnParticle(Particle.DRAGON_BREATH, iLoc.getX(), iLoc.getY(), iLoc.getZ(), 10, 0.05f, 0.05f, 0.05f, 0.1f);
			i.remove();
		}

	}

	private boolean hasWand(Player player) {
		return ItemManager.get.Wand.hasWand(player);
	}

	private boolean isWand(ItemStack i1) {
		return ItemManager.get.Wand.isWand(i1);

	}

}