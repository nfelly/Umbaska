package uk.co.umbaska.Enums;

import org.bukkit.event.inventory.InventoryType;

/**
 * Created by Zachary on 5/18/2015.
 */
public enum InventoryTypes {

    ANVIL("anvil", InventoryType.ANVIL), BEACON("beacon", InventoryType.BEACON), CHEST("chest", InventoryType.CHEST), CREATIVE("creative", InventoryType.CREATIVE), DISPENSER("dispenser", InventoryType.DISPENSER), DROPPER("dropper", InventoryType.DROPPER), ENCHANTING("enchanting", InventoryType.ENCHANTING), ENDER_CHEST("ender chest", InventoryType.ENDER_CHEST), FURNACE("furnace", InventoryType.FURNACE), HOPPER("hopper", InventoryType.HOPPER), MERCHANT("merchant", InventoryType.MERCHANT), PLAYER("player", InventoryType.PLAYER), WORKBENCH("WORKBENCH", InventoryType.WORKBENCH);

    private final String id;
    private final InventoryType effect;

    private InventoryTypes(String id, InventoryType type)
    {
        this.id = id;
        this.effect = type;
    }

    public InventoryType getType(){
        return this.effect;
    }

    public String getId()
    {
        return this.id;
    }
}
