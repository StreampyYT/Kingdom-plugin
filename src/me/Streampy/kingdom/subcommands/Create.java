package me.Streampy.kingdom.subcommands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.kingdom.library.functions;
import me.Streampy.kingdom.records.records;
import me.Streampy.kingdom.records.records.kingdomRec;

public class Create {

	static ArrayList<kingdomRec> kingdomsList = records.kingdomsList;
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return;
		}
		Player player = (Player) sender;
		if (args.length == 2) {
			for (kingdomRec kingdomRecord : kingdomsList) {
				if (kingdomRecord.name.toLowerCase().equals(args[1].toLowerCase())) {
					player.sendMessage(ChatColor.RED + "Deze naam word al gebruikt!");
					return;
				}
			}
			
			functions.createKingdom(args[1], player);
			player.sendMessage("Kingdom is created");
		}else {
			player.sendMessage("/kingdom create <name>");
		}
		return;
	}

}
