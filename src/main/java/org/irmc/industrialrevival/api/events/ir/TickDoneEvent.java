package org.irmc.industrialrevival.api.events.ir;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when a tick is done.
 * This event can be used to perform actions after a tick has been processed.
 */
@Getter
public class TickDoneEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public TickDoneEvent() {
        super(true);
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
