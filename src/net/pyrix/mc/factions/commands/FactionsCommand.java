package net.pyrix.mc.factions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class FactionsCommand {

	public abstract boolean onCommand(CommandSender sender, Command cmd, String[] args);

	public abstract String[][] getArgs();

}
