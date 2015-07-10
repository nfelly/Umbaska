package uk.co.umbaska.Managers;


import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import uk.co.umbaska.Main;

public class Register {

    private static String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].toUpperCase();
    public static Boolean debugInfo = Main.getInstance().getConfig().getBoolean("debug_info");

    public static String getVersion(){
        return version;
    }

    public static Class getClass(String classname){
        Class cls = null;
        try {
            cls =  Class.forName("uk.co.umbaska." + classname + "_" + version);
        }catch (ClassNotFoundException e){
            if (debugInfo){
                Bukkit.getLogger().info("Umbaska »»» Can't Find Class for " + classname + " for version " + version);
            }
        }
        return cls;
    }

	public static void registerAll() {
		Effects.runRegister();
		Expressions.runRegister();
        Enums.runRegister();
        Skript.registerEvent("Slime Split", SimpleEvent.class, SlimeSplitEvent.class, new String[]{"slime split"});
        Skript.registerEvent("Potion Splash", SimpleEvent.class, PotionSplashEvent.class, new String[]{"potion splash"});
        Skript.registerEvent("Sheep Wool Regrow", SimpleEvent.class, SheepRegrowWoolEvent.class, new String[]{"sheep wool regrow"});
        Skript.registerEvent("Leash Entity", SimpleEvent.class, PlayerLeashEntityEvent.class, new String[]{"[player ]leash"});
        Skript.registerEvent("Unleash Entity", SimpleEvent.class, EntityUnleashEvent.class, new String[]{"unleash"});
        Skript.registerEvent("Brew", SimpleEvent.class, BrewEvent.class, new String[]{"brew"});
        Skript.registerEvent("Enchant Prepare", SimpleEvent.class, PrepareItemEnchantEvent.class, new String[]{"[item ]enchant prepare"});
        Skript.registerEvent("Achievement Award", SimpleEvent.class, PlayerAchievementAwardedEvent.class, new String[]{"achievement[ get]"});
        Skript.registerEvent("Note Play", SimpleEvent.class, NotePlayEvent.class, new String[]{"note play"});
        Skript.registerEvent("Inventory Open", SimpleEvent.class, InventoryOpenEvent.class, new String[]{"inventory open"});
        Skript.registerEvent("Health Regen", SimpleEvent.class, EntityRegainHealthEvent.class, new String[]{"[entity] health reg(ain|en)"});

        Bukkit.getLogger().info("Registering Entity Interact Event");
        Skript.registerEvent("Entity Interact", SimpleEvent.class, PlayerInteractAtEntityEvent.class, new String[]{"([entity] interact|armo[u]r stand (right[ ]click|interact))"});
        Skript.registerEvent("Entity Interact", SimpleEvent.class, PlayerInteractAtEntityEvent.class, new String[]{"([entity] interact|armo[u]r stand (right[ ]click|interact))"});
        Skript.registerEvent("Entity Interact", SimpleEvent.class, PlayerInteractAtEntityEvent.class, new String[]{"([entity] interact|armo[u]r stand (right[ ]click|interact))"});
        Skript.registerEvent("Entity Interact", SimpleEvent.class, PlayerInteractAtEntityEvent.class, new String[]{"([entity] interact|armo[u]r stand (right[ ]click|interact))"});
        Skript.registerEvent("Entity Interact", SimpleEvent.class, PlayerInteractAtEntityEvent.class, new String[]{"([entity] interact|armo[u]r stand (right[ ]click|interact))"});
	}
}
