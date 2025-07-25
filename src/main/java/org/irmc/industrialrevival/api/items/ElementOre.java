package org.irmc.industrialrevival.api.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.Smeltery;
import org.irmc.industrialrevival.api.elements.tinker.TinkerType;
import org.irmc.industrialrevival.api.elements.tinker.TinkerTypes;
import org.jetbrains.annotations.Range;

/**
 * @author balugaq
 */
public class ElementOre extends ElementItem {
    @Override
    public @Range(from = 0, to = Smeltery.MAX_FUEL) int getMeltingPoint(ItemStack itemStack) {
        return (int) getElementType().getMeltingPoint() / 10;
    }

    @Override
    public int getFuelUse(ItemStack itemStack) {
        return (int) getElementType().getMeltingPoint() / 10;
    }

    public TinkerType getTinkerType(ItemStack itemStack) {
        return TinkerTypes.ORE;
    }
}
