package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetProvider;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.events.ir.BlockTickEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * ElectricGenerator is a machine that generates energy.
 *
 * @author balugaq
 */
@Getter
public abstract class AbstractElectricGenerator extends AbstractMachine implements EnergyNetProvider {
    private long capacity;

    public AbstractElectricGenerator setCapacity(long capacity) {
        checkRegistered();
        this.capacity = capacity;
        return this;
    }

    @Override
    public @NotNull EnergyNetComponentType getComponentType() {
        return EnergyNetProvider.super.getComponentType();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void preRegister() throws Exception {
        addItemHandlers((BlockTicker) this::tick);
        super.preRegister();
    }

    protected abstract void tick(BlockTickEvent event);

}
