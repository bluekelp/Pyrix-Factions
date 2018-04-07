package net.pyrix.mc.factions.commands.faction;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.pyrix.mc.factions.commands.FactionsCommand;

public class CmdFactionRemove extends FactionsCommand {

	private String[][] args = { { "r" }, { "rmv" }, { "remove" }, { "dis" }, { "disband" }, { "delete" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		return false;
	}

	@Override
	public String[][] getArgs() {
		return args;
	}

}
