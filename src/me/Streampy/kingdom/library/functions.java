package me.Streampy.kingdom.library;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.Streampy.kingdom.records.records;
import me.Streampy.kingdom.records.records.kingdomMemberRec;
import me.Streampy.kingdom.records.records.kingdomRec;
import me.Streampy.kingdom.records.records.playerRec;

public class functions {

	static File kingdomDirectory = files.kingdomDirectory;
	static File kingdomFile = files.kingdomFile;
	static File playerFile = files.playerFile;
	
	static FileConfiguration kingdomConfig = files.kingdomConfig;
	static FileConfiguration playerConfig = files.playerConfig;
	
	static ArrayList<kingdomRec> kingdomsList = records.kingdomsList;
	static ArrayList<playerRec> playersList = records.playersList;
	
	public static void save() {
		saveKingdomFile();
		savePlayerFile();
	}
	
	public static void load() {
		if (kingdomDirectory.exists()) {
			loadKingdomFile();
			loadPlayerFile();
			loadKingdomMembers();
		}
	}
	
	private static void saveKingdomFile() {
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
	
	private static void savePlayerFile() {
		if (playersList.size() >= 0) {
			files.makeDirectoryIfNotExist(kingdomDirectory);
			files.makeFileIfNotExist(playerFile);
			
			try {
				playerConfig.load(playerFile);
				for (int a = 0; a < playersList.size(); a++) {
					playerRec player = playersList.get(a);
					playerConfig.set("player." + a + ".name", player.name);
					playerConfig.set("player." + a + ".uuid", player.uuid);
					playerConfig.set("player." + a + ".kingdom", player.kingdom.name);
					playerConfig.save(playerFile);
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private static void loadKingdomFile() {
		if (kingdomFile.exists()) {
			try {
				kingdomConfig.load(kingdomFile);
				for (int a = 0; kingdomConfig.contains("kingdom." + a); a++) {
					kingdomRec kingdomRecord = new kingdomRec();
					kingdomsList.add(kingdomRecord);
					
					kingdomRecord.name = kingdomConfig.getString("kingdom." + a + ".name");
					kingdomRecord.prefix = kingdomConfig.getString("kingdom." + a + ".prefix");
					kingdomRecord.suffix = kingdomConfig.getString("kingdom." + a + ".suffix");
					
					World world = Bukkit.getWorld(kingdomConfig.getString("kingdom." + a + ".spawn.world"));
					double x = kingdomConfig.getInt("kingdom." + a + ".spawn.x");
					double y = kingdomConfig.getInt("kingdom." + a + ".spawn.y");
					double z = kingdomConfig.getInt("kingdom." + a + ".spawn.z");
					double pitch = kingdomConfig.getInt("kingdom." + a + ".spawn.pitch");
					double yaw = kingdomConfig.getInt("kingdom." + a + ".spawn.yaw");
					
					Location loc = new Location(world, x, y, z, (float) pitch,(float) yaw);
					
					kingdomRecord.spawn = loc;
					
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private static void loadKingdomMembers() {
		//Leden + queen + king
	}
	
	private static void loadPlayerFile() {

	}
	
	public static void createKingdom(String name, Player player) {
		kingdomRec kingdomRecord = new kingdomRec();
		kingdomsList.add(kingdomRecord);
		
		kingdomRecord.name = name;
		kingdomRecord.prefix = "";
		kingdomRecord.suffix = "";
		kingdomRecord.king = null;
		kingdomRecord.queen = null;
		
		Location loc = new Location(player.getWorld(), 0, 0, 0, (float) 0,(float) 0);
		
		kingdomRecord.spawn = loc;
	}
	
	
	
}
