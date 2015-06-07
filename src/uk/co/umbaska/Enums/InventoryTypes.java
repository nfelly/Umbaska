package uk.co.umbaska.Enums;

import org.bukkit.event.inventory.InventoryType;

/**
 * Created by Zachary on 5/18/2015.
 */
public enum InventoryTypes {

    ANVIL("umbaska anvil", InventoryType.ANVIL), BEACON("umbaska beacon", InventoryType.BEACON), CHEST("umbaska chest", InventoryType.CHEST), CREATIVE("umbaska creative", InventoryType.CREATIVE), DISPENSER("umbaska dispenser", InventoryType.DISPENSER), DROPPER("umbaska dropper", InventoryType.DROPPER), ENCHANTING("umbaska enchanting", InventoryType.ENCHANTING), ENDER_CHEST("umbaska ender chest", InventoryType.ENDER_CHEST), FURNACE("umbaska furnace", InventoryType.FURNACE), HOPPER("umbaska hopper", InventoryType.HOPPER), MERCHANT("umbaska merchant", InventoryType.MERCHANT), PLAYER("umbaska player", InventoryType.PLAYER), WORKBENCH("umbaska WORKBENCH", InventoryType.WORKBENCH);

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
