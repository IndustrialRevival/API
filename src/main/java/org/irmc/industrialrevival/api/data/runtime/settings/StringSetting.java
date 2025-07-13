package org.irmc.industrialrevival.api.data.runtime.settings;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public class StringSetting extends ItemSetting<String> {
    public StringSetting(@NotNull IndustrialRevivalItem item, @NotNull String key, @NotNull String defaultValue, @NotNull String minValue, @NotNull String maxValue) {
        super(item, key, defaultValue, minValue, maxValue);
    }

    @Override
    public @NotNull String read(@NotNull String value) throws IllegalArgumentException {
        return value;
    }
}
