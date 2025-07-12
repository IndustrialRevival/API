package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.bukkit.permissions.Permissible;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.Permission;

import javax.annotation.ParametersAreNonnullByDefault;

public interface IPermissionService {
    @ParametersAreNonnullByDefault
    boolean hasPermission(Permissible p, Permission permission);
    @ParametersAreNonnullByDefault
    boolean hasPermission(Permissible p, Location location, Permission permission);
    @ParametersAreNonnullByDefault
    boolean hasPermission(Permissible p, IndustrialRevivalItem item, Permission permission);
}
