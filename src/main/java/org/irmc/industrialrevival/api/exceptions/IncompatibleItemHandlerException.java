package org.irmc.industrialrevival.api.exceptions;

import org.bukkit.NamespacedKey;

/**
 * Exception thrown when an item has incompatible handlers.
 * <p>
 * This exception is raised when an item is configured with handlers that are
 * not compatible with each other or with the item's type. The item will not
 * be registered to prevent runtime errors and unexpected behavior.
 * </p>
 *
 * @author lijinhong11
 */
public class IncompatibleItemHandlerException extends RuntimeException {
    /**
     * Constructs a new IncompatibleItemHandlerException with details about the incompatibility.
     *
     * @param message the reason for the incompatibility
     * @param id the namespaced key of the item that has incompatible handlers
     */
    public IncompatibleItemHandlerException(String message, NamespacedKey id) {
        super(
                """
                        Incompatible item handler found in item with id: %s
                        Reason: %s
                        It will not be registered.
                        """
                        .formatted(id.asString(), message));
    }
}
