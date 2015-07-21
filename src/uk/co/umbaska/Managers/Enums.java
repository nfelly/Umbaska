package uk.co.umbaska.Managers;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.bukkit.Bukkit;
import uk.co.umbaska.Enums.*;
import uk.co.umbaska.Main;
import uk.co.umbaska.Misc.Date.DayOfWeek;
import uk.co.umbaska.Utils.EnumClassInfo;
import uk.co.umbaska.Utils.JSONMessage;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Zachary on 5/25/2015.
 */
public class Enums {
    public static Boolean debugInfo = Main.getInstance().getConfig().getBoolean("debug_info");
    private static String version = Register.getVersion();

    private static void registerEnum(String cls, String name, Boolean multiversion) {
        if (Skript.isAcceptRegistrations()) {
            if (multiversion) {
                Class newCls = Register.getClass(cls);
                if (newCls == null) {
                    Bukkit.getLogger().info("Umbaska »»» Can't Register Enum for " + name + " due to \nWrong Spigot/Bukkit Version!");
                }
                if (debugInfo) {
                    Bukkit.getLogger().info("Umbaska »»» Registered Enum for " + name + " for Version " + version);
                }
                registerEnum(newCls, name);
            } else {
                try {
                    registerEnum(Class.forName(cls), name);
                } catch (ClassNotFoundException e) {
                    Bukkit.getLogger().info("Umbaska »»» Can't Register Enum for " + name + " due to \nWrong Spigot/Bukkit Version!");
                }
            }
        }else{
            Bukkit.getLogger().info("Umbaska »»» Can't Register Enum for " + name + " due to \nSkript Not Accepting Registrations");
        }
    }
    private static void registerEnum(Class cls, String name) {
        if (Skript.isAcceptRegistrations()) {
            EnumClassInfo.create(cls, name).register();
        }
        else{
            Bukkit.getLogger().info("Umbaska »»» Can't Register Enum for " + name + " due to \nSkript Not Accepting Registrations");
        }
    }

    public static void runRegister(){
        registerEnum(InventoryTypes.class, "umbaskainv");
        if (Bukkit.getVersion().contains("1.8")){
            registerEnum(ParticleEnum.class, "particleenum");
            registerEnum(BukkitEffectEnum.class, "bukkiteffect");
        }
        registerEnum("Enums.Attributes", "entityattribute", true);
		registerEnum(Locale.class, "locale");
		registerEnum(DayOfWeek.class, "dayofweek");
        if (Bukkit.getVersion().contains("1.8.1") || Bukkit.getVersion().contains("1.8-R0.1") || Effects.forceGen18Features) {
            Main.getInstance().getLogger().info("[Umbaska > SkQuery] Registered Custom Particle Enum. Have some BACON!!!!");
            Classes.registerClass(new ClassInfo<JSONMessage>(JSONMessage.class, "18jsonmessage").parser(new Parser<JSONMessage>() {
                public JSONMessage parse(String s, ParseContext parseContext) {
                    return null;
                }

                public boolean canParse(ParseContext context) {
                    return false;
                }

                public String toString(JSONMessage jsonMessage, int i) {
                    return jsonMessage.toOldMessageFormat();
                }

                public String toVariableNameString(JSONMessage jsonMessage) {
                    return jsonMessage.toOldMessageFormat();
                }

                public String getVariableNamePattern() {
                    return ".+";
                }
            }));
        }
    }
}
