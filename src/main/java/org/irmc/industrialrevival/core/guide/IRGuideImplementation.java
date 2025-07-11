package org.irmc.industrialrevival.core.guide;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public interface IRGuideImplementation {
    @NotNull
    GuideMode getGuideMode();

    @ParametersAreNonnullByDefault
    void openMainPage(Player player, int page);

    @ParametersAreNonnullByDefault
    void openItemGroup(Player player, ItemGroup itemGroup, int page);

    @ParametersAreNonnullByDefault
    void displayItem(Player player, ItemStack itemStack, int page);
}
