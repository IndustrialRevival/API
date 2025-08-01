package org.irmc.industrialrevival.core.managers;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.core.services.ILanguageManager;
import org.irmc.pigeonlib.enums.Language;
import org.irmc.pigeonlib.file.ConfigFileUtil;
import org.irmc.pigeonlib.language.MessageReplacement;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.jar.JarFile;

/**
 * @author lijinhong11
 * @author balugaq
 */
@Getter
public final class LanguageManager implements ILanguageManager {
    private final JavaPlugin plugin;
    private final Language defaultLanguage;
    private final Map<Language, YamlConfiguration> configurations;
    private boolean detectPlayerLocale;
    private YamlConfiguration defaultConfiguration;

    public LanguageManager(JavaPlugin plugin) {
        this(plugin, Language.ZH_CN);
    }

    public LanguageManager(JavaPlugin plugin, Language defaultLanguage) {
        this.configurations = new HashMap<>();
        this.plugin = plugin;
        this.defaultLanguage = defaultLanguage;
        loadLanguages();
    }

    public static Component parseToComponent(String msg) {
        return MiniMessage.miniMessage().deserialize(msg).decoration(TextDecoration.ITALIC, false);
    }

    public static List<Component> parseToComponentList(List<String> msgList) {
        return msgList.stream().map(LanguageManager::parseToComponent).toList();
    }

    public static Language toLanguage(Locale locale) {
        return toLanguage(locale.toLanguageTag());
    }

    public static Language toLanguage(String lang) {
        for (Language l : Language.values()) {
            if (l.toTagRegionUpper().equalsIgnoreCase(lang)) {
                return l;
            }
        }

        return DEFAULT_LANGUAGE;
    }

    private void loadLanguages() {
        detectPlayerLocale = plugin.getConfig().getBoolean("detect-player-locale", true);

        File pluginFolder = this.plugin.getDataFolder();
        String jarPath = plugin.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        try (JarFile jarFile = new JarFile(jarPath)) {

            jarFile.stream()
                    .filter(entry -> {
                        String name = entry.getName();
                        return name.startsWith(FOLDER_LANGUAGE) && !name.endsWith("/") && name.endsWith(".yml");
                    })
                    .map(entry -> {
                        try (InputStream is = plugin.getResource(entry.getName())) {
                            if (is != null) {
                                if (!YamlConfiguration.loadConfiguration(new InputStreamReader(is)).getKeys(false).isEmpty()) {
                                    return entry.getRealName();
                                }
                            }
                        } catch (Exception e) {
                            plugin.getLogger().severe("Failed to load YAML: " + entry.getName());
                            return null;
                        }

                        return null;
                    })
                    .filter(Objects::nonNull)
                    .forEach(fileName -> ConfigFileUtil.completeLangFile(plugin, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File[] languageFiles = (new File(pluginFolder, CONFIG_LANGUAGE)).listFiles();
        if (languageFiles != null) {
            for (File languageFile : languageFiles) {
                Language language = convertLang(languageFile.getName().replace(".yml", ""));
                YamlConfiguration config = YamlConfiguration.loadConfiguration(languageFile);
                configurations.put(language, config);
                if (language == Language.ZH_CN) {
                    defaultConfiguration = config;
                }
            }
        }

    }

    private Language convertLang(String unchecked) {
        if (unchecked != null && !unchecked.isBlank()) {
            String[] split = unchecked.split("-");
            if (split.length == 1) {
                String[] split2 = unchecked.split("_");
                return toLanguage(split2.length == 1 ? unchecked : unchecked.replace(split2[1], split2[1].toUpperCase()));
            } else {
                return toLanguage(unchecked.replace(split[1], split[1].toUpperCase()));
            }
        } else {
            return Language.ZH_CN;
        }
    }

    public Component getItemName(String id) {
        return parseToComponent(getString(null, SCOPE_ITEM + id + NAME));
    }

    public List<Component> getItemLore(String id) {
        return parseToComponentList(getStringList(null, SCOPE_ITEM + id + LORE));
    }

    public Component getRecipeTypeName(NamespacedKey key) {
        return parseToComponent(getString(null, SCOPE_RECIPE_TYPE + key.getKey() + NAME));
    }

    public List<Component> getRecipeTypeLore(NamespacedKey key) {
        return parseToComponentList(getStringList(null, SCOPE_RECIPE_TYPE + key.getKey() + LORE));
    }

    public Component getGroupName(String id) {
        return parseToComponent(getString(null, SCOPE_GROUP + id + NAME));
    }

    public List<Component> getGroupLore(String id) {
        return parseToComponentList(getStringList(null, SCOPE_GROUP + id + LORE));
    }

    public Component getItemNameByLanguage(Language lang, String id) {
        return parseToComponent(getByLanguage(lang, SCOPE_ITEM + id + NAME));
    }

    public List<Component> getItemLoreByLanguage(Language lang, String id) {
        return parseToComponentList(getStringListByLanguage(lang, SCOPE_ITEM + id + LORE));
    }

    public Component getRecipeTypeNameByLanguage(Language lang, NamespacedKey key) {
        return parseToComponent(getByLanguage(lang, SCOPE_RECIPE_TYPE + key.getKey() + NAME));
    }

    public List<Component> getRecipeTypeLoreByLanguage(Language lang, NamespacedKey key) {
        return parseToComponentList(getStringListByLanguage(lang, SCOPE_RECIPE_TYPE + key.getKey() + LORE));
    }

    public Component getGroupNameByLanguage(Language lang, String id) {
        return parseToComponent(getByLanguage(lang, SCOPE_GROUP + id + NAME));
    }

    public List<Component> getGroupLoreByLanguage(Language lang, String id) {
        return parseToComponentList(getStringListByLanguage(lang, SCOPE_GROUP + id + LORE));
    }

    public void sendMessage(CommandSender CommandSender, String key, MessageReplacement... args) {
        CommandSender.sendMessage(parseToComponent(getString(CommandSender, key, args)));
    }

    public void consoleMessage(String key, MessageReplacement... args) {
        Bukkit.getConsoleSender().sendMessage(parseToComponent(getString(null, key, args)));
    }

    public Component getComponent(@Nullable CommandSender commandSender, String key, MessageReplacement... args) {
        return parseToComponent(getString(commandSender, key, args));
    }

    public Component getComponentByLanguage(@Nullable Language lang, String key, MessageReplacement... args) {
        return parseToComponent(getByLanguage(lang, key, args));
    }

    public List<Component> getComponentList(@Nullable CommandSender CommandSender, String key, MessageReplacement... args) {
        return parseToComponentList(getStringList(CommandSender, key, args));
    }

    public List<Component> getComponentListByLanguage(@Nullable Language lang, String key, MessageReplacement... args) {
        return parseToComponentList(getStringListByLanguage(lang, key, args));
    }

    public String getString(@Nullable CommandSender commandSender, String key, MessageReplacement... args) {
        String msg = getConfiguration(commandSender).getString(key);
        if (msg == null) {
            return key;
        } else {
            for (MessageReplacement arg : args) {
                msg = arg.parse(msg);
            }

            return msg;
        }
    }

    public List<String> getStringList(@Nullable CommandSender commandSender, String key, MessageReplacement... args) {
        List<String> msgList = getConfiguration(commandSender).getStringList(key);

        for (MessageReplacement arg : args) {
            Objects.requireNonNull(arg);
            msgList.replaceAll(arg::parse);
        }

        return msgList;
    }

    public String getByLanguage(@Nullable Language lang, String key, MessageReplacement... args) {
        String msg = getConfiguration(lang).getString(key);
        if (msg == null) {
            return key;
        } else {
            for (MessageReplacement arg : args) {
                msg = arg.parse(msg);
            }

            return msg;
        }
    }

    public List<String> getStringListByLanguage(@Nullable Language lang, String key, MessageReplacement... args) {
        List<String> msgList = getConfiguration(lang).getStringList(key);

        for (MessageReplacement arg : args) {
            Objects.requireNonNull(arg);
            msgList.replaceAll(arg::parse);
        }

        return msgList;
    }

    public void reload() {
        loadLanguages();
    }

    private Configuration getConfiguration(CommandSender p) {
        if (detectPlayerLocale && p instanceof Player pl) {
            return configurations.getOrDefault(toLanguage(pl.locale()), defaultConfiguration);
        } else {
            String lang = plugin.getConfig().getString("language", defaultLanguage.toTagRegionUpper());
            return configurations.getOrDefault(toLanguage(lang), defaultConfiguration);
        }
    }

    public ConfigurationSection getSection(String key) {
        return getSection(null, key);
    }

    public ConfigurationSection getSection(CommandSender p, String key) {
        if (detectPlayerLocale && p instanceof Player pl) {
            return configurations.getOrDefault(toLanguage(pl.locale()), defaultConfiguration).getConfigurationSection(key);
        } else {
            String lang = plugin.getConfig().getString(CONFIG_LANGUAGE, defaultLanguage.toTagRegionUpper());
            return configurations.getOrDefault(toLanguage(lang), defaultConfiguration).getConfigurationSection(key);
        }
    }

    private Configuration getConfiguration(Language lang) {
        return configurations.getOrDefault(lang, defaultConfiguration);
    }
}
