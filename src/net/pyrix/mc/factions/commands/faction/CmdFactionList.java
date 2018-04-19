package net.pyrix.mc.factions.commands.faction;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.UI.GUIPlayerList;
import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.faction.Faction;
import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.storage.StorageManager;
import net.pyrix.mc.factions.utils.C;

public class CmdFactionList extends FactionsCommand {

	private String[][] args = { { "l" }, { "l", "%?" }, { "list" }, { "list", "%?" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 1) {
				FPlayer FPlayer = StorageManager.get.FPlayerStorage.get(player);
				if (FPlayer != null) {
					Faction faction = FPlayer.getFaction();
					player.openInventory(new GUIPlayerList(player, faction.getMembers()).getGUI("&9&l&o" + faction.getName()));
					return true;
				} else {
					player.sendMessage(C.color("&c&oYou currently are not in a faction, you must be in a faction to view /f list."));
					return true;
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
