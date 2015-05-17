/*
 * EffMFG_GiveSpawner.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Spawner;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EffMFG_GiveSpawner extends Effect {

    private Expression<Player> player;
    private Expression<Block> basedon;

    @Override
    protected void execute(Event event){
        Player p = player.getSingle(event);
        Block bo = basedon.getSingle(event);
        CreatureSpawner cs = (CreatureSpawner) bo.getState();
        String e = cs.getCreatureTypeName();
        ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, 1);
        ItemMeta spawnerMeta = spawner.getItemMeta();
        spawnerMeta.setDisplayName(e + " Spawner");
        spawner.setItemMeta(spawnerMeta);
        p.getInventory().addItem(spawner);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Set a spawner";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        player = (Expression<Player>) expressions[0];
        basedon = (Expression<Block>) expressions[1];
        return true;
    }
}