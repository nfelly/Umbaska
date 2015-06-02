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
import uk.co.umbaska.Utils.Disguise.DisguiseHandler;
import uk.co.umbaska.WildSkript.system.*;

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
    @Override
    public void onEnable() {
        plugin = this;
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
            getLogger().info(ChatColor.GREEN + "[Umbaska] Hooked into metrics! :)");
        } catch (IOException e) {
            getLogger().info(ChatColor.DARK_RED + "[Umbaska] Failed to load metrics :(");
        }
        final PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(this, this);
        Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib");
        if (pl != null) {
            enthider = new EntityHider(Main.getInstance(), EntityHider.Policy.BLACKLIST);
        }
        disguiseHandler = new DisguiseHandler(this);
        saveDefaultConfig();
        Register.registerAll();

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