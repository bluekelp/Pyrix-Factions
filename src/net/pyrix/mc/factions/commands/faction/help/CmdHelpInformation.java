package net.pyrix.mc.factions.commands.faction.help;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.storage.StorageManager;
import net.pyrix.mc.factions.utils.TextConvert;

public class CmdHelpInformation extends FactionsCommand {

	private String[][] args = { { "general" }, { "info" }, { "information" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			for (TextConvert text : StorageManager.get.ConfigurationStorage.getCommandHelpInformationMessage()) {
				player.spigot().sendMessage(text.convert());
			}
			return true;
		}
		return false;
	}

	@Override
	public String[][] getArgs() {
		return super.mergeArgs(new CmdHelp().getMainArgs(), args);
	}

}
