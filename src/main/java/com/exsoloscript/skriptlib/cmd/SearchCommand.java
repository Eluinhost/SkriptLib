package com.exsoloscript.skriptlib.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.exsoloscript.skriptlib.SkriptLib;
import com.exsoloscript.skriptlib.data.FileData;

public class SearchCommand implements CommandExecutor {

	private SkriptLib plugin;

	public SearchCommand(SkriptLib s) {
		this.plugin = s;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equalsIgnoreCase("search") || label.equalsIgnoreCase("lookup")) {
			if (sender.isOp()) {
				if (args.length > 0) {
					List<FileData> matches = new ArrayList<FileData>();

					String name = args[0];
					String author = "";
					String version = "";

					if (args.length > 1) {
						author = args[1];
						if (args.length > 2) {
							version = args[2];
						}
					}

					for (FileData fd : this.plugin.getFiles()) {
						if (nameMatches(fd, name) && authorMatches(fd, author) && versionMatches(fd, version))
							matches.add(fd);
					}

					sender.sendMessage(plugin.prefix() + "Listing all files matching to the given data:");

					if (matches.size() > 0) {
						for (FileData fd : matches) {
							sender.sendMessage(ChatColor.GRAY + " - " + fd.getName() + " | author: " + fd.getProperties().get("author") + " | version: " + fd.getProperties().get("version"));
						}
					} else {
						sender.sendMessage(ChatColor.RED + "No matches for the given name");
					}

				} else {
					sender.sendMessage(plugin.prefix() + "Correct usage: /" + label + " <filename;*> [author] [version]");
				}
			} else {

			}
		}

		return false;
	}

	private boolean nameMatches(FileData fd, String name) {
		if (!name.equalsIgnoreCase("*"))
			return fd.getName().contains(name);
		else
			return true;
	}

	private boolean authorMatches(FileData fd, String author) {
		if (author.length() > 0) {
			return author.equalsIgnoreCase(fd.getProperties().get("author"));
		} else
			return true;
	}

	private boolean versionMatches(FileData fd, String version) {
		if (version.length() > 0) {
			return version.equalsIgnoreCase(fd.getProperties().get("version"));
		} else
			return true;
	}
}
