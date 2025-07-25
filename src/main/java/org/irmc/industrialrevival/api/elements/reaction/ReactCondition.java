package org.irmc.industrialrevival.api.elements.reaction;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.items.attributes.ChemicalCompoundContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * ReactCondition represents a condition for reacting an item with another item.
 * It can be a simple condition like "none", "light" or "high temperature",
 * or a more complex one like "electrolysis" or "heating".
 *
 * @author lijinhong11
 * @author balugaq
 * @see ChemicalCompoundContainer
 */
public final class ReactCondition {
    /* Default react conditions */
    public static final ReactCondition NONE = new ReactCondition(Type.NONE);
    public static final ReactCondition LIGHT = new ReactCondition(Type.LIGHT);
    public static final ReactCondition ELECTROLYSIS = new ReactCondition(Type.ELECTROLYSIS);
    public static final ReactCondition HEATING = new ReactCondition(Type.HEATING);
    public static final ReactCondition HIGH_TEMPERATURE = new ReactCondition(Type.HIGH_TEMPERATURE);

    /**
     * -- GETTER --
     * Gets the type of the reacting condition.
     */
    @Getter
    private final Type type;
    private final @Nullable ChemicalCompound catalyst;

    private ReactCondition(@NotNull Type type) {
        this(type, null);
    }

    private ReactCondition(@NotNull Type type, @Nullable ChemicalCompound catalyst) {
        this.type = type;
        this.catalyst = catalyst;
    }

    public static @NotNull ReactCondition asCatalyzer(@NotNull ChemicalCompound compound) {
        return new ReactCondition(Type.CATALYZER, compound);
    }

    /**
     * Null if the {@link ChemicalCompoundContainer} is not a catalyst.
     *
     * @return The chem reactable item that is catalyzing the reaction.
     */
    public @Nullable ChemicalCompound getCatalyst() {
        return catalyst;
    }

    public @NotNull Component humanize() {
        return switch (type) {
            case NONE -> Component.translatable("chemistry.formula.conditions.none");
            case LIGHT -> Component.translatable("chemistry.formula.conditions.light");
            case ELECTROLYSIS -> Component.translatable("chemistry.formula.conditions.electrolysis");
            case HEATING -> Component.translatable("chemistry.formula.conditions.heating");
            case HIGH_TEMPERATURE -> Component.translatable("chemistry.formula.conditions.high_temperature");
            case CATALYZER -> Component.translatable("chemistry.formula.conditions.catalyzer").append(
                    Component.translatable("chemistry." + catalyst.asKey() + "." + catalyst.asKey())); // todo: fix key
        };
    }

    public enum Type {
        NONE,
        LIGHT,
        ELECTROLYSIS,
        HEATING,
        HIGH_TEMPERATURE,
        CATALYZER
    }
}
