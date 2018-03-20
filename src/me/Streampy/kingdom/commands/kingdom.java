package me.Streampy.kingdom.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.kingdom.Main;
import me.Streampy.kingdom.library.functions;
import me.Streampy.kingdom.records.records;
import me.Streampy.kingdom.records.records.kingdomMemberRec;
import me.Streampy.kingdom.records.records.kingdomRec;

public class kingdom implements CommandExecutor {

	public kingdom(Main main) {
		// TODO Auto-generated constructor stub
	}
	
	static ArrayList<kingdomRec> kingdomsList = records.kingdomsList;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kingdom")) {
				if (args.length == 0) {
					//help menu
				}else {
					switch(args[0]) {
						case "create": 
							if (args.length == 2) {
								for (kingdomRec kingdomRecord : kingdomsList) {
									if (kingdomRecord.name.toLowerCase().equals(args[1].toLowerCase())) {
										player.sendMessage(ChatColor.RED + "Deze naam word al gebruikt!");
										return false;
									}
								}
								
								functions.createKingdom(args[1], player);
								player.sendMessage("Kingdom is created");
							}else {
								player.sendMessage("/kingdom create <name>");
							}
							break; 
						case "list": 
							player.sendMessage(ChatColor.GRAY + "===== " + ChatColor.GREEN + "Kingdom" + ChatColor.GRAY + " =====");
							for (kingdomRec kingdomRecord : kingdomsList) {
								player.sendMessage(ChatColor.GRAY + "- " + ChatColor.GREEN  + kingdomRecord.name);
							}
							player.sendMessage(ChatColor.GRAY + "==================");
						case "info":
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
										return false;
									}
								}
								
								player.sendMessage(ChatColor.RED + "Geen kingdom met die naam gevonden!");
								player.sendMessage(ChatColor.GRAY + "==================");
							}else {
								player.sendMessage("/kingdom info <name>");
							}
						default: 
							//help menu
					}
				}
			}else {
				player.sendMessage(ChatColor.RED + "Jij hebt niet de goede permissions!");
			}
		}
		return false;
	}

}
