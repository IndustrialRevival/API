package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.events.vanilla.PrepareTradeSelectIRItemEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PrepareTradeSelectHandler extends ItemHandler {
    void onPrepareTradeSelect(@NotNull PrepareTradeSelectIRItemEvent event);

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return PrepareTradeSelectHandler.class;
    }
}
