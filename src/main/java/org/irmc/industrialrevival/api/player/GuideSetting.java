package org.irmc.industrialrevival.api.player;

import lombok.Data;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.core.guide.GuideMode;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author balugaq
 */
@Data
public class GuideSetting<T> implements ClickHandler, Cloneable {
    public static final NamespacedKey KEY = NamespacedKey.minecraft("key");
    private Predicate<Player> displayable;
    private NamespacedKey key;
    private T value;
    private Function<T, ItemStack> icon;

    public GuideSetting(Predicate<Player> displayable, NamespacedKey key, T value, Function<T, ItemStack> icon) {
        this.displayable = displayable;
        this.key = key;
        this.value = value;
        this.icon = icon.andThen(i -> new CustomItemStack(i).setPDCData(KEY, PersistentDataType.STRING, key.toString()).getBukkit());
    }

    public GuideSetting(NamespacedKey key, T value, Function<T, ItemStack> icon) {
        this(_ -> true, key, value, icon);
    }

    public static <T> GuideSetting<T> of(Predicate<Player> displayable, NamespacedKey key, T value, Function<T, ItemStack> icon) {
        return new GuideSetting<>(displayable, key, value, icon);
    }

    public static <T> GuideSetting<T> of(NamespacedKey key, T value, Function<T, ItemStack> icon) {
        return new GuideSetting<>(key, value, icon);
    }

    public static <T> GuideSetting<T> constof(Predicate<Player> requirement, NamespacedKey key, T defaultValue, Function<T, ItemStack> icon) {
        return of(requirement, key, defaultValue, icon);
    }

    public static <T> GuideSetting<T> constof(NamespacedKey key, T defaultValue, Function<T, ItemStack> icon) {
        return of(key, defaultValue, icon);
    }

    public T get() {
        return value;
    }

    public T get(Player player) {
        return PlayerProfile.getProfile(player).getGuideSettings(this);
    }

    /**
     * Called when an item in the machine menu is clicked.
     *
     * @param player      the player who clicked the item
     * @param clickedItem the clicked item
     * @param clickedSlot the slot where the item was clicked
     * @param clickedMenu the machine menu where the item was clicked
     * @param clickType   the click type
     * @return false if the click should be canceled, true otherwise
     */
    @Override
    public boolean onClick(@NotNull Player player, @Nullable ItemStack clickedItem, @Range(from = 0, to = 53) int clickedSlot, @NotNull SimpleMenu clickedMenu, @NotNull ClickType clickType) {
        var profile = PlayerProfile.getProfile(player);
        if (value instanceof Boolean b) {
            profile.getGuideSettings().setGuideSettings(this, (T) (Boolean) !b);
        }

        if (value instanceof GuideMode gm) {
            if (player.isOp()) {
                profile.getGuideSettings().setGuideSettings(this, (T) gm.next());
            }
        }

        return false;
    }

    public GuideSetting<T> clone() {
        return new GuideSetting<>(key, value, icon);
    }
}
