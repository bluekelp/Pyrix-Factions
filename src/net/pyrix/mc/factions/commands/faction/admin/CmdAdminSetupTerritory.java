package net.pyrix.mc.factions.commands.faction.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.misc.ItemManager;
import net.pyrix.mc.factions.utils.C;

public class CmdAdminSetupTerritory extends FactionsCommand {

	private String[][] args = { { "admin", "setup", "territory", "%?" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("pfacs.admin")) {
				if (!ItemManager.get.Wand.hasWand(player)) {
					final String territoryName = args[3];
					player.getInventory().setItem(player.getInventory().firstEmpty(), ItemManager.get.Wand.getWand(territoryName));
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

	@Override
	public String[][] getArgs() {
		return args;
	}

}
