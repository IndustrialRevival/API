package org.irmc.industrialrevival.api.menu;

import org.bukkit.inventory.ItemStack;

/**
 * @param <T> The type of item to display.
 * @author balugaq
 */
public interface Displayable<T> {
    ItemStack getDisplayItem(T item);
}
