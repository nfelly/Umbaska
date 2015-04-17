/*
 * Main.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.nfell2009.umbaska;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.dynmap.DynmapAPI;
import org.mcstats.Metrics;

import uk.nfell2009.umbaska.Bungee.EffChangeServer;
import uk.nfell2009.umbaska.Bungee.ExprBungeeUUID;
import uk.nfell2009.umbaska.Bungee.Messenger;
import uk.nfell2009.umbaska.Dynmap.EffSetVisOfPlayer;
import uk.nfell2009.umbaska.Dynmap.ExprVisOfPlayer;
import uk.nfell2009.umbaska.Factions.ExprFactionOfPlayer;
import uk.nfell2009.umbaska.Gatt.EffOpenDispenser;
import uk.nfell2009.umbaska.Gatt.EffOpenHopper;
import uk.nfell2009.umbaska.GattSk.Effects.*;
import uk.nfell2009.umbaska.GattSk.Expressions.*;
import uk.nfell2009.umbaska.Misc.*;
import uk.nfell2009.umbaska.NametagEdit.*;
import uk.nfell2009.umbaska.PlotMe.*;
import uk.nfell2009.umbaska.ProtocolLib.*;
import uk.nfell2009.umbaska.Sound.EffPlayTrack;
import uk.nfell2009.umbaska.Spawner.*;
import uk.nfell2009.umbaska.Towny.*;
import uk.nfell2009.umbaska.UUID.ExprNamesOfPlayer;
import uk.nfell2009.umbaska.Vault.ExprGroupOfPlayer;
import uk.nfell2009.umbaska.WildSkript.system.*;
import uk.nfell2009.umbaska.v1_8.ArmorStands.*;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

/*
 *  Importing local packages
 */


public class Main extends JavaPlugin implements Listener {

	public static Plugin dynmap;
	public static DynmapAPI api;
	public static EntityHider enthider;
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;
    public static Messenger messenger;
	private static WildSkriptTimer timer;

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



		 pl = getServer().getPluginManager().getPlugin("ProtocolLib");
		 if (pl != null) {
			 /*
			  * Protocol Lib - Effects - Added by Funnygatt
			  */
			 enthider = new EntityHider(this, EntityHider.Policy.BLACKLIST);
			 Skript.registerEffect(EffHideEntity.class, new String[] {"hide %entity% from %player%"});
			 Skript.registerEffect(EffShowEntity.class, new String[] {"show %entity% to %player%"});
			 Skript.registerEffect(EffToggleVisibility.class, new String[] {"toggle visibility of %entity% for %player%"});
			 Skript.registerExpression(ExprCanSee.class, Boolean.class, ExpressionType.PROPERTY, new String[]{"visibility of %entity% for %player%"});
			 getLogger().info("[Umbaska] Hooked into ProtocolLib and might have added some sweet, sh17 <3 - Funnygatt");
		 }

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
			 messenger = new Messenger(this);
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
		 
		 Skript.registerEffect(EffPlayTrack.class, new String[] { "play (track|song|midi) %string% to %player%" });
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
			 
		/*
		 *   WildSkript - Expressions
		 */
			 Skript.registerExpression(ExprFreeMemory.class, Integer.class, ExpressionType.PROPERTY, new String[] {"free memory"});
			 Skript.registerExpression(ExprJavaVersion.class, String.class, ExpressionType.PROPERTY, new String[] {"java version"});
			 Skript.registerExpression(ExprMaxMemory.class, Integer.class, ExpressionType.PROPERTY, new String[] {"max memory"});
			 Skript.registerExpression(ExprTotalMemory.class, Integer.class, ExpressionType.PROPERTY, new String[] {"total memory"});
			 Skript.registerExpression(ExprTPS.class, Double.class, ExpressionType.PROPERTY, new String[] {"tps"});
			 Skript.registerExpression(ExprPing.class, Integer.class, ExpressionType.PROPERTY, new String[] {"%player% ping"});
			 
		/*
		 *  GattSk stuff
		 */
			 
				//General

				Skript.registerEffect(EffRemoveExplodedBlock.class, "(remove|delete) %block% from [better][ ][new] exploded blocks");

				//Scoreboards

				Skript.registerEffect(EffNewScoreboard.class, "create [a] new scoreboard [named] %string%");
				Skript.registerEffect(EffSetPlayerScoreboard.class, "set scoreboard of %players% to %string%");
				Skript.registerEffect(EffSetScore.class, "set value of score %string% (for|in) [score][board] %string% objective %string% to %number%");
				Skript.registerEffect(EffResetScore.class, "reset [value] [of] score %string% (for|in) [score][board] %string%");
				Skript.registerEffect(EffNewObjective.class, "create [a] [new] %string% objective for [score][board] %string% (called|named) %string%");
				Skript.registerEffect(EffSetObjectiveDisplay.class, "set objective display slot for [objective] %string% in [score][board] %string% to %string%");
				Skript.registerEffect(EffSetObjectiveName.class, "set objective display name for [objective] %string% in [score][board] %string% to %string%");
				Skript.registerEffect(EffUnregisterObjective.class, "unregister objective %string% in [score][board] %string%");

				Skript.registerEffect(EffCreateTeam.class, "create team %string% in [score][board] %string%");
				Skript.registerEffect(EffTeamPlayer.class, "(0¦remove|1¦add) [player] %offlineplayer% (from|to) team %string% in [score][board] %string%");
				Skript.registerEffect(EffSetTeamPrefix.class, "set (0¦suffix|1¦prefix) for team %string% in [score][board] %string% to %string%");
				Skript.registerEffect(EffSetTeamFF.class, "set friendly fire for team %string% in [score][board] %string% to %boolean%");
				Skript.registerEffect(EffSetTeamSeeInvis.class, "set see friendly invisibles for team %string% in [score][board] %string% to %boolean%");

				Skript.registerExpression(ExprGetScore.class, Integer.class, ExpressionType.PROPERTY, new String[] {"value [of] %string% objective %string% for [score] %string%"});
				//Skript.registerExpression(ExprGetPlayerScoreboard.class, Scoreboard.class, ExpressionType.PROPERTY, new String[] {"scoreboard of %player%"});
				Skript.registerExpression(ExprGetObjectiveType.class, String.class, ExpressionType.PROPERTY, new String[] {"objective type of %string% (from|in) [score][board] %scoreboard%"});
				Skript.registerExpression(ExprGetObjectiveDisplay.class, Objective.class, ExpressionType.PROPERTY, new String[] {"objective in [[display]slot] %displayslot% from [score][board] %string%"});
				Skript.registerExpression(ExprGetObjective.class, String.class, ExpressionType.PROPERTY, new String[] {"objective %string% from [score][board] %string%"});

				//World Manager

				Skript.registerEffect(EffCreateWorld.class, "create [a] new world [name[d]] %string%");
				Skript.registerEffect(EffDeleteWorld.class, "delete world %string%");
				Skript.registerEffect(EffUnloadWorld.class, "unload world %string%");
				Skript.registerEffect(EffLoadWorld.class, "load world %string%");
				Skript.registerEffect(EffCreateWorldFrom.class, "create world named %string% from [folder] %string%");

				Skript.registerExpression(ExprLastCreatedWorld.class, World.class, ExpressionType.PROPERTY, new String[]{"clicked item"});

				//Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);

				//EnumClassInfo.create(ScoreboardTypes.class, "ScoreboardTypes").register();
				//EnumClassInfo.create(ScoreboardDisplaySlots.class, "displayslots").register();
				//EnumClassInfo.create(DisplaySlot.class, "displayslot").register();

				Skript.registerExpression(ExprClickedItem.class, ItemStack.class, ExpressionType.PROPERTY, new String[]{"clicked item"});
				Skript.registerExpression(ExprCursorItem.class, ItemStack.class, ExpressionType.PROPERTY, new String[]{"cursor item"});
				Skript.registerExpression(ExprClickedSlot.class, Integer.class, ExpressionType.PROPERTY, new String[]{"clicked slot"});
				Skript.registerExpression(ExprClickType.class, String.class, ExpressionType.PROPERTY, new String[]{"click type"});
				Skript.registerExpression(ExprClickedItemName.class, String.class, ExpressionType.PROPERTY, new String[]{"clicked item name"});
				Skript.registerExpression(ExprClickedItemLore.class, String.class, ExpressionType.PROPERTY, new String[]{"clicked item lore"});

				//Bukkit Server Properties
				Skript.registerExpression(ExprMaxPlayers.class, Integer.class, ExpressionType.SIMPLE, new String[]{"max players"});
				
				//Misc1
				Skript.registerExpression(ExprSpawnReason.class, String.class, ExpressionType.PROPERTY, new String[]{"spawn reason (of|for) %entity%"});
				Skript.registerEffect(EffCustomName.class, "set custom name of %entities% to %name%");
				Skript.registerEffect(EffUpdateInventory.class, "update inventory of %player%");
				Skript.registerEffect(EffResetRecipes.class, "reset all server recipes");
				
			 /* 1.8 Things */

			 	getLogger().info("When Funnygatt and BaeFell work together, amazing things happen! \nGO! SUPER GATTFELL REGISTER SEQUENCE!\nAchievement Get! Used the new Umbaska Version");
			 	Skript.registerEffect(EffOpenHopper.class, "open hopper named %string% to %player%");
			 	Skript.registerEffect(EffOpenDispenser.class, "open dispenser named %string% to %player%");
			 /* 1.8 Things */


			 if (Bukkit.getVersion().contains("1.8")){
				 getLogger().info("It appears you might be using a 1.8 Build! I'm going to attempt to register some things related to it :)");
				 SimplePropertyExpression.register(ExprsArms.class, Boolean.class, "[show] arms", "entity");
				 SimplePropertyExpression.register(ExprsBasePlate.class, Boolean.class, "[show] base plate", "entity");
				 SimplePropertyExpression.register(ExprsGravity.class, Boolean.class, "[has] gravity", "entity");
				 SimplePropertyExpression.register(ExprsSmall.class, Boolean.class, "[is] small", "entity");
				 SimplePropertyExpression.register(ExprsVisible.class, Boolean.class, "[is] visible", "entity");
			 }
		 }
	 }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onSpawn(CreatureSpawnEvent e){
        e.getEntity().setMetadata("spawnreason", new FixedMetadataValue(this, e.getSpawnReason()));
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