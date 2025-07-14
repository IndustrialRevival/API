package org.irmc.industrialrevival.api.physics;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public enum MassUnit {
    ng(Scales.ng_scale),
    ug(Scales.ug_scale),
    mg(Scales.mg_scale),
    g(Scales.g_scale),
    kg(Scales.kg_scale),
    t(Scales.t_scale);

    public static class Scales {
        public static final BigDecimal thousand = new BigDecimal("1000");
        public static final BigDecimal ng_scale = new BigDecimal("1");
        public static final BigDecimal ug_scale = ng_scale.multiply(thousand);
        public static final BigDecimal mg_scale = ug_scale.multiply(thousand);
        public static final BigDecimal g_scale = mg_scale.multiply(thousand);
        public static final BigDecimal kg_scale = g_scale.multiply(thousand);
        public static final BigDecimal t_scale = kg_scale.multiply(thousand);
    }

    public long convert(@NotNull MassUnit current, long value) {
        return convertExact(current, new BigDecimal(value)).longValue();
    }

    public double convert(@NotNull MassUnit current, double value) {
        return convertExact(current, new BigDecimal(value)).doubleValue();
    }

    @NotNull
    public String convert(@NotNull MassUnit current, @NotNull String value) {
        return convertExact(current, new BigDecimal(value)).toPlainString();
    }

    @NotNull
    public BigDecimal convert(@NotNull MassUnit current, @NotNull BigDecimal value) {
        return convertExact(current, value);
    }

    @NotNull
    public BigDecimal convertExact(@NotNull MassUnit current, @NotNull BigDecimal v) {
        return v.multiply(current._2ng).divide(this._2ng, RoundingMode.HALF_UP);
    }

    MassUnit(@NotNull BigDecimal _2ng) {
        this._2ng = _2ng;
    }

    private final BigDecimal _2ng;
}
