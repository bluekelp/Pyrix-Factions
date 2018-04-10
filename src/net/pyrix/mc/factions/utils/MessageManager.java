package net.pyrix.mc.factions.utils;

public class MessageManager {

	public enum Action {
		SHOW_TEXT, RUN_COMMAND, SUGGEST_COMMAND
	}

	private String message;
	private Action action;

	public MessageManager(Action action, String message) {
		this.action = action;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public Action getAction() {
		return action;
	}

}
