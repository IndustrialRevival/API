package org.irmc.industrialrevival.api.exceptions;

/**
 * Exception thrown when a chemical compound is not found in the registry.
 * <p>
 * This exception is raised when attempting to use a chemical compound that
 * has not been registered or does not exist in the chemical compound registry.
 * It provides information about the unknown compound and the context in which
 * it was encountered.
 * </p>
 *
 * @author balugaq
 */
public class UnknownChemicalCompoundException extends IllegalArgumentException {
    /**
     * The name of the unknown chemical compound.
     */
    public String compound = null;

    /**
     * Constructs a new UnknownChemicalCompoundException for a specific compound.
     *
     * @param compound the name of the unknown chemical compound
     */
    public UnknownChemicalCompoundException(String compound) {
        super("Unknown chemical compound: " + compound);
        this.compound = compound;
    }

    /**
     * Constructs a new UnknownChemicalCompoundException with context about where
     * the unknown compound was encountered.
     *
     * @param formula the chemical formula containing the unknown compound
     * @param id the ID of the chemical formula
     * @param cause the original exception that caused this error
     */
    public UnknownChemicalCompoundException(String formula, int id, UnknownChemicalCompoundException cause) {
        super("Unknown chemical compound: " + (cause.compound == null ? "" : cause.compound) + " in ChemicalFormula #" + id + " : " + formula);
    }
}
