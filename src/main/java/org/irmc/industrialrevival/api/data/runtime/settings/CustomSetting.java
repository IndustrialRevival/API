package org.irmc.industrialrevival.api.data.runtime.settings;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public abstract non-sealed class CustomSetting<T> extends ItemSetting<T> {
    public CustomSetting(@NotNull IndustrialRevivalItem item, @NotNull String key, @NotNull T defaultValue) {
        super(item, key, defaultValue);
    }
}
