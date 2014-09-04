package com.exsoloscript.skriptlib.timer;

import com.exsoloscript.skriptlib.SkriptLib;

public class FetchFileTreeTimer implements Runnable {

	private SkriptLib plugin;
	
	public FetchFileTreeTimer(SkriptLib s) {
		this.plugin = s;
	}
	
	public void run() {
		plugin.fetchData();
	}

}
