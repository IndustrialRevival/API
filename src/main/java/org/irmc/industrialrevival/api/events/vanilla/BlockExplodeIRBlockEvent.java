package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a custom event that is fired when an IndustrialRevival block explodes.
 * This event wraps the original {@link BlockExplodeEvent} and adds additional context
 * specific to IndustrialRevival items and their locations.
 */
@Getter
public class BlockExplodeIRBlockEvent extends BlockEvent implements RelatedIRItem {

    private static final HandlerList handlers = new HandlerList();
    /**
     * -- GETTER --
     *  Gets the original BlockExplodeEvent that triggered this custom event.
     *
     * @return the original event
     */
    private final BlockExplodeEvent originalEvent;
    /**
     * -- GETTER --
     *  Gets the location of the IndustrialRevival item involved in the explosion.
     *
     * @return the IR item's location
     */
    private final Location iritemLocation;
    /**
     * -- GETTER --
     *  Gets the IndustrialRevival item involved in the explosion.
     *
     * @return the IR item
     */
    private final IndustrialRevivalItem iritem;

    /**
     * Returns the list of handlers for this event type.
     *
     * @return the handler list
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Constructs a new BlockExplodeIRBlockEvent.
     *
     * @param originalEvent   the original BlockExplodeEvent from Bukkit
     * @param iritemLocation  the location of the IndustrialRevival item involved
     * @param iritem          the IndustrialRevival item involved in the explosion
     */
    public BlockExplodeIRBlockEvent(BlockExplodeEvent originalEvent, Location iritemLocation, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock());
        this.originalEvent = originalEvent;
        this.iritemLocation = iritemLocation;
        this.iritem = iritem;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}