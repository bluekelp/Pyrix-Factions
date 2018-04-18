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

	public Faction get(String faction, boolean ignorecase) {
		for (Faction f : factions) {
			if (!ignorecase && f.getName().equals(faction)) {
				return f;
			} else if (ignorecase && f.getName().equalsIgnoreCase(faction)) {
				return f;
			}
		}
		return null;
	}

	public boolean exists(String faction, boolean ignorecase) {
		Faction fac = get(faction, ignorecase);
		return fac != null ? true : false;
	}

	public boolean remove(Faction... factions) {
		for (Faction f : factions) {

		}
		return false;
	}

}
