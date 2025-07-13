package org.irmc.industrialrevival.api.data.runtime.settings;

import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public class NamespacedKeySetting extends CustomSetting<NamespacedKey> {
    public NamespacedKeySetting(@NotNull IndustrialRevivalItem item, @NotNull String key, @NotNull NamespacedKey defaultValue) {
        super(item, key, defaultValue);
    }

    @Override
    public @NotNull NamespacedKey read(@NotNull String value) throws IllegalArgumentException {
        NamespacedKey result = NamespacedKey.fromString(value);
        if (result == null) {
            throw new IllegalArgumentException("Invalid namespaced key: " + value);
        }
        return result;
    }
}
