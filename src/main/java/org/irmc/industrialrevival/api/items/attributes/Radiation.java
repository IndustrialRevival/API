package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.items.radiation.RadiativeItem;
import org.irmc.industrialrevival.api.items.radiation.RadiationLevel;

/**
 * This interface defines an item which is radioactive. <br>
 * <br>
 *
 * @see RadiativeItem
 */
public interface Radiation extends ItemAttribute {
    RadiationLevel getRadiationLevel();
}
