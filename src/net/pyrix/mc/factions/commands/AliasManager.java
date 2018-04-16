package net.pyrix.mc.factions.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reflections.Reflections;

public class AliasManager implements CommandExecutor {

	private Reflections reflections = new Reflections("net.pyrix.mc.factions.commands.faction");

	protected final Set<Class<? extends FactionsCommand>> commands = reflections.getSubTypesOf(FactionsCommand.class);

	@Override
	// Called when player runs /f /fac or /faction
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		String[] origCaseArgs = args.clone();
		args = convertToLowerCase(args);
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission("pfacs.use")) {
				return false;
			}
		}
		for (Class<? extends FactionsCommand> command : commands) {
			try {
				for (String[] arguments : (String[][]) command.getDeclaredMethod("getArgs").invoke(command.getConstructor().newInstance())) {
					if (Arrays.asList(arguments).contains("%?") && arguments.length == args.length) {
						List<String> newArgs = new ArrayList<String>();
						for (int e = 0; e < arguments.length && e < args.length; e++) {
							String element = arguments[e];
							if (element.equals("%?")) {
								element = args[e];
							}
							newArgs.add(element);
						}
						arguments = newArgs.toArray(new String[newArgs.size()]);
					}
					if (Arrays.equals(arguments, args)) {
						// Bukkit.broadcastMessage("Class: " + command.getSimpleName() + " Class Args: "
						// + Arrays.asList(arguments) + " Cmd Args: " + Arrays.asList(args));
						return (boolean) command.getDeclaredMethod("onCommand", CommandSender.class, Command.class, String[].class).invoke(command.getConstructor().newInstance(),
								sender, cmd, origCaseArgs);
					}

				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	private String[] convertToLowerCase(String[] args) {
		List<String> arguments = new ArrayList<String>();
		for (String s : args) {
			arguments.add(s.toLowerCase());
		}
		return arguments.toArray(new String[arguments.size()]);
	}

}
