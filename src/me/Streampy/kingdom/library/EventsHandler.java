package me.Streampy.kingdom.library;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Streampy.kingdom.Main;

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

}
