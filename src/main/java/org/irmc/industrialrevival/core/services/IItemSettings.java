package org.irmc.industrialrevival.core.services;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.data.runtime.settings.ItemSetting;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Interface for managing item settings in the IndustrialRevival plugin.
 * <p>
 * This interface defines the contract for item settings management functionality,
 * including reading and writing configuration values, creating default configurations,
 * and saving settings to persistent storage. Implementations should handle the
 * configuration file structure and provide type-safe access to item settings.
 * </p>
 * <p>
 * The configuration is organized by item namespace and key, allowing for
 * hierarchical organization of settings for different addons and items.
 * </p>
 *
 * @author balugaq
 * @since 1.0
 * @see IndustrialRevivalItem
 * @see ItemSetting
 */
public interface IItemSettings {
    
    /**
     * Gets the main configuration file for item settings.
     *
     * @return the YAML configuration object containing all item settings
     */
    @NotNull YamlConfiguration getCfg();
    
    /**
     * Gets the configuration file for the specified item.
     * <p>
     * This method provides access to the configuration file that contains
     * settings for the specified item. The returned configuration may be
     * the same as the main configuration or a specific section.
     * </p>
     *
     * @param item the item to get configuration for
     * @return the YAML configuration object for the specified item
     */
    @NotNull ConfigurationSection getCfg(@NotNull IndustrialRevivalItem item);
    
    /**
     * Creates default configuration for the specified item.
     * <p>
     * This method ensures that the specified item has a configuration section
     * with default values. If the configuration section doesn't exist, it will
     * be created with appropriate default settings.
     * </p>
     *
     * @param item the item to create default configuration for
     * @return the updated YAML configuration object
     */
    @NotNull ConfigurationSection createDefaultCfg(@NotNull IndustrialRevivalItem item);
    
    /**
     * Gets the specific setting value for the specified item.
     * <p>
     * Retrieves the value of a specific setting for the given item. If the
     * setting doesn't exist in the configuration, the default value from
     * the setting object should be returned.
     * </p>
     *
     * @param item the item to get settings for
     * @param setting the setting object to retrieve
     * @param <T> the type of the setting value
     * @return the setting value, or the default value if not configured
     */
    <T> @NotNull T getItemSettings(@NotNull IndustrialRevivalItem item, @NotNull ItemSetting<T> setting);
    
    /**
     * Sets the enabled state for the specified item.
     * <p>
     * Controls whether the item is enabled or disabled in the plugin.
     * Disabled items may not function normally or may be hidden from
     * various interfaces.
     * </p>
     *
     * @param item the item to set enabled state for
     * @param enabled whether to enable the item
     */
    void setEnable(@NotNull IndustrialRevivalItem item, boolean enabled);
    
    /**
     * Sets the enchantable state for the specified item.
     * <p>
     * Controls whether the item can receive enchantments through
     * vanilla Minecraft enchanting mechanics.
     * </p>
     *
     * @param item the item to set enchantable state for
     * @param enchantable whether enchantment is allowed
     */
    void setEnchantable(@NotNull IndustrialRevivalItem item, boolean enchantable);
    
    /**
     * Sets the disenchantable state for the specified item.
     * <p>
     * Controls whether enchantments can be removed from the item
     * through vanilla Minecraft disenchanting mechanics.
     * </p>
     *
     * @param item the item to set disenchantable state for
     * @param disenchantable whether disenchantment is allowed
     */
    void setDisenchantable(@NotNull IndustrialRevivalItem item, boolean disenchantable);
    
    /**
     * Sets the hide in guide state for the specified item.
     * <p>
     * Controls whether the item should be hidden from the plugin's
     * guide interface. Hidden items will not appear in item lists
     * or search results within the guide.
     * </p>
     *
     * @param item the item to set hide state for
     * @param hideInGuide whether to hide the item in the guide
     */
    void setHideInGuide(@NotNull IndustrialRevivalItem item, boolean hideInGuide);
    
    /**
     * Sets the general settings for the specified item.
     * <p>
     * Updates the configuration with the current value of the specified
     * setting object. The setting value will be written to the configuration
     * file in the appropriate format.
     * </p>
     *
     * @param item the item to set configuration for
     * @param setting the setting object to update
     * @param <T> the type of the setting value
     */
    <T> void setSettings(@NotNull IndustrialRevivalItem item, @NotNull ItemSetting<T> setting);
    
    /**
     * Saves all current settings to persistent storage.
     * <p>
     * Writes the current in-memory configuration to the disk file.
     * This method should be called after making changes to ensure
     * that modifications are persisted between server restarts.
     * </p>
     */
    void saveSettings();
}
