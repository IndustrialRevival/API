package org.irmc.industrialrevival.api.items.radiation;

import lombok.Getter;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.Radiation;

/**
 * @author lijinhong11
 */
@Getter
public class RadiativeItem extends IndustrialRevivalItem implements Radiation {
    private RadiationLevel radiationLevel = RadiationLevel.LOW;

    public RadiativeItem setRadiationLevel(RadiationLevel radiationLevel) {
        checkRegistered();
        this.radiationLevel = radiationLevel;
        return this;
    }
}
