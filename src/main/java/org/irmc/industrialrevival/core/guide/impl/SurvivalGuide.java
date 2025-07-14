package org.irmc.industrialrevival.core.guide.impl;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.guide.GuideMode;
import org.irmc.industrialrevival.core.guide.IRGuide;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public class SurvivalGuide extends IRGuide {
    public static final SurvivalGuide instance = new SurvivalGuide();

    public static SurvivalGuide instance() {
        return instance;
    }

    @Override
    public @NotNull ItemStack getGuideIcon() {
        return Constants.ItemStacks.GUIDE_BOOK_ITEM;
    }

    @Override
    public @NotNull GuideMode getGuideMode() {
        return GuideMode.SURVIVAL;
    }
}
