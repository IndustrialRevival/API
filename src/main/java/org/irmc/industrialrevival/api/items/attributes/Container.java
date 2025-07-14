package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Warning;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.data.runtime.ContainerMetaData;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactHelper;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.api.machines.process.Environment;
import org.irmc.industrialrevival.api.physics.ContainerType;
import org.irmc.industrialrevival.api.physics.PhysicalState;
import org.irmc.industrialrevival.api.physics.SealStatus;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.irmc.pigeonlib.pdc.types.PersistentDataTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

/**
 * @author lijinhong11
 * @author balugaq
 */
public interface Container extends ItemAttribute, Keyed {
    default @NotNull ReactResult react(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull List<ItemStack> reactItems) {
        return ReactHelper.react(environment, conditions, reactItems);
    }

    /**
     * Returns true if the item is a catalyst for the given condition.
     *
     * @param condition the condition to check.
     * @return true if the item is a catalyst for the given condition, false otherwise.
     */
    @Warning(reason = "move to ConditionSensor")
    @Deprecated
    default boolean isCatalyst(@NotNull ReactCondition condition) {
        return false;
    }


    /** ===== Data ===== */
    /** All the code below describes the object's current status */
    /** Data getting depends {@link ItemStack} */

    @NotNull
    default ContainerMetaData getContainerMetaData(@NotNull ItemStack itemStack) {
        return new ContainerMetaData(
                getChemicalCompound(itemStack),
                getCapacity(itemStack),
                getMass(itemStack),
                getSeal(itemStack),
                getPhysicalState(itemStack),
                getContainerType(itemStack)
        );
    }

    //<editor-fold desc="chemical compound">
    NamespacedKey CHEMICAL_COMPOUND_KEY = KeyUtil.customKey("chemical_compound");

    /**
     * Returns the chemical compound of the item.
     *
     * @param itemStack the item stack to get the chemical compound from.
     * @return the chemical compound of the item.
     */
    default @Nullable ChemicalCompound getChemicalCompound(@NotNull ItemStack itemStack) {
        return IRDock.getRegistry().getChemicalCompound(PersistentDataAPI.get(itemStack.getItemMeta(), CHEMICAL_COMPOUND_KEY, PersistentDataType.STRING));
    }

    default void setChemicalCompound(@NotNull ItemStack itemStack, ChemicalCompound data) {
        itemStack.editMeta(meta -> {
            PersistentDataAPI.set(meta, CHEMICAL_COMPOUND_KEY, PersistentDataType.STRING, data.getName());
        });
    }
    //</editor-fold>

    //<editor-fold desc="mass">
    NamespacedKey MASS_KEY = KeyUtil.customKey("mass");

    /**
     * Returns the quality of each item.
     *
     * @return the quality of each item.
     */

    default double getMass(@NotNull ItemStack itemStack) {
        return PersistentDataAPI.getOrDefault(itemStack.getItemMeta(), MASS_KEY, PersistentDataType.DOUBLE, 0D);
    }

    default void setMass(@NotNull ItemStack itemStack, double data) {
        itemStack.editMeta(meta -> {
            PersistentDataAPI.set(meta, MASS_KEY, PersistentDataType.DOUBLE, data);
        });
    }
    //</editor-fold>

    //<editor-fold desc="seal">
    NamespacedKey SEAL_KEY = KeyUtil.customKey("seal");

    default SealStatus getSeal(@NotNull ItemStack itemStack) {
        return SealStatus.get(PersistentDataAPI.getOrDefault(itemStack.getItemMeta(), MASS_KEY, SealStatus.TYPE, SealStatus.DEFAULT));
    }

    default void setSeal(@NotNull ItemStack itemStack, SealStatus status) {
        itemStack.editMeta(meta -> {
            PersistentDataAPI.set(meta, MASS_KEY, SealStatus.TYPE, status.value());
        });
    }
    //</editor-fold>

    //<editor-fold desc="physical state"
    NamespacedKey PHYSICAL_STATE_KEY = KeyUtil.customKey("physical_state");

    default PhysicalState getPhysicalState(@NotNull ItemStack itemStack) {
        return PhysicalState.get(PersistentDataAPI.getOrDefault(itemStack.getItemMeta(), PHYSICAL_STATE_KEY, PhysicalState.TYPE, PhysicalState.DEFAULT));
    }

    default void setPhysicalState(@NotNull ItemStack itemStack, PhysicalState state) {
        itemStack.editMeta(meta -> {
            PersistentDataAPI.set(meta, PHYSICAL_STATE_KEY, PhysicalState.TYPE, state.value());
        });
    }
    //</editor-fold>

    //<editor-fold desc="capacity"
    NamespacedKey CAPACITY_KEY = KeyUtil.customKey("capacity");

    default double getCapacity(@NotNull ItemStack itemStack) {
        return PersistentDataAPI.getOrDefault(itemStack.getItemMeta(), CAPACITY_KEY, PersistentDataType.DOUBLE, 0D);
    }

    default void setCapacity(@NotNull ItemStack itemStack, double data) {
        itemStack.editMeta(meta -> {
            PersistentDataAPI.set(meta, CAPACITY_KEY, PersistentDataType.DOUBLE, data);
        });
    }
    //</editor-fold>

    //<editor-fold desc="container type"
    NamespacedKey CONTAINER_TYPE_KEY = KeyUtil.customKey("container_type");

    default ContainerType getContainerType(@NotNull ItemStack itemStack) {
        return IRDock.getRegistry().getContainerType(PersistentDataAPI.get(itemStack.getItemMeta(), CONTAINER_TYPE_KEY, PersistentDataTypes.NAMESPACED_KEY));
    }

    default  void setContainerType(@NotNull ItemStack itemStack, ContainerType containerType) {
        itemStack.editMeta(meta -> {
            PersistentDataAPI.set(meta, CONTAINER_TYPE_KEY, PersistentDataTypes.NAMESPACED_KEY, containerType.getKey());
        });
    }
    //</editor-fold>
}
