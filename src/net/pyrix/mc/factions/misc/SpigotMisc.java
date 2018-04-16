package net.pyrix.mc.factions.misc;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.utils.C;

public class SpigotMisc {

	private Player player;

	public SpigotMisc(Player player) {
		this.player = player;
	}

	public void sendActionBarMessage(String message) {
		new BukkitRunnable() {
			public void run() {
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(C.placeholder(message, player, true)));
			}
		}.runTaskTimerAsynchronously(Factions.getInstance(), 0L, 2L);
	}

}
