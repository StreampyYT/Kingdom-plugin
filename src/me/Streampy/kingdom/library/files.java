package me.Streampy.kingdom.library;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class files {

	public static File kingdomDirectory = new File("plugins/Kingdom");
	public static File kingdomFile = new File("plugins/Kingdom/kingdomData.yml");
	public static File playerFile = new File("plugins/Kingdom/playerData.yml");
	
	public static FileConfiguration kingdomConfig = new YamlConfiguration();
	public static FileConfiguration playerConfig = new YamlConfiguration();
	
	public static void makeDirectoryIfNotExist(File file) {
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	public static void makeFileIfNotExist(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
