package net.pyrix.mc.factions.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class C {

	public static String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public static String placeholder(String msg, Player player, boolean color) {
		if (player == null) {
			return placeholder(msg, color);
		}
		msg = msg.replace("%player%", player.getName());
		msg = msg.replace("%player_name%", player.getDisplayName());
		if (color)
			return ChatColor.translateAlternateColorCodes('&', msg);
		return msg;
	}

	public static String placeholder(String msg, boolean color) {
		if (color)
			return ChatColor.translateAlternateColorCodes('&', msg);
		return msg;
	}

}
