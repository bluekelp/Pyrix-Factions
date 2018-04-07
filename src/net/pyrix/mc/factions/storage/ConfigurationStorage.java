package net.pyrix.mc.factions.storage;

public class ConfigurationStorage {

	private String[] CMD_HELP_MESSAGE;

	public void setCommandHelpMessage(String... messages) {
		CMD_HELP_MESSAGE = messages;
	}

	public String[] getCommandHelpMessage() {
		return CMD_HELP_MESSAGE;
	}

}
