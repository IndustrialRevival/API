package org.irmc.industrialrevival.api.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Represents a set of valences for a chemical element.
 * <p>
 * Valence represents the combining capacity of an element, indicating how many
 * electrons an atom can gain, lose, or share when forming chemical bonds.
 * </p>
 * <p>
 * This record encapsulates multiple possible valence values for an element,
 * as many elements can exhibit different oxidation states in different compounds.
 * </p>
 * <p>
 * Valence values range from -8 to +8, representing the possible oxidation states
 * that elements can have in chemical compounds.
 * </p>
 *
 * @param valences the array of valence values for this element
 * @author balugaq
 * @see ElementType
 */
public record Valence(@Range(from = -8, to = 8) int @NotNull ... valences) {
    
    /**
     * Creates a new Valence instance with the specified valence values.
     * <p>
     * This is a convenience factory method that provides a more readable way
     * to create Valence instances compared to the constructor.
     * </p>
     *
     * @param valences the valence values to include in this Valence instance
     * @return a new Valence instance containing the specified valence values
     * @throws IllegalArgumentException if any valence value is outside the valid range [-8, 8]
     */
    public static @NotNull Valence of(@Range(from = -8, to = 8) int @NotNull ... valences) {
        return new Valence(valences);
    }
}
