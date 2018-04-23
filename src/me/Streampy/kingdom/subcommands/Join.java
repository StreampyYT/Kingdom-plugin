package me.Streampy.kingdom.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.kingdom.library.functions;
import me.Streampy.kingdom.records.records.kingdomRec;
import me.Streampy.kingdom.records.records.playerRec;

public class Join {

	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			playerRec playerRecord = functions.getPlayerRecord(player.getUniqueId().toString());
			if (args.length == 2) {
				
				kingdomRec kingdomRecord = functions.getKingdomRecord(args[1]);
				if (kingdomRecord == null) {
					return;
				}
				if (!functions.isPlayerInKingdom(playerRecord)) {
					functions.setPlayerInKingdom(kingdomRecord, playerRecord);
					player.sendMessage(ChatColor.GOLD + "You joined " + ChatColor.GREEN + kingdomRecord.name + ChatColor.GOLD + "!" );
				}else {
					player.sendMessage(ChatColor.RED + "You are already in a kingdom!");
				}
			}else {
				player.sendMessage("Use /" + cmd.getName() + " <name>");
			}
		}
		
	}

}
