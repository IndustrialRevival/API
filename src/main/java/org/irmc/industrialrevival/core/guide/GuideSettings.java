package org.irmc.industrialrevival.core.guide;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.player.GuideSetting;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.industrialrevival.utils.TextUtil;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author balugaq
 */
@Getter
@Setter
public class GuideSettings {
    public static final NamespacedKey KEY_GUIDE_MODE = KeyUtil.customKey("guide_mode");
    public static final NamespacedKey KEY_FIREWORKS_ENABLED = KeyUtil.customKey("fireworks_enabled");
    public static final NamespacedKey KEY_LEARNING_ANIMATION_ENABLED = KeyUtil.customKey("learning_animation_enabled");
    public static final NamespacedKey KEY_LANGUAGE = KeyUtil.customKey("language");

    public static final GuideSetting<GuideMode> GUIDE_MODE = GuideSetting.constof(
            Player::isOp,
            KEY_GUIDE_MODE,
            GuideMode.SURVIVAL,
            mode -> new CustomItemStack(
                    Material.BOOK,
                    TextUtil.upperFirstLetterOnly(mode.name()) + " Guide",
                    "Survival Guide"
            ).getBukkit());

    public static final GuideSetting<Boolean> FIREWORKS_ENABLED = GuideSetting.constof(
            KEY_FIREWORKS_ENABLED,
            true,
            enabled -> new CustomItemStack(
                    Material.FIREWORK_STAR,
                    "Fireworks",
                    "Fireworks: " + TextUtil.getBooleanText(enabled)
            ).getBukkit());

    public static final GuideSetting<Boolean> LEARNING_ANIMATION_ENABLED = GuideSetting.constof(
            KEY_LEARNING_ANIMATION_ENABLED,
            true,
            enabled -> new CustomItemStack(
                    Material.BOOK,
                    "Learning Animation",
                    "Learning Animation: " + TextUtil.getBooleanText(enabled)
            ).getBukkit());

    public static final GuideSetting<String> LANGUAGE = GuideSetting.constof(
            KEY_LANGUAGE,
            Locale.getDefault().toLanguageTag(),
            language -> new CustomItemStack(
                    Material.BOOK,
                    "Language",
                    "Language: " + language).getBukkit());

    public static final GuideSettings DEFAULT_SETTINGS =
            GuideSettings.of(
                    GUIDE_MODE.clone(),
                    FIREWORKS_ENABLED.clone(),
                    LEARNING_ANIMATION_ENABLED.clone(),
                    LANGUAGE.clone()
            );

    public final Map<NamespacedKey, GuideSetting<?>> settings;

    public GuideSettings(Map<NamespacedKey, GuideSetting<?>> settings) {
        this.settings = settings;
    }

    public static @NotNull GuideSettings of(Map<NamespacedKey, GuideSetting<?>> settings) {
        return new GuideSettings(settings);
    }

    public static @NotNull GuideSettings of(GuideSetting<?> @NotNull ... settings) {
        Map<NamespacedKey, GuideSetting<?>> map = new HashMap<>();
        for (GuideSetting<?> setting : settings) {
            map.put(setting.getKey(), setting);
        }

        return of(map);
    }

    public <T> GuideSetting<T> getPlayerSettings(@NotNull GuideSetting<T> clazz) {
        return (GuideSetting<T>) settings.get(clazz.getKey());
    }

    public <T> GuideSetting<T> getPlayerSettings(NamespacedKey key) {
        return (GuideSetting<T>) settings.get(key);
    }

    public <T> void setGuideSettings(@NotNull GuideSetting<T> clazz, T value) {
        getPlayerSettings(clazz).setValue(value);
    }
}
