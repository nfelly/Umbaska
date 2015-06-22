package uk.co.umbaska.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;

public class ItemManager {
	
	public static ItemStack createItem(Material material, int amount, String name, String... lore){
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        if(lore != null) meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
	
	public static ItemStack createItem(Material material, int amount, short id, String name, String... lore){
        ItemStack item = new ItemStack(material, amount, id);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        if(lore != null) meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
	
	public static ItemStack createItem(Material material, String name, String... lore){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        if(lore != null) meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
	
	public static ItemStack createItem(Material material, int amount, String name){
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
	
	public static ItemStack createItem(Material material, String name){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
	
	public static ItemStack createItem(Material material, short id, String name){
        ItemStack item = new ItemStack(material, 1, id);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
	
	public static ItemStack createItem(Material material, int amount, short id, String name){
        ItemStack item = new ItemStack(material, amount, id);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
	
	public static ItemStack createSpawnEgg(int amount, int id, String name, String... lore){
        ItemStack item = new ItemStack(Material.MONSTER_EGG, amount, (short)id);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        if(lore != null) meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
	
	public static ItemStack createPlayerHead(String playername, String name, String... lore){
		ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(ChatColor.GOLD + playername);
		if(name != null) m.setDisplayName(name);
		if(lore != null) m.setLore(Arrays.asList(lore));
		((SkullMeta)m).setOwner(playername);
		i.setItemMeta(m);
		return i;
	}
	
	public static ItemStack createPlayerHead(String playername, String... lore){
		ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(ChatColor.GOLD + playername);
		if(lore != null) m.setLore(Arrays.asList(lore));
		((SkullMeta)m).setOwner(playername);
		i.setItemMeta(m);
		return i;
	}
	
	public static ItemStack cloneSetName(ItemStack item, String name){
		ItemStack itemToName = item.clone();
		ItemMeta meta = itemToName.getItemMeta();
		meta.setDisplayName(name);
		itemToName.setItemMeta(meta);
		return itemToName;
	}
	
	public static ItemStack setName(ItemStack item, String name){
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack setLore(ItemStack item, String... lore){
		ItemStack i = item.clone();
		ItemMeta meta = i.getItemMeta();
        meta.setLore(Arrays.asList(lore));
		i.setItemMeta(meta);
		return i;
	}
	
	public static ItemStack cloneSetLore(ItemStack item, String... lore){
		ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack addLore(ItemStack item, String... lore){
		ItemMeta meta = item.getItemMeta();
        List<String> lorez = meta.getLore();
        for(String s : lore) lorez.add(s);
        meta.setLore(lorez);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack cloneAddLore(ItemStack item, String... lore){
		ItemStack itemToLore = item.clone();
		ItemMeta meta = itemToLore.getItemMeta();
        List<String> lorez = meta.getLore();
        for(String s : lore) lorez.add(s);
        meta.setLore(lorez);
		itemToLore.setItemMeta(meta);
		return itemToLore;
	}
	
	public static ItemMeta getMeta(ItemStack item){
		return item.getItemMeta();
	}
	
	public static boolean playerHaveItem(Player player, ItemStack itemStack){
		if(player.getInventory().contains(itemStack)) return true;
		return false;
	}
	
	public static boolean playerHaveItem(Player player, Material material){
		if(player.getInventory().contains(material)) return true;
		return false;
	}
	
	public static ItemStack addGlow(ItemStack item){
		try{
			EnchantGlow.addGlow(item);
		}catch (Exception e){
            e.printStackTrace();
		}
		return item;
	}
	
	public static String getStringFromItemLore(ItemStack e, String s){
		String result = "";
		for (int i = 0; i < e.getItemMeta().getLore().size(); i++) {
			if (e.getItemMeta().getLore().get(i).contains(s)) {
				return cleanLoreLine(e.getItemMeta().getLore().get(i));
			}
		}
		return result;
	}
	
	private static String cleanLoreLine(String s) { // EXEMPLO: s = "HP: +200"; // EXAMPLE: s = "HP: +200"; //
		String[] args = s.split(":");
		args[1] = ChatColor.stripColor(args[1]);
		String string = args[1].trim();
		return string; //resultado (exemplo) = "+200" // result (example) = "+200" //
	}
	
	/**
	 * @author Arthur
	 * UNTESTED
	 */
	public static class ItemCreator {
		
		private Material material = Material.AIR;
		private int quantity = 1;
		private short data = 0;
		private String name = "null";
		private List<String> lore = new ArrayList<String>();
		private boolean failed = false;
		private ItemStack itemstack;
		private Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
		private boolean done = false;
		
		public ItemCreator (Material material){
			this.material = material;
		}
		
		@SuppressWarnings("deprecation")
		public ItemCreator (int materialid){
			try{
				this.material = Material.getMaterial(materialid);
			}catch (Exception e){
				this.failed = true;
				this.material = Material.SULPHUR;
				this.name = "[ERROR]"; // �c[ERRO]
				ItemStack item = new ItemStack(Material.SULPHUR);
		        ItemMeta meta = item.getItemMeta();
		        meta.setDisplayName("[ERROR]"); // �c[ERRO]
		        item.setItemMeta(meta);
		        this.itemstack = item;
			}
		}
		
		public ItemCreator (String materialname){
			try{
				this.material = Material.matchMaterial(materialname);
			}catch (Exception e){
				this.failed = true;
				this.material = Material.SULPHUR;
				this.name = "[ERROR]"; // �c[ERRO]
				ItemStack item = new ItemStack(Material.SULPHUR);
		        ItemMeta meta = item.getItemMeta();
		        meta.setDisplayName("[ERROR]"); // �c[ERRO]
		        item.setItemMeta(meta);
		        this.itemstack = item;
			}
		}
		
		public String getName(){
			if(this.name.equalsIgnoreCase("null")) return this.material.name();
			return this.name;
		}
		
		public Map<Enchantment, Integer> getEnchantments(){
			return this.enchants;
		}
		
		public int getAmount(){
			return this.quantity;
		}
		
		public ItemCreator addEnchantment(Enchantment enchantment, int level){
			this.enchants.put(enchantment, level);
			return this;
		}
		
		public ItemCreator setName(String name){
			this.name = name;
			return this;
		}
		
		public ItemCreator setAmount(int amount){
			this.quantity = amount;
			return this;
		}
		
		public ItemCreator addLore(String s){
			this.lore.add(s);
			return this;
		}
		
		public ItemCreator addLore(String[] lore){
			for(String s : lore){
				this.lore.add(s);
			}
			return this;
		}
		
		public ItemCreator setLore(String[] lore){
			this.lore = Arrays.asList(lore);
			return this;
		}
		
		public ItemCreator setData(int value){
			this.data = (short) value;
			return this;
		}
		
		public boolean failed(){
			return this.failed;
		}
		
		public short getData(){
			return this.data;
		}
		
		public boolean isDone(){
			return this.done;
		}
		
		public ItemStack clone(){
			if(this.done) return this.itemstack.clone();
			return new ItemStack(Material.AIR);
		}
		
		public ItemStack build(){
			if(this.failed){
				return this.itemstack;
			}else{
				ItemStack item = new ItemStack(this.material, this.quantity, this.data);
		        ItemMeta meta = item.getItemMeta();
		        if(this.enchants != null) item.addUnsafeEnchantments(this.enchants);
		        if(!this.name.equalsIgnoreCase("null")) meta.setDisplayName(this.name);
		        if(this.lore != null) meta.setLore(this.lore);
		        item.setItemMeta(meta);
		        this.itemstack = item;
		        this.done = true;
		        return item;
			}
		}
		
	}
	
	protected static class EnchantGlow extends EnchantmentWrapper {
		private static Enchantment glow;
		public EnchantGlow(int id){
			super(id);
		}
		@Override
		public boolean canEnchantItem(ItemStack item){
			return true;
		}
		@Override
		public boolean conflictsWith(Enchantment other){
			return false;
		}
		@Override
		public EnchantmentTarget getItemTarget(){
			return null;
		}
		@Override
		public int getMaxLevel(){
			return 10;
		}
		@Override
		public String getName(){
			return "Glow";
		}
		@Override
		public int getStartLevel(){
			return 1;
		}
		public static Enchantment getGlow(){
			if (glow != null)
				return glow;
			try{
				Field f = Enchantment.class.getDeclaredField("acceptingNew");
				f.setAccessible(true);
				f.set(null , true);
				f.setAccessible(false);
			}catch (Exception e){
				e.printStackTrace();
			}
			glow = new EnchantGlow(255);
			Enchantment.registerEnchantment(glow);
			return glow;
		}
		public static void addGlow(ItemStack item){
			Enchantment glow = getGlow();
			item.addEnchantment(glow , 1);
		}
	}
		
}