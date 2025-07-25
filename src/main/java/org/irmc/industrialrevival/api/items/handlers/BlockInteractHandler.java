package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.events.vanilla.PlayerInteractIRBlockEvent;
import org.irmc.industrialrevival.api.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface BlockInteractHandler extends ItemHandler {
    /**
     * Called when a player clicks on a block with the item.
     *
     * @param event the {@link PlayerInteractEvent} was triggered
     */
    void onBlockUse(PlayerInteractIRBlockEvent event);

    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        if (!item.getIcon().getType().isBlock()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are blocks can have a block use handler", item.getId());
        }
        if (item instanceof NotPlaceable) {
            return new IncompatibleItemHandlerException("This item cannot be placed", item.getId());
        }
        return null;
    }

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return BlockInteractHandler.class;
    }
}
