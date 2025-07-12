package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemHandler {
    @Nullable
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        return null;
    }

    @NotNull
    Class<? extends ItemHandler> getIdentifier();
}
