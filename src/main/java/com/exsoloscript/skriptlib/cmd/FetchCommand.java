package com.exsoloscript.skriptlib.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.exsoloscript.skriptlib.SkriptLib;

public class FetchCommand implements CommandExecutor {

	private SkriptLib plugin;

	public FetchCommand(SkriptLib s) {
		this.plugin = s;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("fetch")) {
			if (sender.isOp()) {
				plugin.fetchData();
			}
		}
		
		return false;
	}
}
