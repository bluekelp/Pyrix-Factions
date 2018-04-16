package net.pyrix.mc.factions.misc.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.pyrix.mc.factions.utils.C;

public class Wand {

	private final String TerritoryLorePrefix = "&7&oTerritory: &8";

	public ItemStack getWand(String name) {
		ItemStack wand = new ItemStack(Material.BLAZE_ROD, 1);
		wand.addUnsafeEnchantment(Enchantment.DURABILITY, -1);
		ItemMeta meta = wand.getItemMeta();
		if (name == "" || name == null) {
			meta.setLore(new ArrayList<String>(Arrays.asList(new String[] { C.color("&c&oDrop me to remove me!"), })));
		} else {
			meta.setLore(new ArrayList<String>(Arrays.asList(new String[] { C.color(TerritoryLorePrefix + name), C.color("&c&oDrop me to remove me!"), })));
		}
		meta.setDisplayName(C.color("&9&l&oTerritory Wand"));
		wand.setItemMeta(meta);
		return wand;
	}

	public ItemStack getWand() {
		return getWand("");
	}

	public String getTerritorialName(ItemStack wand) {
		if (wand == null || !wand.hasItemMeta()) {
			return null;
		}
		if (wand.getItemMeta().getLore().isEmpty()) {
			return null;
		}
		return wand.getItemMeta().getLore().get(0).replace(C.color(TerritoryLorePrefix), "");
	}

	public ItemStack getWand(Player player) {
		for (ItemStack i : player.getInventory().getStorageContents()) {
			if (isWand(i)) {
				return i;
			}
		}
		return null;
	}

	public boolean hasWand(Player player) {
		ItemStack wand = getWand(player);
		return wand != null ? true : false;
	}

	@SuppressWarnings("deprecation")
	private boolean sameEnchants(Map<Enchantment, Integer> ench1, Map<Enchantment, Integer> ench2) {
		if (ench1.isEmpty() || ench2.isEmpty()) {
			return false;
		}
		for (Enchantment enchment1 : ench1.keySet()) {
			for (Enchantment enchment2 : ench2.keySet()) {
				if (!(enchment1.getName().equals(enchment2.getName()) && enchment1.getId() == enchment2.getId())) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isWand(ItemStack item) {
		if (item == null || !item.hasItemMeta()) {
			return false;
		}
		ItemStack wand = getWand();
		ItemMeta wandMeta = wand.getItemMeta();
		ItemMeta itemMeta = item.getItemMeta();
		return sameEnchants(item.getEnchantments(), wand.getEnchantments()) && itemMeta.getLore().containsAll(wandMeta.getLore()) && itemMeta.getDisplayName().equals(wandMeta
				.getDisplayName());
	}

}
