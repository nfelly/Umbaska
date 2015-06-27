package uk.co.umbaska.Managers;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.dynmap.DynmapAPI;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import uk.co.umbaska.ArmorStands.EffSpawnArmorStand;
import uk.co.umbaska.GattSk.Effects.SimpleScoreboards.*;
import uk.co.umbaska.Misc.EffSendTitle;
import uk.co.umbaska.Misc.EffOpenInventory;
import uk.co.umbaska.Misc.EffOpenInventoryRows;
import uk.co.umbaska.Main;
import uk.co.umbaska.Bungee.EffChangeServer;
import uk.co.umbaska.Bungee.Messenger;
import uk.co.umbaska.Dynmap.*;
import uk.co.umbaska.GattSk.Effects.*;
import uk.co.umbaska.Misc.*;
import uk.co.umbaska.NametagEdit.*;
import uk.co.umbaska.PlaceHolderAPI.EffPlaceholderRegister;
import uk.co.umbaska.PlotMe.*;
import uk.co.umbaska.ProtocolLib.*;
import uk.co.umbaska.ProtocolLib.Disguises.*;
import uk.co.umbaska.Replacers.EffBukkitEffect;
import uk.co.umbaska.Replacers.EffBukkitEffectAll;
import uk.co.umbaska.Replacers.EffParticle;
import uk.co.umbaska.Replacers.EffParticleAll;
import uk.co.umbaska.Sound.EffPlayTrack;
import uk.co.umbaska.Spawner.*;
import uk.co.umbaska.Towny.*;
import uk.co.umbaska.hologramBased.*;


public class Effects {
	
    public static EntityHider enthider;
    public static Boolean enable_tag_features = Main.getInstance().getConfig().getBoolean("enable_tag_features");
    public static Boolean use_bungee = Main.getInstance().getConfig().getBoolean("use_bungee");
    public static Boolean forceGenTitleFeatures = Main.getInstance().getConfig().getBoolean("force-generate-title-features");
    public static Plugin dynmap;
    public static DynmapAPI api;
    public final Logger logger = Logger.getLogger("Minecraft");
    public static Messenger messenger;
    
	public static void runRegister(){
	
		// PLOTME
		Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("PlotMe");
		if (pl != null) {
            Skript.registerEffect(EffPlotTeleport.class, new String[] { "teleport %player% to %string%[ in %world%]" });
            Skript.registerEffect(EffClearPlot.class, new String[] { "clear plot %string% in %world%" });
            Skript.registerEffect(EffMovePlot.class, new String[] { "move %string% to %string% in %world%" });
            Skript.registerEffect(EffDenyPlayer.class, new String[] { "deny %player% from %string%" });
            Skript.registerEffect(EffUnDeny.class, new String[] { "allow %player% to %string%" });
		}
		
        // NOTEBLOCKAPI (NEEDS FIXING)
        
        pl = Bukkit.getServer().getPluginManager().getPlugin("NoteBlockAPI");
        if (pl != null) {
	        Skript.registerEffect(EffPlayTrack.class, new String[] { "play (track|song|midi) %string% to %player%" });
        }

		// SPAWNER
		
        Skript.registerEffect(EffSetSpawner.class, new String[] { "set spawner %location% to %string%" });
        Skript.registerEffect(EffSetDelay.class, new String[] { "set delay of %location% to %integer%" });
        Skript.registerEffect(EffMFG_Drop.class, new String[] { "drop a spawner at %location% based on %block%" });
        Skript.registerEffect(EffMFG_GiveSpawner.class, new String[] { "give a spawner to %player% based on %block%" });
        Skript.registerEffect(EffMFG_SetSpawner.class, new String[] { "set spawner at %location% to its type" });
		
		// TOWNY
		pl = Bukkit.getServer().getPluginManager().getPlugin("Towny");
		if (pl != null) {
		
            Skript.registerEffect(EffSetPlotOwner.class, new String[] { "set owner of plot at %location% to %player%" });
            Skript.registerEffect(EffSetPlotPrice.class, new String[] { "set price of plot at %location% to %double%" });
			
		}
		
		// PLACEHOLDERAPI
		
		pl = Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI");
		if (pl != null) {
            Skript.registerEffect(EffPlaceholderRegister.class, new String[] { "register %string% (as|for) %string%" });			
		}
		
		// MISC/OTHER
		
        Skript.registerEffect(EffDropAll.class, new String[] { "force drop inventory of %player% at %location%" });

        Skript.registerEffect(EffNewFile.class, new String[] { "create [new] file %string%" });
        Skript.registerEffect(EffDelFile.class, new String[] { "delete file %string%" });
        Skript.registerEffect(EffWriteYAML.class, new String[] { "write %string% with [value] %string% to %string%" });
        Skript.registerEffect(EffCopy.class, new String[] { "copy file %string% to %string%" });
        Skript.registerEffect(EffCopyDir.class, new String[] { "copy (d|dir|dire|directory) %string% to %string%" });
		
		// PROTCOLLIB
		
        pl = Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib");
        if (pl != null) {
        	enthider = new EntityHider(Main.getInstance(), EntityHider.Policy.BLACKLIST);
        	
            Skript.registerEffect(EffHideEntity.class, new String[] {"hide %entity% from %player%"});
            Skript.registerEffect(EffShowEntity.class, new String[] {"show %entity% to %player%"});
            Skript.registerEffect(EffToggleVisibility.class, new String[] {"toggle visibility of %entity% for %player%"});
            Skript.registerExpression(ExprCanSee.class, Boolean.class, ExpressionType.PROPERTY, new String[]{"visibility of %entity% for %player%"});

            // Disguises

            Skript.registerEffect(EffDisguise.class, new String[] {"disguise %entity% as %string%"});
            Skript.registerEffect(EffDisguiseName.class, new String[] {"disguise %entity% as %string% with custom name %string%"});
            Skript.registerEffect(EffUndisguise.class, new String[] {"undisguise %entity%"});
        	
        }
		
        // UMBASKAAPI
        
        pl = Bukkit.getServer().getPluginManager().getPlugin("UmbaskaAPI");
        if (pl != null) {
        	
            Skript.registerEffect(EffImgInChat.class, new String[] { "show %player% image %string% with %string%, %string%, %string%" });
            Skript.registerEffect(EffImgFromURL.class, new String[] { "show %player% image from %string% with %string%, %string%, %string%" });
		
        }
        
        // NAMETAGEDIT
        
        pl = Bukkit.getServer().getPluginManager().getPlugin("NametagEdit");

        if (pl != null) {
            if (enable_tag_features == true) {
                Skript.registerEffect(EffSetPrefix.class, new String[] { "set prefix of %player% to %string%" });
                Skript.registerEffect(EffSetSuffix.class, new String[] { "set suffix of %player% to %string%" });
                Skript.registerEffect(EffSetNametag.class, new String[] { "set name tag of %player% to %string%, %string%, %string%" });   
            }
        }

        
        pl = Bukkit.getServer().getPluginManager().getPlugin("dynmap");
        if (pl != null) {
		 /*
		  *  Dynmap - Other
		  */
            PluginManager pm = Bukkit.getServer().getPluginManager();
            dynmap = pm.getPlugin("dynmap");
            api = (DynmapAPI) dynmap;
            if (api == null) {
                Main.getInstance().getLogger().info(ChatColor.RED + "[Umbaska] Damn son! There was a problem hooking into dynmap. Sorry dude.");
            }
            else {

                Skript.registerEffect(EffSetVisOfPlayer.class, new String[]{"set dynmap visibility of %player% to %boolean%"});
            }

        }
        // GATTSK
        
        Skript.registerEffect(EffRemoveExplodedBlock.class, "(remove|delete) %block% from [better][ ][new] exploded blocks");
        Skript.registerEffect(EffClearExplodedBlocks.class, "(remove|delete|clear) all [better] exploded blocks");

        //Scoreboards

        Skript.registerEffect(EffNewScoreboard.class, "create [a] new scoreboard [named] %string%");
        Skript.registerEffect(EffSetPlayerScoreboard.class, "set scoreboard of %players% to %string%");
        Skript.registerEffect(EffSetScore.class, "set value of score %string% (for|in) [score][board] %string% objective %string% to %number%");
        Skript.registerEffect(EffResetScore.class, "reset [value] [of] score %string% (for|in) [score][board] %string%");
        Skript.registerEffect(EffNewObjective.class, "create [a] [new] %string% objective for [score][board] %string% (called|named) %string%");
        Skript.registerEffect(EffSetObjectiveDisplay.class, "set objective display slot for [objective] %string% in [score][board] %string% to %string%");
        Skript.registerEffect(EffSetObjectiveName.class, "set objective display name for [objective] %string% in [score][board] %string% to %string%");
        Skript.registerEffect(EffUnregisterObjective.class, "unregister objective %string% in [score][board] %string%");

        Skript.registerEffect(EffDeleteScoreboard.class, "delete score[ ]board %string%");

        Skript.registerEffect(EffCreateTeam.class, "create team %string% in [score][board] %string%");
        Skript.registerEffect(EffTeamPlayer.class, "(remove|add) [player] %offlineplayer% (from|to) team %string% in [score][board] %string%");
        Skript.registerEffect(EffSetTeamPrefix.class, "set (suffix|prefix) for team %string% in [score][board] %string% to %string%");
        Skript.registerEffect(EffSetTeamFF.class, "set friendly fire for team %string% in [score][board] %string% to %boolean%");
        Skript.registerEffect(EffSetTeamSeeInvis.class, "set see friendly invisibles for team %string% in [score][board] %string% to %boolean%");


        Skript.registerEffect(EffOpenInventory.class, "open %umbaskainv% [named %-string%] to %player%");
        Skript.registerEffect(EffOpenInventoryRows.class, "open %umbaskainv% [named %-string%] with %integer% rows to %player%");


        //World Manager

        Skript.registerEffect(EffCreateWorld.class, "create [a] new world [name[d]] %string%");
        Skript.registerEffect(EffDeleteWorld.class, "delete world %string%");
        Skript.registerEffect(EffUnloadWorld.class, "unload world %string%");
        Skript.registerEffect(EffLoadWorld.class, "load world %string%");
        Skript.registerEffect(EffCreateWorldFrom.class, "create world named %string% from [folder] %string%");
        
        //Misc1
        Skript.registerEffect(EffUpdateInventory.class, "update inventory of %player%");
        Skript.registerEffect(EffResetRecipes.class, "reset all [server] recipes");

        Skript.registerEffect(EffNothing_MFG.class, "do nothing");

        // Temporary Hologram System
        Skript.registerEffect(EffCreateHologram.class, "create [a ]new holo[gram] named %string%");
        Skript.registerEffect(EffSetHoloLine.class, "set holo[gram] line %integer% of holo[gram] %string% to %string%");

        Skript.registerEffect(EffAddHoloLine.class, "set lines of holo[gram] %string% to %strings%");
        Skript.registerEffect(EffDeleteHoloLine.class, "(remove|clear|delete) lines of holo[gram] %string%");
        Skript.registerEffect(EffMoveHolo.class, "move holo[gram] %string% to %location%");
        Skript.registerEffect(EffHoloFollow.class, "make holo[gram] %string% follow %entity%");
        Skript.registerEffect(EffHoloStart.class, "start holo[gram] %string%");
        Skript.registerEffect(EffHoloStop.class, "stop holo[gram] %string%");
        Skript.registerEffect(EffDeleteHolo.class, "delete holo[gram] %string%");

        Skript.registerEffect(EffCreateFollowGram.class, "create [a ]new following holo[gram] (to|that) follow[s] %entity% with [text] %strings%");

        Skript.registerEffect(EffSetHoloType.class, "set holo[gram] type to (0¦wither skull[s]|1¦armor stand[s])");

        Skript.registerEffect(EffNewSimpleScoreboard.class, "create [a] new simple scoreboard [named] %string%");
        Skript.registerEffect(EffSetSlot.class, "set slot %number% of simple [score][board] %string% to %string%");
        Skript.registerEffect(EffSetTitle.class, "set title of simple [score][board] %string% to %string%");
        Skript.registerEffect(EffShowBoard.class, "set simple [score][board] of %players% to %string%");
        Skript.registerEffect(EffClearSlot.class, "clear slot %number% of simple [score][board] %string%");
        Skript.registerEffect(EffDeleteBoard.class, "delete simple [score][ " +
                "][board] %string%");
        if (!Main.getInstance().getConfig().contains("force-generate-title-features")){
            Main.getInstance().getConfig().set("force-generate-title-features", false);
            forceGenTitleFeatures = false;
        }
        if (Bukkit.getVersion().contains("1.8.1") || Bukkit.getVersion().contains("1.8-R0.1") || forceGenTitleFeatures) {
			Skript.registerEffect(EffSendTitle.class, "send [a ]title from %string% and %string% to %players% for %number%, %number%, %number%");
			Skript.registerEffect(EffActionBar.class, "send [a ]action bar from %string% to %players%");
            Skript.registerEffect(EffTabList.class, "(send|set) [advanced ](0¦footer|1¦header) to %string% (to|for) %players%");
		}
		if (Bukkit.getVersion().contains("1.8.1")) {

        	Main.getInstance().getLogger().info("It appears you might be using a 1.8 Build! I'm going to attempt to register some things related to it :)");
            Skript.registerEffect(EffSpawnArmorStand.class, "[umbaska] spawn [an] (armour|armor) stand at %locations%");
            Skript.registerEffect(EffTrailEntity.class, "[umbaska] trail %entities% with [particle] %particleenum%");

            Main.getInstance().getLogger().info("[Umbaska > SkQuery] Attempting to register new Spawn Particle Effect.");
            Skript.registerEffect(EffParticle.class, "[(1.8|Umbaska|skquery isnt updated)] (summon|play|create|activate|spawn) %integer% [of] [particle] %particleenum%[:%number%] [offset (at|by|from) %number%, %number% (,|and) %number%] at %locations% (to|for) %players% [[ with] data %number%] [[(with|and)] secondary data %number%]");
            Skript.registerEffect(EffParticleAll.class, "[(1.8|Umbaska|skquery isnt updated)] (summon|play|create|activate|spawn) %integer% [of] [particle] %particleenum%[:%number%] [offset (at|by|from) %number%, %number% (,|and) %number%] at %locations% [[ with] data %number%] [[(with|and)] secondary data %number%]");
            Skript.registerEffect(EffBukkitEffect.class, "(summon|play|create|activate|spawn) [bukkit] [effect] %bukkiteffect% at %locations% to %players% [[with] [data] %integer%] [[(with|and)] secondary data %integer%]");
            Skript.registerEffect(EffBukkitEffectAll.class, "(summon|play|create|activate|spawn) [bukkit] [effect] %bukkiteffect% at %locations% [[with] [data] %integer%] [[(with|and)] secondary data %integer%]");
        }
        
        if (use_bungee) {
            messenger = new Messenger(Main.getInstance());
            Skript.registerEffect(EffChangeServer.class, "send %player% to %string%");
        }
	}
}
