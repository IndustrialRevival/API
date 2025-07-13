package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.Smeltery;
import org.irmc.industrialrevival.api.elements.melt.MeltedType;
import org.irmc.industrialrevival.api.elements.tinker.TinkerType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * @author balugaq
 */
public interface Meltable extends ItemAttribute {
    @Nullable TinkerType getTinkerType(@Nullable ItemStack itemStack);

    @Nullable MeltedType getMeltedType(@Nullable ItemStack itemStack);

    @Range(from = 0, to = Smeltery.MAX_FUEL)
    int getMeltingPoint(ItemStack itemStack);

    int getFuelUse(ItemStack itemStack);
}
