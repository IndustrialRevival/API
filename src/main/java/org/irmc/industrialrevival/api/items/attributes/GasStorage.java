package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.data.runtime.ContainerMetaData;
import org.irmc.industrialrevival.api.data.runtime.GasContainerMetaData;
import org.irmc.industrialrevival.api.physics.PhysicalState;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.pigeonlib.objects.percentage.PositiveHundredPercentage;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;

/**
 * @author lijinhong11
 */
public interface GasStorage extends ChemicalCompoundContainer {
    NamespacedKey GAS_STORAGE_CAPACITY_KEY = KeyUtil.customKey("gas_storage_capacity");


    default PositiveHundredPercentage getPercentage(ItemStack item) {
        return PositiveHundredPercentage.fromDoublePercentage(PersistentDataAPI.getOrDefault(item.getItemMeta(), GAS_STORAGE_CAPACITY_KEY, PersistentDataType.DOUBLE, 0.0));
    }

    default void setPercentage(ItemStack item, PositiveHundredPercentage gasStorageCapacity) {
        item.editMeta(meta -> PersistentDataAPI.set(meta, GAS_STORAGE_CAPACITY_KEY, PersistentDataType.DOUBLE, gasStorageCapacity.getDecimalValue()));
    }

    void insertGas(ItemStack item, ChemicalCompoundContainer storage);

    @Override
    default void setMass(@NotNull ItemStack itemStack, double mass) {
        throw new UnsupportedOperationException("Use #setPercentage(ItemStack, PositiveHundredPercentage)");
    }

    @Override
    default double getMass(@NotNull ItemStack itemStack) {
        throw new UnsupportedOperationException("Use #getPercentage(ItemStack)");
    }

    @Override
    default void setPhysicalState(@NotNull ItemStack itemStack, @NotNull PhysicalState state) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    @NotNull
    default ContainerMetaData getContainerMetaData(@NotNull ItemStack itemStack) {
        return getGasContainerMetaData(itemStack);
    }

    default GasContainerMetaData getGasContainerMetaData(ItemStack itemStack) {
        return new GasContainerMetaData(
                getChemicalCompound(itemStack),
                getCapacity(itemStack),
                getPercentage(itemStack),
                getSeal(itemStack),
                getPhysicalState(itemStack),
                getContainerType(itemStack)
        );
    }

    @Override
    default @NotNull PhysicalState getPhysicalState(@NotNull ItemStack itemStack) {
        return PhysicalState.GAS;
    }
}
