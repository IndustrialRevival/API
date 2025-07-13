package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when a player interacts with an IndustrialRevival item.
 * This event wraps the original Bukkit {@link PlayerInteractEvent} and provides additional context
 * about the IndustrialRevival item being interacted with.
 *
 * @author balugaq
 */
@Getter
public class IRItemInteractEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    
    /**
     * The IndustrialRevival item being interacted with.
     */
    private final IndustrialRevivalItem iritem;
    
    /**
     * The original Bukkit event that was fired.
     */
    private final PlayerInteractEvent originalEvent;

    /**
     * Constructs a new IRItemInteractEvent.
     *
     * @param originalEvent The original Bukkit event that was fired.
     * @param iritem        The IndustrialRevival item being interacted with.
     */
    public IRItemInteractEvent(PlayerInteractEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getPlayer());
        this.iritem = iritem;
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
