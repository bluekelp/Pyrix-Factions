package net.pyrix.mc.factions.commands.faction;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.faction.Faction;
import net.pyrix.mc.factions.utils.C;

public class CmdFactionCreate extends FactionsCommand {

	private String[][] args = { { "c", "%?" }, { "create", "%?" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("pfacs.create")) {
				String factionName = args[1];
				new Faction(player, factionName);
				player.sendMessage(C.color("&9&oSuccessfully created faction, &f" + factionName + "&9!"));
			}
		}
		return false;
	}

	@Override
	public String[][] getArgs() {
		return args;
	}

}
