package net.pyrix.mc.factions.storage.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.pyrix.mc.factions.Factions;

public class LanguageStorage {

	public LanguageStorage() {
		setup(Factions.getInstance());
	}

	private final File lang = new File(Factions.getInstance().getDataFolder() + "/language.yml");

	private YamlConfiguration langYml;

	public void setup(JavaPlugin p) {

		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (!lang.exists()) {
			try {
				// read this file into InputStream
				inputStream = p.getResource(lang.getName());

				// write the inputStream to a FileOutputStream
				outputStream = new FileOutputStream(lang);

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}

				System.out.println("Done!");

			} catch (IOException e) {
				e.printStackTrace();
				p.getLogger().severe(ChatColor.RED + "Could not create location.yml!");

			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (outputStream != null) {
					try {
						// outputStream.flush();
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

		}
		langYml = YamlConfiguration.loadConfiguration(lang);
		readData();
	}

	public FileConfiguration getData() {
		return langYml;
	}

	public void saveData() {
		try {
			this.langYml.save(lang);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data file!");
		}
	}

	public void reloadData() {
		langYml = YamlConfiguration.loadConfiguration(lang); // updates YamlConfiguration data
	}

	private void readData() {
		// TODO Add code
	}

}
