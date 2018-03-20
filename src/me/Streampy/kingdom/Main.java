package me.Streampy.kingdom;

import org.bukkit.plugin.java.JavaPlugin;

import me.Streampy.kingdom.commands.kingdom;
import me.Streampy.kingdom.library.functions;

public class Main extends JavaPlugin {

	public void onEnable() {
		functions.load();
		
		getCommand("kingdom").setExecutor(new kingdom(this));
		getCommand("kd").setExecutor(new kingdom(this));
	}
	
	public void onDisable() {
		functions.save();
	}
	
}
