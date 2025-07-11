package org.irmc.industrialrevival.core.guide.impl;

import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.irmc.industrialrevival.core.guide.IRGuide;
import org.jetbrains.annotations.NotNull;

public class CheatGuide extends IRGuide {
    public static final CheatGuide instance = new CheatGuide();

    public static CheatGuide instance() {
        return instance;
    }

    @Override
    public @NotNull GuideMode getGuideMode() {
        return GuideMode.CHEAT;
    }
}
