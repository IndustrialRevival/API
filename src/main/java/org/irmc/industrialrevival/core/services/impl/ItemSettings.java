package org.irmc.industrialrevival.core.services.impl;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.data.runtime.settings.ItemSetting;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.core.services.IItemSettings;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.Constants;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Used to manage each items' settings in the `items-settings.yml` file.
 * <p>
 * This class implements the {@link IItemSettings} interface and provides complete item settings management functionality,
 * including reading, writing, and creating default configurations. The configuration file structure is organized
 * by item namespace and key.
 * </p>
 * <p>
 * Configuration file structure example:
 * <pre>
 * addon: // item id's namespace
 *   itemkey: // item id's key
 *     enable: true
 *     enchantable: true
 *     disenchantable: true
 *     hide_in_guide: false
 * </pre>
 * </p>
 *
 * @author lijinhong11
 * @author balugaq
 * @since 1.0
 * @see IItemSettings
 * @see IndustrialRevivalItem
 * @see ItemSetting
 */
@Getter
@ParametersAreNonnullByDefault
public class ItemSettings implements IItemSettings {
    
    /**
     * The YAML configuration object for item settings
     */
    private final YamlConfiguration itemCfg;

    /**
     * Creates a new ItemSettings instance.
     *
     * @param itemCfg the YAML configuration object for managing item settings
     */
    public ItemSettings(YamlConfiguration itemCfg) {
        this.itemCfg = itemCfg;
    }

    /**
     * Gets the item settings configuration file.
     *
     * @return the item settings configuration file object
     */
    @Override
    public @NotNull YamlConfiguration getCfg() {
        return itemCfg;
    }

    /**
     * Gets the configuration file for the specified item.
     *
     * @param item the item to get configuration for
     * @return the item settings configuration file object
     */
    @Override
    public @NotNull YamlConfiguration getCfg(@NotNull IndustrialRevivalItem item) {
        return itemCfg;
    }

    /**
     * Creates default configuration for the specified item.
     * <p>
     * If the item's configuration section doesn't exist in the config, it will be created with default values:
     * - enable: true
     * - enchantable: true
     * - disenchantable: true
     * - hide_in_guide: false
     * </p>
     *
     * @param item the item to create default configuration for
     * @return the updated configuration file object
     */
    @Override
    public @NotNull YamlConfiguration createDefaultCfg(@NotNull IndustrialRevivalItem item) {
        String namespace = item.getId().getNamespace();
        String key = item.getId().getKey();
        
        if (!itemCfg.contains(namespace)) {
            itemCfg.createSection(namespace);
        }
        
        ConfigurationSection addonSection = itemCfg.getConfigurationSection(namespace);
        if (!addonSection.contains(key)) {
            addonSection.createSection(key);
        }
        
        ConfigurationSection itemSection = addonSection.getConfigurationSection(key);
        if (!itemSection.contains("enable")) {
            itemSection.set("enable", true);
        }
        if (!itemSection.contains("enchantable")) {
            itemSection.set("enchantable", true);
        }
        if (!itemSection.contains("disenchantable")) {
            itemSection.set("disenchantable", true);
        }
        if (!itemSection.contains("hide_in_guide")) {
            itemSection.set("hide_in_guide", false);
        }
        
        return itemCfg;
    }

    /**
     * Gets the specific setting value for the specified item.
     * <p>
     * Reads the setting value from the configuration file. If the reading fails or the configuration
     * doesn't exist, returns the default value of the setting.
     * </p>
     *
     * @param item the item to get settings for
     * @param setting the setting object to get
     * @param <T> the type of the setting value
     * @return the setting value, or the default value if configuration doesn't exist or reading fails
     */
    @Override
    public <T> @NotNull T getItemSettings(IndustrialRevivalItem item, @NotNull ItemSetting<T> setting) {
        String namespace = item.getId().getNamespace();
        String key = item.getId().getKey();
        String settingKey = setting.getKey().getKey();
        
        ConfigurationSection itemSection = getItemSection(namespace, key);
        if (itemSection.contains(settingKey)) {
            String value = itemSection.getString(settingKey);
            try {
                return setting.read(value);
            } catch (IllegalArgumentException e) {
                return setting.getDefaultValue();
            }
        }
        
        return setting.getDefaultValue();
    }

    /**
     * Sets the enabled state for the specified item.
     *
     * @param item the item to set enabled state for
     * @param enabled whether to enable the item
     */
    @Override
    public void setEnable(@NotNull IndustrialRevivalItem item, boolean enabled) {
        String namespace = item.getId().getNamespace();
        String key = item.getId().getKey();
        
        ConfigurationSection itemSection = getItemSection(namespace, key);
        itemSection.set("enable", enabled);
    }

    /**
     * Sets the enchantable state for the specified item.
     *
     * @param item the item to set enchantable state for
     * @param enchantable whether enchantment is allowed
     */
    @Override
    public void setEnchantable(@NotNull IndustrialRevivalItem item, boolean enchantable) {
        String namespace = item.getId().getNamespace();
        String key = item.getId().getKey();
        
        ConfigurationSection itemSection = getItemSection(namespace, key);
        itemSection.set("enchantable", enchantable);
    }

    /**
     * Sets the disenchantable state for the specified item.
     *
     * @param item the item to set disenchantable state for
     * @param disenchantable whether disenchantment is allowed
     */
    @Override
    public void setDisenchantable(@NotNull IndustrialRevivalItem item, boolean disenchantable) {
        String namespace = item.getId().getNamespace();
        String key = item.getId().getKey();
        
        ConfigurationSection itemSection = getItemSection(namespace, key);
        itemSection.set("disenchantable", disenchantable);
    }

    /**
     * Sets the hide in guide state for the specified item.
     *
     * @param item the item to set hide state for
     * @param hideInGuide whether to hide the item in the guide
     */
    @Override
    public void setHideInGuide(@NotNull IndustrialRevivalItem item, boolean hideInGuide) {
        String namespace = item.getId().getNamespace();
        String key = item.getId().getKey();
        
        ConfigurationSection itemSection = getItemSection(namespace, key);
        itemSection.set("hide_in_guide", hideInGuide);
    }

    /**
     * Sets the general settings for the specified item.
     * <p>
     * Writes the current value of the setting object to the configuration file.
     * </p>
     *
     * @param item the item to set configuration for
     * @param setting the setting object to set
     * @param <T> the type of the setting value
     */
    @Override
    public <T> void setSettings(@NotNull IndustrialRevivalItem item, @NotNull ItemSetting<T> setting) {
        String namespace = item.getId().getNamespace();
        String key = item.getId().getKey();
        String settingKey = setting.getKey().getKey();
        
        ConfigurationSection itemSection = getItemSection(namespace, key);
        itemSection.set(settingKey, setting.write(setting.getCurrentValue()));
    }

    /**
     * Saves settings to the configuration file.
     * <p>
     * Writes the current in-memory configuration to the disk file. If an exception occurs
     * during saving, the exception stack trace will be printed.
     * </p>
     */
    @Override
    public void saveSettings() {
        try {
            itemCfg.save(Constants.Files.ITEM_SETTINGS_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets or creates the configuration section for the specified item.
     * <p>
     * If the item's configuration section doesn't exist in the config, it will be automatically created.
     * </p>
     *
     * @param namespace the item's namespace
     * @param key the item's key
     * @return the item's configuration section
     */
    private ConfigurationSection getItemSection(String namespace, String key) {
        if (!itemCfg.contains(namespace)) {
            itemCfg.createSection(namespace);
        }
        
        ConfigurationSection addonSection = itemCfg.getConfigurationSection(namespace);
        if (!addonSection.contains(key)) {
            addonSection.createSection(key);
        }
        
        return addonSection.getConfigurationSection(key);
    }

    /**
     * Gets the configuration section for the specified item ID.
     * <p>
     * This is a convenience method for getting an item's configuration section via {@link NamespacedKey}.
     * </p>
     *
     * @param itemId the item's namespaced key
     * @return the item's configuration section
     */
    public ConfigurationSection getSetting(NamespacedKey itemId) {
        return getItemSection(itemId.getNamespace(), itemId.getKey());
    }
}

/**
 * Configuration file template:
 * 
 * addon: // item id's namespace
 *   itemkey: // item id's key
 *     enable: true
 *     enchantable: true
 *     disenchantable: true
 *     hide_in_guide: false
 */
