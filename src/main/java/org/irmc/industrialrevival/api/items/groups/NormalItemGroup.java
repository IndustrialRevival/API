package org.irmc.industrialrevival.api.items.groups;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.jetbrains.annotations.NotNull;

public class NormalItemGroup extends ItemGroup {
    public NormalItemGroup(@NotNull NamespacedKey key, @NotNull ItemStack icon) {
        super(key, icon);
    }

    public NormalItemGroup(@NotNull NamespacedKey key, @NotNull ItemStack icon, int tier) {
        super(key, icon, tier);
    }

    /**
     * Opens the guide menu for this item group.
     *
     * @param p    the player who opened the guide menu
     * @param page the page number of the guide menu to be opened
     */
    @Override
    public void onOpen(@NotNull Player p, int page) {
        GuideUtil.openGroupMenu(p, this, page);
    }
}
