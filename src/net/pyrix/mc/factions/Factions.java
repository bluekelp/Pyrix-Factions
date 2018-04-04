package net.pyrix.mc.factions;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Factions extends JavaPlugin {

	File factionsYml = new File(this.getDataFolder() + "/factions.yml");
	FileConfiguration factionsConfig = YamlConfiguration.loadConfiguration(factionsYml);

	public void onEnable() {
		Player player = Bukkit.getPlayer("geekles");
		// save factions data file
		if (!factionsYml.exists()) {
			try {
				factionsConfig.save(factionsYml);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
