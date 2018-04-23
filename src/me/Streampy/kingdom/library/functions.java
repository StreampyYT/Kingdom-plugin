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
					
					kingdomConfig.set("kingdom." + a + ".member", null);
					for (int b = 0; b < kingdom.members.size(); b++) {
						kingdomMemberRec kingdomMember = kingdom.members.get(b);
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
					if (player.kingdom != null) {
						playerConfig.set("player." + a + ".kingdom", player.kingdom.name);
					}else {
						playerConfig.set("player." + a + ".kingdom", null);
					}
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
		if (kingdomFile.exists()) {
			try {
				kingdomConfig.load(kingdomFile);
				for (int a = 0; kingdomConfig.contains("kingdom." + a); a++) {
					kingdomRec kingdomRecord = getKingdomRecord(kingdomConfig.getString("kingdom." + a + ".name"));
					for (int b = 0; kingdomConfig.contains("kingdom." + a + ".member." + b); b++) {
						
						kingdomMemberRec kingdomMemberRecord = new kingdomMemberRec();
						kingdomRecord.members.add(kingdomMemberRecord);
						
						playerRec playerRecord = getPlayerRecord(kingdomConfig.getString("kingdom." + a + ".member." + b + ".uuid"));
						
						kingdomMemberRecord.player = playerRecord;
						kingdomMemberRecord.rank = kingdomConfig.getString("kingdom." + a + ".member." + b + ".rank");
						
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private static void loadPlayerFile() {
		if (playerFile.exists()) {
			try {
				playerConfig.load(playerFile);
				for (int a = 0; playerConfig.contains("player." + a); a++) {
					playerRec playerRecord = new playerRec();
					playersList.add(playerRecord);
					
					playerRecord.name = playerConfig.getString("player." + a + ".name");
					playerRecord.uuid = playerConfig.getString("player." + a + ".uuid");
					playerRecord.kingdom = getKingdomRecord(playerConfig.getString("player." + a + ".kingdom"));
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
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
	
	public static void createPlayer(Player player) {
		playerRec playerRecord = new playerRec();
		playersList.add(playerRecord);
		
		playerRecord.name = player.getName();
		playerRecord.uuid = player.getUniqueId().toString();
		playerRecord.kingdom = null;
	}
	
	public static void createPlayer(Player player, kingdomRec kingdomRecord) {
		playerRec playerRecord = new playerRec();
		playersList.add(playerRecord);
		
		playerRecord.name = player.getName();
		playerRecord.uuid = player.getUniqueId().toString();
		playerRecord.kingdom = kingdomRecord;
	}
	
	public static void createPlayer(String name, String uuid) {
		playerRec playerRecord = new playerRec();
		playersList.add(playerRecord);
		
		playerRecord.name = name;
		playerRecord.uuid = uuid;
		playerRecord.kingdom = null;
	}
	
	public static void createPlayer(String name, String uuid, kingdomRec kingdomRecord) {
		playerRec playerRecord = new playerRec();
		playersList.add(playerRecord);
		
		playerRecord.name = name;
		playerRecord.uuid = uuid;
		playerRecord.kingdom = kingdomRecord;
	}
	
	public static boolean playerAlreadyCreated(Player player) {
		for (playerRec playerRecord : playersList) {
			if (playerRecord.uuid.equals(player.getUniqueId().toString())) {
				return true;
			}
		}
		return false;
	}

	public static boolean playerAlreadyCreated(String uuid) {
		for (playerRec playerRecord : playersList) {
			if (playerRecord.uuid.equals(uuid)) {
				return true;
			}
		}
		return false;
	}
	
	public static kingdomRec getPlayerKingdomRecord(playerRec player) {
		for (playerRec playerRecords : playersList) {
			if (playerRecords.name.equals(player.name)) {
				return player.kingdom;
			}
		}
		return null;
	}
	
	public static boolean isPlayerInKingdom(playerRec player) {
		for (playerRec playerRecords : playersList) {
			if (playerRecords.name.equals(player.name)) {
				if (player.kingdom != null) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void setPlayerInKingdom(kingdomRec kingdom, playerRec player) {
		//Nog geen ranks!
		kingdomMemberRec kingdomMember = new kingdomMemberRec();
		kingdom.members.add(kingdomMember);
		kingdomMember.player = player;
		kingdomMember.rank = null;
		player.kingdom = kingdom;
	}
	
	public static void removePlayerFromKingdom(kingdomRec kingdom, playerRec player) {
		if (kingdom == null || player == null) return;
		for (int a = 0; a < kingdom.members.size(); a++) {
			kingdomMemberRec kingdomMember = kingdom.members.get(a);
			if (kingdomMember.player.equals(player)) {
				kingdom.members.remove(a);
				player.kingdom = null;
			}
		}
	}
	
	public static playerRec getPlayerRecord(String uuid) {
		for (playerRec playerRecord : playersList) {
			if (playerRecord.uuid.equals(uuid)) {
				return playerRecord;
			}
		}
		return null;
	}
	
	public static kingdomRec getKingdomRecord(String name) {
		for (kingdomRec kingdomRecord : kingdomsList) {
			if (kingdomRecord.name.equals(name)) {
				return kingdomRecord;
			}
		}
		return null;
	}
	
	
	
}
