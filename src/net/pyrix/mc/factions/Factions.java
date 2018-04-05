package net.pyrix.mc.factions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class Factions extends JavaPlugin {

	File factionsYml = new File(this.getDataFolder() + "/factions.yml");
	FileConfiguration factionsConfig = YamlConfiguration.loadConfiguration(factionsYml);

	public void onEnable() {
		// save factions data file
		if (!factionsYml.exists()) {
			try {
				factionsConfig.save(factionsYml);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
