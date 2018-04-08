package me.Streampy.kingdom.subcommands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.kingdom.records.records;
import me.Streampy.kingdom.records.records.kingdomRec;

public class List {

	static ArrayList<kingdomRec> kingdomsList = records.kingdomsList;
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return;
		}
		
		Player player = (Player) sender;
		player.sendMessage(ChatColor.GRAY + "===== " + ChatColor.GREEN + "Kingdom" + ChatColor.GRAY + " =====");
		for (kingdomRec kingdomRecord : kingdomsList) {
			player.sendMessage(ChatColor.GRAY + "- " + ChatColor.GREEN  + kingdomRecord.name);
		}
		player.sendMessage(ChatColor.GRAY + "==================");
		return;
	}

}
