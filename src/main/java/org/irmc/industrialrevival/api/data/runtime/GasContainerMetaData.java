package org.irmc.industrialrevival.api.data.runtime;

import lombok.Getter;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.physics.ContainerType;
import org.irmc.industrialrevival.api.physics.PhysicalState;
import org.irmc.industrialrevival.api.physics.SealStatus;
import org.irmc.pigeonlib.objects.percentage.PositiveHundredPercentage;

@Getter
public class GasContainerMetaData extends ContainerMetaData {
    private final ChemicalCompound compound;
    private final double capacity;
    private final PositiveHundredPercentage percentage;
    private final SealStatus seal;
    private final PhysicalState physicalState;
    private final ContainerType containerType;

    public GasContainerMetaData(ChemicalCompound compound, double capacity, PositiveHundredPercentage percentage, SealStatus seal, PhysicalState physicalState, ContainerType containerType) {
        super(compound, capacity, 0, seal, physicalState, containerType);
        this.compound = compound;
        this.capacity = capacity;
        this.percentage = percentage;
        this.seal = seal;
        this.physicalState = physicalState;
        this.containerType = containerType;
    }

    @Override
    public double getMass() {
        throw new UnsupportedOperationException("Use #getPercentage()");
    }
}
