package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when a player damages an entity using an IndustrialRevival item.
 * This event wraps the original Bukkit {@link EntityDamageByEntityEvent} and provides additional context
 * about the IndustrialRevival item used in the attack.
 *
 * @author balugaq
 */
@Getter
public class IRItemDamageEntityEvent extends EntityEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    
    /**
     * The original Bukkit event that was fired.
     */
    private final EntityDamageByEntityEvent originalEvent;
    
    /**
     * The player who performed the attack.
     */
    private final Player player;
    
    /**
     * The entity that was damaged.
     */
    private final Entity hit;
    
    /**
     * The IndustrialRevival item used in the attack.
     */
    private final IndustrialRevivalItem iritem;

    /**
     * Constructs a new IRItemDamageEntityEvent.
     *
     * @param originalEvent The original Bukkit event that was fired.
     * @param iritem        The IndustrialRevival item used in the attack.
     * @throws IllegalArgumentException if the damager in the original event is not a player.
     */
    public IRItemDamageEntityEvent(EntityDamageByEntityEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
        if (originalEvent.getDamager() instanceof Player) {
            this.player = (Player) originalEvent.getDamager();
        } else {
            throw new IllegalArgumentException("Damager is not a player");
        }
        this.hit = originalEvent.getEntity();
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
