package org.irmc.industrialrevival.api.physics;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public enum CapacityUnit {
    nm3(Scales.nm3_scale),
    um3(Scales.um3_scale),
    mm3(Scales.mm3_scale),
    cm3(Scales.cm3_scale),
    dm3(Scales.dm3_scale),
    m3(Scales.m3_scale);

    public static class Scales {
        public static final BigDecimal thousand = new BigDecimal("1000");
        public static final BigDecimal million = thousand.multiply(thousand);
        public static final BigDecimal nm3_scale = new BigDecimal("1");
        public static final BigDecimal um3_scale = nm3_scale.multiply(million);
        public static final BigDecimal mm3_scale = um3_scale.multiply(million);
        public static final BigDecimal cm3_scale = mm3_scale.multiply(thousand);
        public static final BigDecimal dm3_scale = cm3_scale.multiply(thousand);
        public static final BigDecimal m3_scale = dm3_scale.multiply(thousand);
    }

    public long convert(@NotNull CapacityUnit current, long value) {
        return convertExact(current, new BigDecimal(value)).longValue();
    }

    public double convert(@NotNull CapacityUnit current, double value) {
        return convertExact(current, new BigDecimal(value)).doubleValue();
    }

    @NotNull
    public String convert(@NotNull CapacityUnit current, @NotNull String value) {
        return convertExact(current, new BigDecimal(value)).toPlainString();
    }

    @NotNull
    public BigDecimal convert(@NotNull CapacityUnit current, @NotNull BigDecimal value) {
        return convertExact(current, value);
    }

    @NotNull
    public BigDecimal convertExact(@NotNull CapacityUnit current, @NotNull BigDecimal v) {
        return v.multiply(current._2nm3).divide(this._2nm3, RoundingMode.HALF_UP);
    }

    CapacityUnit(@NotNull BigDecimal _2nm3) {
        this._2nm3 = _2nm3;
    }

    private final BigDecimal _2nm3;
}
