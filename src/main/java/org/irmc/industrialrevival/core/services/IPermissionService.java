package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.bukkit.permissions.Permissible;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.Permission;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Interface for permission service.
 * Provides methods for checking permissions in various contexts.
 *
 * @author balugaq
 */
public interface IPermissionService {
    /**
     * Checks if a permissible entity has a specific permission.
     *
     * @param p          the permissible entity to check
     * @param permission the permission to check for
     * @return true if the permissible entity has the permission, false otherwise
     */
    @ParametersAreNonnullByDefault
    boolean hasPermission(Permissible p, Permission permission);

    /**
     * Checks if a permissible entity has a specific permission at a given location.
     *
     * @param p          the permissible entity to check
     * @param location   the location to check the permission for
     * @param permission the permission to check for
     * @return true if the permissible entity has the permission at the location, false otherwise
     */
    @ParametersAreNonnullByDefault
    boolean hasPermission(Permissible p, Location location, Permission permission);

    /**
     * Checks if a permissible entity has a specific permission for an industrial revival item.
     *
     * @param p          the permissible entity to check
     * @param item       the industrial revival item to check the permission for
     * @param permission the permission to check for
     * @return true if the permissible entity has the permission for the item, false otherwise
     */
    @ParametersAreNonnullByDefault
    boolean hasPermission(Permissible p, IndustrialRevivalItem item, Permission permission);
}