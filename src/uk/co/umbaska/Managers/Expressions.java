package uk.co.umbaska.Managers;

import java.util.UUID;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
import uk.co.umbaska.NametagEdit.*;
import uk.co.umbaska.PlaceHolderAPI.EffParse;
import uk.co.umbaska.PlotMe.*;
import uk.co.umbaska.ProtocolLib.ExprCanSee;
import uk.co.umbaska.Spawner.*;
import uk.co.umbaska.Towny.*;
import uk.co.umbaska.UUID.*;
import uk.co.umbaska.WildSkript.system.*;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class Expressions {

    public static Boolean use_bungee = Main.getInstance().getConfig().getBoolean("use_bungee");
    public static Messenger messenger;

    public static void runRegister(){

        // PLOTME

        Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("PlotMe");
        if (pl != null) {
            Skript.registerExpression(ExprPlotAtPlayer.class, String.class, ExpressionType.PROPERTY, new String[] {"plot at %player%"});
            Skript.registerExpression(ExprPlotAtLoc.class, String.class, ExpressionType.PROPERTY, new String[] {"plot at location %location%"});
            Skript.registerExpression(ExprGetOwner.class, String.class, ExpressionType.PROPERTY, new String[] {"get owner of %string%"});
            Skript.registerExpression(ExprGetPlayerPlots.class, String.class, ExpressionType.PROPERTY, new String[] {"plots of %player%"});
            Skript.registerExpression(ExprTopCorner.class, Location.class, ExpressionType.PROPERTY, new String[] {"(top|upper) corner of %string% in %world%"});
            Skript.registerExpression(ExprBottomCorner.class, Location.class, ExpressionType.PROPERTY, new String[] {"(bottom|lower) corner of %string% in %world%"});
        }

        // SPAWNER

        Skript.registerExpression(ExprDelayTime.class, Integer.class, ExpressionType.PROPERTY, new String[] {"delay time of %location%"});
        Skript.registerExpression(ExprSpawnedType.class, String.class, ExpressionType.PROPERTY, new String[] {"entity type of %location%"});
        Skript.registerExpression(ExprItemName.class, String.class, ExpressionType.SIMPLE, "item name");

        pl = Bukkit.getServer().getPluginManager().getPlugin("Towny");
        if (pl != null) {

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

        }

        // UUID

        Skript.registerExpression(ExprNamesOfPlayer.class, String.class, ExpressionType.COMBINED, "names of %string%");
        Skript.registerExpression(ExprUUIDOfEntity.class, String.class, ExpressionType.SIMPLE, "uuid of %entity%");
		Skript.registerExpression(EffCentredText.class, String.class, ExpressionType.SIMPLE, "cent(er|re)d %string%");
		Skript.registerExpression(EffCentredTextSize.class, String.class, ExpressionType.SIMPLE, "cent(er|re)d %string% [with] [max] [length] [of] %-integer%");
        if (use_bungee == true) {
            messenger = new Messenger(Main.getInstance());
            Skript.registerExpression(ExprBungeeUUID.class, UUID.class, ExpressionType.PROPERTY, new String[] {"bungee uuid of %player%"});
        }



        // HOLOGRAMS (WIP)
	        
			 /*
			  *  Holograms - Effects
			  

	        Skript.registerEffect(EffCreateHologram.class, new String[] { "create [new] hologram at %location% with %string% in %string% [for %long%]" });
	        Skript.registerEffect(EffAddLineBelow.class, new String[] { "add %string% below hologram at %location% in %string%" });
	        Skript.registerEffect(EffAddLineAbove.class, new String[] { "add %string% above hologram at %location% in %string%" });
	        Skript.registerEffect(EffDeleteHologram.class, new String[] { "delete hologram at %location% in %string%" });
	        Skript.registerEffect(EffMoveHologram.class, new String[] { "move hologram from %location% to %location% in %string%" });
	        Skript.registerEffect(EffSetText.class, new String[] { "set text of hologram at %location% to %string% in %string%" });
	        Skript.registerEffect(EffSetTextAbove.class, new String[] { "set text of line above hologram at %location% to %string% in %string%" });
	        Skript.registerEffect(EffSetTextBelow.class, new String[] { "set text of line below hologram at %location% to %string% in %string%" });
	        Bukkit.getLogger().info("Information Gathered: Funnygatt really hates this hologram api -.-");

			 /*
			  *  Holograms - Expressions
			  

	        Skript.registerExpression(ExprGetLineAbove.class, String.class, ExpressionType.SIMPLE, "text of line above hologram at %location% in %string%");
	        Skript.registerExpression(ExprGetLineAbove.class, String.class, ExpressionType.SIMPLE, "text of line below hologram at %location% in %string%");
	        Skript.registerExpression(ExprGetLineAbove.class, String.class, ExpressionType.SIMPLE, "text of hologram at %location% in %string%");
			  */

        // UMBASKAAPI

        pl = Bukkit.getServer().getPluginManager().getPlugin("UmbaskaAPI");
        if (pl != null) {

            Skript.registerExpression(ExprFactionOfPlayer.class, String.class, ExpressionType.PROPERTY, new String[] {"faction of %player%"});

        }

        // MISC

        Skript.registerExpression(ExprArmourPoints.class, Double.class, ExpressionType.PROPERTY, new String[] {"(armour|armor) points of %player%"});
        Skript.registerExpression(ExprItemCountInSlot.class, ItemStack.class, ExpressionType.PROPERTY, new String[] {"items in %number% of %player%"});
        Skript.registerExpression(ExprGetJSONString.class, String.class, ExpressionType.SIMPLE, "json string %string% from %string%");
        Skript.registerExpression(ExprGetDigits.class, String.class, ExpressionType.SIMPLE, "get (digits|numbers|nums|num) of %string%");
        Skript.registerExpression(ExprYAMLString.class, String.class, ExpressionType.SIMPLE, "get string %string% from %string%");
        Skript.registerExpression(ExprYAMLInteger.class, Integer.class, ExpressionType.SIMPLE, "get integer %string% from %string%");
        Skript.registerExpression(ExprYAMLBoolean.class, Boolean.class, ExpressionType.SIMPLE, "get boolean %string% from %string%");



        pl = Bukkit.getServer().getPluginManager().getPlugin("NametagEdit");
        if (pl != null) {

            Skript.registerExpression(ExprGetPrefix.class, String.class, ExpressionType.PROPERTY, new String[] {"prefix of %player%"});
            Skript.registerExpression(ExprGetSuffix.class, String.class, ExpressionType.PROPERTY, new String[] {"suffix of %player%"});
            Skript.registerExpression(ExprGetNametag.class, String.class, ExpressionType.PROPERTY, new String[] {"name tag of %player%"});

        }



        // CLIPS PLACEHOLDERAPI

        pl = Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI");
        if (pl != null) {
            Skript.registerExpression(EffParse.class, String.class, ExpressionType.PROPERTY, new String[] {"placeholder parse %string% as %player%"});
        }

        // DYNMAP

        pl = Bukkit.getServer().getPluginManager().getPlugin("Dynmap");
        if (pl != null) {
            Skript.registerExpression(ExprVisOfPlayer.class, Boolean.class, ExpressionType.PROPERTY, new String[] {"dynmap visibility of %player%"});
        }

        // WILDSKRIPT EXPRESSIONS

        Skript.registerExpression(ExprFreeMemory.class, Integer.class, ExpressionType.PROPERTY, new String[] {"free memory"});
        Skript.registerExpression(ExprJavaVersion.class, String.class, ExpressionType.PROPERTY, new String[] {"java version"});
        Skript.registerExpression(ExprMaxMemory.class, Integer.class, ExpressionType.PROPERTY, new String[] {"max memory"});
        Skript.registerExpression(ExprTotalMemory.class, Integer.class, ExpressionType.PROPERTY, new String[] {"total memory"});
        Skript.registerExpression(ExprTPS.class, Double.class, ExpressionType.PROPERTY, new String[] {"tps"});
        Skript.registerExpression(ExprPing.class, Integer.class, ExpressionType.PROPERTY, new String[] {"%player% ping"});

        // GATTSK STUFF

        // ProtocolLib

        pl = Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib");
        if (pl != null) {
            Skript.registerExpression(ExprCanSee.class, Boolean.class, ExpressionType.PROPERTY, new String[] {"visibility of %entities% for %player%"});
        }

        //General



        //Skript.registerExpression(ExprLastCreatedWorld.class, World.class, ExpressionType.SIMPLE, new String[]{"clicked item"});

        //Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);

        //EnumClassInfo.create(ScoreboardTypes.class, "ScoreboardTypes").register();
        //EnumClassInfo.create(ScoreboardDisplaySlots.class, "displayslots").register();
        //EnumClassInfo.create(DisplaySlot.class, "displayslot").register();

        Skript.registerExpression(ExprClickedItem.class, ItemStack.class, ExpressionType.SIMPLE, new String[]{"clicked item"});
        Skript.registerExpression(ExprCursorItem.class, ItemStack.class, ExpressionType.SIMPLE, new String[]{"cursor item"});
        Skript.registerExpression(ExprClickedSlot.class, Integer.class, ExpressionType.SIMPLE, new String[]{"clicked slot"});
        Skript.registerExpression(ExprClickType.class, String.class, ExpressionType.SIMPLE, new String[]{"click type"});
        Skript.registerExpression(ExprClickedItemName.class, String.class, ExpressionType.SIMPLE, new String[]{"clicked item name"});
        Skript.registerExpression(ExprClickedItemLore.class, String.class, ExpressionType.SIMPLE, new String[]{"clicked item lore"});

        //Bukkit Server Properties
        Skript.registerExpression(ExprMaxPlayers.class, Integer.class, ExpressionType.SIMPLE, new String[]{"max players"});

        //Misc1
        Skript.registerExpression(ExprSpawnReason.class, String.class, ExpressionType.SIMPLE, new String[]{"spawn reason (of|for) %entity%"});

        Skript.registerExpression(ExprGetScore.class, Integer.class, ExpressionType.PROPERTY, new String[] {"value [of] %string% objective %string% for [score] %string%"});
        //Skript.registerExpression(ExprGetPlayerScoreboard.class, Scoreboard.class, ExpressionType.PROPERTY, new String[] {"scoreboard of %player%"});
        Skript.registerExpression(ExprGetObjectiveType.class, String.class, ExpressionType.PROPERTY, new String[] {"objective type of %string% (from|in) [score][board] %scoreboard%"});
        Skript.registerExpression(ExprGetObjectiveDisplay.class, Objective.class, ExpressionType.PROPERTY, new String[] {"objective in [[display]slot] %displayslot% from [score][board] %string%"});
        Skript.registerExpression(ExprGetObjective.class, String.class, ExpressionType.PROPERTY, new String[] {"objective %string% from [score][board] %string%"});
				 /* 1.8 Things */

        Main.getInstance().getLogger().info("When Funnygatt and BaeFell work together, amazing things happen! \nGO! SUPER GATTFELL REGISTER SEQUENCE!\nAchievement Get! Used the new Umbaska Version");
        if (Bukkit.getVersion().contains("1.8")) {
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
        } else {
        	Bukkit.getLogger().warning("[Umbaska] Failed to load 1.8 syntax");
        }


	             /* Books! */
        Skript.registerExpression(ExprBookTitle.class, String.class, ExpressionType.SIMPLE, new String[]{"[book] title of %itemstack%"});
        Skript.registerExpression(ExprBook.class, ItemStack.class, ExpressionType.PROPERTY, new String[]{"book with title %string%"});
        Skript.registerExpression(ExprPages.class, ItemStack.class, ExpressionType.PROPERTY, new String[]{"pages of [book] %itemstack% [to %-string%]"});
        Skript.registerExpression(ExprAuthor.class, ItemStack.class, ExpressionType.PROPERTY, new String[]{"author of [book] %itemstack% [to %-string%]"});
        Skript.registerExpression(ExprAllinOne.class, ItemStack.class, ExpressionType.PROPERTY, new String[]{"book (with|from|by) author %string% [with] title %string% [and] pages %strings%"});

        // 1.8



    }
}
	

