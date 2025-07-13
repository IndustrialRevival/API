package org.irmc.industrialrevival.api.data.runtime.settings;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public class LongSetting extends ComparableItemSetting<Long> {
    public LongSetting(@NotNull IndustrialRevivalItem item, @NotNull String key, @NotNull Long defaultValue, @NotNull Long minValue, @NotNull Long maxValue) {
        super(item, key, defaultValue, minValue, maxValue);
    }

    @Override
    public @NotNull Long read(@NotNull String value) throws IllegalArgumentException {
        return Long.parseLong(value);
    }
}
