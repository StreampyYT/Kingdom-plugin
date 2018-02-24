package me.Streampy.kingdom.library;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;

import me.Streampy.kingdom.records.records;
import me.Streampy.kingdom.records.records.kingdomMemberRec;
import me.Streampy.kingdom.records.records.kingdomRec;
import me.Streampy.kingdom.records.records.playerRec;

public class functions {

	File kingdomDirectory = files.kingdomDirectory;
	File kingdomFile = files.kingdomFile;
	File playerFile = files.playerFile;
	
	FileConfiguration kingdomConfig = files.kingdomConfig;
	FileConfiguration playerConfig = files.playerConfig;
	
	ArrayList<kingdomRec> kingdomsList = records.kingdomsList;
	ArrayList<playerRec> playersList = records.playersList;
	
	public void saveKingdomFile() {
		if (kingdomsList.size() >= 0) {
			files.makeDirectoryIfNotExist(kingdomDirectory);
			files.makeFileIfNotExist(kingdomFile);
			
			try {
				kingdomConfig.load(kingdomFile);
				for (int a = 0; a < kingdomsList.size(); a++) {
					kingdomRec kingdom = kingdomsList.get(a);
					kingdomConfig.set("kingdom." + a + ".name", kingdom.name);
					kingdomConfig.set("kingdom." + a + ".prefix", kingdom.prefix);
					kingdomConfig.set("kingdom." + a + ".suffix", kingdom.suffix);
					kingdomConfig.set("kingdom." + a + ".king", kingdom.king);
					kingdomConfig.set("kingdom." + a + ".queen", kingdom.queen);
					
					kingdomConfig.set("kingdom." + a + ".spawn.world", kingdom.spawn.getWorld().getName());
					kingdomConfig.set("kingdom." + a + ".spawn.x", kingdom.spawn.getX());
					kingdomConfig.set("kingdom." + a + ".spawn.y", kingdom.spawn.getY());
					kingdomConfig.set("kingdom." + a + ".spawn.z", kingdom.spawn.getZ());
					kingdomConfig.set("kingdom." + a + ".spawn.yaw", kingdom.spawn.getYaw());
					kingdomConfig.set("kingdom." + a + ".spawn.pitch", kingdom.spawn.getPitch());
					
					for (int b = 0; b < kingdom.members.size(); b++) {
						kingdomMemberRec kingdomMember = kingdom.members.get(b);
						kingdomConfig.set("kingdom." + a + ".member." + b + ".name", kingdomMember.player.name);
						kingdomConfig.set("kingdom." + a + ".member." + b + ".uuid", kingdomMember.player.uuid);
						kingdomConfig.set("kingdom." + a + ".member." + b + ".rank", kingdomMember.rank);
					}
					kingdomConfig.save(kingdomFile);
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void savePlayerFile() {
		files.makeDirectoryIfNotExist(kingdomDirectory);
		files.makeFileIfNotExist(playerFile);
	}
	
}
