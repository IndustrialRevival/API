package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event triggered when an Enderman moves a block that is associated with an IndustrialRevival item.
 * This event wraps the original {@link EntityChangeBlockEvent} and provides additional context specific to IndustrialRevival.
 *
 * @author balugaq
 */
@Getter
public class EndermanMoveIRBlockEvent extends EntityEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final EntityChangeBlockEvent originalEvent;
    private final Entity enderman;
    private final IndustrialRevivalItem iritem;

    /**
     * Constructs a new EndermanMoveIRBlockEvent.
     *
     * @param originalEvent The original event that was fired by Bukkit when the Enderman moved a block.
     * @param iritem        The IndustrialRevival item associated with the block being moved.
     * @throws IllegalArgumentException if the entity in the original event is not an Enderman.
     */
    public EndermanMoveIRBlockEvent(EntityChangeBlockEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity());
        if (originalEvent.getEntity().getType() == EntityType.ENDERMAN) {
            this.originalEvent = originalEvent;
            this.enderman = originalEvent.getEntity();
            this.iritem = iritem;
        } else {
            throw new IllegalArgumentException("Entity is not an enderman");
        }
    }

    /**
     * Gets the list of handlers for this event.
     *
     * @return the handler list.
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Gets the list of handlers for this event.
     *
     * @return the handler list.
     */
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}