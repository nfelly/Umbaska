package uk.co.umbaska.Managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class YAMLManager {

	public FileConfiguration newCustomYml(String file) {
		File customYml = new File(file);
		return YamlConfiguration.loadConfiguration(customYml);
	}
	
	
	public Object getSingleYAML(FileConfiguration customConfig, String path, Integer type) {
		if (type == 1) {
			return customConfig.getString(path);
		} else if (type == 2) {
			return customConfig.getInt(path);
		} else if (type == 3) {
			return customConfig.getBoolean(path);
		} else {
			return null;
		}
	}
	
	public void writeYAML(File file, FileConfiguration customConfig, String path, String value) {
		customConfig.set(path, value);
		try {
			customConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteYAML(File file, FileConfiguration customConfig, String path) {
		customConfig.set(path, null);
		try {
			customConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
