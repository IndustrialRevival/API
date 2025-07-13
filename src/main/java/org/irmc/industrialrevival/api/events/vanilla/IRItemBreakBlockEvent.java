package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when a player breaks a block using an IndustrialRevival item.
 * This event wraps the original Bukkit {@link BlockBreakEvent} and provides additional context
 * about the IndustrialRevival item used to break the block.
 *
 * @author balugaq
 */
@Getter
public class IRItemBreakBlockEvent extends BlockEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    
    /**
     * The IndustrialRevival item used to break the block.
     */
    private final IndustrialRevivalItem iritem;
    
    /**
     * The original Bukkit event that was fired.
     */
    private final BlockBreakEvent originalEvent;

    /**
     * Constructs a new IRItemBreakBlockEvent.
     *
     * @param originalEvent The original Bukkit event that was fired.
     * @param iritem        The IndustrialRevival item used to break the block.
     */
    public IRItemBreakBlockEvent(BlockBreakEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock());
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
