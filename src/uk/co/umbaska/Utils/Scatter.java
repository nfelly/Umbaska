package uk.co.umbaska.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zachary on 4/12/2015.
 */
public class Scatter {

    World world;
    final Plugin p;
    int radius;
    int x;
    int z;
    int delay = 20;
    Entity[] players;
    ItemStack[] badBlocks;

    public Scatter(Plugin p, World world, int radius, int x, int z, ItemStack[] badBlocks, int delay, Entity[] players){
        this.world = world;
        this.p = p;
        this.radius =radius;
        this.x = x;
        this.z = z;
        this.badBlocks = badBlocks;
        this.delay = delay;
        this.players = players;
        scatter();
    }

    public Scatter(Plugin p, int radius, Location loc, ItemStack[] badBlocks, int delay, Entity[] players){
        this.world = loc.getWorld();
        this.p = p;
        this.radius =radius;
        this.x = loc.getBlockX();
        this.z = loc.getBlockZ();
        this.badBlocks = badBlocks;
        this.delay = delay;
        this.players = players;
        scatter();
    }

    private void scatter(){
        Random random = new Random();
        final double randomAngle = random.nextDouble() * Math.PI * 2.0D;
        final double newradius = this.radius * random.nextDouble();
        final int[] coords = convertFromRadiansToBlock(newradius, randomAngle);
        Bukkit.getScheduler().runTaskTimer(p, new Runnable() {
            int count = -1;
            @Override
            public void run() {
                count++;
                Entity e = players[count];
                scatterPlayerRandom(e, randomAngle, newradius, coords);
            };
        }, delay, delay);

    }

    public void scatterPlayerRandom(final Entity p, final double angle, final double newrad, final int[] coords)
    {
        final Location finalTeleport = new Location(this.world, 0.0D, 0.0D, 0.0D);

        finalTeleport.setX(coords[0]);
        finalTeleport.setZ(coords[1]);

        finalTeleport.setX(finalTeleport.getX() + this.x);
        finalTeleport.setZ(finalTeleport.getZ() + this.z);

        finalTeleport.setX(Math.round(finalTeleport.getX()) + 0.5D);
        finalTeleport.setZ(Math.round(finalTeleport.getZ()) + 0.5D);
        if (!this.world.getChunkAt(finalTeleport).isLoaded()) {
            this.world.getChunkAt(finalTeleport).load(true);
        }
        final Location loc = finalTeleport;
        final Plugin plug = this.p;
        final World worlds = this.world;
        Bukkit.getScheduler().runTaskLater(this.p, new Runnable() {
            Location finalTeleport2 = loc;
            @Override
            public void run() {
                worlds.getChunkAt(finalTeleport2).load(true);
                finalTeleport2.setY(getSafeY(finalTeleport2));
                Location blockUnder = finalTeleport2;
                blockUnder.setY(blockUnder.getBlockY() - 1);
                List<Material> banned = new ArrayList<Material>();
                if (badBlocks.length > 0) {
                    for (ItemStack b : badBlocks) {
                        banned.add(b.getType());
                    }
                }
                if (finalTeleport2.getBlock().getType() != Material.AIR){

                    finalTeleport2.setY(getSafeY(finalTeleport2));
                }
                for (Material ban : banned){
                    if (blockUnder.getBlock().getType() == ban || finalTeleport2.getBlock().getType() == ban){
                        scatterPlayerRandom(p, angle, newrad, coords);
                        return;
                    }
                }
                Bukkit.getScheduler().runTaskLater(plug, new Runnable() {
                    @Override
                    public void run() {
                        p.teleport(finalTeleport);
                    }
                }, 20L);
            }
        }, 40L);
        //Bukkit.broadcastMessage(finalTeleport.getX() + " :X  Z: " + finalTeleport.getZ() + " P: " + p.getName());


    }

    private int getSafeY(Location loc)
    {
        return loc.getWorld().getHighestBlockYAt(loc);
    }

    private int[] convertFromRadiansToBlock(double radius, double angle)
    {
        return new int[] { (int)Math.round(radius * Math.cos(angle)), (int)Math.round(radius * Math.sin(angle)) };
    }
}
