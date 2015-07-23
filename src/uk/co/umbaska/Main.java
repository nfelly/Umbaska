/*
 * Main.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;

//import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
//import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;
import org.mcstats.Metrics;

import uk.co.umbaska.Bungee.*;
import uk.co.umbaska.Managers.Register;
import uk.co.umbaska.ProtocolLib.*;
import uk.co.umbaska.System.*;
import uk.co.umbaska.Utils.Disguise.DisguiseHandler;
import uk.co.umbaska.Utils.FreezeListener;
import uk.co.umbaska.Utils.ItemManager;

import java.io.IOException;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    //public static HologramManager holoManager;
    public static Entity armorStand;
    public static Plugin dynmap;
    public static DynmapAPI api;
    public static EntityHider enthider;
    public final Logger logger = Logger.getLogger("Minecraft");
    public static DisguiseHandler disguiseHandler;
    public static Main plugin;
    public static Messenger messenger;
    private static WildSkriptTimer timer;
    public static FreezeListener freezeListener;
    public static ItemManager itemManager;
    public static String schemFolder;
    @Override
    public void onEnable() {
        plugin = this;
        if (!getConfig().contains("Metrics")){
            getConfig().set("Metrics", true);
        }
        if (getConfig().getBoolean("Metrics")) {
            try {
                Metrics metrics = new Metrics(this);
                metrics.start();
                getLogger().info(ChatColor.GREEN + "[Umbaska] Hooked into metrics! :)");
            } catch (IOException e) {
                getLogger().info(ChatColor.DARK_RED + "[Umbaska] Failed to load metrics :(");
            }
        }
        if (Register.getVersion().contains("1_7")){
            meNoLikey1_7();
            return;
        }
        final PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(this, this);
        Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib");
        if (pl != null) {
            enthider = new EntityHider(Main.getInstance(), EntityHider.Policy.BLACKLIST);
        }
        disguiseHandler = new DisguiseHandler(this);
        if (!getConfig().contains("force-generate-18-features")){
            getConfig().set("force-generate-18-features", true);
        }
        if (!getConfig().contains("schematic_location")){
            getConfig().set("schematic_location", "PLUGINFOLDER/schematics/ #Use PLUGINFOLDER to get Umbaska's folder. Otherwise, put in your own directory.");
        }
        timer = new WildSkriptTimer();
        timer.run();
        schemFolder = getConfig().getString("schematic_location").replace("PLUGINFOLDER", getDataFolder().getAbsolutePath());
        saveDefaultConfig();
        Register.registerAll();
        freezeListener = new FreezeListener(this);
        itemManager = new ItemManager();


    }

    public void meNoLikey1_7(){
        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (p.isOp())
                    p.sendMessage("[Umbaska] Hey, you're running 1.7! Umbaska doesn't like 1.7! Meany ;w;");
                }
            }
        }, 60 * 20, 60 * 20);
    }
    
    public void onDisable() {
    	plugin = null;
    	inst = null;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onSpawn(CreatureSpawnEvent e){
        e.getEntity().setMetadata("spawnreason", new FixedMetadataValue(this, e.getSpawnReason()));
    }

    public static WildSkriptTimer getTimer(){
        return timer;
    }

    private static Main inst;

    public Main() {
        inst = this;
    }

    public static Main getInstance() {
        return inst;
    }
}