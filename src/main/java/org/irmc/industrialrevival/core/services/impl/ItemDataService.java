package org.irmc.industrialrevival.core.services.impl;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.irmc.industrialrevival.core.services.IItemDataService;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class ItemDataService implements IItemDataService {
    public Optional<NamespacedKey> getId(ItemStack stack) {
        if (stack == null) {
            return Optional.empty();
        }

        if (!stack.hasItemMeta() || stack.getItemMeta() == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(
                PersistentDataAPI.getNamespacedKey(stack.getItemMeta(), Constants.ItemStackKeys.ITEM_ID_KEY)
        );
    }

    public void setId(ItemStack stack, NamespacedKey id) {
        if (stack == null) {
            return;
        }

        ItemMeta meta = stack.getItemMeta();
        PersistentDataAPI.set(meta, Constants.ItemStackKeys.ITEM_ID_KEY, PersistentDataType.STRING, id.toString());
        stack.setItemMeta(meta);
    }

    public Optional<RadiationLevel> getRadiationLevel(ItemStack stack) {
        if (stack == null) {
            return Optional.empty();
        }

        if (!stack.hasItemMeta() || stack.getItemMeta() == null) {
            return Optional.empty();
        }
        return Optional.of(RadiationLevel.valueOf(stack.getItemMeta()
                .getPersistentDataContainer()
                .get(Constants.ItemStackKeys.RADIATION_LEVEL_KEY, PersistentDataType.STRING)));
    }

    public void setRadiationLevel(ItemStack stack, RadiationLevel radiationLevel) {
        if (stack == null) {
            return;
        }

        ItemMeta meta = stack.getItemMeta();
        if (meta == null) {
            return;
        }

        DataUtil.setPDC(meta, Constants.ItemStackKeys.RADIATION_LEVEL_KEY, radiationLevel.name());
        stack.setItemMeta(meta);
    }

    @Override
    public OptionalInt getCustomModelData(@Nullable ItemStack stack) {
        if (stack == null || stack.getType() == Material.AIR) {
            return OptionalInt.empty();
        }

        ItemMeta meta = stack.getItemMeta();
        if (meta == null) {
            return OptionalInt.empty();
        }

        return OptionalInt.of(meta.getCustomModelData());
    }

    @Override
    public void setCustomModelData(@NotNull ItemStack stack, Integer customModelData) {
        ItemMeta meta = stack.getItemMeta();
        meta.setCustomModelData(customModelData);
        stack.setItemMeta(meta);
    }

    @Override
    public OptionalDouble getEnergy(@Nullable ItemStack stack) {
        if (stack == null) {
            return OptionalDouble.empty();
        }

        ItemMeta meta = stack.getItemMeta();
        if (meta == null) {
            return OptionalDouble.empty();
        }

        double value = PersistentDataAPI.getDouble(meta, Constants.ItemStackKeys.ENERGY_KEY);
        return OptionalDouble.of(value);
    }

    @Override
    public void setEnergy(@NotNull ItemStack stack, Double energy) {
        ItemMeta meta = stack.getItemMeta();
        PersistentDataAPI.setDouble(meta, Constants.ItemStackKeys.ENERGY_KEY, energy);
        stack.setItemMeta(meta);
    }

    @Override
    public OptionalDouble getMaxEnergy(@Nullable ItemStack stack) {
        if (stack == null) {
            return OptionalDouble.empty();
        }

        ItemMeta meta = stack.getItemMeta();
        if (meta == null) {
            return OptionalDouble.empty();
        }

        double value = PersistentDataAPI.getDouble(meta, Constants.ItemStackKeys.MAX_ENERGY_KEY);
        return OptionalDouble.of(value);
    }

    @Override
    public void setMaxEnergy(@NotNull ItemStack stack, Double maxEnergy) {
        ItemMeta meta = stack.getItemMeta();
        PersistentDataAPI.set(meta, Constants.ItemStackKeys.MAX_ENERGY_KEY, PersistentDataType.DOUBLE, maxEnergy);
        stack.setItemMeta(meta);
    }
}
