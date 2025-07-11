package org.irmc.industrialrevival.core.guide;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.utils.GuideUtil;

import javax.annotation.ParametersAreNonnullByDefault;

public abstract class IRGuide implements IRGuideImplementation {
    @Override
    @ParametersAreNonnullByDefault
    public void openMainPage(Player player, int page) {
        GuideUtil.openMainMenu(player, page);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void openItemGroup(Player player, ItemGroup itemGroup, int page) {
        GuideUtil.openGroupMenu(player, itemGroup, page);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void displayItem(Player player, ItemStack item, int page) {
        GuideUtil.lookup(player, item, page);
    }
}
