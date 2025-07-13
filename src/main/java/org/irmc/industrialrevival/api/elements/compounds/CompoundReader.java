package org.irmc.industrialrevival.api.elements.compounds;

import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.registry.ChemicalCompounds;
import org.irmc.industrialrevival.utils.ElementUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Abstract base class for reading and parsing compound data from NamespacedKey representations.
 * <p>
 * This class provides a framework for converting NamespacedKey objects into Compound instances.
 * Different implementations can handle various compound formats and sources, such as elements
 * or chemical compounds.
 * </p>
 * <p>
 * The class uses a namespace-based approach to determine which reader should handle a given key,
 * allowing for extensible compound parsing without modifying existing code.
 * </p>
 *
 * @author balugaq
 * @see Compound
 * @see NamespacedKey
 */
public abstract class CompoundReader {
    
    /**
     * Determines if this reader can handle the specified NamespacedKey.
     * <p>
     * This method should check the namespace and/or key format to determine
     * if this reader is appropriate for parsing the given key.
     * </p>
     *
     * @param key the NamespacedKey to check
     * @return true if this reader can handle the key, false otherwise
     */
    public abstract boolean adapter(@NotNull NamespacedKey key);

    /**
     * Reads and parses a Compound from the specified NamespacedKey.
     * <p>
     * This method should extract the necessary information from the key and
     * construct an appropriate Compound instance.
     * </p>
     *
     * @param key the NamespacedKey to parse
     * @return the parsed Compound instance
     */
    @NotNull
    public abstract Compound read(@NotNull NamespacedKey key);

    /**
     * Reader implementation for parsing element-based compounds.
     * <p>
     * This reader handles keys with the "element" namespace and converts them
     * to Element-based compounds using the element name as the key.
     * </p>
     *
     * @author balugaq
     */
    public static class ElementReader extends CompoundReader {
        
        /**
         * Checks if the key uses the element namespace.
         *
         * @param key the NamespacedKey to check
         * @return true if the key uses the element namespace
         */
        @Override
        public boolean adapter(@NotNull NamespacedKey key) {
            return key.getNamespace().equals(Compound.ELEMENT_NAMESPACE);
        }

        /**
         * Creates an Element-based compound from the key.
         * <p>
         * The key part should be the name of an element, which is then
         * converted to an ElementType and wrapped in a compound.
         * </p>
         *
         * @param key the NamespacedKey containing the element name
         * @return an Element-based compound
         */
        @Override
        @NotNull
        public Compound read(@NotNull NamespacedKey key) {
            return ChemicalCompounds.asCompound(ElementUtil.forName(key.getKey()));
        }
    }

    /**
     * Reader implementation for parsing chemical compounds.
     * <p>
     * This reader handles keys with the "chemical" namespace and converts them
     * to ChemicalCompound instances using the chemical formula as the key.
     * </p>
     *
     * @author balugaq
     */
    public static class ChemicalReader extends CompoundReader {
        
        /**
         * Checks if the key uses the chemical namespace.
         *
         * @param key the NamespacedKey to check
         * @return true if the key uses the chemical namespace
         */
        @Override
        public boolean adapter(@NotNull NamespacedKey key) {
            return key.getNamespace().equals(Compound.CHEMICAL_NAMESPACE);
        }

        /**
         * Creates a ChemicalCompound from the key.
         * <p>
         * The key part should be a chemical formula, which is then
         * looked up in the chemical compounds registry.
         * </p>
         *
         * @param key the NamespacedKey containing the chemical formula
         * @return a ChemicalCompound instance
         */
        @Override
        @NotNull
        public Compound read(@NotNull NamespacedKey key) {
            return ChemicalCompounds.asCompound(ChemicalCompound.forName(key.getKey()));
        }
    }
}
