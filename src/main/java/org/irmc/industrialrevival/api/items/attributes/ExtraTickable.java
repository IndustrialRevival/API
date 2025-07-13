package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.Keyed;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public interface ExtraTickable extends Keyed {
    @NotNull When getTime();

    void tick(@NotNull Location location);

    enum When {
        TICK_START,
        TICK_DONE
    }
}
