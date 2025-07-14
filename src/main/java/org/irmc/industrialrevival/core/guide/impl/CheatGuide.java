package org.irmc.industrialrevival.core.guide.impl;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.guide.GuideMode;
import org.irmc.industrialrevival.core.guide.IRGuide;
import org.irmc.industrialrevival.utils.Constants;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public class CheatGuide extends IRGuide {
    public static final CheatGuide instance = new CheatGuide();

    public static CheatGuide instance() {
        return instance;
    }

    @Override
    public @NotNull ItemStack getGuideIcon() {
        return Constants.ItemStacks.CHEAT_GUIDE_BOOK_ITEM;
    }

    @Override
    public @NotNull GuideMode getGuideMode() {
        return GuideMode.CHEAT;
    }
}
