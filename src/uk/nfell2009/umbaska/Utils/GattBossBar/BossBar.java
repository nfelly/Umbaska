package uk.nfell2009.umbaska.Utils.GattBossBar;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftWither;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;

/**
 * Created by Zachary on 5/9/2015.
 */
public class BossBar {

    Player player;
    String text;
    CraftWither boss;
    public BossBar(Player p){
        player = p;
    }

    public BossBar(Player p, String text){
        player = p;
        this.text = text;
    }

    public void setText(String text){
        this.text = text;
    }

    public void clear(){
        this.text = "";
    }

    public void update(){
        boss = (CraftWither) player.getWorld().spawnEntity(player.getLocation(), EntityType.WITHER);
        boss.setCustomName(text);
    }
}
