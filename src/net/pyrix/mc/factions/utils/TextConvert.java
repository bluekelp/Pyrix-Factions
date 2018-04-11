package net.pyrix.mc.factions.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class TextConvert {

	private Player player;
	private String message;

	private List<TextComponentPlaceholder> placeholders = new ArrayList<TextComponentPlaceholder>();

	public TextConvert(Player player, String message) {
		this.player = player;
		this.message = message;
	}

	public TextConvert(String message) {
		this.message = message;
	}

	public String toString() {
		if (player == null)
			return C.placeholder(this.message, player, true);
		return C.color(this.message);

	}

	public TextConvert set(String placeholder, String message, MessageManager... manager) {
		for (int i = 0; i < StringUtils.countMatches(this.message, placeholder); i++) {
			placeholders.add(new TextComponentPlaceholder(placeholder, message, manager));
		}
		return this;
	}

	public TextComponent convert() {
		TextComponent main = new TextComponent();
		if (!placeholders.isEmpty()) {
			for (TextComponentPlaceholder placeholder : placeholders) {
				String msg = placeholder.getMessage();
				String hvr = placeholder.getActionMessage(net.pyrix.mc.factions.utils.MessageManager.Action.SHOW_TEXT);
				String cmd = placeholder.getActionMessage(net.pyrix.mc.factions.utils.MessageManager.Action.RUN_COMMAND);
				String sgt = placeholder.getActionMessage(net.pyrix.mc.factions.utils.MessageManager.Action.SUGGEST_COMMAND);
				TextComponent text = new TextComponent(C.placeholder(msg, player, true));
				if (hvr != null) {
					BaseComponent[] hoverText = TextComponent.fromLegacyText(C.placeholder(hvr, player, true));
					text.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, hoverText));
				}
				if (cmd != null) {
					text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd));
				}
				if (sgt != null) {
					text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, sgt));
				}
				placeholder.setTextComponent(text);
			}

			List<TextComponentPlaceholder> ph = new ArrayList<TextComponentPlaceholder>();
			int largestValue = 0;
			String storedPlaceholder = "";
			int previousIndex = 0;
			// organize placeholders in order
			for (TextComponentPlaceholder placeholder : placeholders) {
				String p = placeholder.getPlaceholder();
				int index = message.indexOf(p);
				if (storedPlaceholder.equals(p)) {
					index = message.indexOf(p, previousIndex + 1);
				}
				if (index > largestValue) {
					ph.add(placeholder);
					largestValue = index;
				} else {
					TextComponentPlaceholder place = ph.remove(ph.size() - 1);
					ph.add(placeholder);
					ph.add(place);
				}
				previousIndex = index;
				storedPlaceholder = p;
			}

			int c = 0;
			int from = 0;
			for (int counter = 0; counter < ph.size(); counter++) {
				TextComponentPlaceholder placeholder = ph.get(counter);
				from = message.indexOf(placeholder.getPlaceholder(), from + 1);
				main.addExtra(new TextComponent(C.placeholder(message.substring(c, from), player, true)));
				main.addExtra(placeholder.getTextComponent());
				c = from + placeholder.getPlaceholder().length();
				if (counter + 1 == ph.size()) {
					main.addExtra(new TextComponent(C.placeholder(message.substring(c, message.length()), player, true)));
				}
			}
		} else {
			main = new TextComponent(C.placeholder(message, player, true));
		}
		return main;

	}

}
