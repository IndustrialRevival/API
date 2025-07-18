package org.irmc.industrialrevival.core.translation;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CiFunction;
import org.jetbrains.annotations.Nullable;

public class TranslateContext {
    @Getter
    private final String key;
    private final CiFunction<Player, ItemStack, String, String> function;

    public TranslateContext(String key) {
        this(key, null);
    }

    public TranslateContext(String key, @Nullable CiFunction<Player, ItemStack, String, String> function) {
        this.key = key;
        this.function = function;
    }

    public String apply(Player player, ItemStack itemStack, String text) {
        return function == null ? text : function.apply(player, itemStack, text);
    }
}
