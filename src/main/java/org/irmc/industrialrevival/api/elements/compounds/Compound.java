package org.irmc.industrialrevival.api.elements.compounds;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A component representing a chemical compound in the industrial chemistry system.
 * Provides standardized interfaces for describing and processing compound substances.
 *
 * @author balugaq
 * @see Chemical - For specific chemical implementations
 * @see Element - For basic elemental components
 */
public interface Compound extends Keyed {
    /**
     * List of registered compound readers that handle different compound formats.
     * Readers are used to parse NamespacedKey representations into Compound objects.
     */
    List<CompoundReader> COMPOUND_READERS = new ArrayList<>();
    
    /**
     * Namespace identifier for element-based compounds.
     * Used in key formatting to distinguish between different compound types.
     */
    String ELEMENT_NAMESPACE = "element";
    
    /**
     * Namespace identifier for synthetic chemicals.
     * Used in key formatting to distinguish between different compound types.
     */
    String CHEMICAL_NAMESPACE = "chemical";

    /**
     * Attempts to construct a Compound instance from a NamespacedKey.
     * Delegates to registered CompoundReaders to find an appropriate parser.
     *
     * @param key The NamespacedKey to convert
     * @return A Compound object if a matching reader is found, otherwise null
     */
    @Nullable
    static Compound fromKey(NamespacedKey key) {
        for (CompoundReader reader : COMPOUND_READERS) {
            if (reader.adapter(key)) {
                return reader.read(key);
            }
        }

        return null;
    }

    /**
     * Gets the molar mass of this compound in g/mol.
     *
     * @return The molar mass value
     */
    double getMolarMass();

    /**
     * Converts this compound into its atomic composition.
     * Returns a map of element types to their respective quantities in the compound.
     *
     * @return A map of atomic composition
     */
    @NotNull Map<ElementType, Double> toAtomic();
}