package me.Streampy.kingdom.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.kingdom.library.functions;
import me.Streampy.kingdom.records.records.playerRec;

public class Leave {

	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 1) {
				playerRec playerRecord = functions.getPlayerRecord(player.getUniqueId().toString());
				if (functions.isPlayerInKingdom(playerRecord)) {
					functions.removePlayerFromKingdom(playerRecord.kingdom, playerRecord);
					player.sendMessage(ChatColor.GOLD + "You leaved your kingdom!");
				}else {
					player.sendMessage(ChatColor.RED + "You didn't join a kingdom!");
				}
			}else {
				player.sendMessage(ChatColor.RED + "Use /kingdom leave");
			}
		}
		
	}

}
