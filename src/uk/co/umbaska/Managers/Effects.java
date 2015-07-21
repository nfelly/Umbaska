package uk.co.umbaska.Managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.dynmap.DynmapAPI;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import uk.co.umbaska.ArmorStands.EffSpawnArmorStand;
import uk.co.umbaska.GattSk.Effects.InventoryClick.EffSetClickedItem;
import uk.co.umbaska.GattSk.Effects.InventoryClick.EffSetCursorItem;
import uk.co.umbaska.GattSk.Effects.SimpleScoreboards.*;
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
import uk.co.umbaska.Sound.EffPlayTrack;
import uk.co.umbaska.Spawner.*;
import uk.co.umbaska.System.*;
import uk.co.umbaska.Towny.*;
import uk.co.umbaska.WorldEdit.*;
import uk.co.umbaska.hologramBased.*;


public class Effects {

    public static EntityHider enthider;
    public static Boolean enable_tag_features = Main.getInstance().getConfig().getBoolean("enable_tag_features");
    public static Boolean use_bungee = Main.getInstance().getConfig().getBoolean("use_bungee");
    public static Boolean forceGenTitleFeatures = Main.getInstance().getConfig().getBoolean("force-generate-title-features");
    public static Boolean forceGen18Features = Main.getInstance().getConfig().getBoolean("force-generate-18-features");
    public static Plugin dynmap;
    public static DynmapAPI api;
    public static Messenger messenger;
    public static Boolean debugInfo = Main.getInstance().getConfig().getBoolean("debug_info");
    private static String version = Register.getVersion();

    private static void registerNewEffect(String name, String cls, String syntax, Boolean multiversion){
        if (Skript.isAcceptRegistrations()){
            if (multiversion){
                Class newCls = Register.getClass(cls);
                if (newCls == null) {
                    Bukkit.getLogger().info("Umbaska »»» Can't Register Effect for " + name + " due to Can't find Class!");
                    return;
                }
                registerNewEffect(name, newCls, syntax);
            }
            else{
                try {
                    registerNewEffect(name, Class.forName(cls), syntax);
                }catch (ClassNotFoundException e){
                    Bukkit.getLogger().info("Umbaska »»» Can't Register Effect for " + name + " due to Wrong Spigot/Bukkit Version!");
                }
            }
        }
        else{
            Bukkit.getLogger().info("Umbaska »»» Can't Register Effect for " + name + " due to Skript Not Accepting Registrations");
        }
    }

    private static void registerNewEffect(String name, Class cls, String syntax){
        if (Skript.isAcceptRegistrations()){
            registerNewEffect(cls, syntax);
            if (debugInfo) {
                Bukkit.getLogger().info("Umbaska »»» Registered Effect for " + name + " with syntax \n" + syntax);
            }
        }
        else{
            Bukkit.getLogger().info("Umbaska »»» Can't Register Effect for " + name + " due to Skript Not Accepting Registrations");
        }
    }

    private static void registerNewEffect(Class cls, String syntax){
        if (Skript.isAcceptRegistrations()){
            Skript.registerEffect(cls, syntax);
            if (debugInfo) {
                Bukkit.getLogger().info("Umbaska »»» Registered Effect for " + cls.getName() + " with syntax\n " + syntax);
            }
        }
        else{
            Bukkit.getLogger().info("Umbaska »»» Can't Register Effect for " + cls.getName() + " due to Skript Not Accepting Registrations");
        }
    }

    @SuppressWarnings("deprecation")
    public static void runRegister(){

        // PLOTME

        Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("PlotMe");
        if (pl != null) {
            registerNewEffect(EffPlotTeleport.class, "teleport %player% to %string%[ in %world%]");
            registerNewEffect(EffClearPlot.class, "clear plot %string% in %world%");
            registerNewEffect(EffMovePlot.class, "move %string% to %string% in %world%" );
            registerNewEffect(EffDenyPlayer.class, "deny %player% from %string%" );
            registerNewEffect(EffUnDeny.class,"allow %player% to %string%" );
        }

        // NOTEBLOCKAPI (NEEDS FIXING)

        pl = Bukkit.getServer().getPluginManager().getPlugin("NoteBlockAPI");
        if (pl != null) {
            registerNewEffect(EffPlayTrack.class, "play (track|song|midi) %string% to %player%");
        }

        // SPAWNER

        registerNewEffect(EffSetSpawner.class, "set spawner %location% to %string%");
        registerNewEffect(EffSetDelay.class, "set delay of %location% to %integer%");
        registerNewEffect(EffMFG_Drop.class, "drop a spawner at %location% based on %block%");
        registerNewEffect(EffMFG_GiveSpawner.class, "give a spawner to %player% based on %block%");
        registerNewEffect(EffMFG_SetSpawner.class, "set spawner at %location% to its type");

        // TOWNY
        pl = Bukkit.getServer().getPluginManager().getPlugin("Towny");
        if (pl != null) {

            registerNewEffect(EffSetPlotOwner.class, "set owner of plot at %location% to %player%");
            registerNewEffect(EffSetPlotPrice.class, "set price of plot at %location% to %double%");

        }

        // PLACEHOLDERAPI

        pl = Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI");
        if (pl != null) {
            registerNewEffect(EffPlaceholderRegister.class, "register %string% (as|for) %string%");
        }

        // MISC/OTHER

        registerNewEffect(EffDropAll.class, "force drop inventory of %player% at %location%");

        registerNewEffect(EffCreateFile.class, "create [new] file %string%");
        registerNewEffect(EffDeleteFile.class, "(df|delete) [file] %string%");
        registerNewEffect(EffSetLine.class, "set line %integer% in [file] %string% to %string%");
        registerNewEffect(EffWriteToFile.class, "(write|wf) %string% to %string%)");

        registerNewEffect(EffWriteYAML.class, "write %string% with [value] %string% to %string%");
        registerNewEffect(EffCopy.class, "copy file %string% to %string%");
        registerNewEffect(EffCopyDir.class, "copy (d|dir|dire|directory) %string% to %string%");

        // PROTCOLLIB

        pl = Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib");
        if (pl != null) {
            enthider = new EntityHider(Main.getInstance(), EntityHider.Policy.BLACKLIST);

            registerNewEffect(EffHideEntity.class, "protocol hide %entity% from %players%");
            registerNewEffect(EffShowEntity.class, "protocol show %entity% to %players%");
            registerNewEffect(EffToggleVisibility.class, "toggle visibility of %entity% for %players%");
            Skript.registerExpression(ExprCanSee.class, Boolean.class, ExpressionType.PROPERTY, "visibility of %entity% for %player%");

            // Disguises

            registerNewEffect(EffDisguise.class, "disguise %entity% as %string%");
            registerNewEffect(EffDisguiseName.class, "disguise %entity% as %string% with custom name %string%");
            registerNewEffect(EffUndisguise.class, "undisguise %entity%");

        }

        // UMBASKAAPI

        pl = Bukkit.getServer().getPluginManager().getPlugin("UmbaskaAPI");
        if (pl != null) {

            registerNewEffect(EffImgInChat.class,  "show %player% image %string% with %string%, %string%, %string%");
            registerNewEffect(EffImgFromURL.class,  "show %player% image from %string% with %string%, %string%, %string%");

        }

        // NAMETAGEDIT

        pl = Bukkit.getServer().getPluginManager().getPlugin("NametagEdit");

        if (pl != null) {
            if (enable_tag_features == true) {
                registerNewEffect(EffSetPrefix.class,  "set prefix of %player% to %string%");
                registerNewEffect(EffSetSuffix.class,  "set suffix of %player% to %string%");
                registerNewEffect(EffSetNametag.class,  "set name tag of %player% to %string%, %string%, %string%");
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

                registerNewEffect(EffSetVisOfPlayer.class, "set dynmap visibility of %player% to %boolean%" );
            }

        }

        pl = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (pl != null){
            registerNewEffect(EffPasteSchematic.class, "paste schematic %string% at %location%");
            registerNewEffect(EffPasteSchematicNoAir.class, "paste schematic %string% at %location% (ignoring air|with no air)");
            registerNewEffect(EffPlaceSchematic.class, "place schematic %string% at %location%");
            registerNewEffect(EffPlaceSchematicNoAir.class, "place schematic %string% at %location% (ignoring|with no) air");
            registerNewEffect(EffSaveSchematic.class, "save schematic %string% based (off of|on) %player%['s selection]");
        }

        // GATTSK

        registerNewEffect(EffRemoveExplodedBlock.class, "(remove|delete) %block% from [better][ ][new] exploded blocks");
        registerNewEffect(EffClearExplodedBlocks.class, "(remove|delete|clear) all [better] exploded blocks");

        //Scoreboards

        registerNewEffect(EffNewScoreboard.class, "create [a] new scoreboard [named] %string%");
        registerNewEffect(EffSetPlayerScoreboard.class, "set scoreboard of %players% to %string%");
        registerNewEffect(EffSetScore.class, "set value of score %string% (for|in) [score][board] %string% objective %string% to %number%");
        registerNewEffect(EffResetScore.class, "reset [value] [of] score %string% (for|in) [score][board] %string%");
        registerNewEffect(EffNewObjective.class, "create [a] [new] %string% objective for [score][board] %string% (called|named) %string%");
        registerNewEffect(EffSetObjectiveDisplay.class, "set objective display slot for [objective] %string% in [score][board] %string% to %string%");
        registerNewEffect(EffSetObjectiveName.class, "set objective display name for [objective] %string% in [score][board] %string% to %string%");
        registerNewEffect(EffUnregisterObjective.class, "unregister objective %string% in [score][board] %string%");

        registerNewEffect(EffDeleteScoreboard.class, "delete score[ ]board %string%");

        registerNewEffect(EffCreateTeam.class, "create team %string% in [score][board] %string%");
        registerNewEffect(EffTeamPlayer.class, "(remove|add) [player] %offlineplayer% (from|to) team %string% in [score][board] %string%");
        registerNewEffect(EffSetTeamPrefix.class, "set (suffix|prefix) for team %string% in [score][board] %string% to %string%");
        registerNewEffect(EffSetTeamFF.class, "set friendly fire for team %string% in [score][board] %string% to %boolean%");
        registerNewEffect(EffSetTeamSeeInvis.class, "set see friendly invisibles for team %string% in [score][board] %string% to %boolean%");


        registerNewEffect("Open Inventory", "Misc.EffOpenInventory", "open %umbaskainv% [named %-string%] to %player%", true);
        registerNewEffect("Open Inventory", "Misc.EffOpenInventoryClose", "open %umbaskainv% [named %-string%] to %player% (that closes|to close)", true);
        registerNewEffect(EffOpenInventoryRows.class, "open %umbaskainv% [named %-string%] with %integer% rows to %player%");


        //World Manager

        registerNewEffect(EffCreateWorld.class, "create [a] new world [name[d]] %string%");
        registerNewEffect(EffDeleteWorld.class, "delete world %string%");
        registerNewEffect(EffUnloadWorld.class, "unload world %string%");
        registerNewEffect(EffLoadWorld.class, "load world %string%");
        registerNewEffect(EffCreateWorldFrom.class, "create world named %string% from [folder] %string%");

        //Misc1

        registerNewEffect(EffLoadChunk.class, "load chunk at (%location%|%chunk%)");
        registerNewEffect(EffUnloadChunk.class, "unload chunk at (%location%|%chunk%)");
        registerNewEffect(EffGenerateChunk.class, "generate chunk at (%location%|%chunk%)");

        registerNewEffect(EffUpdateInventory.class, "update inventory of %player%");
        registerNewEffect(EffResetRecipes.class, "reset all [server] recipes");

        registerNewEffect(EffNothing_MFG.class, "do nothing");

        // Temporary Hologram System
        registerNewEffect(EffCreateHologram.class, "create [a ]new holo[gram] named %string%");
        registerNewEffect(EffSetHoloLine.class, "set holo[gram] line %integer% of holo[gram] %string% to %string%");

        registerNewEffect(EffAddHoloLine.class, "set lines of holo[gram] %string% to %strings%");
        registerNewEffect(EffDeleteHoloLine.class, "(remove|clear|delete) lines of holo[gram] %string%");
        registerNewEffect(EffMoveHolo.class, "move holo[gram] %string% to %location%");
        registerNewEffect(EffHoloFollow.class, "make holo[gram] %string% follow %entity%");
        registerNewEffect(EffHoloStart.class, "start holo[gram] %string%");
        registerNewEffect(EffHoloStop.class, "stop holo[gram] %string%");
        registerNewEffect(EffDeleteHolo.class, "delete holo[gram] %string%");

        registerNewEffect(EffCreateFollowGram.class, "create [a ]new following holo[gram] (to|that) follow[s] %entity% with [text] %strings%");

        registerNewEffect(EffSetHoloType.class, "set holo[gram] type to (0¦wither skull[s]|1¦armor stand[s])");

        registerNewEffect(EffNewSimpleScoreboard.class, "create [a] new simple scoreboard [named] %string%");
        registerNewEffect(EffSetSlot.class, "set slot %number% of simple [score][board] %string% to %string%");
        registerNewEffect(EffSetTitle.class, "set title of simple [score][board] %string% to %string%");
        registerNewEffect(EffShowBoard.class, "set simple [score][board] of %players% to %string%");
        registerNewEffect(EffClearSlot.class, "clear slot %number% of simple [score][board] %string%");
        registerNewEffect(EffDeleteBoard.class, "delete simple [score][ ][board] %string%");

        registerNewEffect(EffSetCursorItem.class, "set cursor item to %itemstack%");
        registerNewEffect(EffSetClickedItem.class, "set clicked item to %itemstack%");

        registerNewEffect(EffScatter.class, "scatter %entities% around %integer%(,| and) %integer% [in] [world] %world% (with|for) rad[ius] of %integer% [ignoring %-itemstacks%] [with delay of %-integer% [between teleports]]");

        if (!Main.getInstance().getConfig().contains("force-generate-title-features")){
            Main.getInstance().getConfig().set("force-generate-title-features", false);
            forceGenTitleFeatures = false;
        }

        registerNewEffect("Clear Bounding Box", "ArmorStands.Direction.EffClearHitbox", "clear hitbox of %entity%", true);

        registerNewEffect("Set Attribute", "Attributes.EffSetAttribute", "set [entity] attribute %entityattributes% of %entity% to %number%", true);
        registerNewEffect("Title", "Misc.EffSendTitle", "send [a ]title from %string% and %string% to %players% for %number%, %number%, %number%", true);
        registerNewEffect("Action Bar", "Misc.EffActionBar", "send [a ]action bar from %string% to %players%", true);
        registerNewEffect(EffTabList.class, "(send|set) [advanced ](0¦footer|1¦header) to %string% (to|for) %players%");
        Main.getInstance().getLogger().info("It appears you might be using a 1.8 Build! I'm going to attempt to register some things related to it :)");
        registerNewEffect(EffSpawnArmorStand.class, "[umbaska] spawn [an] (armour|armor) stand at %locations%");
        registerNewEffect("Trail Entity", "Misc.EffTrailEntity", "[umbaska] trail %entities% with [%number% of ]%particleenum%[:%number%] [[ with] data %number%] [[(with|and)] secondary data %number%]", true);

        Main.getInstance().getLogger().info("[Umbaska > SkQuery] Attempting to register new Spawn Particle Effect.");
        registerNewEffect("Better Particle", "Replacers.EffParticle", "[(1.8|Umbaska|skquery isnt updated)] (summon|play|create|activate|spawn) %number% [of] %particleenum%[:%number%] [offset (at|by|from) %number%, %number%(,| and) %number%] at %locations% (to|for) %players% [[ with] data %number%] [[(with|and)] secondary data %number%]", true);
        registerNewEffect("Better Particle All", "Replacers.EffParticleAll", "[(1.8|Umbaska|skquery isnt updated)] (summon|play|create|activate|spawn) %number% [of] %particleenum%[:%number%] [offset (at|by|from) %number%, %number%(,| and) %number%] at %locations% [[ with] data %number%] [[(with|and)] secondary data %number%]", true);
        registerNewEffect("Better Effect", "Replacers.EffBukkitEffect", "(summon|play|create|activate|spawn) [bukkit] [effect] %bukkiteffect% at %locations% to %players% [[with] [data] %integer%] [[(with|and)] secondary data %integer%]", true);
        registerNewEffect("Better Effect All", "Replacers.EffBukkitEffectAll", "(summon|play|create|activate|spawn) [bukkit] [effect] %bukkiteffect% at %locations% [[with] [data] %integer%] [[(with|and)] secondary data %integer%]", true);

        if (use_bungee) {
            messenger = new Messenger(Main.getInstance());
            registerNewEffect(EffChangeServer.class, "send %player% to %string%");
        }
    }
}
