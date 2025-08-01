package org.irmc.industrialrevival.core.services;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.irmc.pigeonlib.enums.Language;
import org.irmc.pigeonlib.language.MessageReplacement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Language manager interface for handling multi-language support
 * 
 * @author balugaq
 */
public interface ILanguageManager {
    Language DEFAULT_LANGUAGE = Language.ZH_CN;
    String SCOPE_ITEM = "item.";
    String SCOPE_RECIPE_TYPE = "recipe_type.";
    String SCOPE_GROUP = "group.";
    String SCOPE_MESSAGE = "message.";
    String NAME = ".name";
    String LORE = ".lore";
    String FOLDER_LANGUAGE = "languages/";
    String CONFIG_LANGUAGE = "language";
    
    /**
     * Get the name of an item by its ID
     *
     * @param id The item ID
     * @return The item name component
     */
    @NotNull
    Component getItemName(@NotNull String id);

    /**
     * Get the lore of an item by its ID
     *
     * @param id The item ID
     * @return The item lore component list
     */
    @NotNull
    List<Component> getItemLore(@NotNull String id);

    /**
     * Get the name of a recipe type by its key
     *
     * @param key The recipe type key
     * @return The recipe type name component
     */
    @NotNull
    Component getRecipeTypeName(@NotNull NamespacedKey key);

    /**
     * Get the lore of a recipe type by its key
     *
     * @param key The recipe type key
     * @return The recipe type lore component list
     */
    @NotNull
    List<Component> getRecipeTypeLore(@NotNull NamespacedKey key);

    /**
     * Get the name of a group by its ID
     *
     * @param id The group ID
     * @return The group name component
     */
    @NotNull
    Component getGroupName(@NotNull String id);

    /**
     * Get the lore of a group by its ID
     *
     * @param id The group ID
     * @return The group lore component list
     */
    @NotNull
    List<Component> getGroupLore(@NotNull String id);

    /**
     * Get the name of an item by its ID in a specific language
     *
     * @param lang The language
     * @param id   The item ID
     * @return The item name component
     */
    @NotNull
    Component getItemNameByLanguage(@NotNull Language lang, @NotNull String id);

    /**
     * Get the lore of an item by its ID in a specific language
     *
     * @param lang The language
     * @param id   The item ID
     * @return The item lore component list
     */
    @NotNull List<Component> getItemLoreByLanguage(@NotNull Language lang, @NotNull String id);

    /**
     * Get the name of a recipe type by its key in a specific language
     *
     * @param lang The language
     * @param key  The recipe type key
     * @return The recipe type name component
     */
    @NotNull Component getRecipeTypeNameByLanguage(@NotNull Language lang, @NotNull NamespacedKey key);

    /**
     * Get the lore of a recipe type by its key in a specific language
     *
     * @param lang The language
     * @param key  The recipe type key
     * @return The recipe type lore component list
     */
    @NotNull List<Component> getRecipeTypeLoreByLanguage(@NotNull Language lang, @NotNull NamespacedKey key);

    /**
     * Get the name of a group by its ID in a specific language
     *
     * @param lang The language
     * @param id   The group ID
     * @return The group name component
     */
    @NotNull
    Component getGroupNameByLanguage(@NotNull Language lang, @NotNull String id);

    /**
     * Get the lore of a group by its ID in a specific language
     *
     * @param lang The language
     * @param id   The group ID
     * @return The group lore component list
     */
    @NotNull
    List<Component> getGroupLoreByLanguage(@NotNull Language lang, @NotNull String id);

    /**
     * Send a message to a command sender
     *
     * @param commandSender The command sender
     * @param key           The message key
     * @param args          The message arguments
     */
    void sendMessage(@NotNull CommandSender commandSender, @NotNull String key, @NotNull MessageReplacement @NotNull ... args);

    /**
     * Send a message to the console
     *
     * @param key  The message key
     * @param args The message arguments
     */
    void consoleMessage(@NotNull String key, @NotNull MessageReplacement @NotNull ... args);

    /**
     * Get a component message
     *
     * @param commandSender The command sender
     * @param key           The message key
     * @param args          The message arguments
     * @return The message component
     */
    @NotNull
    Component getComponent(@Nullable CommandSender commandSender, @NotNull String key, @NotNull MessageReplacement @NotNull ... args);

    /**
     * Get a component message in a specific language
     *
     * @param lang The language
     * @param key  The message key
     * @param args The message arguments
     * @return The message component
     */
    @NotNull
    Component getComponentByLanguage(@Nullable Language lang, @NotNull String key, @NotNull MessageReplacement @NotNull ... args);

    /**
     * Get a list of component messages
     *
     * @param commandSender The command sender
     * @param key           The message key
     * @param args          The message arguments
     * @return The message component list
     */
    @NotNull
    List<Component> getComponentList(@Nullable CommandSender commandSender, @NotNull String key, @NotNull MessageReplacement @NotNull ... args);

    /**
     * Get a list of component messages in a specific language
     *
     * @param lang The language
     * @param key  The message key
     * @param args The message arguments
     * @return The message component list
     */
    @NotNull
    List<Component> getComponentListByLanguage(@Nullable Language lang, @NotNull String key, @NotNull MessageReplacement @NotNull ... args);

    /**
     * Get a string message
     *
     * @param commandSender The command sender
     * @param key           The message key
     * @param args          The message arguments
     * @return The message string
     */
    @NotNull
    String getString(@Nullable CommandSender commandSender, @NotNull String key, @NotNull MessageReplacement @NotNull ... args);

    /**
     * Get a list of string messages
     *
     * @param commandSender The command sender
     * @param key           The message key
     * @param args          The message arguments
     * @return The message string list
     */
    @NotNull
    List<String> getStringList(@Nullable CommandSender commandSender, @NotNull String key, @NotNull MessageReplacement @NotNull ... args);

    /**
     * Get a string message in a specific language
     *
     * @param lang The language
     * @param key  The message key
     * @param args The message arguments
     * @return The message string
     */
    @NotNull
    String getByLanguage(@Nullable Language lang, @NotNull String key, @NotNull MessageReplacement @NotNull ... args);

    /**
     * Get a list of string messages in a specific language
     *
     * @param lang The language
     * @param key  The message key
     * @param args The message arguments
     * @return The message string list
     */
    @NotNull
    List<String> getStringListByLanguage(@Nullable Language lang, @NotNull String key, @NotNull MessageReplacement @NotNull ... args);

    /**
     * Reload the language configurations
     */
    void reload();

    /**
     * Get a configuration section
     *
     * @param key The configuration key
     * @return The configuration section
     */
    @NotNull
    ConfigurationSection getSection(@NotNull String key);

    /**
     * Get a configuration section for a specific command sender
     *
     * @param p   The command sender
     * @param key The configuration key
     * @return The configuration section
     */
    @NotNull
    ConfigurationSection getSection(@NotNull CommandSender p, @NotNull String key);
}
