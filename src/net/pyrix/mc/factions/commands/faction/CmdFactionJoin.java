package net.pyrix.mc.factions.commands.faction;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pyrix.mc.factions.commands.FactionsCommand;
import net.pyrix.mc.factions.faction.Faction;
import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.storage.StorageManager;
import net.pyrix.mc.factions.utils.C;

public class CmdFactionJoin extends FactionsCommand {

	private String[][] args = { { "j", "%?" }, { "join", "%?" } };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			String faction = args[1];
			Faction fac = StorageManager.get.FactionStorage.get(faction, true);
			if (fac != null) {
				FPlayer fplayer = StorageManager.get.FPlayerStorage.get(player);
				if (fplayer != null) {
					if (fac.equals(fplayer.getFaction())) {
						player.sendMessage(C.color("&c&oYou're already in that faction."));
						return true;
					}
					player.sendMessage(C.color("&c&oYou must first leave, &f&o" + fplayer.getFaction().getName() + "&c&o faction in order to join &f&o" + fac.getName() + "&c&o!"));
					return true;
				} else {
					new FPlayer(player, fac);
					player.sendMessage(C.color("&9&oSuccessfully joined, &f" + fac.getName() + "&9!"));
					return true;
				}
			} else {
				player.sendMessage(C.color("&c&oThat faction doesn't exist!"));
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
