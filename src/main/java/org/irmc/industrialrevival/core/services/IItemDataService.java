package org.irmc.industrialrevival.core.services;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface IItemDataService {
    @Nullable Optional<NamespacedKey> getId(@Nullable ItemStack stack);
    void setId(@NotNull ItemStack stack, @NotNull NamespacedKey id);
    @NotNull Optional<RadiationLevel> getRadiationLevel(@Nullable ItemStack stack);
    void setRadiationLevel(@NotNull ItemStack stack, @NotNull RadiationLevel radiationLevel);
    OptionalInt getCustomModelData(@Nullable ItemStack stack);
    void setCustomModelData(@NotNull ItemStack stack, int customModelData);
    OptionalDouble getEnergy(@Nullable ItemStack stack);
    void setEnergy(@NotNull ItemStack stack, double energy);
    OptionalDouble getMaxEnergy(@Nullable ItemStack stack);
    void setMaxEnergy(@NotNull ItemStack stack, double maxEnergy);
}
