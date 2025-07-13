package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when a player performs a right-click action.
 * This event wraps the original Bukkit {@link PlayerInteractEvent} and provides
 * a simplified interface for handling right-click interactions.
 *
 * @author balugaq
 */
@Getter
public class PlayerRightClickEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    
    /**
     * The original Bukkit event that was fired.
     */
    private final PlayerInteractEvent originalEvent;

    /**
     * Constructs a new PlayerRightClickEvent.
     *
     * @param originalEvent The original Bukkit event that was fired.
     */
    public PlayerRightClickEvent(PlayerInteractEvent originalEvent) {
        super(originalEvent.getPlayer());
        this.originalEvent = originalEvent;
    }

    /**
     * Gets the list of handlers for this event.
     *
     * @return The handler list.
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Gets the list of handlers for this specific instance of the event.
     *
     * @return The handler list for this event.
     */
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
