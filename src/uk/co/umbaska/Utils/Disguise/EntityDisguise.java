package uk.co.umbaska.Utils.Disguise;

import org.bukkit.Bukkit;

/**
 * Created by Zachary on 5/6/2015.
 */
public enum EntityDisguise {
    ZOMBIE("EntityZombie"), WITHER_SKELETON("EntitySkeleton"), SKELETON(
            "EntitySkeleton"), ZOMBIEPIG("EntityPigZombie"), BLAZE(
            "EntityBlaze"), ENDERMAN("EntityEnderman"), CREEPER("EntityCreeper"), SPIDER(
            "EntitySpider"), WITCH("EntityWitch"), WITHER_BOSS("EntityWither"), GHAST(
            "EntityGhast"), GIANT("EntityGiantZombie"), SLIME("EntitySlime"), CAVE_SPIDER(
            "EntityCaveSpider"), SILVERFISH("EntitySilverfish"), MAGMA_CUBE(
            "EntityMagmaCube"), BAT("EntityBat"), PIG("EntityPig"), SHEEP(
            "EntitySheep"), COW("EntityCow"), CHICKEN("EntityChicken"), SQUID(
            "EntitySquid"), WOLF("EntityWolf"), OCELOT("EntityOcelot"), HORSE(
            "EntityHorse"), VILLAGER("EntityVillager"), IRON_GOLEM(
            "EntityIronGolem"), SNOWMAN("EntitySnowman"), ENDER_DRAGON(
            "EntityEnderDragon"), MOOSHROOM("EntityMushroomCow"), UNKNOWN("UNKOWN");
    private final String cls;

    EntityDisguise(String cls) {
        this.cls = cls;
    }

    /**
     * <b><u>FORGET THIS! DONT USE IT!</u></b>
     *
     * @return
     */
    public String getClassName() {
        return "net.minecraft.server."
                + Bukkit.getServer().getClass().getPackage().getName()
                .substring(23) + "." + cls;
    }
}