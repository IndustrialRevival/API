package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerEvent;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when a player opens a custom MachineMenu.
 * This event wraps the original Bukkit {@link InventoryOpenEvent} and provides additional context
 * about the IndustrialRevival menu being opened.
 *
 * @author balugaq
 */
@Getter
public class MenuOpenEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    
    /**
     * The original Bukkit inventory open event that was fired.
     */
    private final InventoryOpenEvent inventoryOpenEvent;
    
    /**
     * The IndustrialRevival menu being opened.
     */
    private final MachineMenu openedMenu;

    /**
     * Constructs a new MenuOpenEvent.
     *
     * @param originalEvent The original Bukkit inventory open event that was fired.
     * @param openedMenu    The IndustrialRevival menu being opened.
     */
    public MenuOpenEvent(InventoryOpenEvent originalEvent, @NotNull MachineMenu openedMenu) {
        super((Player) originalEvent.getPlayer());
        this.inventoryOpenEvent = originalEvent;
        this.openedMenu = openedMenu;
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
