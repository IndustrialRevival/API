package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when an IndustrialRevival block is broken.
 * This event wraps the original Bukkit {@link BlockBreakEvent} and provides additional context
 * about the IndustrialRevival item associated with the broken block.
 *
 * @author balugaq
 */
@Getter
public class IRBlockBreakEvent extends BlockEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    
    /**
     * The original Bukkit event that was fired.
     */
    private final BlockBreakEvent originalEvent;
    
    /**
     * The IndustrialRevival item associated with the broken block.
     */
    private final IndustrialRevivalItem iritem;

    /**
     * Constructs a new IRBlockBreakEvent.
     *
     * @param originalEvent The original Bukkit event that was fired.
     * @param iritem        The IndustrialRevival item associated with the broken block.
     */
    public IRBlockBreakEvent(BlockBreakEvent originalEvent, IndustrialRevivalItem iritem) {
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
