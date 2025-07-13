package org.irmc.industrialrevival.api.exceptions;

import lombok.Getter;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;

/**
 * Exception thrown when there is a conflict between item IDs from different addons.
 * <p>
 * This exception is raised when two different addons try to register items with the same ID,
 * indicating a conflict in the item registry. The conflicting item will not be registered
 * to prevent data inconsistency and potential issues.
 * </p>
 * <p>
 * This exception provides information about both the original addon that registered the ID
 * first and the conflicting addon that attempted to use the same ID.
 * </p>
 *
 * @author lijinhong11
 */
@Getter
public class IdConflictException extends RuntimeException {
    private final IndustrialRevivalAddon originalAddon;
    private final IndustrialRevivalAddon conflictingAddon;

    /**
     * Constructs a new IdConflictException with details about the conflicting addons.
     *
     * @param id the conflicting item ID
     * @param origin the original addon that registered the ID first
     * @param conflictingAddon the addon that attempted to use the same ID
     */
    public IdConflictException(String id, IndustrialRevivalAddon origin, IndustrialRevivalAddon conflictingAddon) {
        super(
                """
                        Item id conflict detected:
                        Item id: %s
                        Original addon: %s
                        Conflicting item's addon: %s
                        
                        The conflicting item will not be registered.
                        """
                        .formatted(
                                id,
                                origin.getPlugin().getName(),
                                conflictingAddon.getPlugin().getName()));

        this.originalAddon = origin;
        this.conflictingAddon = conflictingAddon;
    }
}
