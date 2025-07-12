package org.irmc.industrialrevival.api.menu.handlers;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.jetbrains.annotations.NotNull;

/**
 * Functional interface for handling clicks outside of a menu's inventory.
 * This is typically used in GUI systems to handle interactions that occur
 * when the player clicks on an inventory slot not belonging to the current menu.
 *
 * @author balugaq
 */
@FunctionalInterface
public interface OutsideClickHandler {
    /**
     * Handles the click event that occurs outside the menu.
     *
     * @param event the inventory click event triggered by the player
     * @param menu  the SimpleMenu instance associated with the inventory
     */
    void onClick(@NotNull InventoryClickEvent event, @NotNull SimpleMenu menu);
}