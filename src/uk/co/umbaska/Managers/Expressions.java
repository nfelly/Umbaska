package uk.co.umbaska.Managers;

import java.util.Date;
import java.util.UUID;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Objective;

import com.palmergames.bukkit.towny.object.Town;

import uk.co.umbaska.ArmorStands.*;
import uk.co.umbaska.ArmorStands.Direction.*;
import uk.co.umbaska.ArmorStands.Direction.Arms.*;
import uk.co.umbaska.ArmorStands.Direction.Legs.*;
import uk.co.umbaska.Main;
import uk.co.umbaska.Bungee.*;
import uk.co.umbaska.Dynmap.*;
import uk.co.umbaska.Factions.*;
import uk.co.umbaska.GattSk.Expressions.*;
import uk.co.umbaska.Misc.*;
import uk.co.umbaska.Misc.Books.*;
import uk.co.umbaska.Misc.Date.*;
import uk.co.umbaska.NametagEdit.*;
import uk.co.umbaska.PlaceHolderAPI.EffParse;
import uk.co.umbaska.PlotMe.*;
import uk.co.umbaska.ProtocolLib.ExprCanSee;
import uk.co.umbaska.Spawner.*;
import uk.co.umbaska.System.*;
import uk.co.umbaska.Towny.*;
import uk.co.umbaska.UUID.*;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import uk.co.umbaska.WorldEdit.*;

public class Expressions {

    public static Boolean use_bungee = Main.getInstance().getConfig().getBoolean("use_bungee");
    public static Messenger messenger;
    public static Boolean debugInfo = Main.getInstance().getConfig().getBoolean("debug_info");


    private static void registerNewExpression(String name, String cls, Class returnType, ExpressionType expressionType, String syntax, Boolean multiversion){
        if (Skript.isAcceptRegistrations()){
            if (multiversion){
                Class newCls = Register.getClass(cls);
                if (newCls == null) {
                    Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Can't find Class!");
                    return;
                }
                if (debugInfo) {
                    Bukkit.getLogger().info("Umbaska »»» Registered Expression for " + name + " with syntax\n " + syntax + " for Version " + Register.getVersion());
                    return;
                }
                registerNewExpression(name, newCls, returnType, expressionType, syntax);
            }
            else{
                try {
                    registerNewExpression(name, Class.forName(cls), returnType, expressionType, syntax);
                }catch (ClassNotFoundException e){
                    Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Wrong Spigot/Bukkit Version!");
                }
            }
        }
        else{
            Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Skript Not Accepting Registrations");
        }
    }

    private static void registerNewExpression(String name, Class cls, Class returnType, ExpressionType expressionType, String syntax){
        if (Skript.isAcceptRegistrations()){
            registerNewExpression(cls, returnType, expressionType, syntax);
            if (debugInfo) {
                Bukkit.getLogger().info("Umbaska »»» Registered Expression for " + name + " with syntax \n" + syntax);
            }
        }
        else{
            Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Skript Not Accepting Registrations");
        }
    }

    private static void registerNewExpression(Class cls, Class returnType, ExpressionType expressionType, String syntax){
        if (Skript.isAcceptRegistrations()){
            Skript.registerExpression(cls, returnType, expressionType, syntax);
            if (debugInfo) {
                Bukkit.getLogger().info("Umbaska »»» Registered Expression for " + cls.getName() + " with syntax\n " + syntax);
            }
        }
        else{
            Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + cls.getName() + " due to Skript Not Accepting Registrations");
        }
    }
    
    public static void runRegister(){

        // PLOTME

        Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("PlotMe");
        if (pl != null) {
            registerNewExpression(ExprPlotAtPlayer.class, String.class, ExpressionType.PROPERTY, "plot at %player%");
            registerNewExpression(ExprPlotAtLoc.class, String.class, ExpressionType.PROPERTY, "plot at location %location%");
            registerNewExpression(ExprGetOwner.class, String.class, ExpressionType.PROPERTY, "get owner of %string%");
            registerNewExpression(ExprGetPlayerPlots.class, String.class, ExpressionType.PROPERTY, "plots of %player%");
            registerNewExpression(ExprTopCorner.class, Location.class, ExpressionType.PROPERTY, "(top|upper) corner of %string% in %world%");
            registerNewExpression(ExprBottomCorner.class, Location.class, ExpressionType.PROPERTY, "(bottom|lower) corner of %string% in %world%");
        }

        // SPAWNER

        registerNewExpression(ExprDelayTime.class, Integer.class, ExpressionType.PROPERTY, "delay time of %location%");
        registerNewExpression(ExprSpawnedType.class, String.class, ExpressionType.PROPERTY, "entity type of %location%");
        registerNewExpression(ExprItemName.class, String.class, ExpressionType.SIMPLE, "item name");

        pl = Bukkit.getServer().getPluginManager().getPlugin("Towny");
        if (pl != null) {

            registerNewExpression(ExprTownAtPlayer.class, String.class, ExpressionType.PROPERTY, "town at %player%");
            registerNewExpression(ExprTownOfPlayer.class, Town.class, ExpressionType.PROPERTY, "town of %player%");
            registerNewExpression(ExprTDBank.class, Double.class, ExpressionType.PROPERTY, "town balance of %string%");
            registerNewExpression(ExprTDPlayerCount.class, Integer.class, ExpressionType.PROPERTY, "player[ ]count of %string%");
            registerNewExpression(ExprTDPlayers.class, String.class, ExpressionType.PROPERTY, "players of %string%");
            registerNewExpression(ExprTDTaxes.class, Double.class, ExpressionType.PROPERTY, "town taxes of %string%");
            registerNewExpression(ExprPlotOwner.class, String.class, ExpressionType.PROPERTY, "owner of plot at %location%");
            registerNewExpression(ExprPlotPrice.class, Double.class, ExpressionType.PROPERTY, "price of plot at %location%");
            registerNewExpression(ExprRDLastOnline.class, String.class, ExpressionType.PROPERTY, "resident data last online of %player%");
            registerNewExpression(ExprRDLastOnlineDate.class, String.class, ExpressionType.PROPERTY, "resident data last online date of %player%");
            registerNewExpression(ExprRDChatName.class, String.class, ExpressionType.PROPERTY, "resident data chat name of %player%");
            registerNewExpression(ExprRDFriends.class, String.class, ExpressionType.PROPERTY, "resident data friends of %player%");
            registerNewExpression(ExprRDNationRanks.class, String.class, ExpressionType.PROPERTY, "resident data nation ranks of %player%");
            registerNewExpression(ExprRDRegistered.class, Long.class, ExpressionType.PROPERTY, "resident data registered of %player%");
            registerNewExpression(ExprRDSurname.class, String.class, ExpressionType.PROPERTY, "resident data surname of %player%");
            registerNewExpression(ExprRDTitle.class, String.class, ExpressionType.PROPERTY, "resident data title of %player%");

        }

        // UUID

        registerNewExpression(ExprNamesOfPlayer.class, String.class, ExpressionType.COMBINED, "names of %string%");
        registerNewExpression(ExprUUIDOfEntity.class, String.class, ExpressionType.SIMPLE, "uuid of %entity%");
		registerNewExpression(EffCentredText.class, String.class, ExpressionType.SIMPLE, "cent(er|re)d %string%");
		registerNewExpression(EffCentredTextSize.class, String.class, ExpressionType.SIMPLE, "cent(er|re)d %string% [with] [max] [length] [of] %-integer%");
        if (use_bungee == true) {
            messenger = new Messenger(Main.getInstance());
            registerNewExpression(ExprBungeeUUID.class, UUID.class, ExpressionType.PROPERTY, "bungee uuid of %player%");
        }



        // HOLOGRAMS (WIP)
	        
			 /*
			  *  Holograms - Effects
			  

	        Skript.registerEffect(EffCreateHologram.class,  "create [new] hologram at %location% with %string% in %string% [for %long%]");
	        Skript.registerEffect(EffAddLineBelow.class,  "add %string% below hologram at %location% in %string%");
	        Skript.registerEffect(EffAddLineAbove.class,  "add %string% above hologram at %location% in %string%");
	        Skript.registerEffect(EffDeleteHologram.class,  "delete hologram at %location% in %string%");
	        Skript.registerEffect(EffMoveHologram.class,  "move hologram from %location% to %location% in %string%");
	        Skript.registerEffect(EffSetText.class,  "set text of hologram at %location% to %string% in %string%");
	        Skript.registerEffect(EffSetTextAbove.class,  "set text of line above hologram at %location% to %string% in %string%");
	        Skript.registerEffect(EffSetTextBelow.class,  "set text of line below hologram at %location% to %string% in %string%");
	        Bukkit.getLogger().info("Information Gathered: Funnygatt really hates this hologram api -.-");

			 /*
			  *  Holograms - Expressions
			  

	        registerNewExpression(ExprGetLineAbove.class, String.class, ExpressionType.SIMPLE, "text of line above hologram at %location% in %string%");
	        registerNewExpression(ExprGetLineAbove.class, String.class, ExpressionType.SIMPLE, "text of line below hologram at %location% in %string%");
	        registerNewExpression(ExprGetLineAbove.class, String.class, ExpressionType.SIMPLE, "text of hologram at %location% in %string%");
			  */

        // UMBASKAAPI

        pl = Bukkit.getServer().getPluginManager().getPlugin("UmbaskaAPI");
        if (pl != null) {

            registerNewExpression(ExprFactionOfPlayer.class, String.class, ExpressionType.PROPERTY, "faction of %player%");

        }

        // MISC

        registerNewExpression(ExprArmourPoints.class, Double.class, ExpressionType.PROPERTY, "(armour|armor) points of %player%");
        registerNewExpression(ExprItemCountInSlot.class, ItemStack.class, ExpressionType.PROPERTY, "items in %number% of %player%");
        registerNewExpression(ExprGetJSONString.class, String.class, ExpressionType.SIMPLE, "json string %string% from %string%");
        registerNewExpression(ExprGetDigits.class, String.class, ExpressionType.SIMPLE, "get (digits|numbers|nums|num) of %string%");
        registerNewExpression(ExprYAMLString.class, String.class, ExpressionType.SIMPLE, "get string %string% from %string%");
        registerNewExpression(ExprYAMLInteger.class, Integer.class, ExpressionType.SIMPLE, "get integer %string% from %string%");
        registerNewExpression(ExprYAMLBoolean.class, Boolean.class, ExpressionType.SIMPLE, "get boolean %string% from %string%");
        registerNewExpression(ExprNewLocation.class, Location.class, ExpressionType.SIMPLE, "new location %number%, %number%, %number% in world %string%");
        
        registerNewExpression(ExprFileExists.class, Boolean.class, ExpressionType.PROPERTY, "exist(e|a)nce of %string%");
        registerNewExpression(ExprGetFile.class, String.class, ExpressionType.PROPERTY, "file from %string%");
		registerNewExpression(ExprContent.class, String.class, ExpressionType.SIMPLE, "content (from|of) file %string%");
        registerNewExpression(ExprGetLine.class, String.class, ExpressionType.SIMPLE, "line %integer% in file %string%");



        pl = Bukkit.getServer().getPluginManager().getPlugin("NametagEdit");
        if (pl != null) {

            registerNewExpression(ExprGetPrefix.class, String.class, ExpressionType.PROPERTY, "prefix of %player%");
            registerNewExpression(ExprGetSuffix.class, String.class, ExpressionType.PROPERTY, "suffix of %player%");
            registerNewExpression(ExprGetNametag.class, String.class, ExpressionType.PROPERTY, "name tag of %player%");

        }



        // CLIPS PLACEHOLDERAPI

        pl = Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI");
        if (pl != null) {
            registerNewExpression(EffParse.class, String.class, ExpressionType.PROPERTY, "placeholder parse %string% as %player%");
        }

        // DYNMAP

        pl = Bukkit.getServer().getPluginManager().getPlugin("Dynmap");
        if (pl != null) {
            registerNewExpression(ExprVisOfPlayer.class, Boolean.class, ExpressionType.PROPERTY, "dynmap visibility of %player%");
        }

        // WILDSKRIPT EXPRESSIONS

        registerNewExpression(ExprFreeMemory.class, Integer.class, ExpressionType.PROPERTY, "free memory");
        registerNewExpression(ExprJavaVersion.class, String.class, ExpressionType.PROPERTY, "java version");
        registerNewExpression(ExprMaxMemory.class, Integer.class, ExpressionType.PROPERTY, "max memory");
        registerNewExpression(ExprTotalMemory.class, Integer.class, ExpressionType.PROPERTY, "total memory");
        registerNewExpression(ExprTPS.class, Double.class, ExpressionType.PROPERTY, "tps");
        registerNewExpression(ExprPing.class, Integer.class, ExpressionType.PROPERTY, "%player%[[']s] ping");
        registerNewExpression(ExprPing.class, Integer.class, ExpressionType.PROPERTY, "ping of %player%");

        // GATTSK STUFF

        // ProtocolLib

        pl = Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib");
        if (pl != null) {
            registerNewExpression(ExprCanSee.class, Boolean.class, ExpressionType.PROPERTY, "visibility of %entities% for %player%");
        }

        pl = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (pl != null){
            registerNewExpression(ExprAllSchematics.class, String.class, ExpressionType.COMBINED, "all schematics");
        }

        registerNewExpression(ExprDyed.class, ItemStack.class, ExpressionType.SIMPLE, "%itemstack% (colo[u]red|dyed) %color%");
        registerNewExpression(ExprDyedRGB.class, ItemStack.class, ExpressionType.SIMPLE, "%itemstack% (colo[u]red|dyed) %number%, %number%(,| and) %number%");

        registerNewExpression(ExprClickedItem.class, ItemStack.class, ExpressionType.SIMPLE, "clicked item");
        registerNewExpression(ExprCursorItem.class, ItemStack.class, ExpressionType.SIMPLE, "cursor item");
        registerNewExpression(ExprClickedSlot.class, Integer.class, ExpressionType.SIMPLE, "clicked slot");
        registerNewExpression(ExprClickType.class, String.class, ExpressionType.SIMPLE, "click type");
        registerNewExpression(ExprClickedItemName.class, String.class, ExpressionType.SIMPLE, "clicked item name");
        registerNewExpression(ExprClickedItemLore.class, String.class, ExpressionType.SIMPLE, "clicked item lore");

        //Bukkit Server Properties
        registerNewExpression(ExprMaxPlayers.class, Integer.class, ExpressionType.SIMPLE, "max players");

        //Misc1
        registerNewExpression(ExprSpawnReason.class, String.class, ExpressionType.SIMPLE, "spawn reason (of|for) %entity%");

        registerNewExpression(ExprGetScore.class, Integer.class, ExpressionType.PROPERTY, "value [of] %string% objective %string% for [score] %string%");
        //registerNewExpression(ExprGetPlayerScoreboard.class, Scoreboard.class, ExpressionType.PROPERTY, "scoreboard of %player%");
        registerNewExpression(ExprGetObjectiveType.class, String.class, ExpressionType.PROPERTY, "objective type of %string% (from|in) [score][board] %scoreboard%");
        registerNewExpression(ExprGetObjectiveDisplay.class, Objective.class, ExpressionType.PROPERTY, "objective in [[display]slot] %displayslot% from [score][board] %string%");
        registerNewExpression(ExprGetObjective.class, String.class, ExpressionType.PROPERTY, "objective %string% from [score][board] %string%");
				 /* 1.8 Things */
        //registerNewExpression(ExprBetterExplodedBlocks.class, Block.class, ExpressionType.COMBINED, "[better] exploded blocks");
        registerNewExpression(ExprDirectionLocation.class, Location.class, ExpressionType.COMBINED, "[the] (location|position) %number% (block|meter)[s] in [the] direction %direction% of %location%");
        registerNewExpression(ExprDirectionLocation.class, Location.class, ExpressionType.COMBINED, "(location|position) [of] direction %direction% (*|times|multiplied by length) %number% (from|with) [origin] %location%");
        Main.getInstance().getLogger().info("When Funnygatt and BaeFell work together, amazing things happen! \nGO! SUPER GATTFELL REGISTER SEQUENCE!\nAchievement Get! Used the new Umbaska Version");
        SimplePropertyExpression.register(ExprFreeze.class, Boolean.class, "freeze state", "player");
        SimplePropertyExpression.register(ExprCanMoveEntities.class, Boolean.class, "[can] collide [with entities]", "player");

        registerNewExpression(ExprEntityFromVariable.class, Entity.class, ExpressionType.SIMPLE, "entity from [variable] %entity%");

		// Date

		registerNewExpression(ExprGetDate.class, Date.class, ExpressionType.SIMPLE, "date from %string% using format %string%");
		registerNewExpression(ExprGetDateWithLocale.class, Date.class, ExpressionType.SIMPLE, "date from %string% using format %string% and locale %locale%");

		registerNewExpression(ExprGetDay.class, DayOfWeek.class, ExpressionType.SIMPLE, "day[ of week] from %date%");
		registerNewExpression(ExprGetHour.class, Integer.class, ExpressionType.SIMPLE, "hour from %date%");
		registerNewExpression(ExprGetMinute.class, Integer.class, ExpressionType.SIMPLE, "minute from %date%");
		registerNewExpression(ExprGetSecond.class, Integer.class, ExpressionType.SIMPLE, "second from %date%");
		registerNewExpression(ExprGetMillisecond.class, Integer.class, ExpressionType.SIMPLE, "millisecond from %date%");
		registerNewExpression(ExprGetYear.class, Integer.class, ExpressionType.SIMPLE, "year from %date%");

        registerNewExpression(ExprFallDistance.class, Number.class, ExpressionType.SIMPLE, "fall distance of %entity%");

        SimplePropertyExpression.register(ExprForceFly.class, Boolean.class, "(is flying|force fly)", "player");


        registerNewExpression(ExprUnbreakable.class, ItemStack.class, ExpressionType.PROPERTY, "[a[n]] unbreakable %itemstacks%");
        if (Bukkit.getVersion().contains("1.8")) { // Doesn't require specific 1.8 version.
            Bukkit.getLogger().info("[Umbaska] Registering Armor Stand related expressions");
            SimplePropertyExpression.register(ExprsArms.class, Boolean.class, "[show] arms", "entity");
            SimplePropertyExpression.register(ExprsBasePlate.class, Boolean.class, "[show] base plate", "entity");
            SimplePropertyExpression.register(ExprsGravity.class, Boolean.class, "[has] gravity", "entity");
            SimplePropertyExpression.register(ExprsSmall.class, Boolean.class, "[is] small", "entity");
            SimplePropertyExpression.register(ExprsVisible.class, Boolean.class, "[is] visible", "entity");
            SimplePropertyExpression.register(ExprsRightLegDirectionX.class, Number.class, "right leg (x angle|angle x)", "entity");
            SimplePropertyExpression.register(ExprsRightLegDirectionY.class, Number.class, "right leg (y angle|angle y)", "entity");
            SimplePropertyExpression.register(ExprsRightLegDirectionZ.class, Number.class, "right leg (z angle|angle z)", "entity");

            SimplePropertyExpression.register(ExprsLeftLegDirectionX.class, Number.class, "left leg (x angle|angle x)", "entity");
            SimplePropertyExpression.register(ExprsLeftLegDirectionY.class, Number.class, "left leg (y angle|angle y)", "entity");
            SimplePropertyExpression.register(ExprsLeftLegDirectionZ.class, Number.class, "left leg (z angle|angle z)", "entity");

            SimplePropertyExpression.register(ExprsRightArmDirectionX.class, Number.class, "right arm (x angle|angle x)", "entity");
            SimplePropertyExpression.register(ExprsRightArmDirectionY.class, Number.class, "right arm (y angle|angle y)", "entity");
            SimplePropertyExpression.register(ExprsRightArmDirectionZ.class, Number.class, "right arm (z angle|angle z)", "entity");

            SimplePropertyExpression.register(ExprsLeftArmDirectionX.class, Number.class, "left arm (x angle|angle x)", "entity");
            SimplePropertyExpression.register(ExprsLeftArmDirectionY.class, Number.class, "left arm (y angle|angle y)", "entity");
            SimplePropertyExpression.register(ExprsLeftArmDirectionZ.class, Number.class, "left arm (z angle|angle z)", "entity");

            SimplePropertyExpression.register(ExprsHeadDirectionX.class, Number.class, "head (x angle|angle x)", "entity");
            SimplePropertyExpression.register(ExprsHeadDirectionY.class, Number.class, "head (y angle|angle y)", "entity");
            SimplePropertyExpression.register(ExprsHeadDirectionZ.class, Number.class, "head (z angle|angle z)", "entity");

            SimplePropertyExpression.register(ExprsBodyDirectionX.class, Number.class, "body (x angle|angle x)", "entity");
            SimplePropertyExpression.register(ExprsBodyDirectionY.class, Number.class, "body (y angle|angle y)", "entity");
            SimplePropertyExpression.register(ExprsBodyDirectionZ.class, Number.class, "body (z angle|angle z)", "entity");
        }
        registerNewExpression("Glow", "Misc.ExprBetterGlow", ItemStack.class, ExpressionType.SIMPLE, "[a[n]] [umbaska] glow[ing] %itemstack%", true);
	             /* Books! */
        registerNewExpression(ExprBookTitle.class, String.class, ExpressionType.SIMPLE, "[book] title of %itemstack%");
        registerNewExpression(ExprBook.class, ItemStack.class, ExpressionType.PROPERTY, "book with title %string%");
        registerNewExpression(ExprPages.class, ItemStack.class, ExpressionType.PROPERTY, "pages of [book] %itemstack% [to %-string%]");
        registerNewExpression(ExprAuthor.class, ItemStack.class, ExpressionType.PROPERTY, "author of [book] %itemstack% [to %-string%]");
        registerNewExpression(ExprAllinOne.class, ItemStack.class, ExpressionType.PROPERTY, "book (with|from|by) author %string% [with] title %string% [and] pages %strings%");

        // 1.8
    }
}
	

