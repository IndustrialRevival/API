package org.irmc.industrialrevival.core.guide;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.irmc.industrialrevival.api.menu.SimpleMenu;

/**
 * @author balugaq
 * @author lijinhong11
 */
@Data
@RequiredArgsConstructor
public class GuideEntry {
    private final IRGuideImplementation guide;
    private final SimpleMenu content;

    @Setter
    private int page;

    public static GuideEntry warp(IRGuideImplementation guide, SimpleMenu value) {
        var e = new GuideEntry(guide, value);
        e.setPage(1);
        return e;
    }
}
