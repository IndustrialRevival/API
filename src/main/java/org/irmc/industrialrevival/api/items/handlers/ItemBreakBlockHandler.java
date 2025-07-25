package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@FunctionalInterface
public interface ItemBreakBlockHandler extends ItemHandler {
    /**
     * Called when a tool is used on a block.
     * <br/>
     * <b>Note: block drop item doesn't add to the drops list.</b>
     *
     * @param event the {@link BlockBreakEvent} that was fired
     * @param tool  the {@link ItemStack} that was used as a tool
     * @param drops the list of {@link ItemStack}s that will be dropped by the block
     */
    void onBreakBlock(@NotNull BlockBreakEvent event, @NotNull ItemStack tool, @NotNull List<ItemStack> drops);

    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        if (!item.getIcon().getType().isItem()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are items can have a item break block handler", item.getId());
        }

        return null;
    }

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return ItemBreakBlockHandler.class;
    }
}
