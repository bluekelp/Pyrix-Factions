package net.pyrix.mc.factions.commands;

import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.Manager;

public class CommandManager extends Manager {

	@Override
	public void onEnable() {
		initializeAlias();
	}

	protected String[] aliases = { "f", "fac", "faction" };

	private void initializeAlias() {
		for (String a : aliases) {
			Factions.getInstance().getCommand(a).setExecutor(new AliasManager());
		}

	}

}
