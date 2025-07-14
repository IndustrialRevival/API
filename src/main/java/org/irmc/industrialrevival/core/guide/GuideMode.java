package org.irmc.industrialrevival.core.guide;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.core.guide.impl.CheatGuide;
import org.irmc.industrialrevival.core.guide.impl.SurvivalGuide;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author balugaq
 */
public interface GuideMode extends Keyed {
    GuideMode SURVIVAL = SurvivalGuideMode.instance();
    GuideMode CHEAT = CheatGuideMode.instance();

    @Nullable
    static GuideMode valueOf(@NotNull String modeName) {
        return IRDock.getRegistry().getGuideMode(modeName);
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class SurvivalGuideMode implements GuideMode {
        private static final SurvivalGuideMode instance = new SurvivalGuideMode();

        private static SurvivalGuideMode instance() {
            return instance;
        }

        static {
            IRDock.getRegistry().registerGuide(instance, SurvivalGuide.instance());
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return KeyUtil.customKey("survival");
        }
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class CheatGuideMode implements GuideMode {
        private static final CheatGuideMode instance = new CheatGuideMode();

        private static CheatGuideMode instance() {
            return instance;
        }

        static {
            IRDock.getRegistry().registerGuide(instance, CheatGuide.instance());
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return KeyUtil.customKey("cheat");
        }
    }
}
