package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.events.ir.BlockTickEvent;

/**
 * @author balugaq
 */
public abstract class ElectricManualGenerator extends AbstractElectricGenerator {
    @Override
    public GeneratorType getGeneratorType() {
        return GeneratorType.MANUAL;
    }

    @Override
    protected void tick(BlockTickEvent event) {

    }
}
