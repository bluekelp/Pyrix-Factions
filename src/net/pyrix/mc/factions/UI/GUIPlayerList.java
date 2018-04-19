package net.pyrix.mc.factions.UI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.storage.StorageManager;
import net.pyrix.mc.factions.utils.C;

public class GUIPlayerList {

	private Player Owner;
	private Player[] players;

	public GUIPlayerList(Player Owner, Player... players) {
		this.Owner = Owner;
		this.players = players;
	}

	public GUIPlayerList(Player Owner, FPlayer... fPlayers) {
		this.Owner = Owner;
		List<Player> players = new ArrayList<Player>();
		for (FPlayer player : fPlayers) {
			players.add(player.getPlayer());
		}
		this.players = players.toArray(new Player[players.size()]);

	}

	// TODO Prevent players from moving items around in GUI
	@SuppressWarnings("deprecation")
	public Inventory getGUI(String title) {
		Inventory GUI = Bukkit.createInventory(Owner, ((players.length / 9) + 1) * 9, C.placeholder(title, Owner, true));
		for (Player player : players) {
			FPlayer FPlayer = StorageManager.get.FPlayerStorage.get(player);
			ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta skull = (SkullMeta) item.getItemMeta();
			skull.setDisplayName(C.color("&8[&9&l" + FPlayer.getRole().toString() + "&8] &9&l&o" + player.getName()));
			// skull.setLore(new ArrayList<String>(Arrays.asList(new String[] {
			// C.color("&8[&9&l" + FPlayer.getRole().toString() + "&8] &9&o" +
			// player.getName()) })));
			skull.setOwner(player.getName());
			item.setItemMeta(skull);
			GUI.setItem(GUI.firstEmpty(), item);
		}
		return GUI;
	}

}
