package net.pyrix.mc.factions.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class AliasManager implements CommandExecutor {

	private Reflections reflections = new Reflections("net.pyrix.mc.factions.commands.faction", new SubTypesScanner(false));;

	protected final Set<Class<? extends FactionsCommand>> commands = reflections.getSubTypesOf(FactionsCommand.class);

	@Override
	// Called when player runs /f /fac or /faction
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission("pfacs.use")) {
				return false;
			}
		}
		for (Class<? extends FactionsCommand> command : commands) {
			try {
				for (String[] arguments : (String[][]) command.getDeclaredMethod("getArgs").invoke(command.getConstructor().newInstance())) {
					if (Arrays.equals(arguments, args)) {
						return (boolean) command.getDeclaredMethod("onCommand", CommandSender.class, Command.class, String[].class).invoke(command.getConstructor().newInstance(),
								sender, cmd, args);
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
