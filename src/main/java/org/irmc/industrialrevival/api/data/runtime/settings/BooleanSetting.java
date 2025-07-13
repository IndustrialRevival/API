package org.irmc.industrialrevival.api.data.runtime.settings;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public class BooleanSetting extends ComparableItemSetting<Boolean> {
    public BooleanSetting(@NotNull IndustrialRevivalItem item, @NotNull String key, @NotNull Boolean defaultValue, @NotNull Boolean minValue, @NotNull Boolean maxValue) {
        super(item, key, defaultValue, minValue, maxValue);
    }

    @Override
    public @NotNull Boolean read(@NotNull String value) {
        return Boolean.parseBoolean(value);
    }
}
