package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when an entity explosion affects a custom IndustrialRevival block.
 * This event wraps the original Bukkit {@link EntityExplodeEvent} and provides additional context
 * regarding the IndustrialRevival item involved in the explosion.
 *
 * @author balugaq
 */
@Getter
public class EntityExplodeIRBlockEvent extends EntityEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final EntityExplodeEvent originalEvent;
    private final Location iritemLocation;
    private final IndustrialRevivalItem iritem;

    /**
     * Constructs a new EntityExplodeIRBlockEvent.
     *
     * @param originalEvent The original Bukkit event that was fired.
     * @param iritemLocation The location of the IndustrialRevival block involved in the explosion.
     * @param iritem         The IndustrialRevival item associated with the exploded block.
     */
    public EntityExplodeIRBlockEvent(EntityExplodeEvent originalEvent, Location iritemLocation, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity());
        this.originalEvent = originalEvent;
        this.iritemLocation = iritemLocation;
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