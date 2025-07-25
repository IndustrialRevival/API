package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;

@Getter
public class MenuCloseEvent extends InventoryEvent {
    private static final HandlerList handlers = new HandlerList();
    private final InventoryCloseEvent originalEvent;
    private final MachineMenu menu;

    public MenuCloseEvent(@NotNull InventoryCloseEvent originalEvent, @NotNull MachineMenu menu) {
        super(originalEvent.getView());
        this.originalEvent = originalEvent;
        this.menu = menu;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return (Player) originalEvent.getPlayer();
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
