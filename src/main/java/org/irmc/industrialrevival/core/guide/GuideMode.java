package org.irmc.industrialrevival.core.guide;

/**
 * @author balugaq
 */
public enum GuideMode {
    SURVIVAL,
    CHEAT;

    public GuideMode next() {
        return switch (this) {
            case SURVIVAL -> CHEAT;
            case CHEAT -> SURVIVAL;
        };
    }
}
