package me.Streampy.kingdom.records;

import java.util.ArrayList;

import org.bukkit.Location;

import me.Streampy.kingdom.library.Status;

public class records {

	public static class kingdomRec {
		public String name;
		public String prefix;
		public playerRec king;
		public playerRec queen;
		public Location spawn;
		public Status status;
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
