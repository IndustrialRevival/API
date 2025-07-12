package org.irmc.industrialrevival.api.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

/**
 * Interface representing an object that holds an inventory.
 * This extends Bukkit's InventoryHolder to ensure the inventory returned is not null.
 */
public interface IRInventoryHolder extends InventoryHolder {
    /**
     * Returns the inventory held by this object.
     *
     * @return the inventory associated with this holder
     */
    @Override
    @NotNull Inventory getInventory();
}