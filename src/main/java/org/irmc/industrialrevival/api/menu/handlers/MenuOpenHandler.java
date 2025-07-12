package org.irmc.industrialrevival.api.menu.handlers;

import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.jetbrains.annotations.NotNull;

/**
 * Functional interface for handling actions when a menu is opened by a player.
 * This can be used to perform initialization logic or send messages when a menu is displayed.
 *
 * @author balugaq
 * @author lijinhong11
 */
@FunctionalInterface
public interface MenuOpenHandler {
    /**
     * Called when a player opens a menu.
     *
     * @param player the player who opened the menu
     * @param menu   the SimpleMenu instance being opened
     */
    void onOpen(@NotNull Player player, @NotNull SimpleMenu menu);
}