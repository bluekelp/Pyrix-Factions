package net.pyrix.mc.factions.commands.faction;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.faction.Faction;
import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.storage.StorageManager;
import net.pyrix.mc.factions.utils.C;

public class CmdFactionRemove extends FactionsCommand {

	private String[][] args = {
			{ "r" },
			{ "r", "%?" },
			{ "rmv" },
			{ "rmv", "%?" },
			{ "remove" },
			{ "remove", "%?" },
			{ "dis" },
			{ "dis", "%?" },
			{ "disband" },
			{ "disband", "%?" },
			{ "delete" },
			{ "delete", "%?" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("pfacs.admin")) {
				if (args.length == 2) {
					String faction = args[1];
					Faction fac = StorageManager.get.FactionStorage.get(faction, true);
					if (fac != null) {
						fac.destroy();
						player.sendMessage(C.color("&a&oSuccessfully removed faction, &f&o" + fac.getName() + "&a&o!"));
						return true;
					}
				} else {
					FPlayer FPlayer = StorageManager.get.FPlayerStorage.get(player);
					if (FPlayer != null) {
						Faction faction = FPlayer.getFaction();
						StorageManager.get.FactionStorage.remove(faction);
						player.sendMessage(C.color("&a&oSuccessfully removed faction, &f&o" + faction.getName() + "&a&o!"));
						return true;
					} else {
						player.sendMessage(C.color("&c&oYou are not currently in a faction!"));
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public String[][] getArgs() {
		return args;
	}

}
