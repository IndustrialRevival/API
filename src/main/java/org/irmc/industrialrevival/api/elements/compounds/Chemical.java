package org.irmc.industrialrevival.api.elements.compounds;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

/**
 * Represents a chemical compound in the IndustrialRevival system.
 * <p>
 * This class implements the {@link Compound} interface and provides a way to represent
 * chemical compounds with their molecular structure and properties. It encapsulates
 * a {@link ChemicalCompound} and provides methods to access its molar mass and
 * atomic composition.
 * </p>
 * <p>
 * The class automatically registers a {@link CompoundReader.ChemicalReader} for
 * parsing chemical compounds from various sources.
 * </p>
 *
 * @author balugaq
 * @see Compound
 * @see ChemicalCompound
 * @see ElementType
 */
@Data
@AllArgsConstructor
@ParametersAreNonnullByDefault
public class Chemical implements Compound {
    static {
        COMPOUND_READERS.add(new CompoundReader.ChemicalReader());
    }

    private final @NotNull ChemicalCompound compound;
    private final @NotNull NamespacedKey key;

    /**
     * Creates a new Chemical instance with the specified compound.
     * <p>
     * The key is automatically generated using the compound's key representation
     * and the chemical namespace.
     * </p>
     *
     * @param compound the chemical compound to represent
     */
    public Chemical(@NotNull ChemicalCompound compound) {
        this(compound, new NamespacedKey(Compound.CHEMICAL_NAMESPACE, compound.asKey()));
    }

    /**
     * Gets the molar mass of this chemical compound.
     * <p>
     * The molar mass is calculated from the atomic masses of all elements
     * in the compound, weighted by their stoichiometric coefficients.
     * </p>
     *
     * @return the molar mass of the compound in g/mol
     */
    @Override
    public double getMolarMass() {
        return compound.getMolarMass();
    }

    /**
     * Converts this chemical compound to its atomic composition.
     * <p>
     * Returns a map where the keys are element types and the values are
     * the number of atoms of each element in the compound.
     * </p>
     *
     * @return a map representing the atomic composition of the compound
     */
    public @NotNull Map<ElementType, Double> toAtomic() {
        return compound.toAtomic();
    }

    /**
     * Gets the unique key identifier for this chemical compound.
     *
     * @return the NamespacedKey identifier for this compound
     */
    @Override
    public @NotNull NamespacedKey getKey() {
        return key;
    }
}
