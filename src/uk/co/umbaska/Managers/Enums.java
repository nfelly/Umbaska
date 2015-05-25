package uk.co.umbaska.Managers;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import uk.co.umbaska.Enums.InventoryTypes;
import uk.co.umbaska.Enums.ParticleEnum;
import uk.co.umbaska.Main;
import uk.co.umbaska.Utils.EnumClassInfo;
import uk.co.umbaska.Utils.JSONMessage;

/**
 * Created by Zachary on 5/25/2015.
 */
public class Enums {

    public static void runRegister(){
        EnumClassInfo.create(ParticleEnum.class, "particleenum").register();
        Main.getInstance().getLogger().info("[Umbaska > SkQuery] Registered Custom Particle Enum. Have some BACON!!!!");
        EnumClassInfo.create(InventoryTypes.class, "inventorytypes").register();
        Classes.registerClass(new ClassInfo<JSONMessage>(JSONMessage.class, "18jsonmessage").parser(new Parser<JSONMessage>()
        {
            public JSONMessage parse(String s, ParseContext parseContext)
            {
                return null;
            }

            public boolean canParse(ParseContext context)
            {
                return false;
            }

            public String toString(JSONMessage jsonMessage, int i)
            {
                return jsonMessage.toOldMessageFormat();
            }

            public String toVariableNameString(JSONMessage jsonMessage)
            {
                return jsonMessage.toOldMessageFormat();
            }

            public String getVariableNamePattern()
            {
                return ".+";
            }
        }));
    }
}
