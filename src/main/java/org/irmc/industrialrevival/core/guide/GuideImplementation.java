package org.irmc.industrialrevival.core.guide;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Interface defining the guide implementation for IndustrialRevival items and features.
 * This interface provides various methods to open and display guide content to players.
 *
 * @author balugaq
 */
public interface GuideImplementation {
    @NotNull
    ItemStack getGuideIcon();

    /**
     * Gets the current guide mode being used by this implementation.
     *
     * @return GuideMode enum representing the current guide mode
     */
    @NotNull
    GuideMode getGuideMode();

    /**
     * Opens the main page of the guide for a player.
     *
     * @param player Player who will see the guide
     * @param page   Page number to display (used for pagination)
     */
    @ParametersAreNonnullByDefault
    void openMainPage(Player player, int page);

    /**
     * Opens a specific item group in the guide for a player.
     *
     * @param player    Player who will see the guide
     * @param itemGroup Item group to display
     * @param page      Page number within the item group to display
     */
    @ParametersAreNonnullByDefault
    void openItemGroup(Player player, ItemGroup itemGroup, int page);

    /**
     * Displays a specific item in the guide for a player.
     *
     * @param player    Player who will see the guide
     * @param itemStack Item to display in the guide
     * @param page      Page number for the item display
     */
    @ParametersAreNonnullByDefault
    void displayItem(Player player, ItemStack itemStack, int page);

    default void open(@NotNull Player player) {
        GuideUtil.openMainMenu(player, this);
    }
}