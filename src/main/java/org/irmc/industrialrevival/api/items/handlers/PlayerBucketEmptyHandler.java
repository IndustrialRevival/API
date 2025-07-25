package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.events.vanilla.PlayerBucketEmptyToIRBlockEvent;
import org.jetbrains.annotations.NotNull;

public interface PlayerBucketEmptyHandler extends ItemHandler {
    void onPlayerBucketEmpty(@NotNull PlayerBucketEmptyToIRBlockEvent event);

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return PlayerBucketEmptyHandler.class;
    }
}
