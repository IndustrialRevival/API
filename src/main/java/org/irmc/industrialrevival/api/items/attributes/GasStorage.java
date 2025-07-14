package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.physics.PhysicalState;
import org.jetbrains.annotations.NotNull;

/**
 * @author lijinhong11
 */
public interface GasStorage extends ChemicalCompoundContainer {
    void insertGas(ItemStack item, ChemicalCompoundContainer storage);

    @Override
    default void setPhysicalState(@NotNull ItemStack itemStack, @NotNull PhysicalState state) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    default @NotNull PhysicalState getPhysicalState(@NotNull ItemStack itemStack) {
        return PhysicalState.GAS;
    }
}
