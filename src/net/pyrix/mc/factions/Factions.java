package net.pyrix.mc.factions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import net.pyrix.mc.factions.storage.StorageManager;

public class Factions extends JavaPlugin {

	final File factionsDir = new File(this.getDataFolder() + "/factions");
	final File territoryDir = new File(this.getDataFolder() + "/territories");

	private static Factions factions;

	public static Factions getInstance() {
		return factions;
	}

	public void onEnable() {
		factions = this;

		if (!new File(this.getDataFolder(), "config.yml").exists()) {
			System.out.println(ChatColor.YELLOW + "No Config Found! Generating a new one...");
			this.saveDefaultConfig(); // Retrieves config.yml stored in plugin
		}

		if (!factionsDir.exists()) {
			factionsDir.mkdir();
		}

		if (!territoryDir.exists()) {
			territoryDir.mkdir();
		}

		Set<Class<? extends Manager>> debugClasses = initializeClasses();
		if (debugClasses != null) {
			getLogger().info("Unsuccessfully initialized classes ->: ");
			for (Class<?> c : debugClasses) {
				getLogger().info("> " + c.getName());
			}
			return;
		}
		getLogger().info("PyrixFactions successfully initialized! ^ω^");

		StorageManager.get.LanguageStorage.setup(this);

	}

	private Set<Class<? extends Manager>> initializeClasses() {
		// Initializes only classes that extend Manager first
		Reflections reflections = new Reflections("net.pyrix.mc.factions", new SubTypesScanner(false));

		Set<Class<? extends Manager>> allManagerClasses = reflections.getSubTypesOf(Manager.class);
		for (Class<?> c : allManagerClasses) {
			try {
				c.getDeclaredMethod("onEnable").invoke(c.getConstructor().newInstance());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
				e.printStackTrace();
				return allManagerClasses;
			}
		}
		return null;

	}

}
