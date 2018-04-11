package net.pyrix.mc.factions.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class FactionsCommand {

	public abstract boolean onCommand(CommandSender sender, Command cmd, String[] args);

	public abstract String[][] getArgs();

	protected String[][] mergeArgs(String[][] args1, String[][] args2) {
		List<String[]> combinedArgs = new ArrayList<String[]>();
		for (String[] a1 : args1) {
			for (String[] a2 : args2) {
				List<String> arguments = new ArrayList<String>(Arrays.asList(a1));
				arguments.addAll(Arrays.asList(a2));
				combinedArgs.add(arguments.toArray(new String[arguments.size()]));
			}
		}
		String[][] combined = combinedArgs.toArray(new String[combinedArgs.size()][combinedArgs.size()]);
		return combined;
	}

}
