package org.irmc.industrialrevival.api.machines;

/**
 * ElectricCapacitor is an item that can be used to store energy.
 *
 * @author balugaq
 */
public abstract class ElectricCapacitor extends EnergyComponent {
    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CAPACITOR;
    }
}
