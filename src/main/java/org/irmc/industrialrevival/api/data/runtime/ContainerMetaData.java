package org.irmc.industrialrevival.api.data.runtime;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.physics.ContainerType;
import org.irmc.industrialrevival.api.physics.PhysicalState;
import org.irmc.industrialrevival.api.physics.SealStatus;

public record ContainerMetaData(
        ChemicalCompound compound,
        double capacity,
        double mass,
        SealStatus seal,
        PhysicalState physicalState,
        ContainerType containerType) {}
