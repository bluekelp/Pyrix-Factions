package net.pyrix.mc.factions.commands.faction.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.misc.ItemManager;
import net.pyrix.mc.factions.territories.TerritoryManager;
import net.pyrix.mc.factions.utils.C;

public class CmdAdminSetupTerritory extends FactionsCommand {

	private String[][] args = { { "admin", "remove", "territory", "%?" }, { "admin", "setup", "territory", "%?" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("pfacs.admin")) {
				if (args[1].equalsIgnoreCase(this.args[1][1])) {
					if (!ItemManager.get.Wand.hasWand(player)) {
						final String territoryName = args[3];
						player.getInventory().setItem(player.getInventory().firstEmpty(), ItemManager.get.Wand.getWand(territoryName));
						return true;
					} else {
						player.sendMessage(C.color("&c&oYou already have a wand!"));
						return true;
					}
				} else {
					final String territoryName = args[3];
					if (TerritoryManager.i.removeTerritories(territoryName)) {
						player.sendMessage(C.color("&a&oSuccessfully removed " + territoryName + "!"));
						return true;
					}
					player.sendMessage(C.color("&c&oUnsuccessfully removed " + territoryName + ", check to be sure you typed in the name correctly."));
					return true;
				}
			}
		} else {
			System.out.println("Use must be a player in order to run that command!");
			return true;
		}
		return false;
	}

	@Override
	public String[][] getArgs() {
		return args;
	}

}
