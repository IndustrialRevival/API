package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.dock.IRDock;
import org.jetbrains.annotations.NotNull;

/**
 * This utility class provides methods for creating custom {@link NamespacedKey} instances.
 * It simplifies the process of generating keys by allowing the use of a default namespace (the plugin's name)
 * or specifying a custom namespace.
 *
 * @author balugaq
 */
@UtilityClass
public class KeyUtil {

    /**
     * Creates a {@link NamespacedKey} using the default namespace (the plugin's name) and the specified key.
     *
     * @param key The key to use for the {@link NamespacedKey}.
     * @return A {@link NamespacedKey} with the plugin's namespace and the provided key.
     */
    public static @NotNull NamespacedKey customKey(@NotNull String key) {
        return new NamespacedKey(IRDock.getPlugin(), key.toLowerCase());
    }

    /**
     * Creates a new {@link NamespacedKey} by appending a suffix to an existing key.
     * <p>
     * This method takes an existing NamespacedKey and appends the specified string to its key part,
     * creating a new NamespacedKey with the same namespace but a modified key.
     * </p>
     *
     * @param key the existing NamespacedKey to append to
     * @param append the string to append to the key
     * @return a new NamespacedKey with the appended string
     */
    public static @NotNull NamespacedKey appendOnKey(@NotNull NamespacedKey key, @NotNull String append) {
        return new NamespacedKey(key.getNamespace(), key.getKey() + "_" + append.toLowerCase());
    }
}
