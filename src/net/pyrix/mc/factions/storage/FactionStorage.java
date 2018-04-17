package net.pyrix.mc.factions.storage;

import java.util.ArrayList;
import java.util.List;

import net.pyrix.mc.factions.faction.Faction;

public class FactionStorage {

	static List<Faction> factions = new ArrayList<Faction>();

	public void add(Faction faction) {
		factions.add(faction);
	}

	public Faction[] getFactions() {
		return factions.toArray(new Faction[factions.size()]);
	}

}
