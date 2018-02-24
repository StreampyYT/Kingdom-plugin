package me.Streampy.kingdom.records;

import java.util.ArrayList;

import org.bukkit.Location;

public class records {

	public static class kingdomRec {
		public String name;
		public String prefix;
		public String suffix;
		public playerRec king;
		public playerRec queen;
		public Location spawn;
		public ArrayList<kingdomMemberRec> members = new ArrayList<kingdomMemberRec>();
	}
	
	public static class kingdomMemberRec {
		public playerRec player;
		public String rank;
	}
	
	public static class playerRec {
		public String name;
		public String uuid;
		public kingdomRec kingdom;
	}
	
	public static ArrayList<kingdomRec> kingdomsList = new ArrayList<kingdomRec>();
	public static ArrayList<playerRec> playersList = new ArrayList<playerRec>();
	
}
