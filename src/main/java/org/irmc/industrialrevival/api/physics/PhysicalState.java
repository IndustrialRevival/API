package org.irmc.industrialrevival.api.physics;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

@Getter
public enum PhysicalState {
    SOLID(0),
    LIQUID(1),
    GAS(2),
    PLASMA(3);

    public static final int DEFAULT = SOLID.value();
    public static final PersistentDataType<Integer, Integer> TYPE = PersistentDataType.INTEGER;
    private final int intValue;

    PhysicalState(int intValue) {
        this.intValue = intValue;
    }

    public int value() {
        return intValue;
    }

    @NotNull
    public static PhysicalState get(@Range(from = 0, to = 3) int intValue) {
        Preconditions.checkArgument(intValue >= 0, "intValue is negative");
        Preconditions.checkArgument(intValue <= 3, "intValue is greater than 3");

        return switch (intValue) {
            case 0 -> SOLID;
            case 1 -> LIQUID;
            case 2 -> GAS;
            case 3 -> PLASMA;
            default -> null;
        };
    }
}
