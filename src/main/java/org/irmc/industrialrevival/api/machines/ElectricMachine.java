package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.events.ir.BlockTickEvent;
import org.jetbrains.annotations.NotNull;

/*
 * ElectricMachine is a BasicMachine that consumes energy.
 * @author balugaq
 */
public abstract class ElectricMachine extends EnergyComponent {
    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public void preRegister() throws Exception {
        addItemHandlers((BlockTicker) this::tick);
        super.preRegister();
    }

    protected void tick(@NotNull BlockTickEvent event) {
        // TODO: implement tick logic
        // machineRecipes.findNextRecipe()
    }
}
