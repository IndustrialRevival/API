package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.events.ir.BlockTickEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public abstract class ElectricManualGenerator extends AbstractElectricGenerator {
    @Override
    public @NotNull GeneratorType getGeneratorType() {
        return GeneratorType.MANUAL;
    }

    @Override
    protected void tick(BlockTickEvent event) {

    }
}
