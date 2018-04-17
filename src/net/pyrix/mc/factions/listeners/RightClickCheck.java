package net.pyrix.mc.factions.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.misc.ItemManager;
import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.territories.Territory;
import net.pyrix.mc.factions.utils.C;

public class RightClickCheck implements Listener {

	private Map<UUID, Territory> firstLocation = new HashMap<UUID, Territory>();
	private Map<UUID, List<Block>> storedChangedBlocks = new HashMap<UUID, List<Block>>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRightClickWithWand(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		if (action == Action.RIGHT_CLICK_BLOCK && hasWand(player) && e.getHand() == EquipmentSlot.HAND) {
			ItemStack item = player.getInventory().getItemInMainHand();
			if (isWand(item)) {
				ItemStack wand = ItemManager.get.Wand.getWand(player);
				Block block = e.getClickedBlock();

				new BukkitRunnable() {
					public void run() {
						player.sendBlockChange(block.getLocation(), Material.WOOL, (byte) 0);
					}
				}.runTaskLater(Factions.getInstance(), 1L);

				if (!storedChangedBlocks.containsKey(player.getUniqueId())) {
					storedChangedBlocks.put(player.getUniqueId(), new ArrayList<Block>(Arrays.asList(new Block[] { block })));
				} else {
					List<Block> blocks = storedChangedBlocks.get(player.getUniqueId());
					blocks.add(block);
					storedChangedBlocks.put(player.getUniqueId(), blocks);
				}

				if (firstLocation.containsKey(player.getUniqueId())) {
					firstLocation.get(player.getUniqueId()).setSecondLocation(block.getLocation());

					firstLocation.remove(player.getUniqueId());
					clearBlocks(player);
					player.sendMessage(C.color("&a&oSuccessfully set area for territory, &2&o" + ItemManager.get.Wand.getTerritorialName(wand) + "&a!"));
				} else {
					firstLocation.put(player.getUniqueId(), new Territory(player, ItemManager.get.Wand.getTerritorialName(wand), block.getLocation()));
				}

			}

		}
	}

	private void clearBlocks(Player player) {
		if (storedChangedBlocks.containsKey(player.getUniqueId())) {
			for (Block b : storedChangedBlocks.get(player.getUniqueId())) {
				new BukkitRunnable() {
					@SuppressWarnings("deprecation")
					public void run() {
						player.sendBlockChange(b.getLocation(), b.getType(), (byte) b.getData());
					}
				}.runTaskLater(Factions.getInstance(), 1L);
			}
			storedChangedBlocks.remove(player.getUniqueId());
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
			player.getWorld().spawnParticle(Particle.DRAGON_BREATH, iLoc.getX(), iLoc.getY(), iLoc.getZ(), 10, 0.05f, 0.05f, 0.05f, 0.25f);
			firstLocation.remove(player.getUniqueId());
			clearBlocks(player);
			i.remove();
		}

	}

	// TODO: Remove this listener! Only being used for testing.
	@EventHandler
	public void onGeekJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (player.getName().equals("geekles")) {
			new FPlayer(player);
		}
	}

	private boolean hasWand(Player player) {
		return ItemManager.get.Wand.hasWand(player);
	}

	private boolean isWand(ItemStack i1) {
		return ItemManager.get.Wand.isWand(i1);

	}

}