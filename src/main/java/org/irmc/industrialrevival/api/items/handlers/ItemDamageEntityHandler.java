package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.vanilla.IRItemDamageEntityEvent;
import org.irmc.industrialrevival.api.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemDamageEntityHandler extends ItemHandler {
    /**
     * This function is called when a {@link Player} attacks an {@link Entity} with a {@link IndustrialRevivalItem}
     *
     * @param event the event that occurred
     */
    void onHit(@NotNull IRItemDamageEntityEvent event);

    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        if (!item.getIcon().getType().isItem()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are items can have a use item damage entity handler", item.getId());
        }

        return null;
    }

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return ItemDamageEntityHandler.class;
    }
}
