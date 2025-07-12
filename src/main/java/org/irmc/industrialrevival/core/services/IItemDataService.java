package org.irmc.industrialrevival.core.services;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

/**
 * Service interface for managing custom item data in the IndustrialRevival plugin.
 * Provides methods to get and set various properties of item stacks.
 *
 * @author balugaq
 */
public interface IItemDataService {
    /**
     * Gets the NamespacedKey ID of an item stack if it exists.
     *
     * @param stack the item stack to check, may be null
     * @return an optional containing the NamespacedKey ID if present, empty otherwise
     */
    @Nullable Optional<NamespacedKey> getId(@Nullable ItemStack stack);

    /**
     * Sets the NamespacedKey ID of an item stack.
     *
     * @param stack the item stack to modify
     * @param id    the NamespacedKey ID to set
     */
    void setId(@NotNull ItemStack stack, @NotNull NamespacedKey id);

    /**
     * Gets the radiation level of an item stack if it exists.
     *
     * @param stack the item stack to check
     * @return an optional containing the radiation level if present, empty otherwise
     */
    @NotNull Optional<RadiationLevel> getRadiationLevel(@Nullable ItemStack stack);

    /**
     * Sets the radiation level of an item stack.
     *
     * @param stack          the item stack to modify
     * @param radiationLevel the radiation level to set
     */
    void setRadiationLevel(@NotNull ItemStack stack, @NotNull RadiationLevel radiationLevel);

    /**
     * Gets the custom model data of an item stack if it exists.
     *
     * @param stack the item stack to check, may be null
     * @return an optional containing the custom model data if present, empty otherwise
     */
    OptionalInt getCustomModelData(@Nullable ItemStack stack);

    /**
     * Sets the custom model data of an item stack.
     *
     * @param stack           the item stack to modify
     * @param customModelData the custom model data to set, may be null
     */
    void setCustomModelData(@NotNull ItemStack stack, Integer customModelData);

    /**
     * Gets the energy value of an item stack if it exists.
     *
     * @param stack the item stack to check, may be null
     * @return an optional containing the energy value if present, empty otherwise
     */
    OptionalDouble getEnergy(@Nullable ItemStack stack);

    /**
     * Sets the energy value of an item stack.
     *
     * @param stack  the item stack to modify
     * @param energy the energy value to set, may be null
     */
    void setEnergy(@NotNull ItemStack stack, Double energy);

    /**
     * Gets the maximum energy value of an item stack if it exists.
     *
     * @param stack the item stack to check, may be null
     * @return an optional containing the maximum energy value if present, empty otherwise
     */
    OptionalDouble getMaxEnergy(@Nullable ItemStack stack);

    /**
     * Sets the maximum energy value of an item stack.
     *
     * @param stack     the item stack to modify
     * @param maxEnergy the maximum energy value to set, may be null
     */
    void setMaxEnergy(@NotNull ItemStack stack, Double maxEnergy);
}