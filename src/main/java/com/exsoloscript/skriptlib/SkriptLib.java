package com.exsoloscript.skriptlib;

import com.exsoloscript.skriptlib.cmd.FetchCommand;
import com.exsoloscript.skriptlib.cmd.GetCommand;
import com.exsoloscript.skriptlib.cmd.SearchCommand;
import com.exsoloscript.skriptlib.data.FileData;
import com.exsoloscript.skriptlib.timer.FetchFileTreeTimer;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SkriptLib extends JavaPlugin {

	private List<FileData> files;
	
	public void onEnable() {
		this.files = new ArrayList<FileData>();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new FetchFileTreeTimer(this), 0, 6000);
		
		registerCommands();
	}

	public void onDisable() {

	}
	
	private void registerCommands() {
		getCommand("get").setExecutor(new GetCommand(this));
		getCommand("search").setExecutor(new SearchCommand(this));
		getCommand("fetch").setExecutor(new FetchCommand(this));
	}

	public void getSkript(String name) {
		if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
			download(name, "plugins/Skript/scripts");
		} else {
			messageOps("Skript has to be installed before you can download any scripts.");
		}
	}

	public void getPlugin(String name) {
		download(name, "plugins");
	}
	
	private void download(String name, String dir) {
		URL url = null;
		try {
			url = new URL("http://skriptlib.exsoloscript.com/files/" + name + "/" + name);
		} catch (MalformedURLException e) {
			messageOps("Something went wrong whilst connecting to the SkriptLib server.");
			return;
		}
		
		if (!exists(url)) {
			messageOps("Script with the name '" + name + "' does not exist. Look for scripts on the SkriptLib server with /search or /lookup");
			return;
		}
		
		try {
            ByteStreams.copy(Resources.newInputStreamSupplier(url), Files.newOutputStreamSupplier(new File(dir, name)));
		} catch (IOException e) {
			messageOps("Something went wrong whilst copying the file from the SkriptLib server to your Skript direcotry");
			return;
		}
	}

	public void messageOps(String msg) {
		for (Player p : Bukkit.getOnlinePlayers())
			if (p.isOp())
				p.sendMessage(prefix() + msg);
		System.out.println(prefix() + msg);
	}
	
	public void fetchData() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new URL("http://skriptlib.exsoloscript.com/files.php").openStream()));

			String s = in.readLine();
			
			if (s != null) {
				this.files = FileData.parse(s.split("<br>"));
			}
			
			in.close();
			
			messageOps("File tree was fetched from the server.");
		} catch (Exception e) {
			messageOps("Error whilst trying to fetch the file tree from the server.");
		}
	}

	public boolean exists(URL url) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String prefix() {
		return ChatColor.AQUA + "[" + ChatColor.GOLD + "SkriptLib" + ChatColor.AQUA + "] " + ChatColor.RESET;
	}

	public List<FileData> getFiles() {
		return files;
	}

	public void setFiles(List<FileData> files) {
		this.files = files;
	}
	
}
