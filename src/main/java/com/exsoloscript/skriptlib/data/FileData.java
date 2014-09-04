package com.exsoloscript.skriptlib.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileData {

	private String name;
	private Map<String, String> properties;

	public FileData(String name) {
		this.name = name;
		this.properties = new HashMap<String, String>();
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public static List<FileData> parse(String[] entries) {
		List<FileData> data = new ArrayList<FileData>();

		for (String s : entries) {
			s = s.trim();
			String[] infos = s.split(" ");
			if (infos.length > 0) {
				FileData fd = new FileData(infos[0]);
				infos = Arrays.copyOfRange(infos, 1, infos.length);
				for (String p : infos) {
					String[] prop = p.split("=");
					if (prop.length == 2) {
						fd.getProperties().put(prop[0], prop[1]);
					}
				}
				
				data.add(fd);
			}
		}

		return data;
	}
}
