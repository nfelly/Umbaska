/*
 * Main.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.nfell2009.umbaska;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;
import org.mcstats.Metrics;

import com.palmergames.bukkit.towny.object.Town;

import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.Skript;
import uk.nfell2009.umbaska.Sound.*;
/*
 *  Importing local packages
 */
import uk.nfell2009.umbaska.PlotMe.*;
import uk.nfell2009.umbaska.Spawner.*;
import uk.nfell2009.umbaska.Towny.*;
import uk.nfell2009.umbaska.UUID.ExprNamesOfPlayer;
import uk.nfell2009.umbaska.Vault.*;
import uk.nfell2009.umbaska.Bungee.*;
import uk.nfell2009.umbaska.Dynmap.*;
import uk.nfell2009.umbaska.Factions.ExprFactionOfPlayer;
import uk.nfell2009.umbaska.Misc.*;
import uk.nfell2009.umbaska.NametagEdit.*;


public class Main extends JavaPlugin implements Listener {

	public static Plugin dynmap;
	public static DynmapAPI api;
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;
	 @Override
	    public void onEnable() {
		 
		 try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		    getLogger().info(ChatColor.GREEN + "[Umbaska] Hooked into metrics! :)");
		 } catch (IOException e) {
		    getLogger().info(ChatColor.DARK_RED + "[Umbaska] Failed to load metrics :(");
		 }
		 
		 final PluginManager pluginManager = getServer().getPluginManager();
		 pluginManager.registerEvents(this, this);
		 
		 loadConfiguration();
		 
		 Plugin pl = getServer().getPluginManager().getPlugin("PlotMe");
		 
		 if (pl != null) {
		 
		 /*
		  *  PlotMe - Effects
		  */
		 
		 Skript.registerEffect(EffPlotTeleport.class, new String[] { "teleport %player% to %string%[ in %world%]" });
		 Skript.registerEffect(EffClearPlot.class, new String[] { "clear plot %string% in %world%" });
		 Skript.registerEffect(EffMovePlot.class, new String[] { "move %string% to %string% in %world%" });
		 Skript.registerEffect(EffDenyPlayer.class, new String[] { "deny %player% from %string%" });
		 Skript.registerEffect(EffUnDeny.class, new String[] { "allow %player% to %string%" });
		 
		 
		 /*
		  *  PlotMe - Expressions
		  */
		 
		 Skript.registerExpression(ExprPlotAtPlayer.class, String.class, ExpressionType.PROPERTY, new String[] {"plot at %player%"});
		 Skript.registerExpression(ExprPlotAtLoc.class, String.class, ExpressionType.PROPERTY, new String[] {"plot at location %location%"});
		 Skript.registerExpression(ExprGetOwner.class, String.class, ExpressionType.PROPERTY, new String[] {"get owner of %string%"});
		 Skript.registerExpression(ExprGetPlayerPlots.class, String.class, ExpressionType.PROPERTY, new String[] {"plots of %player%"});
		 Skript.registerExpression(ExprTopCorner.class, Location.class, ExpressionType.PROPERTY, new String[] {"(top|upper) corner of %string% in %world%"});
		 Skript.registerExpression(ExprBottomCorner.class, Location.class, ExpressionType.PROPERTY, new String[] {"(bottom|lower) corner of %string% in %world%"});
		 getLogger().info(ChatColor.GREEN + "[Umbaska] Hooked into PlotMe");
		 } 
		 
		 /*
		  *  Spawner - Effects
		  */
		 
		 Skript.registerEffect(EffSetSpawner.class, new String[] { "set spawner %location% to %string%" });
		 Skript.registerEffect(EffSetDelay.class, new String[] { "set delay of %location% to %integer%" });
		 Skript.registerEffect(EffMFG_Drop.class, new String[] { "drop a spawner at %location% based on %block%" });
		 Skript.registerEffect(EffMFG_GiveSpawner.class, new String[] { "give a spawner to %player% based on %block%" });
		 Skript.registerEffect(EffMFG_SetSpawner.class, new String[] { "set spawner at %location% to its type" });
		 
		 
		 /*
		  *  Spawner - Expressions
		  */
		 
		 Skript.registerExpression(ExprDelayTime.class, Integer.class, ExpressionType.PROPERTY, new String[] {"delay time of %location%"});
		 Skript.registerExpression(ExprSpawnedType.class, String.class, ExpressionType.PROPERTY, new String[] {"entity type of %location%"});
		 Skript.registerExpression(ExprItemName.class, String.class, ExpressionType.SIMPLE, "item name");
		 getLogger().info(ChatColor.GREEN + "[Umbaska] Just loaded Spawner syntaxs...");
		 pl = getServer().getPluginManager().getPlugin("Towny");
		 
		 /*
		  *  UUID - Expressions
		  */
		 
		 Skript.registerExpression(ExprNamesOfPlayer.class, String.class, ExpressionType.SIMPLE, "names of %string%");
		 getLogger().info(ChatColor.GREEN + "[Umbaska] Finished loading up UUID stuffs");
		 
		 if (pl != null) {
		 
		 /*
		  *  Towny - Effects
		  */
		 
		 
		 
		 Skript.registerEffect(EffSetPlotOwner.class, new String[] { "set owner of plot at %location% to %player%" });
		 Skript.registerEffect(EffSetPlotPrice.class, new String[] { "set price of plot at %location% to %double%" });
		 
		 /*
		  *  Towny - Expressions
		  */
		 
		 Skript.registerExpression(ExprTownAtPlayer.class, String.class, ExpressionType.PROPERTY, new String[] {"town at %player%"});
		 Skript.registerExpression(ExprTownOfPlayer.class, Town.class, ExpressionType.PROPERTY, new String[] {"town of %player%"});
		 Skript.registerExpression(ExprTDBank.class, Double.class, ExpressionType.PROPERTY, new String[] {"town balance of %string%"});
		 Skript.registerExpression(ExprTDPlayerCount.class, Integer.class, ExpressionType.PROPERTY, new String[] {"player[ ]count of %string%"});
		 Skript.registerExpression(ExprTDPlayers.class, String.class, ExpressionType.PROPERTY, new String[] {"players of %string%"});
		 Skript.registerExpression(ExprTDTaxes.class, Double.class, ExpressionType.PROPERTY, new String[] {"town taxes of %string%"});
		 Skript.registerExpression(ExprPlotOwner.class, String.class, ExpressionType.PROPERTY, new String[] {"owner of plot at %location%"});
		 Skript.registerExpression(ExprPlotPrice.class, Double.class, ExpressionType.PROPERTY, new String[] {"price of plot at %location%"});
		 Skript.registerExpression(ExprRDLastOnline.class, String.class, ExpressionType.PROPERTY, new String[] {"resident data last online of %player%"});
		 Skript.registerExpression(ExprRDLastOnlineDate.class, String.class, ExpressionType.PROPERTY, new String[] {"resident data last online date of %player%"});
		 Skript.registerExpression(ExprRDChatName.class, String.class, ExpressionType.PROPERTY, new String[] {"resident data chat name of %player%"});
		 Skript.registerExpression(ExprRDFriends.class, String.class, ExpressionType.PROPERTY, new String[] {"resident data friends of %player%"});
		 Skript.registerExpression(ExprRDNationRanks.class, String.class, ExpressionType.PROPERTY, new String[] {"resident data nation ranks of %player%"});
		 Skript.registerExpression(ExprRDRegistered.class, Long.class, ExpressionType.PROPERTY, new String[] {"resident data registered of %player%"});
		 Skript.registerExpression(ExprRDSurname.class, String.class, ExpressionType.PROPERTY, new String[] {"resident data surname of %player%"});
		 Skript.registerExpression(ExprRDTitle.class, String.class, ExpressionType.PROPERTY, new String[] {"resident data title of %player%"});
		 
		 /*
		  *  Towny - Conditions
		  */
		 
		 Skript.registerCondition(CondIsAlly.class, "%string% is ally with %string%", "%string% is(n't| not) ally with %string%");
		 Skript.registerCondition(CondIsNeutral.class, "%string% is neutral", "%string% is(n't| not) neutral");
		 Skript.registerCondition(CondIsEnemy.class, "%string% is enemy with %string%", "%string% is(n't| not) enemy with %string%");
		 getLogger().info(ChatColor.GREEN + "[Umbaska] Towny = hooked!");
		 }
		 
		 
		 
		 Skript.registerEffect(EffDropAll.class, new String[] { "force drop inventory of %player% at %location%" });
		 pl = getServer().getPluginManager().getPlugin("UmbaskaAPI");
		 if (pl != null) {
			 
			 /*
			  *  UmbaskaAPI - Effects
			  */
			 
			 Skript.registerEffect(EffImgInChat.class, new String[] { "show %player% image %string% with %string%, %string%, %string%" });
			 Skript.registerEffect(EffImgFromURL.class, new String[] { "show %player% image from %string% with %string%, %string%, %string%" });
			 getLogger().info(ChatColor.GREEN + "[Umbaska] Hooked into UmbaskaAPI");
			 
			 /*
			  *  UmbaskaAPI - Expressions
			  */
			 
			 Skript.registerExpression(ExprFactionOfPlayer.class, String.class, ExpressionType.PROPERTY, new String[] {"faction of %player%"});
		 }
		 
		 /*
		  *  Misc - Expressions
		  */
		 
		 Skript.registerExpression(ExprArmourPoints.class, Double.class, ExpressionType.PROPERTY, new String[] {"armour points of %player%"});
		 Skript.registerExpression(ExprItemCountInSlot.class, ItemStack.class, ExpressionType.PROPERTY, new String[] {"items in %number% of %player%"});
		 
		 /*
		  *  Events - Registeration
		  */
		 
		 
		 Skript.registerEvent("On Umbaska Rightclick", SimpleEvent.class, PlayerInteractEvent.class, "umbaska rightclick");
		 EventValues.registerEventValue(PlayerInteractEvent.class,
			        Player.class, new Getter<Player, PlayerInteractEvent>() {
			                @Override
			                @javax.annotation.Nullable
			                public Player get(PlayerInteractEvent e) {
			                        return (Player) e.getPlayer();
			                }
		 }, 0);
		 getLogger().info(ChatColor.GREEN + "[Umbaska] Loaded up umbaska rightclick. You don't need to know about this...");

		 
		 /*
		  *  Bungee - Effects
		  */
		 if (use_bungee == true) {
			 new Messenger(this);
			 Skript.registerEffect(EffChangeServer.class, new String[] { "send %player% to %string%" });
			 
			 
 		 /*
		  *  Bungee - Expressions
		  */
			 
			 Skript.registerExpression(ExprBungeeUUID.class, UUID.class, ExpressionType.PROPERTY, new String[] {"bungee uuid of %player%"});
			 
			 getLogger().info(ChatColor.GREEN + "[Umbaska] BungeeCord is a hooked! GO GO GO!");
			 
		 }
		 
		 pl = getServer().getPluginManager().getPlugin("NametagEdit");
		 
		 if (pl != null) {
			 if (enable_tag_features == true) {
		 
		 /*
		  *  NametagEdit - Effects
		  */
		 
		 Skript.registerEffect(EffSetPrefix.class, new String[] { "set prefix of %player% to %string%" });
		 Skript.registerEffect(EffSetSuffix.class, new String[] { "set suffix of %player% to %string%" });
		 Skript.registerEffect(EffSetNametag.class, new String[] { "set name tag of %player% to %string%, %string%, %string%" });
		 
		 /*
		  *  NametagEdit - Expressions
		  */
		 
		 Skript.registerExpression(ExprGetPrefix.class, String.class, ExpressionType.PROPERTY, new String[] {"prefix of %player%"});
		 Skript.registerExpression(ExprGetSuffix.class, String.class, ExpressionType.PROPERTY, new String[] {"suffix of %player%"});
		 Skript.registerExpression(ExprGetNametag.class, String.class, ExpressionType.PROPERTY, new String[] {"name tag of %player%"});
		 getLogger().info(ChatColor.GREEN + "[Umbaska] Le hooked le NametagEdit");
			 }
		 }
		 
		 pl = getServer().getPluginManager().getPlugin("NoteBlockAPI");
		 if (pl != null) {
		 
		 /*
		  *  Sound - Effects
		  */
		 
		 Skript.registerEffect(EffPlayTrack.class, new String[] { "play sound %string% to %player%" });
		 getLogger().info(ChatColor.GREEN + "[Umbaska] Today I learnt that there were NoteBlockAPI hooks (01/03/15)");
		 
		 }
		 
		 
		 pl = getServer().getPluginManager().getPlugin("dynmap");
		 if (pl != null) {
		 /*
		  *  Dynmap - Other
		  */
	        PluginManager pm = getServer().getPluginManager();
	        dynmap = pm.getPlugin("dynmap");
	        api = (DynmapAPI)dynmap;
	        if (api == null) {
	        	getLogger().info(ChatColor.RED + "[Umbaska] Damn son! There was a problem hooking into dynmap. Sorry dude.");
	        }
		 
		 /*
		  *  Dynmap - Effects
		  */
		 
			 Skript.registerEffect(EffSetVisOfPlayer.class, new String[] { "set dynmap visibility of %player% to %boolean%" });
	        
	     /*
	      *  Dynmap - Expressions
	      */
	        
			 Skript.registerExpression(ExprVisOfPlayer.class, Boolean.class, ExpressionType.PROPERTY, new String[] {"dynmap visibility of %player%"});
			 getLogger().info(ChatColor.GREEN + "[Umbaska] Can I appear on the livemap? No? Well I hooked into Dynmap");
		 }
		 
		 pl = getServer().getPluginManager().getPlugin("Vault");
		 
		 if (pl != null) {
		 
		 
		 /*
		  *  Vault - Expressions
		  */
			 setupPermissions();
			 Skript.registerExpression(ExprGroupOfPlayer.class, String.class, ExpressionType.PROPERTY, new String[] {"primary group of %player%"});
			 getLogger().info(ChatColor.GREEN + "[Umbaska] i can haz perform perms stuffs!!! Aka hooked into Vault");
			 
		 }
		 
		 
		 
	 }
	 
	 
	 
	 public static Permission perms = null;
	 
	 public boolean setupPermissions() {
	 	 RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
	     perms = rsp.getProvider();
	     return perms != null;
	 }
	 
	 public void loadConfiguration(){
	 	String path = "use_bungee";
	 	getConfig().addDefault(path, false);
	 	path = "enable_tag_features";
	 	getConfig().addDefault(path, true);
	 	getConfig().options().copyDefaults(true);
	 	saveConfig();
	 }
	 	
	 public Boolean use_bungee = getConfig().getBoolean("use_bungee");
	 public Boolean enable_tag_features = getConfig().getBoolean("enable_tag_features");
	 
	 private static Main inst;
	  
	 public Main() {
		 inst = this;
	 }
	  
	 public static Main getInstance() {
		 return inst;
	 }
}