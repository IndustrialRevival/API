package org.irmc.industrialrevival.api.data.runtime.settings;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public class IntegerSetting extends ComparableItemSetting<Integer> {
    public IntegerSetting(@NotNull IndustrialRevivalItem item, @NotNull String key, @NotNull Integer defaultValue, @NotNull Integer minValue, @NotNull Integer maxValue) {
        super(item, key, defaultValue, minValue, maxValue);
    }

    @Override
    public @NotNull Integer read(@NotNull String value) throws IllegalArgumentException {
        return Integer.parseInt(value);
    }
}
