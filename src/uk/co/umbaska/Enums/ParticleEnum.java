package uk.co.umbaska.Enums;

import org.bukkit.Effect;

/**
 * Created by Zachary on 5/17/2015.
 */
public enum ParticleEnum {
    HUGE_EXPLOSION("hugeexplosion", Effect.EXPLOSION_HUGE), LARGE_EXPLODE("largeexplode", Effect.EXPLOSION_LARGE),  FIREWORKS_SPARK("fireworksSpark", Effect.FIREWORKS_SPARK),  SUSPENDED("suspended", Effect.SMALL_SMOKE),  DEPTH_SUSPEND("depthsuspend", Effect.VOID_FOG),  CRIT("crit", Effect.CRIT),  MAGIC_CRIT("magicCrit", Effect.MAGIC_CRIT),  SMOKE("smoke", Effect.PARTICLE_SMOKE),  MOB_SPELL("mobSpell", Effect.POTION_SWIRL),  MOB_SPELL_AMBIENT("mobSpellAmbient", Effect.POTION_SWIRL_TRANSPARENT),  SPELL("spell", Effect.SPELL),  INSTANT_SPELL("instantSpell", Effect.INSTANT_SPELL),  WITCH_MAGIC("witchMagic", Effect.WITCH_MAGIC),  NOTE("note", Effect.NOTE),  PORTAL("portal", Effect.PORTAL),  ENCHANTMENT_TABLE("enchantmenttable", Effect.FLYING_GLYPH),  EXPLODE("explode", Effect.EXPLOSION),  FLAME("flame", Effect.FLAME),  LAVA("lava", Effect.LAVA_POP),  FOOTSTEP("footstep", Effect.FOOTSTEP),  SPLASH("splash", Effect.SPLASH),  LARGE_SMOKE("largesmoke", Effect.LARGE_SMOKE),  CLOUD("cloud", Effect.CLOUD),  RED_DUST("reddust", Effect.COLOURED_DUST),  SNOWBALL_POOF("snowballpoof", Effect.SNOWBALL_BREAK),  DRIP_WATER("dripWater", Effect.WATERDRIP),  DRIP_LAVA("dripLava", Effect.LAVADRIP),  SNOW_SHOVEL("snowshovel", Effect.SNOW_SHOVEL),  SLIME("slime", Effect.SLIME),  HEART("heart", Effect.HEART),  ANGRY_VILLAGER("angryVillager", Effect.VILLAGER_THUNDERCLOUD),  HAPPY_VILLAGER("happyVillager", Effect.HAPPY_VILLAGER);

    private final String id;
    private final Effect effect;

    private ParticleEnum(String id, Effect effect)
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
