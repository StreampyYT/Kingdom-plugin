package me.Streampy.kingdom.library;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Streampy.kingdom.Main;
import me.Streampy.kingdom.records.records.playerRec;

public class EventsHandler implements Listener {
	
	public EventsHandler(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!functions.playerAlreadyCreated(player)) {
			functions.createPlayer(player);
		}
	}
	
	@EventHandler
	public void onChatRender(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if (!functions.playerAlreadyCreated(player)) {
			functions.createPlayer(player);
		}
		
		playerRec playerRecord = functions.getPlayerRecord(player.getUniqueId().toString());
		String format = "";
		if (playerRecord.kingdom != null) {
			//name moet naar prefix wanneer dit mogelijk is gemaakt!
			format = ChatColor.DARK_GRAY + "[" + ChatColor.RESET + playerRecord.kingdom.name + ChatColor.DARK_GRAY + "][" + ChatColor.GRAY  + player.getName() + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + event.getMessage();
		}else {
			//niet in kingdom
			format = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "No kingdom" + ChatColor.DARK_GRAY + "][" + ChatColor.GRAY  + player.getName() + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + event.getMessage();
		}
		event.setFormat(format);
	}

}
