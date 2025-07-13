package org.irmc.industrialrevival.api.exceptions;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;

/**
 * Exception thrown when there is a conflict between chemical formulas.
 * <p>
 * This exception is raised when two chemical formulas have the same ID but different
 * raw formula representations, indicating a conflict in the chemical formula registry.
 * The conflicting formula will not be registered to prevent data inconsistency.
 * </p>
 *
 * @author balugaq
 */
public class FormulaConflictException extends RuntimeException {
    /**
     * Constructs a new FormulaConflictException with the specified detail message.
     *
     * @param message the detail message
     */
    public FormulaConflictException(String message) {
        super(message);
    }

    /**
     * Constructs a new FormulaConflictException with details about the conflicting formulas.
     *
     * @param formula1 the first conflicting formula
     * @param formula2 the second conflicting formula
     */
    public FormulaConflictException(ChemicalFormula formula1, ChemicalFormula formula2) {
        super(String.format("""
                Formula conflict detected:
                Formula id: %s
                Formula1: %s
                Formula2: %s
                
                The conflicting formula will not be registered.
                """, formula1.getId(), formula1.getRawFormula(), formula2.getRawFormula()));
    }
}
