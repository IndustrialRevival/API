package org.irmc.industrialrevival.api.data.runtime.settings;

import lombok.Getter;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.dock.IRDock;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
@Getter
public abstract non-sealed class ComparableItemSetting<T extends Comparable<T>> extends ItemSetting<T> {
    private final @NotNull T minValue;
    private final @NotNull T maxValue;

    public ComparableItemSetting(@NotNull IndustrialRevivalItem item, @NotNull String key, @NotNull T defaultValue, @NotNull T minValue, @NotNull T maxValue) {
        super(item, key, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = IRDock.getItemSettings().getItemSettings(item, this);
        if (this.currentValue.compareTo(minValue) < 0) {
            this.currentValue = minValue;
        }

        if (this.currentValue.compareTo(maxValue) > 0) {
            this.currentValue = maxValue;
        }
    }
}
