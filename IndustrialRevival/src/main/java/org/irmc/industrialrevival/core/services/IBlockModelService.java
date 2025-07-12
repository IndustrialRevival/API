package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.irmc.industrialrevival.api.objects.display.DisplayGroup;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Interface for block model service.
 * Manages display groups for blocks at specific locations.
 */
public interface IBlockModelService {
    /**
     * Gets all display groups for a given location.
     *
     * @param location the location to check
     * @return a list of display groups at the specified location
     */
    @NotNull List<DisplayGroup> getDisplayGroups(@NotNull Location location);

    /**
     * Adds one or more display groups to a location.
     *
     * @param location the location to add display groups to
     * @param displayGroups the display groups to add
     */
    void addDisplayGroup(@NotNull Location location, @NotNull DisplayGroup @NotNull ... displayGroups);

    /**
     * Removes one or more display groups from a location.
     *
     * @param location the location to remove display groups from
     * @param displayGroups the display groups to remove
     */
    void removeDisplayGroup(@NotNull Location location, @NotNull DisplayGroup @NotNull ... displayGroups);

    /**
     * Removes all display groups from a location.
     *
     * @param location the location to remove all display groups from
     */
    void removeAllDisplayGroup(@NotNull Location location);
}