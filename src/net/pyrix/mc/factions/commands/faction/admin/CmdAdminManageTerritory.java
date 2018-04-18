package net.pyrix.mc.factions.commands.faction.admin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.territories.Territory;
import net.pyrix.mc.factions.territories.TerritoryManager;
import net.pyrix.mc.factions.utils.C;

public class CmdAdminManageTerritory extends FactionsCommand {

	private String[][] args = { { "admin", "set", "territory", "%?", "center" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("pfacs.admin")) {
				Location loc = player.getLocation().getBlock().getLocation();
				loc.setX(loc.getX() + 0.5);
				loc.setZ(loc.getZ() + 0.5);
				Territory territory = TerritoryManager.i.getTerritory(args[3]);
				if (territory != null) {
					territory.getSymbolAnimationMechanic().setSymbol(loc);
					player.sendMessage(C.color("&a&oSuccessfully updated territory's center!"));
					return true;
				}
				player.sendMessage(C.color("&c&oThe territory, " + args[3] + " does not exist!"));
				return true;
			}
		}
		return false;
	}

	@Override
	public String[][] getArgs() {
		return args;
	}

}
