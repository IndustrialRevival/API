package org.irmc.industrialrevival.api.elements.compounds;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

/**
 * Represents a chemical element as a compound in the IndustrialRevival system.
 * <p>
 * This class implements the {@link Compound} interface and provides a way to represent
 * individual chemical elements as compounds. It encapsulates an {@link ElementType} and
 * provides methods to access its atomic mass and atomic composition.
 * </p>
 * <p>
 * The class automatically registers an {@link CompoundReader.ElementReader} for
 * parsing element-based compounds from various sources.
 * </p>
 *
 * @author balugaq
 * @see Compound
 * @see ElementType
 * @see Chemical
 */
@Data
@AllArgsConstructor
@ParametersAreNonnullByDefault
public class Element implements Compound {
    static {
        COMPOUND_READERS.add(new CompoundReader.ElementReader());
    }

    private final @NotNull ElementType element;
    private final @NotNull NamespacedKey key;

    /**
     * Creates a new Element instance with the specified element type.
     * <p>
     * The key is automatically generated using the element's name in lowercase
     * and the element namespace.
     * </p>
     *
     * @param elementType the element type to represent
     */
    public Element(@NotNull ElementType elementType) {
        this(elementType, new NamespacedKey(Element.ELEMENT_NAMESPACE, elementType.name().toLowerCase()));
    }

    /**
     * Gets the molar mass of this element.
     * <p>
     * For elements, the molar mass is equivalent to the relative atomic mass
     * of the element.
     * </p>
     *
     * @return the molar mass of the element in g/mol
     */
    public double getMolarMass() {
        return element.getRelativeAtomicMass();
    }

    /**
     * Converts this element to its atomic composition.
     * <p>
     * For elements, this returns a map containing only this element with a
     * coefficient of 1.0, representing a single atom of the element.
     * </p>
     *
     * @return a map representing the atomic composition (single element with coefficient 1.0)
     */
    public @NotNull Map<ElementType, Double> toAtomic() {
        return Map.of(element, 1D);
    }

    /**
     * Gets the unique key identifier for this element.
     *
     * @return the NamespacedKey identifier for this element
     */
    @Override
    public @NotNull NamespacedKey getKey() {
        return key;
    }

}
