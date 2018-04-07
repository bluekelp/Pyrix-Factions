package net.pyrix.mc.factions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class Factions extends JavaPlugin {

	File factionsDir = new File(this.getDataFolder() + "/factions");

	private static Factions factions;

	public static Factions getInstance() {
		return factions;
	}

	public void onEnable() {
		factions = this;
		// save factions data file
		if (!factionsDir.exists()) {
			factionsDir.mkdir();
		}
		initializeClasses();

	}

	private void initializeClasses() {
		// Initializes only classes that extend Manager first
		Reflections reflections = new Reflections("net.pyrix.mc.factions", new SubTypesScanner(false));

		Set<Class<? extends Manager>> allManagerClasses = reflections.getSubTypesOf(Manager.class);
		for (Class<?> c : allManagerClasses) {
			try {
				c.getDeclaredMethod("onEnable").invoke(c.getConstructor().newInstance());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
				e.printStackTrace();
			}
		}

	}

}
