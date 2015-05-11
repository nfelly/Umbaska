package uk.co.umbaska.GattSk.Extras;

import ch.njol.skript.Skript;
import uk.co.umbaska.*;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.regex.Matcher;


/**
 * Created by Zachary on 11/14/2014.
 */
public class WorldManagers {

	public static String error;

	public static World lastCreatedWorld;

	public static String[] worldList = new String[]{};

	public static void createWorld(String name){
		if (!worldExists(name)){
			World world = Bukkit.createWorld(new WorldCreator(name));
			lastCreatedWorld = Bukkit.getWorld(world.getName());
		}
		else{
			error = "World already exists";
			worldError();
		}

	}

	public static void createWorldUsingGenerator(String name, String generatorName){
		if (!worldExists(name)){
			WorldCreator c = new WorldCreator(name);
			c.generator(getGenerator(generatorName, name));
			World world = c.createWorld();
			lastCreatedWorld = Bukkit.getWorld(world.getName());
		}
		else{
			error = "World already exists";
			worldError();
		}

	}

	static ChunkGenerator getGenerator(String generator, String name)
	{
		if(generator == null)
		{
			return null;
		}
		Plugin generatorPlugin = Bukkit.getPluginManager().getPlugin(generator);
		if(generatorPlugin == null)
		{
			return null;
		}
		return generatorPlugin.getDefaultWorldGenerator(name, null);
	}

	public static void createWorldFrom(String name, String folderName){
		if (!worldExists(name)){
			File srcFolder = new File(folderName);
			File destFolder = new File(name);
			if (!srcFolder.exists()){
				error = "Folder to clone doesn't exist!";
				worldError();
				return;
			}
			else {

				try{
					FileUtils.deleteDirectory(destFolder);
					FileUtils.copyDirectory(srcFolder, destFolder);
				}catch(IOException e){
					e.printStackTrace();
					//error, just exit
					System.exit(0);
				}


				final String worldname = name;
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {

					public void run() {

						World world = Bukkit.createWorld(new WorldCreator(worldname));
						lastCreatedWorld = Bukkit.getWorld(world.getName());

					}
				}, 60L);


			}
		}
		else{
			error = "World already exists";
			worldError();
		}

	}

	public static void copyFolder(File src, File dest)
			throws IOException {

		if(src.isDirectory()){

			//if directory not exists, create it
			if(!dest.exists()){
				dest.mkdir();
				System.out.println("Directory copied from "
						+ src + "  to " + dest);
			}

			//list all the directory contents
			String files[] = src.list();

			for (String file : files) {
				//construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				//recursive copy
				copyFolder(srcFile,destFile);
			}

		}else{
			//if file, then copy it
			//Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes
			while ((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			System.out.println("File copied from " + src + " to " + dest);
		}
	}
	public static void deleteWorld(String name){
		if (worldExists(name)){
			Bukkit.unloadWorld(name, false);

			final String delfile = name.replaceAll("/", Matcher.quoteReplacement(File.separator));

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {

				public void run() {
					File file = new File(delfile);
					file.delete();

				}

			}, 20L);
		}
		else{
			error = "World doesn't exist";
			worldError();
		}
	}

	public static void unloadWorld(String name){
		if (worldExists(name)){
			Bukkit.unloadWorld(name, true);
		}
		else{
			error = "World doesn't exist";
			worldError();
		}
	}

	public static void loadWorld(String name){
		if (!worldExists(name)){
			Bukkit.createWorld(new WorldCreator(name));
		}
		else{
			error = "World already exists";
			worldError();
		}

	}

	public static boolean worldExists(String name){
		if (Bukkit.getWorld(name) != null){
			return true;
		}
		else{
			return false;
		}
	}

	public static void worldError(){
		Skript.error(Skript.SKRIPT_PREFIX + "Error occured within WorldManager'" + error + "'");
	}

}