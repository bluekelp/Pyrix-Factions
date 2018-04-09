package net.pyrix.mc.factions.utils;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class TextConvert {

	private Player player;
	private String message;
	private String hoverText;
	private String command;

	public TextConvert(Player player, String message, String hoverText, CommandText command) {
		this.player = player;
		this.message = message;
		this.hoverText = hoverText;
		this.command = command.getCommand();
	}

	public TextConvert(Player player, String message, String hoverText) {
		this.player = player;
		this.message = message;
		this.hoverText = hoverText;
	}

	public TextConvert(Player player, String message, CommandText command) {
		this.player = player;
		this.message = message;
		this.command = command.getCommand();
	}

	public TextConvert(String message, String hoverText) {
		this.message = message;
		this.hoverText = hoverText;
	}

	public TextConvert(String message, CommandText command) {
		this.message = message;
		this.command = command.getCommand();
	}

	public TextConvert(String message) {
		this.message = message;
	}

	public String toString() {
		if (player == null)
			return C.placeholder(this.message, player, true);
		return C.color(this.message);

	}

	public TextComponent convertToTextComponent() {
		TextComponent text = new TextComponent(C.placeholder(message, player, true));
		if (hoverText != null) {
			BaseComponent[] hoverText = TextComponent.fromLegacyText(C.placeholder(this.hoverText, player, true));
			text.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, hoverText));
		}
		if (command != null) {
			text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		}
		return text;
	}

}
