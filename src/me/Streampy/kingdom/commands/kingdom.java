package me.Streampy.kingdom.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.kingdom.Main;
import me.Streampy.kingdom.records.records;
import me.Streampy.kingdom.records.records.kingdomRec;
import me.Streampy.kingdom.subcommands.Create;
import me.Streampy.kingdom.subcommands.Info;
import me.Streampy.kingdom.subcommands.Join;
import me.Streampy.kingdom.subcommands.List;

public class kingdom implements CommandExecutor {

	public kingdom(Main main) {
		// TODO Auto-generated constructor stub
	}
	
	static ArrayList<kingdomRec> kingdomsList = records.kingdomsList;

	Create create = new Create();
	Info info = new Info();
	List list = new List();
	Join join = new Join();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kingdom.help")) {
				if (args.length == 0) {
					menu(player, cmd);
				}else {
					switch(args[0]) {
						case "create": 
							create.onCommand(sender, cmd, label, args);
							break; 
						case "list": 
							list.onCommand(sender, cmd, label, args);
							break;
						case "info":
							info.onCommand(sender, cmd, label, args);
							break;
						case "join":
							join.onCommand(sender, cmd, label, args);
							break;
						default: 
							menu(player, cmd);
					}
				}
			}else {
				player.sendMessage(ChatColor.RED + "Jij hebt niet de goede permissions!");
			}
		}
		return false;
	}
	
	public void menu(Player player, Command cmd) {
		player.sendMessage(commandMessage(cmd, player, "help", "kingdom.help"));
		player.sendMessage(commandMessage(cmd, player, "list", "kingdom.list"));
		player.sendMessage(commandMessage(cmd, player, "info <name>", "kingdom.info"));
	}
	
	public String commandMessage(Command cmd, Player player, String command, String permission) {
		if (player.hasPermission(permission)) {
			return ChatColor.GRAY + "/" + cmd.getName() + " " + ChatColor.GREEN + command;
		}
		return null;
	}
}
