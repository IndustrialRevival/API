package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.irmc.industrialrevival.api.display.DisplayGroup;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service interface for managing block models in the IndustrialRevival plugin.
 * Provides methods to retrieve, add, and remove display groups associated with specific locations.
 *
 * @author balugaq
 */
public interface IBlockModelService {
    /**
     * Retrieves all display groups associated with a given location.
     *
     * @param location the location to check
     * @return a list of display groups associated with the location
     */
    @NotNull List<DisplayGroup> getDisplayGroups(@NotNull Location location);

    /**
     * Adds one or more display groups to a specific location.
     *
     * @param location      the location to add display groups to
     * @param displayGroups the display groups to add
     */
    void addDisplayGroup(@NotNull Location location, @NotNull DisplayGroup @NotNull ... displayGroups);

    /**
     * Removes one or more display groups from a specific location.
     *
     * @param location      the location to remove display groups from
     * @param displayGroups the display groups to remove
     */
    void removeDisplayGroup(@NotNull Location location, @NotNull DisplayGroup @NotNull ... displayGroups);

    /**
     * Removes all display groups from a specific location.
     *
     * @param location the location to clear all display groups from
     */
    void removeAllDisplayGroup(@NotNull Location location);
}