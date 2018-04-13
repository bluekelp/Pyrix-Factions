package net.pyrix.mc.factions.commands.faction.admin;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.listeners.ListenerManager;
import net.pyrix.mc.factions.utils.C;

public class CmdAdminSetupTerritory extends FactionsCommand {

	private String[][] args = { { "admin", "setup", "territory", "%?" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("pfacs.admin")) {
				if (!ListenerManager.get.RightClickCheck.playerHasWand(player)) {
					final String territoryName = args[3];
					player.getInventory().setItem(player.getInventory().firstEmpty(), giveWand(player));
					return true;
				} else {
					player.sendMessage(C.color("&c&oYou already have a wand!"));
					return true;
				}
			}
		} else {
			System.out.println("Use must be a player in order to run that command!");
			return true;
		}
		return false;
	}

	private ItemStack giveWand(Player player) {
		player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1f, 0f);
		ItemStack wand = new ItemStack(Material.BLAZE_ROD, 1);
		wand.addUnsafeEnchantment(Enchantment.DURABILITY, -1);
		ItemMeta meta = wand.getItemMeta();
		meta.setLore(new ArrayList<String>(Arrays.asList(new String[] { C.color("&c&oDrop me to remove me!") })));
		meta.setDisplayName(C.color("&9&l&oTerritory Wand"));
		wand.setItemMeta(meta);
		ListenerManager.get.RightClickCheck.addPlayerWithWand(player, wand);
		return wand;
	}

	@Override
	public String[][] getArgs() {
		return args;
	}

}
