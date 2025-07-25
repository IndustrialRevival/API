package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.events.vanilla.PrepareGrindstoneIRItemEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PrepareGrindstoneHandler extends ItemHandler {
    void onPrepareGrindstone(@NotNull PrepareGrindstoneIRItemEvent event);

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return PrepareGrindstoneHandler.class;
    }
}
