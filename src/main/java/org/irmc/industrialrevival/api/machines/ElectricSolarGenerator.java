package org.irmc.industrialrevival.api.machines;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.events.ir.BlockTickEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public abstract class ElectricSolarGenerator extends AbstractElectricGenerator {
    @Override
    protected void tick(BlockTickEvent event) {
        Block block = event.getBlock();
        MachineMenu menu = event.getMenu();
        byte lightLevel = block.getLightFromSky();
        if (isDay(block, menu, lightLevel)) {
            addEnergyProduction(menu.getLocation(), getDayEnergyProduction(block, menu, lightLevel));
        } else {
            addEnergyProduction(menu.getLocation(), getNightEnergyProduction(block, menu, lightLevel));
        }
    }

    protected abstract boolean isDay(Block block, MachineMenu menu, byte lightLevel);

    protected abstract long getDayEnergyProduction(Block block, MachineMenu menu, byte lightLevel);

    protected abstract long getNightEnergyProduction(Block block, MachineMenu menu, byte lightLevel);

    @Override
    public @NotNull GeneratorType getGeneratorType() {
        return GeneratorType.SOLAR;
    }
}
