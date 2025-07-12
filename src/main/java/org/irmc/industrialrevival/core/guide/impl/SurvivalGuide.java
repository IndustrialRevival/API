package org.irmc.industrialrevival.core.guide.impl;

import org.irmc.industrialrevival.core.guide.GuideMode;
import org.irmc.industrialrevival.core.guide.IRGuide;
import org.jetbrains.annotations.NotNull;

public class SurvivalGuide extends IRGuide {
    public static final SurvivalGuide instance = new SurvivalGuide();

    public static SurvivalGuide instance() {
        return instance;
    }

    @Override
    public @NotNull GuideMode getGuideMode() {
        return GuideMode.SURVIVAL;
    }
}
