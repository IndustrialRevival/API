package org.irmc.industrialrevival.core.guide;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Interface for guide implementation.
 * Defines methods for displaying guide content to players.
 */
public interface IRGuideImplementation {
    /**
     * Gets the current guide mode.
     *
     * @return the current guide mode
     */
    @NotNull
    GuideMode getGuideMode();

    /**
     * Opens the main page of the guide for a player.
     *
     * @param player the player to open the guide for
     * @param page the page number to display
     */
    @ParametersAreNonnullByDefault
    void openMainPage(Player player, int page);

    /**
     * Opens an item group in the guide for a player.
     *
     * @param player the player to open the guide for
     * @param itemGroup the item group to display
     * @param page the page number to display
     */
    @ParametersAreNonnullByDefault
    void openItemGroup(Player player, ItemGroup itemGroup, int page);

    /**
     * Displays a specific item in the guide for a player.
     *
     * @param player the player to open the guide for
     * @param itemStack the item to display
     * @param page the page number to display
     */
    @ParametersAreNonnullByDefault
    void displayItem(Player player, ItemStack itemStack, int page);
}