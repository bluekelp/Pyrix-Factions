package net.pyrix.mc.factions.commands.faction;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.storage.StorageManager;
import net.pyrix.mc.factions.utils.CommandText;
import net.pyrix.mc.factions.utils.TextConvert;

public class CmdHelp extends FactionsCommand {

	private String[][] args = { {}, { "?" }, { "h" }, { "help" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			for (String msg : StorageManager.get.ConfigurationStorage.getCommandHelpMessage()) {
				player.spigot().sendMessage(new TextConvert(player, msg, "&7Click Me!", new CommandText("/f help story")).convertToTextComponent());
			}
			return true;
		} else {
			System.out.println("You must be a player in order to execute that command!");
		}
		return false;
	}

	@Override
	public String[][] getArgs() {
		return args;
	}

}
