package org.irmc.industrialrevival.api.menu.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * Handler interface for advanced click events in menus.
 * Extends ClickHandler to provide additional context about click events.
 *
 * @author lijinhong11
 */
@FunctionalInterface
public interface AdvancedClickHandler extends ClickHandler {
    /**
     * Called when an item in the machine menu is clicked.
     * This method is called with less context than the extended version.
     *
     * @param player      the player who clicked the item
     * @param clickedItem the clicked item
     * @param clickedSlot the slot where the item was clicked (range: 0-53)
     * @param clickedMenu the machine menu where the item was clicked
     * @param clickType   the type of click performed
     * @return always false, use {@link #onClick(Player, ItemStack, int, SimpleMenu, ClickType, InventoryClickEvent)}
     */

    @Override
    default boolean onClick(
            @NotNull Player player,
            @Nullable ItemStack clickedItem,
            @Range(from = 0, to = 53) int clickedSlot,
            @NotNull SimpleMenu clickedMenu,
            @NotNull ClickType clickType) {
        return false;
    }

    /**
     * Called when an item in the machine menu is clicked with full event context.
     * Implement this method to handle click events with complete information.
     *
     * @param player    the player who clicked the item
     * @param item      the clicked item
     * @param slot      the slot where the item was clicked (range: 0-53)
     * @param menu      the machine menu where the item was clicked
     * @param clickType the type of click performed
     * @param event     the full inventory click event
     * @return whether the click event was consumed by this handler
     */
    boolean onClick(
            @NotNull Player player,
            @Nullable ItemStack item,
            @Range(from = 0, to = 53) int slot,
            @NotNull SimpleMenu menu,
            @NotNull ClickType clickType,
            @NotNull InventoryClickEvent event);
}