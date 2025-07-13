package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.items.radiation.RadiativeItem;
import org.irmc.industrialrevival.api.items.radiation.RadiationLevel;
import org.jetbrains.annotations.NotNull;

/**
 * This interface defines an item which is radioactive. <br>
 * <br>
 *
 * @author lijinhong11
 * @see RadiativeItem
 */
public interface Radiation extends ItemAttribute {
    @NotNull RadiationLevel getRadiationLevel();
}
