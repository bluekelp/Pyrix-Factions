package net.pyrix.mc.factions.misc;

import net.pyrix.mc.factions.Manager;
import net.pyrix.mc.factions.misc.items.Wand;

public class ItemManager extends Manager {

	public static ItemManager get;

	public Wand Wand;

	@Override
	public void onEnable() {
		get = this;
		Wand = new Wand();

	}

}
