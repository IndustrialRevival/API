package org.irmc.industrialrevival.api.events.ir;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event indicating that the industrial revival system has been fully initialized.
 * This event is fired when all initialization processes are completed.
 *
 * @author balugaq
 */
public class IndustrialRevivalFinalizedEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    public IndustrialRevivalFinalizedEvent() {
        super(true);
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
