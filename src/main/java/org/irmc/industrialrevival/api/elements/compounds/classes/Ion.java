package org.irmc.industrialrevival.api.elements.compounds.classes;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;

/**
 * An interface for {@link ChemicalCompound}s that are ions.
 * <p>
 * This interface extends {@link Attribute} and serves as a marker for chemical compounds
 * that represent ions - atoms or molecules that have gained or lost electrons, resulting
 * in a net electrical charge.
 * </p>
 * <p>
 * Ions are fundamental to many chemical processes, including:
 * <ul>
 *   <li>Electrolyte solutions and conductivity</li>
 *   <li>Acid-base reactions</li>
 *   <li>Precipitation reactions</li>
 *   <li>Electrochemical processes</li>
 * </ul>
 * </p>
 * <p>
 * Compounds implementing this interface can be identified as ions and may have
 * special handling in chemical reactions and calculations.
 * </p>
 *
 * @author baluagq
 * @see ChemicalCompound
 * @see Attribute
 */
public interface Ion extends Attribute {
}
