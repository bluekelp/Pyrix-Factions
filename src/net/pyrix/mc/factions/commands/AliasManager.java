package net.pyrix.mc.factions.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.commands.faction.CmdFactionCreate;
import net.pyrix.mc.factions.commands.faction.CmdFactionList;
import net.pyrix.mc.factions.commands.faction.CmdFactionRemove;
import net.pyrix.mc.factions.commands.faction.CmdHelp;

public class AliasManager extends CommandManager implements CommandExecutor {

	protected final FactionsCommand[] commands = { new CmdFactionCreate(), new CmdFactionList(), new CmdFactionRemove(), new CmdHelp() };

	@Override
	// Called when player runs /f /fac or /faction
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission("pfacs.use")) {
				return false;
			}
		}
		for (FactionsCommand command : commands) {
			for (String[] arguments : command.getArgs()) {
				if (Arrays.equals(arguments, args)) {
					return command.onCommand(sender, cmd, args);
				}
			}
		}
		return false;
	}

}
