package org.irmc.industrialrevival.api;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.pigeonlib.enums.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

/**
 * Interface representing an addon for the IndustrialRevival plugin.
 * Provides common functionality and metadata for addons.
 *
 * @author lijinhong11
 */
public interface IndustrialRevivalAddon extends Plugin {
    /**
     * Returns the translation key for this addon.
     * The translation key is typically derived from the plugin name.
     *
     * @return the translation key
     */
    default String translateKey() {
        return getPlugin().getName().toLowerCase();
    }

    /**
     * Returns the underlying JavaPlugin instance.
     *
     * @return the JavaPlugin instance
     */
    @NotNull JavaPlugin getPlugin();

    /**
     * Returns the URL of the issue tracker for this addon, if available.
     *
     * @return the issue tracker URL or null if not available
     */
    @Nullable String getIssueTrackerURL();

    /**
     * Returns the version of this addon.
     * Defaults to the version from the plugin description.
     *
     * @return the version string
     */
    default @NotNull String getVersion() {
        return getPlugin().getDescription().getVersion();
    }

    /**
     * Returns the name of this addon.
     * Defaults to the name from the plugin description.
     *
     * @return the addon name
     */
    default @NotNull String getName() {
        return getPlugin().getDescription().getName();
    }

    /**
     * Returns the logger instance for this addon.
     * Defaults to the plugin's logger.
     *
     * @return the logger instance
     */
    default @NotNull Logger getLogger() {
        return getPlugin().getLogger();
    }

    /**
     * Returns the default language for this addon.
     * Defaults to English (United States).
     *
     * @return the default language
     */
    default @NotNull Language getDefaultLanguage() {
        return Language.EN_US;
    }
}