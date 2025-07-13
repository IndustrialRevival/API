package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when a player performs a left-click action.
 * This event wraps the original Bukkit {@link PlayerInteractEvent} and provides
 * a simplified interface for handling left-click interactions.
 *
 * @author balugaq
 */
@Getter
public class PlayerLeftClickEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    /**
     * Constructs a new PlayerLeftClickEvent.
     *
     * @param originalEvent The original Bukkit event that was fired.
     */
    public PlayerLeftClickEvent(PlayerInteractEvent originalEvent) {
        super(originalEvent.getPlayer());
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
