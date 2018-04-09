package net.pyrix.mc.factions.utils;

public class CommandText {
	private String command;

	public CommandText(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public String toPlainText() {
		return command.replaceFirst("/", "");
	}

}
