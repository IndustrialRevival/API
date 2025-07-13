package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.tinker.TinkerType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public interface TinkerProduct extends ItemAttribute {
    @NotNull TinkerType getTinkerType();

    @NotNull ItemStack getProduct();

    @NotNull IndustrialRevivalItem getIRItem();
}
