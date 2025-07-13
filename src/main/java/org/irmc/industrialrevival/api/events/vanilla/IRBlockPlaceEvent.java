package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when an IndustrialRevival block is placed.
 * This event wraps the original Bukkit {@link BlockPlaceEvent} and provides additional context
 * about the IndustrialRevival item being placed. This event can be cancelled to prevent
 * the block from being placed.
 *
 * @author balugaq
 */
@Getter
public class IRBlockPlaceEvent extends BlockEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    
    /**
     * The original Bukkit event that was fired.
     */
    private final BlockPlaceEvent originalEvent;
    
    /**
     * The IndustrialRevival item being placed.
     */
    private final IndustrialRevivalItem iritem;
    
    /**
     * Whether this event is cancelled.
     */
    @Setter
    private boolean cancelled;

    /**
     * Constructs a new IRBlockPlaceEvent.
     *
     * @param originalEvent The original Bukkit event that was fired.
     * @param iritem        The IndustrialRevival item being placed.
     */
    public IRBlockPlaceEvent(BlockPlaceEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
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
