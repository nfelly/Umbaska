package uk.co.umbaska.Enums;

import org.bukkit.Effect;

/**
 * Created by Zachary on 5/17/2015.
 */
public enum BukkitEffectEnum {
    STEP_SOUND("step sound", Effect.STEP_SOUND), BLAZE_SHOOT("blaze shoot", Effect.BLAZE_SHOOT), RECORD_PLAY("record play", Effect.RECORD_PLAY);

    private final String id;
    private final Effect effect;

    private BukkitEffectEnum(String id, Effect effect)
    {
        this.id = id;
        this.effect = effect;
    }

    public Effect getEffect(){
        return this.effect;
    }

    public String getId()
    {
        return this.id;
    }
}
