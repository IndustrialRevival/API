package org.irmc.industrialrevival.core.translation;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CiFunction;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TranslateContext {
    @Getter
    private final String key;
    private final CiFunction<Player, ItemStack, Component, Component> nameFunction;
    private CiFunction<Player, ItemStack, List<Component>, List<Component>> loreFunction;

    public TranslateContext(String key) {
        this(key, null);
    }

    public TranslateContext(String key, @Nullable CiFunction<Player, ItemStack, Component, Component> function) {
        this.key = key;
        this.nameFunction = function;
    }

    public Component apply(Player player, ItemStack itemStack, Component text) {
        return nameFunction == null ? text : nameFunction.apply(player, itemStack, text);
    }

    public List<Component> apply(Player player, ItemStack itemStack, List<Component> text) {
        return loreFunction == null ? text : loreFunction.apply(player, itemStack, text);
    }
}
