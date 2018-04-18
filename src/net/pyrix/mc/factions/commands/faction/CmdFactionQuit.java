package net.pyrix.mc.factions.commands.faction;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.faction.Faction;
import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.storage.StorageManager;
import net.pyrix.mc.factions.utils.C;

public class CmdFactionQuit extends FactionsCommand {

	private String[][] args = { { "quit" }, { "leave" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			FPlayer FPlayer = StorageManager.get.FPlayerStorage.get(player);
			if (FPlayer != null) {
				Faction faction = FPlayer.getFaction();
				FPlayer.quit();
				player.sendMessage(C.color("&a&oSuccessfully left, &f&o" + faction.getName() + "&a&o!"));
				return true;
			} else {
				player.sendMessage(C.color("&c&oYou can not currently leave a faction when you are not in one."));
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
