package org.irmc.industrialrevival.api.menu.handlers;

import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.jetbrains.annotations.NotNull;

/**
 * Functional interface for handling actions when a menu is closed by a player.
 * This can be used to clean up resources or save state after the menu is closed.
 *
 * @author lijinhong11
 */
@FunctionalInterface
public interface MenuCloseHandler {
    /**
     * Called when a player closes a menu.
     *
     * @param player the player who closed the menu
     * @param menu   the SimpleMenu instance being closed
     */
    void onClose(@NotNull Player player, @NotNull SimpleMenu menu);
}