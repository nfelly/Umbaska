package uk.co.umbaska.Enums;

import org.bukkit.Effect;

/**
 * Created by Zachary on 5/17/2015.
 */
public enum ParticleEnum {
    PARTICLE_TILE_DUST("tile dust", Effect.TILE_DUST),
    PARTICLE_TILE_BREAK("tile break", Effect.TILE_BREAK),
    PARTICLE_BLOCK_DUST("tile dust", Effect.TILE_DUST),
    PARTICLE_BLOCK_BREAK("tile break", Effect.TILE_BREAK),
    PARTICLE_ITEM_BREAK("item break", Effect.ITEM_BREAK),
    PARTICLE_HUGE_EXPLOSION("hugeexplosion", Effect.EXPLOSION_HUGE),
    PARTICLE_LARGE_EXPLODE("largeexplode", Effect.EXPLOSION_LARGE),
    PARTICLE_FIREWORKS_SPARK("fireworksSpark", Effect.FIREWORKS_SPARK),
    PARTICLE_SUSPENDED("suspended", Effect.SMALL_SMOKE),
    PARTICLE_DEPTH_SUSPEND("depthsuspend", Effect.VOID_FOG),
    PARTICLE_CRIT("crit", Effect.CRIT),
    PARTICLE_MAGIC_CRIT("magicCrit", Effect.MAGIC_CRIT),
    PARTICLE_SMOKE("smoke", Effect.PARTICLE_SMOKE),
    PARTICLE_MOB_SPELL("mobSpell", Effect.POTION_SWIRL),
    PARTICLE_MOB_SPELL_AMBIENT("mobSpellAmbient", Effect.POTION_SWIRL_TRANSPARENT),
    PARTICLE_SPELL("spell", Effect.SPELL),
    PARTICLE_INSTANT_SPELL("instantSpell", Effect.INSTANT_SPELL),
    PARTICLE_WITCH_MAGIC("witchMagic", Effect.WITCH_MAGIC),
    PARTICLE_NOTE("note", Effect.NOTE),
    PARTICLE_PORTAL("portal", Effect.PORTAL),
    PARTICLE_ENCHANTMENT_TABLE("enchantmenttable", Effect.FLYING_GLYPH),
    PARTICLE_EXPLODE("explode", Effect.EXPLOSION),
    PARTICLE_FLAME("flame", Effect.FLAME),
    PARTICLE_LAVA("lava", Effect.LAVA_POP),
    PARTICLE_FOOTSTEP("footstep", Effect.FOOTSTEP),
    PARTICLE_SPLASH("splash", Effect.SPLASH),
    PARTICLE_LARGE_SMOKE("largesmoke", Effect.LARGE_SMOKE),
    PARTICLE_CLOUD("cloud", Effect.CLOUD),
    PARTICLE_RED_DUST("reddust", Effect.COLOURED_DUST),
    PARTICLE_SNOWBALL_POOF("snowballpoof", Effect.SNOWBALL_BREAK),
    PARTICLE_DRIP_WATER("dripWater", Effect.WATERDRIP),
    PARTICLE_DRIP_LAVA("dripLava", Effect.LAVADRIP),
    PARTICLE_SNOW_SHOVEL("snowshovel", Effect.SNOW_SHOVEL),
    PARTICLE_SLIME("slime", Effect.SLIME),
    PARTICLE_HEART("heart", Effect.HEART),
    PARTICLE_ANGRY_VILLAGER("angryVillager", Effect.VILLAGER_THUNDERCLOUD),
    PARTICLE_HAPPY_VILLAGER("happyVillager", Effect.HAPPY_VILLAGER);

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
