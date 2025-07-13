package org.irmc.industrialrevival.api.elements.compounds.classes;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;

/**
 * An interface for {@link ChemicalCompound}s that are oxides.
 * <p>
 * This interface extends {@link Attribute} and serves as a marker for chemical compounds
 * that are oxides - compounds containing oxygen combined with one or more other elements.
 * </p>
 * <p>
 * Oxides are one of the most common types of compounds and play important roles in:
 * <ul>
 *   <li>Metallurgy and metal extraction processes</li>
 *   <li>Acid-base chemistry (acidic and basic oxides)</li>
 *   <li>Environmental chemistry and atmospheric processes</li>
 *   <li>Industrial chemical reactions</li>
 *   <li>Mineral formation and geology</li>
 * </ul>
 * </p>
 * <p>
 * Compounds implementing this interface can be identified as oxides and may have
 * special properties and behaviors in chemical reactions, particularly in relation
 * to their acidity, basicity, or reactivity with other compounds.
 * </p>
 *
 * @author baluagq
 * @see ChemicalCompound
 * @see Attribute
 */
public interface Oxide extends Attribute {
}
