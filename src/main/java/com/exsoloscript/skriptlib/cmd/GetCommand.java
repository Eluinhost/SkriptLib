package com.exsoloscript.skriptlib.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.exsoloscript.skriptlib.SkriptLib;

public class GetCommand implements CommandExecutor {

	private SkriptLib plugin;
	
	public GetCommand(SkriptLib s) {
		this.plugin = s;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("get")) {
			if (sender.isOp()) {
				if (args.length > 0) {
					if (args[0].endsWith(".sk")) {
						this.plugin.getSkript(args[0]);
					} else if (args[0].endsWith(".jar")) {
						this.plugin.getPlugin(args[0]);
					} else {
						sender.sendMessage("Unknown file ending. Allowed file endings are .sk and .jar");
						return true;
					}
					
					plugin.messageOps("File was donwloaded successfully");
				} else
					sender.sendMessage(plugin.prefix() + "Correct usage: /get <filename>");
			} else 
				sender.sendMessage(plugin.prefix() + "Only an operator can execute this command.");
		}
		return false;
	}
}
