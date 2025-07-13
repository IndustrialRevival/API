package org.irmc.industrialrevival.core.guide;

import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public enum GuideMode {
    SURVIVAL,
    CHEAT;

    public @NotNull GuideMode next() {
        return switch (this) {
            case SURVIVAL -> CHEAT;
            case CHEAT -> SURVIVAL;
        };
    }
}
