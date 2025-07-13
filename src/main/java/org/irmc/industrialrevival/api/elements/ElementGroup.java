package org.irmc.industrialrevival.api.elements;

/**
 * Enum representing the groups of elements in the periodic table.
 * <p>
 * This enum defines the standard group classifications used in chemistry,
 * following the IUPAC (International Union of Pure and Applied Chemistry) 
 * naming convention for element groups.
 * </p>
 * <p>
 * The groups are numbered using Roman numerals and letters:
 * - Groups 1-2: Main group elements (A groups)
 * - Groups 3-12: Transition metals (B groups)
 * - Groups 13-18: Main group elements (A groups)
 * </p>
 *
 * @author balugaq
 * @see ElementType
 */
public enum ElementGroup {
    /** Group 1A - Alkali metals */
    IA,
    /** Group 2A - Alkaline earth metals */
    IIA,
    /** Group 3B - Scandium group */
    IIIB,
    /** Group 4B - Titanium group */
    IVB,
    /** Group 5B - Vanadium group */
    VB,
    /** Group 6B - Chromium group */
    VIB,
    /** Group 7B - Manganese group */
    VIIB,
    /** Group 8B - Iron, Cobalt, Nickel group */
    VIIIB,
    /** Group 1B - Copper group */
    IB,
    /** Group 2B - Zinc group */
    IIB,
    /** Group 3A - Boron group */
    IIIA,
    /** Group 4A - Carbon group */
    IVA,
    /** Group 5A - Nitrogen group */
    VA,
    /** Group 6A - Oxygen group (Chalcogens) */
    VIA,
    /** Group 7A - Halogens */
    VIIA,
    /** Group 8A - Noble gases */
    VIIIA;

    // Alias constants for better readability
    /** Alias for IA - Alkali metals */
    public static final ElementGroup ALKALI_METALS = IA;
    /** Alias for IIA - Alkaline earth metals */
    public static final ElementGroup ALKALINE_EARTH_METALS = IIA;
    /** Alias for IVA - Carbon group */
    public static final ElementGroup CARBON_GROUP = IVA;
    /** Alias for VA - Nitrogen group */
    public static final ElementGroup NITROGEN_GROUP = VA;
    /** Alias for VIA - Oxygen group (Chalcogens) */
    public static final ElementGroup CHALCOGENS = VIA;
    /** Alias for VIIA - Halogens */
    public static final ElementGroup HALOGENS = VIIA;
    /** Alias for VIIIB - Noble gases */
    public static final ElementGroup NOBLE_GASES = VIIIB;
    /** Alias for VIIIB - Noble gases (alternative name) */
    public static final ElementGroup O = VIIIB;
}
