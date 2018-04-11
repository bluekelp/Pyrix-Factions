package net.pyrix.mc.factions.commands;

import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.Manager;

public class CommandManager extends Manager {

	private AliasManager alias;

	@Override
	public void onEnable() {
		alias = new AliasManager();
		Factions.getInstance().getLogger().info("Registering Commands...");
		initializeAlias();
	}

	protected String[] aliases = { "f", "fac", "factions" };

	private void initializeAlias() {
		for (String a : aliases) {
			Factions.getInstance().getLogger().info("Registering Alias: " + a);
			Factions.getInstance().getCommand(a).setExecutor(alias);
		}

	}

}
