package org.irmc.industrialrevival.api.data.runtime;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.physics.ContainerType;
import org.irmc.industrialrevival.api.physics.PhysicalState;
import org.irmc.industrialrevival.api.physics.SealStatus;

@RequiredArgsConstructor
@Data
public class ContainerMetaData {
    private final ChemicalCompound compound;
    private final double capacity;
    private final double mass;
    private final SealStatus seal;
    private final PhysicalState physicalState;
    private final ContainerType containerType;
}
