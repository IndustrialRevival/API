package org.irmc.industrialrevival.api.data.runtime.settings;

import lombok.Data;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.dock.IRDock;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
@Data
public abstract sealed class ItemSetting<T> implements Keyed permits ComparableItemSetting, CustomSetting {
    protected @NotNull T currentValue;
    protected final @NotNull IndustrialRevivalItem item;
    protected final @NotNull String key;
    protected final @NotNull T defaultValue;
    protected final @NotNull NamespacedKey namespacedKey;

    public ItemSetting(@NotNull IndustrialRevivalItem item, @NotNull String key, @NotNull T defaultValue) {
        this.item = item;
        this.key = key;
        this.defaultValue = defaultValue;
        this.currentValue = IRDock.getItemSettings().getItemSettings(item, this);
        this.namespacedKey = new NamespacedKey(item.getAddon(), key);
    }

    public abstract @NotNull T read(@NotNull String value) throws IllegalArgumentException;
    public @NotNull String write(@NotNull T value) {
        return value.toString();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return namespacedKey;
    }
}
