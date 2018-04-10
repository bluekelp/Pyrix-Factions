package net.pyrix.mc.factions.utils;

import net.md_5.bungee.api.chat.TextComponent;
import net.pyrix.mc.factions.utils.MessageManager.Action;

public class TextComponentPlaceholder {

	private MessageManager[] managers;
	private String message;
	private String placeholder;
	private TextComponent text;

	public TextComponentPlaceholder(String placeholder, String message, MessageManager... manager) {
		this.placeholder = placeholder;
		this.message = message;
		this.managers = manager;
	}

	public void setTextComponent(TextComponent text) {
		this.text = text;
	}

	public TextComponent getTextComponent() {
		return text;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public String getMessage() {
		return message;
	}

	public String getActionMessage(Action action) {
		for (MessageManager manager : managers) {
			if (manager.getAction() == action) {
				return manager.getMessage();
			}
		}
		return null;
	}

	public String toString() {
		return placeholder;
	}

}
