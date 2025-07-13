package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when a player kills an entity using an IndustrialRevival item.
 * This event wraps the original Bukkit {@link EntityDeathEvent} and provides additional context
 * about the IndustrialRevival item used to kill the entity.
 *
 * @author balugaq
 */
@Getter
public class IRItemKillEntityEvent extends EntityEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    
    /**
     * The IndustrialRevival item used to kill the entity.
     */
    private final IndustrialRevivalItem iritem;
    
    /**
     * The original Bukkit event that was fired.
     */
    private final EntityDeathEvent originalEvent;
    
    /**
     * The player who killed the entity.
     */
    private final Player killer;

    /**
     * Constructs a new IRItemKillEntityEvent.
     *
     * @param originalEvent The original Bukkit event that was fired.
     * @param iritem        The IndustrialRevival item used to kill the entity.
     */
    public IRItemKillEntityEvent(EntityDeathEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity());
        this.iritem = iritem;
        this.originalEvent = originalEvent;
        this.killer = originalEvent.getEntity().getKiller();
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
