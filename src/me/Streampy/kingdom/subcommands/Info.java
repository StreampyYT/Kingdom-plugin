package me.Streampy.kingdom.subcommands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.kingdom.records.records;
import me.Streampy.kingdom.records.records.kingdomMemberRec;
import me.Streampy.kingdom.records.records.kingdomRec;

public class Info {

	static ArrayList<kingdomRec> kingdomsList = records.kingdomsList;
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return;
		}
		
		Player player = (Player) sender;
		if (args.length == 2) {
			player.sendMessage(ChatColor.GRAY + "==================");
			for (kingdomRec kingdomRecord : kingdomsList) {
				if (kingdomRecord.name.toLowerCase().equals(args[1].toLowerCase())) {
					
					player.sendMessage("name: " + kingdomRecord.name);
					if (kingdomRecord.king == null) {
						player.sendMessage("king: " + ChatColor.RED + "No king!");
					}else {
						player.sendMessage("king: " + kingdomRecord.king.name);
					}
					if (kingdomRecord.queen == null) {
						player.sendMessage("queen: " + ChatColor.RED + "No queen!");
					}else {
						player.sendMessage("queen: " + kingdomRecord.queen.name);
					}
					
					String members = "";
					for (kingdomMemberRec member : kingdomRecord.members) {
						if (member.player != null) {
							members = ", " + member.player.name;
						}
					}
					if (members.length() >= 1) {
						player.sendMessage("members: " + members.substring(1));
					}else {
						player.sendMessage("members: " + ChatColor.RED + "No members!");
					}
					player.sendMessage(ChatColor.GRAY + "==================");
					return;
				}
			}
			
			player.sendMessage(ChatColor.RED + "Geen kingdom met die naam gevonden!");
			player.sendMessage(ChatColor.GRAY + "==================");
		}else {
			player.sendMessage("/kingdom info <name>");
		}
		return;
	}

}
