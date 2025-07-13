package org.irmc.industrialrevival.api.data.runtime.settings;

import lombok.Data;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.dock.IRDock;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
@Data
public abstract class ItemSetting<T extends Comparable<T>> {
    private @NotNull T currentValue;
    private final @NotNull IndustrialRevivalItem item;
    private final @NotNull String key;
    private final @NotNull T defaultValue;
    private final @NotNull T minValue;
    private final @NotNull T maxValue;

    public ItemSetting(@NotNull IndustrialRevivalItem item, @NotNull String key, @NotNull T defaultValue, @NotNull T minValue, @NotNull T maxValue) {
        this.item = item;
        this.key = key;
        this.defaultValue = defaultValue;
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

    public abstract @NotNull T read(@NotNull String value) throws IllegalArgumentException;
}
