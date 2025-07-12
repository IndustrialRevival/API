package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.irmc.industrialrevival.api.objects.display.DisplayGroup;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IBlockModelService {
    @NotNull List<DisplayGroup> getDisplayGroups(@NotNull Location location);

    void addDisplayGroup(@NotNull Location location, @NotNull DisplayGroup @NotNull ... displayGroups);

    void removeDisplayGroup(@NotNull Location location, @NotNull DisplayGroup @NotNull ... displayGroups);

    void removeAllDisplayGroup(@NotNull Location location);
}
