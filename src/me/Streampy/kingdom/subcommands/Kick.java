package me.Streampy.kingdom.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.kingdom.library.functions;
import me.Streampy.kingdom.records.records.playerRec;

public class Kick {

	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 2) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target != null) {
					playerRec targetRecord = functions.getPlayerRecord(target.getUniqueId().toString());
					if (functions.isPlayerInKingdom(targetRecord)) {
						functions.removePlayerFromKingdom(targetRecord.kingdom, targetRecord);
						player.sendMessage(ChatColor.GOLD + "You kicked " + args[1] + " out a kingdom!");
					}else {
						player.sendMessage(ChatColor.RED + "Player didn't join a kingdom!");
					}
					
				}else {
					player.sendMessage(ChatColor.RED + "Player isn't found!");
				}
			}
		}
	}
}
