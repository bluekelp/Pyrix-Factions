package net.pyrix.mc.factions.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RightClickCheck implements Listener {

	private Map<UUID, ItemStack> PLAYERS_WITH_WANDS = new HashMap<UUID, ItemStack>();

	public void addPlayerWithWand(Player player, ItemStack wand) {
		PLAYERS_WITH_WANDS.put(player.getUniqueId(), wand);
	}

	public boolean playerHasWand(Player player) {
		return PLAYERS_WITH_WANDS.containsKey(player.getUniqueId());
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRightClickWithWand(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		Bukkit.broadcastMessage("1");
		if (action == Action.RIGHT_CLICK_BLOCK && PLAYERS_WITH_WANDS.containsKey(player.getUniqueId())) {
			Bukkit.broadcastMessage("2");
			ItemStack item = player.getInventory().getItemInMainHand();
			Bukkit.broadcastMessage("3");
			if (isSimilar(item, PLAYERS_WITH_WANDS.get(player.getUniqueId()))) {
				Bukkit.broadcastMessage("4");
				Block block = e.getClickedBlock();
				player.sendBlockChange(block.getLocation(), Material.WOOL, (byte) 0);
				Bukkit.broadcastMessage("5");
			}

		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Bukkit.broadcastMessage("Inventory: " + player.getInventory().getType());
		if (PLAYERS_WITH_WANDS.containsKey(player.getUniqueId()) && (e.getClickedInventory().getType() == InventoryType.PLAYER || e.getClickedInventory()
				.getType() == InventoryType.CREATIVE)) {
			Bukkit.broadcastMessage("2");
			ItemStack item = e.getCurrentItem();
			if (isSimilar(item, PLAYERS_WITH_WANDS.get(player.getUniqueId()))) {
				Bukkit.broadcastMessage("3");
				e.setCursor(new ItemStack(Material.AIR));
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onWandDrop(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		if (PLAYERS_WITH_WANDS.containsKey(player.getUniqueId())) {
			Item i = e.getItemDrop();
			ItemStack item = i.getItemStack();
			if (isSimilar(item, (PLAYERS_WITH_WANDS.get(player.getUniqueId())))) {
				Location iLoc = i.getLocation();
				player.getWorld().playSound(iLoc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
				player.getWorld().spawnParticle(Particle.DRAGON_BREATH, iLoc.getX(), iLoc.getY(), iLoc.getZ(), 10, 0.05f, 0.05f, 0.05f, 0.5f);
				i.remove();
				PLAYERS_WITH_WANDS.remove(player.getUniqueId());
			}
		}
	}

	private boolean isSimilar(ItemStack i1, ItemStack i2) {
		if (i1 == null || i2 == null) {
			return false;
		}
		if (i1.hasItemMeta() && i2.hasItemMeta()) {
			return i1.getType().equals(i2.getType()) && i1.getItemMeta().getDisplayName().equals(i2.getItemMeta().getDisplayName()) && i1.getItemMeta().getLore().containsAll(i2
					.getItemMeta().getLore()) && i1.getEnchantments().keySet().containsAll(i2.getEnchantments().keySet());
		} else {
			return i1.getType().equals(i2.getType()) && i1.getEnchantments().keySet().containsAll(i2.getEnchantments().keySet());
		}

	}

}