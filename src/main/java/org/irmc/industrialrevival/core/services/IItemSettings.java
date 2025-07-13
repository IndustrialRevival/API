package org.irmc.industrialrevival.core.services;

import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.data.runtime.settings.ItemSetting;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IItemSettings {
    @NotNull YamlConfiguration getCfg();
    @NotNull YamlConfiguration getCfg(@NotNull IndustrialRevivalItem item);
    @NotNull YamlConfiguration createDefaultCfg(@NotNull IndustrialRevivalItem item);
    <T> @NotNull List<ItemSetting<T>> getItemSettings(@NotNull IndustrialRevivalItem item);
    <T> @NotNull T getItemSettings(@NotNull IndustrialRevivalItem item, @NotNull ItemSetting<T> setting);
    void setEnable(@NotNull IndustrialRevivalItem item, boolean enabled);
    void setEnchantable(@NotNull IndustrialRevivalItem item, boolean enchantable);
    void setDisenchantable(@NotNull IndustrialRevivalItem item, boolean disenchantable);
    void setHideInGuide(@NotNull IndustrialRevivalItem item, boolean hideInGuide);
    <T> void setSettings(@NotNull IndustrialRevivalItem item, @NotNull ItemSetting<T> setting);
    void saveSettings();
}
