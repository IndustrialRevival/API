package org.irmc.industrialrevival.api.menu;

import org.bukkit.inventory.ItemStack;

/**
 * Interface for objects that can be represented as an ItemStack for display purposes.
 *
 * @param <T> The type of item to display.
 * @author balugaq
 */
public interface Displayable<T> {
    /**
     * Gets the ItemStack representation of the given item.
     *
     * @param item the item to convert into an ItemStack
     * @return the ItemStack suitable for display
     */
    ItemStack getDisplayItem(T item);
}