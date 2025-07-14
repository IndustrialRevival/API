package org.irmc.industrialrevival.core.guide;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 * @author lijinhong11
 */
@Data
@RequiredArgsConstructor
public class GuideEntry {
    private final @NotNull GuideImplementation guide;
    private final @NotNull SimpleMenu content;

    @Setter
    private int page;

    public static @NotNull GuideEntry warp(GuideImplementation guide, SimpleMenu value) {
        var e = new GuideEntry(guide, value);
        e.setPage(1);
        return e;
    }
}
