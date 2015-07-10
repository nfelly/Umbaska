package uk.co.umbaska.Managers;

//import uk.co.umbaska.Enums.InventoryTypes;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.bukkit.Bukkit;
import uk.co.umbaska.Enums.*;
import uk.co.umbaska.Main;
import uk.co.umbaska.Utils.EnumClassInfo;
import uk.co.umbaska.Utils.JSONMessage;

/**
 * Created by Zachary on 5/25/2015.
 */
public class Enums {

    private static void registerEnum()

    public static void runRegister(){

        EnumClassInfo.create(InventoryTypes.class, "umbaskainv").register(); // This isn't a 1.8 only thing.
        if (Bukkit.getVersion().contains("1.8")){
            EnumClassInfo.create(ParticleEnum.class, "particleenum").register();
            EnumClassInfo.create(BukkitEffectEnum.class, "bukkiteffect").register(); // These work with any 1.8 build :)
        }
        if (Bukkit.getVersion().contains("1.8.1") || Bukkit.getVersion().contains("1.8-R0.1") || Effects.forceGen18Features) {
            Main.getInstance().getLogger().info("[Umbaska > SkQuery] Registered Custom Particle Enum. Have some BACON!!!!");
            EnumClassInfo.create(Attributes.class, "entityattribute").register();
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
