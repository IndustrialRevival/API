package org.irmc.industrialrevival.api.physics;

import lombok.Getter;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

@Getter
public enum SealStatus {
    SEALED(false),
    OPEN(true);

    public static final boolean DEFAULT = OPEN.value();
    public static final PersistentDataType<Byte, Boolean> TYPE = PersistentDataType.BOOLEAN;
    private final boolean booleanValue;

    SealStatus(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public boolean value() {
        return booleanValue;
    }

    @NotNull
    public static SealStatus get(boolean booleanValue) {
        return booleanValue ? OPEN : SEALED;
    }
}
