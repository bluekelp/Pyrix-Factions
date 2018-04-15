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

	public ItemStack getWand() {
		ItemStack wand = new ItemStack(Material.BLAZE_ROD, 1);
		wand.addUnsafeEnchantment(Enchantment.DURABILITY, -1);
		ItemMeta meta = wand.getItemMeta();
		meta.setLore(new ArrayList<String>(Arrays.asList(new String[] { C.color("&c&oDrop me to remove me!") })));
		meta.setDisplayName(C.color("&9&l&oTerritory Wand"));
		wand.setItemMeta(meta);
		return wand;
	}

	public boolean hasWand(Player player) {
		for (ItemStack i : player.getInventory().getStorageContents()) {
			if (isWand(i)) {
				return true;
			}
		}
		return false;
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
		return sameEnchants(item.getEnchantments(), wand.getEnchantments()) && itemMeta.getLore().equals(wandMeta.getLore()) && itemMeta.getDisplayName().equals(wandMeta
				.getDisplayName());
	}

}
