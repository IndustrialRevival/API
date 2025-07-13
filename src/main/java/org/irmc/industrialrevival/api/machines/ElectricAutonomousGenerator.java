package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.events.ir.BlockTickEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public abstract class ElectricAutonomousGenerator extends AbstractElectricGenerator {
    @Override
    protected void tick(BlockTickEvent event) {
        addEnergyProduction(event.getMenu().getLocation(), getEnergyProduction(event.getBlock(), event.getMenu()));
    }

    @Override
    public @NotNull GeneratorType getGeneratorType() {
        return GeneratorType.AUTONOMOUS;
    }
}
