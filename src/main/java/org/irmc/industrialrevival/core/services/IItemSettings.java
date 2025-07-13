package org.irmc.industrialrevival.core.services;

import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.data.runtime.settings.ItemSetting;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IItemSettings {
    @NotNull YamlConfiguration getSettings();
    @NotNull YamlConfiguration getSettings(@NotNull IndustrialRevivalItem item);
    @NotNull YamlConfiguration createDefaultSettings(@NotNull IndustrialRevivalItem item);
    <T extends Comparable<T>> @NotNull List<ItemSetting<T>> getItemSettings(@NotNull IndustrialRevivalItem item);
    <T extends Comparable<T>> @NotNull T getItemSettings(@NotNull IndustrialRevivalItem item, @NotNull ItemSetting<T> setting);
    void setEnable(@NotNull IndustrialRevivalItem item, boolean enabled);
    void setEnchantable(@NotNull IndustrialRevivalItem item, boolean enchantable);
    void setDisenchantable(@NotNull IndustrialRevivalItem item, boolean disenchantable);
    void setHideInGuide(@NotNull IndustrialRevivalItem item, boolean hideInGuide);
    <T extends Comparable<T>> void setSettings(@NotNull IndustrialRevivalItem item, @NotNull ItemSetting<T> setting);
    void saveSettings();
}
