package org.irmc.industrialrevival.api.elements.compounds;

import com.google.common.base.Preconditions;
import lombok.Data;
import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.compounds.types.IonCompound;
import org.irmc.industrialrevival.api.elements.compounds.types.OxideCompound;
import org.irmc.industrialrevival.api.elements.registry.ChemicalCompounds;
import org.irmc.industrialrevival.dock.IRDock;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * A chemical compound, consisting of a name and a map of its constituent compounds and their respective amounts.
 * All built-in {@link ChemicalCompound}s are in {@link ChemicalCompounds}
 *
 * @author balugaq
 * @see ChemicalCompounds
 * @see IonCompound
 * @see OxideCompound
 */
@Data
public class ChemicalCompound {
    @NotNull
    public final String name;
    @NotNull
    public final Map<Compound, Double> compounds;

    /**
     * Creates a new chemical compound with the given name and compounds.
     *
     * @param name      the name of the chemical compound
     * @param compounds the map of constituent compounds and their respective amounts
     */
    public ChemicalCompound(@NotNull String name, @NotNull Map<Compound, Double> compounds) {
        this(name, compounds, true);
    }

    /**
     * Creates a new chemical compound with the given name and compounds.
     *
     * @param name      the name of the chemical compound
     * @param compounds the map of constituent compounds and their respective amounts
     * @param register  whether to register the chemical compound in the global set of all chemical compounds
     */
    public ChemicalCompound(@NotNull String name, @NotNull Map<Compound, Double> compounds, boolean register) {
        Preconditions.checkNotNull(name, "name cannot be null");
        Preconditions.checkNotNull(compounds, "compounds cannot be null");
        Preconditions.checkArgument(!compounds.isEmpty(), "compounds cannot be empty");

        this.name = name;
        this.compounds = compounds;
        if (register) {
            IRDock.getRegistry().getChemicalCompounds().put(name, this);
        }
    }

    /**
     * Used to find a chemical compound by its name like {@code forName("SO4")} or {@code forName("Ca(OH)2")} are both valid.
     *
     * @param name the name of the chemical compound to find
     * @return the chemical compound with the given name, or null if no such compound exists
     */
    @Nullable
    @Contract("null -> null")
    public static ChemicalCompound forName(@Nullable String name) {
        if (name == null) {
            return null;
        }

        return IRDock.getRegistry().getChemicalCompounds().get(name);
    }

    /**
     * Converts the compound name to a key format suitable for use in NamespacedKey.
     * <p>
     * This method replaces parentheses with hyphens and periods, and converts the result to lowercase.
     * For example, "Ca(OH)2" becomes "ca-oh.2".
     * </p>
     *
     * @return the compound name converted to a key format
     */
    public String asKey() {
        return name.replaceAll("\\(", "-").replaceAll("\\)", ".").toLowerCase();
    }

    /**
     * Calculates the molar mass of this chemical compound.
     * <p>
     * The molar mass is calculated by summing the molar masses of all constituent compounds,
     * weighted by their stoichiometric coefficients.
     * </p>
     *
     * @return the molar mass of the compound in g/mol
     */
    public double getMolarMass() {
        double mass = 0;
        for (var entry : compounds.entrySet()) {
            mass += entry.getKey().getMolarMass() * entry.getValue();
        }

        return mass;
    }

    /**
     * Converts this chemical compound to its atomic composition.
     * <p>
     * Returns a map where the keys are element types and the values are
     * the total number of atoms of each element in the compound, taking into account
     * the stoichiometric coefficients of all constituent compounds.
     * </p>
     *
     * @return a map representing the atomic composition of the compound
     */
    public Map<ElementType, Double> toAtomic() {
        Map<ElementType, Double> atomic = new HashMap<>();
        for (var entry : compounds.entrySet()) {
            for (var atomicEntry : entry.getKey().toAtomic().entrySet()) {
                atomic.merge(atomicEntry.getKey(), atomicEntry.getValue() * entry.getValue(), Double::sum);
            }
        }

        return atomic;
    }

    /**
     * Gets a human-readable name for this chemical compound.
     * <p>
     * This method removes underscores from the compound name to make it more readable.
     * </p>
     *
     * @return a human-readable name for the compound
     */
    public String getHumanizedName() {
        return name.replaceAll("_", "");
    }

    /**
     * Gets a human-readable name as a Component for this chemical compound.
     * <p>
     * This method returns the humanized name wrapped in a Component for use in
     * user interfaces and displays.
     * </p>
     *
     * @return a Component containing the human-readable name
     */
    public Component humanizedName() {
        return Component.text(getHumanizedName());
    }
}
